package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.DriverBean;
import com.example.huoshangkou.jubowan.inter.OnDeleteCallBack;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：DriverAddAdapter
 * 描述：
 * 创建时间：2018-04-18  08:28
 */

public class DriverAddAdapter extends BaseAbstractAdapter<DriverBean> {
    public DriverAddAdapter(Context context, List<DriverBean> listData, int resId) {
        super(context, listData, resId);
    }

    OnDeleteCallBack deleteCallBack;

    @Override
    public void convert(ViewHolder holder, final DriverBean bean, final int position) {
        EditText etDriverName = holder.getView(R.id.et_driver_name);
        EditText etCarNum = holder.getView(R.id.et_car_num);
        EditText etPhone = holder.getView(R.id.et_phone);
        TextView tvDelete = holder.getView(R.id.tv_delete);
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCallBack.onDelete("", position);
            }
        });

        etDriverName.clearFocus();
        etCarNum.clearFocus();
        etPhone.clearFocus();
        //先清除之前的文本改变监听
        if (etDriverName.getTag() instanceof TextWatcher) {
            etDriverName.removeTextChangedListener((TextWatcher) (etDriverName.getTag()));
        }
        if (etCarNum.getTag() instanceof TextWatcher) {
            etCarNum.removeTextChangedListener((TextWatcher) (etCarNum.getTag()));
        }
        if (etPhone.getTag() instanceof TextWatcher) {
            etPhone.removeTextChangedListener((TextWatcher) (etPhone.getTag()));
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
                    bean.setName(null);
                } else {
                    bean.setName(StringUtils.getNoSapceStr(String.valueOf(editable)));
                }
            }
        };


        final TextWatcher oneAgeWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    bean.setCarNum(null);
                } else {
                    bean.setCarNum(StringUtils.getNoSapceStr(String.valueOf(editable)));
                }
            }
        };
        final TextWatcher oneSexWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    bean.setPhone(null);
                } else {
                    bean.setPhone(StringUtils.getNoSapceStr(String.valueOf(editable)));
                }
            }
        };

        //吧监听设置到不同的EditText上
        etDriverName.addTextChangedListener(oneNameWatcher);
        etDriverName.setTag(oneNameWatcher);

        etCarNum.addTextChangedListener(oneAgeWatcher);
        etCarNum.setTag(oneAgeWatcher);

        etPhone.addTextChangedListener(oneSexWatcher);
        etPhone.setTag(oneSexWatcher);

        etDriverName.setText(TextUtils.isEmpty(bean.getName()) ? "" : bean.getName());
        etCarNum.setText(TextUtils.isEmpty(bean.getCarNum()) ? "" : bean.getCarNum());
        etPhone.setText(TextUtils.isEmpty(bean.getPhone()) ? "" : bean.getPhone());

    }

    public void setDeleteCallBack(OnDeleteCallBack deleteCallBack) {
        this.deleteCallBack = deleteCallBack;
    }
}
