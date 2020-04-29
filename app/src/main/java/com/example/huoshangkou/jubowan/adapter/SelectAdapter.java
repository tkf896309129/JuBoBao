package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.SelectManBean;
import com.example.huoshangkou.jubowan.chat.helper.ChatHelper;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.PhoneUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.widget.ContactItemInterface;
import com.example.huoshangkou.jubowan.widget.ContactListAdapter;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.checkapply
 * 类名：SelectAdapter
 * 描述：
 * 创建时间：2016-12-08  14:55
 */
public class SelectAdapter extends ContactListAdapter {
    private Context context;
    private boolean mIsAddGroup = false;

    public SelectAdapter(Context _context, int _resource, List<SelectManBean> _items, boolean isAddGroup) {
        super(_context, _resource, _items);
        context = _context;
        mIsAddGroup = isAddGroup;
    }

    @Override
    public void populateDataForRow(View parentView, final ContactItemInterface item, int position) {
        super.populateDataForRow(parentView, item, position);

        TextView textView = (TextView) parentView.findViewById(R.id.cityName);
        TextView tvPhone = (TextView) parentView.findViewById(R.id.tv_phone);
        ImageView imgHead = (ImageView) parentView.findViewById(R.id.iv_head);
        ImageView imgChat = (ImageView) parentView.findViewById(R.id.iv_chat_message);
        ImageView imgChatCheck = (ImageView) parentView.findViewById(R.id.iv_check_group);
        TextView tvGroupName = (TextView) parentView.findViewById(R.id.tv_group_name);

        //休息
        TextView tvRest = (TextView) parentView.findViewById(R.id.tv_rest);
        //飞行中
        TextView tvFly = (TextView) parentView.findViewById(R.id.tv_fly);
        //请假
        TextView tvHoliday = (TextView) parentView.findViewById(R.id.tv_holiday);
        //会议中
        TextView tvMeting = (TextView) parentView.findViewById(R.id.tv_meting);
        //出差
        TextView tvWork = (TextView) parentView.findViewById(R.id.tv_work);
        TextView tvOutWork = (TextView) parentView.findViewById(R.id.tv_out_work);
        //下班
        TextView tvAfterWoke = (TextView) parentView.findViewById(R.id.tv_after_woke);
        //旷工
        TextView tvMissWoke = (TextView) parentView.findViewById(R.id.tv_miss_woke);
        //值班
        TextView tvStillWoke = (TextView) parentView.findViewById(R.id.tv_still_woke);
        ImageView imgPhone = (ImageView) parentView.findViewById(R.id.iv_phone);

        //群聊相关
        if (mIsAddGroup) {
            imgChatCheck.setVisibility(View.VISIBLE);
            imgPhone.setVisibility(View.GONE);
            imgChat.setVisibility(View.GONE);
        } else {
            imgChatCheck.setVisibility(View.GONE);
            imgPhone.setVisibility(View.VISIBLE);
            imgChat.setVisibility(View.VISIBLE);
        }
        if (item.isCheck()) {
            imgChatCheck.setImageResource(R.mipmap.gouxuany_icon_on);
        } else {
            imgChatCheck.setImageResource(R.mipmap.gouxuany_icon_off);
        }


        imgPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CopyIosDialogUtils.getInstance().getIosDialog(context, "是否拨打：" + item.getDisplayInfo() + "\n" + item.getPhone(), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        PhoneUtils.getInstance().callDialog(item.getPhone(), context);
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });

        //0：无模式  1：飞行中  2：会议中
        switch (item.getNowPattern()) {
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
        //    1：上班  2：休息  3：请假 4：出差
        switch (item.getNowState()) {
            case 1:
                tvWork.setVisibility(View.VISIBLE);
                tvRest.setVisibility(View.GONE);
                tvHoliday.setVisibility(View.GONE);
                tvOutWork.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.GONE);
                tvMissWoke.setVisibility(View.GONE);
                tvStillWoke.setVisibility(View.GONE);
                break;
            case 2:
                tvRest.setVisibility(View.VISIBLE);
                tvWork.setVisibility(View.GONE);
                tvHoliday.setVisibility(View.GONE);
                tvOutWork.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.GONE);
                tvMissWoke.setVisibility(View.GONE);
                tvStillWoke.setVisibility(View.GONE);
                break;
            case 3:
                tvHoliday.setVisibility(View.VISIBLE);
                tvWork.setVisibility(View.GONE);
                tvRest.setVisibility(View.GONE);
                tvOutWork.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.GONE);
                tvMissWoke.setVisibility(View.GONE);
                tvStillWoke.setVisibility(View.GONE);
                break;
            case 4:
                tvOutWork.setVisibility(View.VISIBLE);
                tvWork.setVisibility(View.GONE);
                tvRest.setVisibility(View.GONE);
                tvHoliday.setVisibility(View.GONE);
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

        textView.setText(item.getDisplayInfo());
        if (StringUtils.isNoEmpty(item.getHeadImagePic())) {
            GlideUtils.getInstance().displayCricleImage(getContext(), item.getHeadImagePic(), imgHead);
        } else {
            GlideUtils.getInstance().displayCricleImage(getContext(), R.mipmap.touxiang_icon, imgHead);
        }
        tvGroupName.setText(item.getGroupName());
        tvPhone.setText(item.getGroupName());

        imgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatHelper.getInstance().toPersonChat(context, item.getId() + "", item.getDisplayInfo());
            }
        });

    }
}
