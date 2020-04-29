package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.AddGroupActivity;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.CheckGroupBean;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：CheckGroupAdapter
 * 描述：
 * 创建时间：2019-02-19  14:08
 */

public class CheckGroupAdapter extends BaseAbstractAdapter<CheckGroupBean.ReObjBean.DepListBean> {
    private OnPositionCallBack positionCallBack;
    private String sign = "";
    //是否是子成员
    private boolean isChild = false;
    private String saleManage = "";
    private boolean isSuper = false;


    public CheckGroupAdapter(Context context, List<CheckGroupBean.ReObjBean.DepListBean> listData, String sign, boolean isChild, boolean isSuper, int resId, String saleManage) {
        super(context, listData, resId);
        this.sign = sign;
        this.isSuper = isSuper;
        this.isChild = isChild;
        this.saleManage = saleManage;
    }

    public CheckGroupAdapter(Context context, List<CheckGroupBean.ReObjBean.DepListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, final CheckGroupBean.ReObjBean.DepListBean bean, final int position) {
        RelativeLayout rlGroup = holder.getView(R.id.rl_group);
        TextView tvGroup = holder.getView(R.id.tv_group);
        TextView tvNums = holder.getView(R.id.tv_man_nums);
        TextView tvChange = holder.getView(R.id.tv_change);
        ImageView imgRgiht = holder.getView(R.id.img_right);
        final SwipeMenuLayout menuLayout = holder.getView(R.id.swip_menu);
        String name = "";
        if (StringUtils.isNoEmpty(bean.getRoleName())) {
            name = bean.getRoleName().trim();
        } else {
            name = bean.getRoleName();
        }
        if (StringUtils.isNoEmpty(sign) && !isChild && !StringUtils.isNoEmpty(saleManage) && isSuper) {
            menuLayout.setSwipeEnable(true);
        } else {
            menuLayout.setSwipeEnable(false);
        }

//        if(StringUtils.isNoEmpty(saleManage)){
//            menuLayout.setSwipeEnable(false);
//        }else {
//            menuLayout.setSwipeEnable(true);
//        }
        tvGroup.setText(name + "  (" + bean.getNumberOfEmployees() + ")");
        tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuLayout.quickClose();
                IntentUtils.getInstance().toActivity(context, AddGroupActivity.class, bean.getParentId() + "", bean.getID() + "");
            }
        });
        rlGroup.setOnClickListener(new View.OnClickListener() {
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
