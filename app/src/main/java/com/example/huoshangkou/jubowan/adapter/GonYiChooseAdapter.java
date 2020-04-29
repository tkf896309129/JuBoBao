package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.GonYiListBean;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：GonYiChooseAdapter
 * 描述：
 * 创建时间：2019-04-17  11:25
 */

public class GonYiChooseAdapter extends BaseAbstractAdapter<GonYiListBean> {

    private OnPositionCallBack positionCallBack;

    public GonYiChooseAdapter(Context context, List<GonYiListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, final GonYiListBean bean, final int position) {
        ImageView imgCheck = holder.getView(R.id.iv_check);
        TextView tvName = holder.getView(R.id.tv_name);
        EditText etContent = holder.getView(R.id.et_context);

        etContent.clearFocus();
        //先清除之前的文本改变监听
        if (etContent.getTag() instanceof TextWatcher) {
            etContent.removeTextChangedListener((TextWatcher) (etContent.getTag()));
        }

        //文本改变监听
        final TextWatcher oneNameWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    bean.setContent(null);
                } else {
                    bean.setContent(StringUtils.getNoSapceStr(String.valueOf(editable)));
                }
            }
        };

        //吧监听设置到不同的EditText上
        etContent.addTextChangedListener(oneNameWatcher);
        etContent.setTag(oneNameWatcher);
        etContent.setText(TextUtils.isEmpty(bean.getContent()) ? "" : bean.getContent());

        tvName.setText(bean.getTitle());
        if (bean.isCheck()) {
            imgCheck.setImageResource(R.mipmap.icon_gouxuan);
        } else {
            imgCheck.setImageResource(R.mipmap.gouxuany_icon_off);
        }
        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positionCallBack.onPositionClick(position);
            }
        });
        imgCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positionCallBack.onPositionClick(position);
            }
        });
    }

    public void setPositionCallBack(OnPositionCallBack positionCallBack) {
        this.positionCallBack = positionCallBack;
    }
}
