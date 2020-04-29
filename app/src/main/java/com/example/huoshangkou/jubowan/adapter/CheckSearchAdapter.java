package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.chat.helper.ChatHelper;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.widget.ContactItemInterface;

import java.util.List;

import static android.R.id.list;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.checkapply
 * 类名：CheckSearchAdapter
 * 描述：
 * 创建时间：2016-12-09  16:08
 */
public class CheckSearchAdapter extends BaseAbstractAdapter<ContactItemInterface> {
    private String isAllMember;

    public CheckSearchAdapter(Context context, List<ContactItemInterface> listData,String isAllMember, int resId) {
        super(context, listData, resId);
        this.isAllMember = isAllMember;
    }


    @Override
    public void convert(ViewHolder holder, final ContactItemInterface bean, int position) {

        TextView tvSearch = holder.getView(R.id.tv_search);
        TextView tvGroupName = holder.getView(R.id.tv_group_name);
        TextView tvPhone = holder.getView(R.id.tv_phone);
        ImageView imgHead = holder.getView(R.id.iv_head);
        ImageView imgChat = holder.getView(R.id.iv_chat_message);

        //休息
        TextView tvRest = holder.getView(R.id.tv_rest);
        //飞行中
        TextView tvFly = holder.getView(R.id.tv_fly);
        //请假
        TextView tvHoliday = holder.getView(R.id.tv_holiday);
        //会议中
        TextView tvMeting = holder.getView(R.id.tv_meting);
        //出差
        TextView tvWork = holder.getView(R.id.tv_work);
        TextView tvZhiBan = holder.getView(R.id.tv_zhi_ban);
        TextView tvOutWork = holder.getView(R.id.tv_out_work);
        //下班
        TextView tvAfterWoke = holder.getView(R.id.tv_after_woke);
        //旷工
        TextView tvMissWoke = holder.getView(R.id.tv_miss_woke);
        //值班
        TextView tvStillWoke = holder.getView(R.id.tv_still_woke);

        ImageView imgPhone = holder.getView(R.id.iv_phone);
        tvSearch.setText(bean.getDisplayInfo());
        tvPhone.setText(bean.getGroupName());
        tvGroupName.setText("");
        GlideUtils.getInstance().displayCricleImage(context, bean.getHeadImagePic(), imgHead);
        //通讯录电话
        if (StringUtils.isNoEmpty(isAllMember) && isAllMember.equals("linkMan")) {
            imgPhone.setVisibility(View.VISIBLE);
        }
        imgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatHelper.getInstance().toPersonChat(context, bean.getId() + "", bean.getDisplayInfo());
            }
        });
        //0：无模式  1：飞行中  2：会议中
        switch (bean.getNowPattern()) {
            case 0:
                tvFly.setVisibility(View.GONE);
                tvMeting.setVisibility(View.GONE);
                break;
            case 1:
                tvFly.setVisibility(View.VISIBLE);
                tvMeting.setVisibility(View.GONE);
                break;
            case 2:
                tvFly.setVisibility(View.GONE);
                tvMeting.setVisibility(View.VISIBLE);
                break;
        }
        //    1：上班  2：休息  3：请假 4：出差 7:下班 8：旷工 9值班
        switch (bean.getNowState()) {
            case 1:
                tvWork.setVisibility(View.VISIBLE);
                tvRest.setVisibility(View.GONE);
                tvHoliday.setVisibility(View.GONE);
                tvOutWork.setVisibility(View.GONE);
                tvZhiBan.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.GONE);
                tvMissWoke.setVisibility(View.GONE);
                tvStillWoke.setVisibility(View.GONE);
                break;
            case 2:
                tvRest.setVisibility(View.VISIBLE);
                tvWork.setVisibility(View.GONE);
                tvHoliday.setVisibility(View.GONE);
                tvOutWork.setVisibility(View.GONE);
                tvZhiBan.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.GONE);
                tvMissWoke.setVisibility(View.GONE);
                tvStillWoke.setVisibility(View.GONE);
                break;
            case 3:
                tvHoliday.setVisibility(View.VISIBLE);
                tvWork.setVisibility(View.GONE);
                tvRest.setVisibility(View.GONE);
                tvOutWork.setVisibility(View.GONE);
                tvZhiBan.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.GONE);
                tvMissWoke.setVisibility(View.GONE);
                tvStillWoke.setVisibility(View.GONE);
                break;
            case 4:
                tvOutWork.setVisibility(View.VISIBLE);
                tvWork.setVisibility(View.GONE);
                tvRest.setVisibility(View.GONE);
                tvHoliday.setVisibility(View.GONE);
                tvZhiBan.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.GONE);
                tvMissWoke.setVisibility(View.GONE);
                tvStillWoke.setVisibility(View.GONE);
                break;
            case 7:
                tvOutWork.setVisibility(View.GONE);
                tvWork.setVisibility(View.GONE);
                tvRest.setVisibility(View.GONE);
                tvHoliday.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.VISIBLE);
                tvMissWoke.setVisibility(View.GONE);
                tvStillWoke.setVisibility(View.GONE);
                break;
            case 8:
                tvOutWork.setVisibility(View.GONE);
                tvWork.setVisibility(View.GONE);
                tvRest.setVisibility(View.GONE);
                tvHoliday.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.GONE);
                tvMissWoke.setVisibility(View.VISIBLE);
                tvStillWoke.setVisibility(View.GONE);
                break;
            case 9:
                tvOutWork.setVisibility(View.GONE);
                tvWork.setVisibility(View.GONE);
                tvRest.setVisibility(View.GONE);
                tvHoliday.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.GONE);
                tvMissWoke.setVisibility(View.GONE);
                tvStillWoke.setVisibility(View.VISIBLE);
                break;
            default:
                tvOutWork.setVisibility(View.GONE);
                tvWork.setVisibility(View.GONE);
                tvRest.setVisibility(View.GONE);
                tvHoliday.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.GONE);
                tvMissWoke.setVisibility(View.GONE);
                tvStillWoke.setVisibility(View.GONE);
                break;
        }
    }
}
