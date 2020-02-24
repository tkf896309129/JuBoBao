package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.MemberListBean;
import com.example.huoshangkou.jubowan.bean.SignGroupBean;
import com.example.huoshangkou.jubowan.inter.OnCommonCallBack;
import com.example.huoshangkou.jubowan.inter.OnMemberClick;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.SignManUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.AnimatedExpandableListView;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：SignManListAdapter
 * 描述：
 * 创建时间：2017-04-24  10:37
 */

public class SignManListAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {


    private List<SignGroupBean> list_group;
    private List<List<MemberListBean>> list_child;
    private Context context;


    OnCommonCallBack callBack;
    OnMemberClick memberClick;




    public SignManListAdapter(List<SignGroupBean> list_group, List<List<MemberListBean>> list_child, Context context) {
        this.list_group = list_group;
        this.list_child = list_child;
        this.context = context;
    }

    @Override
    public View getRealChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View childView = LayoutInflater.from(context).inflate(R.layout.item_sign_child, null);
        ImageView imgPic = (ImageView) childView.findViewById(R.id.iv_head_pic);
        TextView tvName = (TextView) childView.findViewById(R.id.tv_name);
        TextView tvPhone = (TextView) childView.findViewById(R.id.tv_phone);
        RelativeLayout rlChildClick = (RelativeLayout) childView.findViewById(R.id.rl_child_click);
        ImageView imgSelectState = (ImageView) childView.findViewById(R.id.iv_select_state);

        if (list_child.get(groupPosition).get(childPosition).isCheck()) {
            imgSelectState.setVisibility(View.VISIBLE);
        } else {
            imgSelectState.setVisibility(View.GONE);
        }

        rlChildClick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                callBack.onClick(groupPosition, childPosition);
                return false;
            }
        });

        rlChildClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memberClick.onMemberClick(groupPosition, childPosition);
            }
        });


        GlideUtils.getInstance().displayCricleImage(context, list_child.get(groupPosition).get(childPosition).getPic1(), imgPic);
        tvName.setText(list_child.get(groupPosition).get(childPosition).getLinkMan());
        tvPhone.setText("手机：" + list_child.get(groupPosition).get(childPosition).getLoginName());

        return childView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return list_child == null ? 0 : list_child.get(groupPosition).size();
    }

    @Override
    public int getGroupCount() {
        return list_group == null ? 0 : list_group.size();
    }

    @Override
    public Object getGroup(int i) {
        return list_group == null ? null : list_group.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list_child == null ? null : list_child.get(i).get(i1);
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
        View groupView = LayoutInflater.from(context).inflate(R.layout.item_sign_group, null);
        ImageView imgState = (ImageView) groupView.findViewById(R.id.iv_state_pic);
        TextView tvGroupName = (TextView) groupView.findViewById(R.id.tv_group_name);
        TextView tvManNum = (TextView) groupView.findViewById(R.id.tv_man_num);
        if (b) {
            imgState.setImageResource(R.mipmap.sanjiao_open);
        } else {
            imgState.setImageResource(R.mipmap.sanjiao);
        }

        tvGroupName.setText(list_group.get(i).getGroupName());
        tvManNum.setText(list_group.get(i).getManCount());

        return groupView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    public void setCallBack(OnCommonCallBack callBack) {
        this.callBack = callBack;
    }

    public void setMemberClick(OnMemberClick memberClick) {
        this.memberClick = memberClick;
    }
}
