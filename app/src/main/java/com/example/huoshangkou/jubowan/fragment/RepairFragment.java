package com.example.huoshangkou.jubowan.fragment;

import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.LoginActivity;
import com.example.huoshangkou.jubowan.activity.MyRepairOrderActivity;
import com.example.huoshangkou.jubowan.activity.RepairRepliyActivity;
import com.example.huoshangkou.jubowan.adapter.RepairTypeAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.RepairProblemBean;
import com.example.huoshangkou.jubowan.bean.RepairTypeListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnRepairClick;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LoginUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：RepairFragment
 * 描述：维修维保界
 * 创建时间：2017-01-04  10:17
 */

public class RepairFragment extends BaseFragment {
    //我靠
    @Bind(R.id.tv_title)
    TextView tvTitle;
    //类别
    @Bind(R.id.grid_view_repair)
    ScrollGridView gridViewRepair;

    //维修维保类别
    RepairTypeAdapter typeAdapter;
    //数据
    List<RepairTypeListBean> repairTypeBeanList;

    public static RepairFragment newInstance() {
        RepairFragment fragment = new RepairFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_repair;
    }

    @Override
    public void initData() {
        //设置标题  为什么重视
        tvTitle.setText(R.string.repair);

        //集合初始化
        repairTypeBeanList = new ArrayList<>();
        //设置数据
        setRepairData();
        typeAdapter = new RepairTypeAdapter(getActivity(), repairTypeBeanList, R.layout.item_repair_type);
        gridViewRepair.setAdapter(typeAdapter);
        //绑定适配器
        typeAdapter.setRepairClick(new OnRepairClick() {
            //维修申报
            @Override
            public void onTopClick(int position) {
                if (!LoginUtils.getInstance().isLogin(getActivity())) {
                    IntentUtils.getInstance().toActivity(getActivity(), LoginActivity.class);
                    return;
                }

                IntentUtils.getInstance().toActivity(getActivity(), RepairRepliyActivity.class, repairTypeBeanList.get(position));
            }

            //查看维修订单
            @Override
            public void onBottomClick(int position) {
                if (!LoginUtils.getInstance().isLogin(getActivity())) {
                    IntentUtils.getInstance().toActivity(getActivity(), LoginActivity.class);
                    return;
                }
                IntentUtils.getInstance().toActivity(getActivity(), MyRepairOrderActivity.class, repairTypeBeanList.get(position).getID() + "");
            }
        });
    }

    //设置数据集合
    private void setRepairData() {
        repairTypeBeanList.clear();
        OkhttpUtil.getInstance().setUnCacheData(getActivity(), getString(R.string.loading_message),
                UrlConstant.getInstance().URL + PostConstant.getInstance().GET_REPAIR_CLASS
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        RepairProblemBean problemBean = JSON.parseObject(json, RepairProblemBean.class);
                        repairTypeBeanList.addAll(problemBean.getReList());
                        typeAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFail() {
                        CopyIosDialogUtils.getInstance().getErrorDialog(getActivity(), "加载失败是否重新加载", new CopyIosDialogUtils.ErrDialogCallBack() {
                            @Override
                            public void confirm() {
                                setRepairData();
                            }
                        });
                    }
                });
    }


    @Override
    public void onResume() {
        super.onResume();

        String str = (String) SharedPreferencesUtils.getInstance().get(getActivity(), SharedKeyConstant.getInstance().REPAIR, "");
        if (!StringUtils.isNoEmpty(str)) {
            return;
        }

        setRepairData();
        SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().REPAIR, "");

    }
}
