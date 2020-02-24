package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.MemberListBean;
import com.example.huoshangkou.jubowan.inter.ChooseCheckMan;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.PhoneUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.AnimatedExpandableListView;

import org.simple.eventbus.EventBus;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.checkapply
 * 类名：EpCheckGroupAdapter
 * 描述：
 * 创建时间：2016-12-09  13:38
 */
public class EpCheckGroupAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    private List<String> list_group;
    private List<List<MemberListBean>> list_child;
    private Context context;
    //是否是通讯录
    private String isAllMember = "";
    private String titleDialog = "";
    private String pic = "";

    public EpCheckGroupAdapter(List<String> list_group, List<List<MemberListBean>> list_child, Context context) {
        this.list_group = list_group;
        this.list_child = list_child;
        this.context = context;
    }

    public EpCheckGroupAdapter(List<String> list_group, List<List<MemberListBean>> list_child, Context context, String isAllMember) {
        this.list_group = list_group;
        this.list_child = list_child;
        this.context = context;
        this.isAllMember = isAllMember;
    }

    public EpCheckGroupAdapter(List<String> list_group, List<List<MemberListBean>> list_child, Context context, String isAllMember, String title) {
        this.list_group = list_group;
        this.list_child = list_child;
        this.context = context;
        this.isAllMember = isAllMember;
        titleDialog = title;
    }

    @Override
    public int getGroupCount() {
        return list_group == null ? 0 : list_group.size();
    }

//    @Override
//    public int getChildrenCount(int groupPosition) {
//        return list_child == null ? 0 : list_child.get(groupPosition).size();
//    }

    @Override
    public Object getGroup(int groupPosition) {
        return list_group == null ? null : list_group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list_child == null ? null : list_child.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ep_group, parent, false);
        TextView tvGroup = (TextView) view.findViewById(R.id.tv_group);
        TextView tvNums = (TextView) view.findViewById(R.id.tv_man_nums);
        ImageView imgRgiht = (ImageView) view.findViewById(R.id.img_right);
        tvGroup.setText(list_group.get(groupPosition));
        tvNums.setText(list_child.get(groupPosition).size() + "人");

        if (isExpanded) {
            imgRgiht.setBackgroundResource(R.mipmap.gray_down);
        } else {
            imgRgiht.setBackgroundResource(R.mipmap.gray_right);
        }
        return view;
    }

    @Override
    public View getRealChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ep_child, parent, false);
        TextView tvChild = (TextView) view.findViewById(R.id.tv_child);
        TextView tvPhone = (TextView) view.findViewById(R.id.tv_phone);
        ImageView imgHead = (ImageView) view.findViewById(R.id.iv_check_head);
        RelativeLayout rlCheckMan = (RelativeLayout) view.findViewById(R.id.rl_check_man);

        rlCheckMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isNoEmpty(isAllMember)) {
                    CopyIosDialogUtils.getInstance().getIosDialog(context, "是否拨打：" + list_child.get(groupPosition).get(childPosition).getLinkMan() + "\n" + list_child.get(groupPosition).get(childPosition).getLoginName(), new CopyIosDialogUtils.iosDialogClick() {
                        @Override
                        public void confimBtn() {
                            PhoneUtils.getInstance().callDialog(list_child.get(groupPosition).get(childPosition).getLoginName(), context);
                        }

                        @Override
                        public void cancelBtn() {

                        }
                    });
                    return;
                }
                pic = "";
                if (StringUtils.isNoEmpty(list_child.get(groupPosition).get(childPosition).getPic1())) {
                    pic = list_child.get(groupPosition).get(childPosition).getPic1();
                } else {
                    pic = "noPic";
                }
                DialogUtils.getInstance().chooseCheckMan(context, "温馨提示", "是否选择 (" + list_child.get(groupPosition).get(childPosition).getLinkMan() + ") 作为" + titleDialog, new ChooseCheckMan() {
                    @Override
                    public void onCheck() {
                        EventBus.getDefault().post(list_child.get(groupPosition).get(childPosition).getID() + "," + list_child.get(groupPosition).get(childPosition).getLinkMan() + "," + pic, "checkManId");
                    }
                });
            }
        });

        tvChild.setText(list_child.get(groupPosition).get(childPosition).getLinkMan());
        tvPhone.setText("手机号：" + list_child.get(groupPosition).get(childPosition).getLoginName());
        GlideUtils.getInstance().displayCricleImage(context, list_child.get(groupPosition).get(childPosition).getPic1(), imgHead);

        return view;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return list_child == null ? 0 : list_child.get(groupPosition).size();
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
