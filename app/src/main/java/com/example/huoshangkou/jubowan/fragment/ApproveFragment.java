package com.example.huoshangkou.jubowan.fragment;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ApplyAdapter;
import com.example.huoshangkou.jubowan.adapter.ApproveAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.ApproveCheckListBean;
import com.example.huoshangkou.jubowan.bean.GetApplyListBean;
import com.example.huoshangkou.jubowan.constant.ApproveConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.fragment.function.ApproveListFunction;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：ApproveFragment
 * 描述：
 * 创建时间：2017-03-17  09:48
 */

public class ApproveFragment extends BaseFragment {
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    SmartRefreshLayout xRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;

    private ApproveAdapter approveAdapter;
    private List<ApproveCheckListBean> listData;
    //审批类型
    private String type = "";
    //申请或者审批
    private String checkType = "";
    //申请
    private ApplyAdapter applyAdapter;
    private List<ApproveCheckListBean> listApply;

    public static ApproveFragment newInstance() {
        ApproveFragment fragment = new ApproveFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_approve;
    }

    @Override
    public void initData() {
        listApply = new ArrayList<>();
        listData = new ArrayList<>();

        Bundle bundle = getArguments();
        type = bundle.getString(IntentUtils.getInstance().TYPE, "");

        //判断是审批 还是 申请
        checkType = bundle.getString(IntentUtils.getInstance().APPROVE_TYPE, "");
        //审批
        if (checkType.equals(ApproveConstant.getInstance().APPROVE)) {
            ApproveListFunction.getInstance().getApproveListData(xRefresh, approveAdapter, getActivity(), listData, lvRefresh, type, llNoData);
            //申请
        } else if (checkType.equals(ApproveConstant.getInstance().APPLY)) {
            ApproveListFunction.getInstance().getApplyListData(xRefresh, getActivity(), type, lvRefresh, applyAdapter, listApply, llNoData);
            //知会
        } else if (checkType.equals(ApproveConstant.getInstance().MINE_ZH)) {
            ApproveListFunction.getInstance().getMineZhApply(xRefresh, getActivity(), type, lvRefresh, applyAdapter, listApply, llNoData);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        String agree = (String) SharedPreferencesUtils.getInstance().get(getActivity(), SharedKeyConstant.getInstance().APPROVE_AGREE, "");
        //判断是否需要刷新
        if (!agree.equals(SharedValueConstant.getInstance().APPROVE_AGREE)) {
            return;
        }
        //审批
        if (checkType.equals(ApproveConstant.getInstance().APPROVE)) {
            ApproveListFunction.getInstance().getApproveListData(xRefresh, approveAdapter, getActivity(), listData, lvRefresh, type, llNoData);
            //申请
        } else if (checkType.equals(ApproveConstant.getInstance().APPLY)) {
            ApproveListFunction.getInstance().getApplyListData(xRefresh, getActivity(), type, lvRefresh, applyAdapter, listApply, llNoData);
        } else if (checkType.equals(ApproveConstant.getInstance().MINE_ZH)) {
            ApproveListFunction.getInstance().getMineZhApply(xRefresh, getActivity(), type, lvRefresh, applyAdapter, listApply, llNoData);
        }
        SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().APPROVE_AGREE, "");
    }
}
