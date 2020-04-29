package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.OperateMemberBean;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.view.AnimatedExpandableListView;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：EpOperateMemberAdapter
 * 描述：
 * 创建时间：2019-03-06  09:46
 */

public class EpOperateMemberAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    private List<String> listGroup;
    private List<List<OperateMemberBean.DBean.ReObjBean.OperatorsBean>> listChild;
    private Context context;
    private StringCallBack stringCallBack;

    public EpOperateMemberAdapter(List<String> listGroup, List<List<OperateMemberBean.DBean.ReObjBean.OperatorsBean>> listChild, Context context) {
        this.listGroup = listGroup;
        this.listChild = listChild;
        this.context = context;
    }

    @Override
    public View getRealChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View childView = LayoutInflater.from(context).inflate(R.layout.item_operate_child, null);
        TextView tvName = (TextView) childView.findViewById(R.id.tv_name);
        tvName.setText(listChild.get(groupPosition).get(childPosition).getRealName());
        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringCallBack.onSuccess(listChild.get(groupPosition).get(childPosition).getRealName());
            }
        });

        return childView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return listChild.get(groupPosition).size();
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public Object getGroup(int i) {
        return listGroup.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listChild.get(i).get(i1);
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
        View groupView = LayoutInflater.from(context).inflate(R.layout.item_operate_group, null);
        TextView tvOperate = (TextView) groupView.findViewById(R.id.tv_operate_group);
        tvOperate.setText(listGroup.get(i));
        return groupView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    public void setStringCallBack(StringCallBack stringCallBack) {
        this.stringCallBack = stringCallBack;
    }
}
