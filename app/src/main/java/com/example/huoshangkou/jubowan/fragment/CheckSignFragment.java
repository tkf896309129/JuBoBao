package com.example.huoshangkou.jubowan.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ReadyCheckSignAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.CheckSignBean;
import com.example.huoshangkou.jubowan.bean.CheckSignListBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SignConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.CheckSignCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：CheckSignFragment
 * 描述：
 * 创建时间：2017-04-19  08:56
 */

public class CheckSignFragment extends BaseFragment {

    //待审核
    ReadyCheckSignAdapter redySignAdapter;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    XRefreshView xRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;

    private int pageSize = 1;

    private String approveOver = "";

    private String approveUserId = "";

    //是否是已审核
    private boolean isAlerdyCheck = false;

    private List<CheckSignListBean> testList;

    String postStr = "";

    public static CheckSignFragment newInstance() {
        CheckSignFragment fragment = new CheckSignFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_check_sign;
    }

    @Override
    public void initData() {
        //就是补签的咯 只是补签的一种状态正常的签到次序 未审批的签到 同意的话 是三个
        String type = getArguments().getString(IntentUtils.getInstance().TYPE);
        String str = getArguments().getString(IntentUtils.getInstance().STR);
        if (StringUtils.isNoEmpty(str)) {
            postStr = PostConstant.getInstance().SIGN_CHECK_LIST_ADMIN;
        } else {
            postStr = PostConstant.getInstance().SIGN_CHECK_LIST;
        }
        approveUserId = getArguments().getString(IntentUtils.getInstance().APPROVE_TYPE_ID);


        if (type.equals(SignConstant.getInstance().CHECK_SIGN)) {
            isAlerdyCheck = false;
        } else if (type.equals(SignConstant.getInstance().UN_CHECK_SIGN)) {
            isAlerdyCheck = true;
        }

        approveOver = getArguments().getString(IntentUtils.getInstance().VALUE);

        testList = new ArrayList<>();

//        for (int i = 0; i < 10; i++) {
//            CheckSignListBean listBean = new CheckSignListBean();
//            testList.add(listBean);
//        }
        lvRefresh.setDividerHeight(0);

        redySignAdapter = new ReadyCheckSignAdapter(getActivity(), testList, R.layout.item_check_sign);
        lvRefresh.setAdapter(redySignAdapter);

        redySignAdapter.setSignCallBack(new CheckSignCallBack() {
            @Override
            public void onAgree(String id) {
                checkAddSign(approveUserId, id, "1");
            }

            @Override
            public void onDisAgree(String id) {
                checkAddSign(approveUserId, id, "0");
            }
        });

//        getCheckList(approveOver);
        xRefresh.setPullLoadEnable(true);

        xRefresh.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                pageSize = 1;
                testList.clear();
                getCheckList(approveOver);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pageSize++;
                getCheckList(approveOver);
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }


    //获取待审批的列表 + PersonConstant.getInstance().getUserId()
    public void getCheckList(String approver) {
        if (StringUtils.isNoEmpty(approveUserId)) {
        } else {
            approver = "";
        }

        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + postStr
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().PAGE_SIZE + "=" + pageSize + "&"
                + FieldConstant.getInstance().APPROVE_OVER + "=" + approver, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                CheckSignBean signBean = JSON.parseObject(json, CheckSignBean.class);
                int num = signBean.getReList().size();
                List<CheckSignListBean> reList = signBean.getReList();
                for (int i = 0; i < num; i++) {
                    reList.get(i).setNeedCheck(isAlerdyCheck);
                }

                testList.addAll(reList);

                if (testList.size() == 0) {
                    llNoData.setVisibility(View.VISIBLE);
                } else {
                    llNoData.setVisibility(View.GONE);
                }

                redySignAdapter.notifyDataSetChanged();

                xRefresh.stopLoadMore();
                xRefresh.stopRefresh();
            }

            @Override
            public void onFail() {

            }
        });
    }

    //审批补签
    public void checkAddSign(String approveUserId, String trackId, String approveOverState) {
        OkhttpUtil.getInstance().setUnCacheData(getActivity(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().RETROACTIVE_APPROVE
                + FieldConstant.getInstance().APPROVE_USER_ID + "=" + approveUserId + "&"
                + FieldConstant.getInstance().TRACK_ID + "=" + trackId + "&"
                + FieldConstant.getInstance().APPROVE_OVER + "=" + approveOverState, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("审批成功");
                    pageSize = 1;
                    testList.clear();
                    getCheckList(approveOver);
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        pageSize = 1;
        testList.clear();
        getCheckList(approveOver);
    }
}
