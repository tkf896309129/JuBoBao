package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.LoanIouAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.LoanIntroBean;
import com.example.huoshangkou.jubowan.bean.LoanIntroListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
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
 * 类名：LoanIouActivity
 * 描述：借款借据
 * 创建时间：2017-09-11  16:52
 */

public class LoanIouActivity extends BaseActivity {
    LoanIouAdapter iouAdapter;
    List<LoanIntroListBean> list;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    XRefreshView xRefresh;

    //合同号
    private String contractNo = "";
    //订单号
    private String orderId = "";
    private int pageSize = 1;

    private String linkMan = "";
    private String phone = "";

    @Override
    public int initLayout() {
        return R.layout.activity_loan_iou;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        list = new ArrayList<>();
        contractNo = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        orderId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        iouAdapter = new LoanIouAdapter(getContext(), list, R.layout.item_loan_iou);
        lvRefresh.setAdapter(iouAdapter);
        lvRefresh.setDividerHeight(0);

        tvTitle.setText("借款借据");

        lvRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentUtils.getInstance().toActivity(getContext(), LoanIntroDetailActivity.class, list.get(i).getID() + "");
            }
        });

        getLoanIntroList(contractNo);

        xRefresh.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                pageSize = 1;
                list.clear();
                getLoanIntroList(contractNo);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pageSize++;
                getLoanIntroList(contractNo);
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }

    @OnClick({R.id.tv_loan_iou, R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_loan_iou:
                IntentUtils.getInstance().toActivity(getContext(), LoanIntroActivity.class, contractNo,linkMan,phone);
                break;
            case R.id.ll_back:
                this.finish();
                break;
        }
    }


    //借据列表
    public void getLoanIntroList(String contractNo) {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_DUE_BILL_LIST
                + FieldConstant.getInstance().CONTRACT_NO + "=" + contractNo, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LoanIntroBean introBean = JSON.parseObject(json, LoanIntroBean.class);
                list.addAll(introBean.getReList());
                iouAdapter.notifyDataSetChanged();

                linkMan = introBean.getLinkMan();
                phone = introBean.getLoanTel();

                if(xRefresh!=null){
                    xRefresh.stopLoadMore();
                    xRefresh.stopRefresh();
                }

            }

            @Override
            public void onFail() {
                if(xRefresh!=null){
                    xRefresh.stopLoadMore();
                    xRefresh.stopRefresh();
                }
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
            getLoanIntroList(contractNo);

            SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().INIT_LOAN, "");
        }
    }
}
