package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.BorrowAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.LoanBean;
import com.example.huoshangkou.jubowan.bean.LoanListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LoanAdviceDialogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：BorrowMoneyActivity
 * 描述：
 * 创建时间：2017-08-29  13:36
 */

public class BorrowMoneyActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    XRefreshView xRefreshView;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;
    @Bind(R.id.tv_borrow_apply)
    TextView tvBorrowApply;

    List<LoanListBean> list;
    BorrowAdapter borrowAdapter;

    private int pageSize = 1;
    //0没有融资资格 1有融资资格
    private int isLoanQua = 0;
    private String state = "";

    @Override
    public int initLayout() {
        return R.layout.activity_borrow_money;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("聚玻服务");
        tvRight.setText("说明");
        state = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        tvBorrowApply.setVisibility(View.GONE);

        xRefreshView.setPullLoadEnable(true);
        list = new ArrayList<>();

        borrowAdapter = new BorrowAdapter(getContext(), list, R.layout.item_loan_borrow);
        lvRefresh.setAdapter(borrowAdapter);
        lvRefresh.setDividerHeight(0);

        xRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                pageSize = 1;
                list.clear();
                getLoanList();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pageSize++;
                getLoanList();
            }

            /**
             * @param direction >0: 下拉释放，<0:上拉释放 注：暂时没有使用这个方法
             *
             */
            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });

        getLoanList();
    }


    @OnClick({R.id.tv_borrow_apply, R.id.tv_right, R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_borrow_apply:
                //没有贷款资格
                if (isLoanQua == 0) {
                    CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "您不符合贷款条件，请您认真阅读本页面右上角说明", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                    return;
                }
                LoanAdviceDialogUtils.getInstance().getLoanDialog(getContext(), loanBean);
                break;
            case R.id.tv_right:
                IntentUtils.getInstance().toActivity(getContext(), LoanRemarkActivity.class);
                break;
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

    LoanBean loanBean;

    //获取贷款列表
    public void getLoanList() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().LOAN_LIST
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().PAGE_SIZE + "=" + pageSize, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                loanBean = JSON.parseObject(json, LoanBean.class);
                list.addAll(loanBean.getReList());
                borrowAdapter.notifyDataSetChanged();
                String[] split = loanBean.getAndroid_key().split(",");

                if (list.size() == 0) {
                    llNoData.setVisibility(View.VISIBLE);
                } else {
                    llNoData.setVisibility(View.GONE);
                }
                //是否有贷款资格
                isLoanQua = loanBean.getIsQualification();
//                Util.API_KEY = "16VAhN3pePbW7_2OWJieXnxWp3tddrqY";
//                Util.setApiKey("16VAhN3pePbW7_2OWJieXnxWp3tddrqY");
//                Util.setApiKey("16VAhN3pePbW7_2OWJieXnxWp3tddrqY");
//                Util.API_KEY = split[0];
//                Util.API_SECRET = "AOzMBnApZr_cJaKKbqOvnV2YzW27mF-E";
//                Util.API_SECRET = split[1];
                xRefreshView.stopLoadMore();
                xRefreshView.stopRefresh();

                if (split.length == 2) {
                    SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().FACE_KEY, split[0]);
                    SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().FACE_SECRECT, split[1]);
                }

            }

            @Override
            public void onFail() {
                xRefreshView.stopLoadMore();
                xRefreshView.stopRefresh();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        String isInit = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().INIT_LOAN, "");
        if (StringUtils.isNoEmpty(isInit)) {
            pageSize = 1;
            list.clear();
            getLoanList();

            SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().INIT_LOAN, "");
        }
    }




}
