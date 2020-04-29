package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.SignDayBean;
import com.example.huoshangkou.jubowan.bean.SignMonthBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ChooseDialogPositionCallBack;
import com.example.huoshangkou.jubowan.inter.OnSignDetailClick;
import com.example.huoshangkou.jubowan.inter.SignVistorCallBack;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：SignDetailAdapter
 * 描述：
 * 创建时间：2017-04-05  10:21
 */

public class SignDetailAdapter extends BaseExpandableListAdapter {
    private List<SignMonthBean> group_list;
    private Context mContext;
    private List<List<SignDayBean>> child_list;
    private OnSignDetailClick signDetailCallBack;
    private SignVistorCallBack signVistorCallBack;


    public SignDetailAdapter(List<SignMonthBean> group_list, Context mContext, List<List<SignDayBean>> child_list) {
        this.group_list = group_list;
        this.mContext = mContext;
        this.child_list = child_list;
    }

    @Override
    public int getGroupCount() {
        return group_list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return child_list.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return group_list.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return child_list.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        View groupView = LayoutInflater.from(mContext).inflate(R.layout.item_sign_top, null);
        TextView tvMonth = (TextView) groupView.findViewById(R.id.tv_month);
        String time = group_list.get(i).getCreateTime();
        String[] split = time.split("/");
        if (split.length != 3) {
            tvMonth.setText(group_list.get(i).getCreateTime());
        }
        String times = split[2] + split[1] + "月";
        //字体颜色
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(times);
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(70);
        spannableStringBuilder.setSpan(sizeSpan, 0, 2, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tvMonth.setText(spannableStringBuilder);

        return groupView;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        View childView = LayoutInflater.from(mContext).inflate(R.layout.item_sign_detail, null);
        SignDayBean signDayBean = child_list.get(i).get(i1);
        RelativeLayout rlChid = (RelativeLayout) childView.findViewById(R.id.rl_child_view);
        TextView tvTime = (TextView) childView.findViewById(R.id.tv_time);
        TextView tvSignName = (TextView) childView.findViewById(R.id.tv_sign_name);
        TextView tvWriteRecord = (TextView) childView.findViewById(R.id.tv_write_record);
        TextView tvSignAddress = (TextView) childView.findViewById(R.id.tv_sign_address);
        TextView tvContent = (TextView) childView.findViewById(R.id.tv_content);
        ScrollGridView gridView = (ScrollGridView) childView.findViewById(R.id.grid_sign_view);
        List<String> imgList = new ArrayList<>();
        imgList.addAll(PhotoUtils.getInstance().getListImage(signDayBean.getPic()));
        ImageAddAdapter imageAddAdapter = new ImageAddAdapter(mContext, imgList, PhotoConstant.getInstance().IS_NO_DELETE, "sign");
        gridView.setAdapter(imageAddAdapter);
        tvSignAddress.setText(signDayBean.getAddress());

        if (StringUtils.isNoEmpty(signDayBean.getRemark())) {
            tvContent.setText(signDayBean.getRemark());
        } else {
            tvContent.setText(signDayBean.getVisitContent());
        }
        if (!StringUtils.isNoEmpty(signDayBean.getCompany())) {
            tvSignName.setText(signDayBean.getPoiName());
            tvWriteRecord.setVisibility(View.GONE);
        } else {
            tvWriteRecord.setVisibility(View.VISIBLE);
            //+"("+signDayBean.getLinkMan()+")"
            tvSignName.setText("拜访公司-" + signDayBean.getCompany() + "(" + StringUtils.getNoNullStr(signDayBean.getLinkMan()) + ")");
        }
        String time = signDayBean.getCreateTime();
        tvTime.setText(time);
        rlChid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signDetailCallBack.onChildClick(i, i1);
            }
        });
        tvWriteRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signVistorCallBack.showVistor( child_list.get(i).get(i1));

            }
        });
        return childView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    public void setSignDetailCallBack(OnSignDetailClick signDetailCallBack) {
        this.signDetailCallBack = signDetailCallBack;
    }

    public void setSignVistorCallBack(SignVistorCallBack signVistorCallBack) {
        this.signVistorCallBack = signVistorCallBack;
    }
}
