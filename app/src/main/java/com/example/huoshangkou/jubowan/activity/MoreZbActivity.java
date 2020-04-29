package com.example.huoshangkou.jubowan.activity;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.MoreZbFunction;
import com.example.huoshangkou.jubowan.adapter.MineZbAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.base.HideBaseActivity;
import com.example.huoshangkou.jubowan.bean.TieTypeBean;
import com.example.huoshangkou.jubowan.bean.ZbBean;
import com.example.huoshangkou.jubowan.bean.ZbListBean;
import com.example.huoshangkou.jubowan.bean.ZbMessageBean;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ChooseDialogCallBack;
import com.example.huoshangkou.jubowan.inter.ChooseDialogPositionCallBack;
import com.example.huoshangkou.jubowan.inter.OnTieSelectCallBack;
import com.example.huoshangkou.jubowan.inter.OnZbBeanCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TieSelectWindow;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：MoreZbActivity
 * 描述：
 * 创建时间：2017-04-08  13:59
 */

public class MoreZbActivity extends HideBaseActivity implements TextView.OnEditorActionListener {
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    XRefreshView xRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.et_search_zb)
    EditText etSearchZb;
    @Bind(R.id.tv_select_type)
    TextView tvTypeSelect;
    @Bind(R.id.tv_standard)
    TextView tvStandard;
    MineZbAdapter zbAdapter;
    private List<ZbListBean> list;
    List<TieTypeBean> tieTtypeList;
    ArrayList<String> typeShowList;
    ArrayList<String> standardList;
    //最小规模
    private String large = "";
    //最大规模
    private String small = "";
    //项目类型
    private String projectType = "";

    String searchZb = "";

    private int pageSize = 1;

    @Override
    public int initLayout() {
        return R.layout.activity_more_zb;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvRight.setText("搜索");

        list = new ArrayList<>();
        tieTtypeList = new ArrayList<>();
        typeShowList = new ArrayList<>();
        standardList = new ArrayList<>();

        zbAdapter = new MineZbAdapter(this, list, R.layout.item_mine_zb);
        lvRefresh.setAdapter(zbAdapter);
        lvRefresh.setDividerHeight(0);

        etSearchZb.setOnEditorActionListener(this);

        //获取招标详情数据
//        getSearchZb();
        xRefresh.setAutoRefresh(true);

        lvRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MoreZbFunction.getInstance().toZbDetail(getContext(), list.get(i).getRequestID() + "");
            }
        });

        xRefresh.setPullLoadEnable(true);

        //初始化数据
        initTypeData();
        xRefresh.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                list.clear();
                pageSize = 1;
                getSearchZb();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pageSize++;
                getSearchZb();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }

    @OnClick({R.id.ll_back, R.id.iv_put_zb, R.id.ll_select_tie, R.id.ll_standard, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.iv_put_zb:
                IntentUtils.getInstance().toActivity(getContext(), PostZbActivity.class);
                break;
            case R.id.ll_select_tie:
                PickDialogUtils.getInstance().getChooseDialog(getContext(), "选择项目类型", typeShowList, new ChooseDialogCallBack() {
                    @Override
                    public void onClickSuccess(String choose) {
                        tvTypeSelect.setText(choose + "  ");
                        if (choose.equals("全部")) {
                            projectType = "";
                        } else {
                            projectType = choose;
                        }
                        searchZb = "";
                        pageSize = 1;
                        list.clear();
                        getSearchZb();
                        etSearchZb.setText("");
                    }
                });
                break;
            case R.id.ll_standard:
                PickDialogUtils.getInstance().getChoosePositionDialog(getContext(), "选择项目规模", standardList, new ChooseDialogPositionCallBack() {
                    @Override
                    public void onGetMessagePosition(String message, int position) {
                        tvStandard.setText(message + "  ");

                        switch (position) {
                            case 0:
                                small = "1000";
                                large = "5000";
                                break;
                            case 1:
                                small = "5000";
                                large = "15000";
                                break;
                            case 2:
                                small = "15000";
                                large = "30000";
                                break;
                            case 3:
                                small = "30000";
                                large = "50000";
                                break;
                            case 4:
                                small = "50000";
                                large = "";
                                break;
                            case 5:
                                small = "";
                                large = "";
                                break;
                        }
                        pageSize = 1;
                        list.clear();
                        getSearchZb();
                    }
                });
                break;
            //搜索
            case R.id.tv_right:
                searchZb = etSearchZb.getText().toString().trim();
                if (!StringUtils.isNoEmpty(searchZb)) {
                    ToastUtils.getMineToast("请输入搜索信息");
                    return;
                }
                projectType = "";
                tvTypeSelect.setText("项目类型");
                pageSize = 1;
                list.clear();
                getSearchZb();
                break;
        }
    }

    //添加搜索数据
    public void initTypeData() {
        typeShowList.add("幕墙");
        typeShowList.add("门窗");
        typeShowList.add("其他");
        typeShowList.add("全部");


        standardList.add("1000-5000");
        standardList.add("5000-10000");
        standardList.add("15000-30000");
        standardList.add("30000-50000");
        standardList.add(">50000");
        standardList.add("全部");


        TieTypeBean typeBeanMq = new TieTypeBean();
        typeBeanMq.setType("幕墙项目");

        TieTypeBean typeBeanDoor = new TieTypeBean();
        typeBeanDoor.setType("门窗项目");

        TieTypeBean typeBeanQt = new TieTypeBean();
        typeBeanQt.setType("其他");

        TieTypeBean typeBeanAll = new TieTypeBean();
        typeBeanAll.setType("全部");


        tieTtypeList.add(typeBeanMq);
        tieTtypeList.add(typeBeanDoor);
        tieTtypeList.add(typeBeanQt);
        tieTtypeList.add(typeBeanAll);
    }

    //搜索招标数据
    public void getSearchZb() {
        if (StringUtils.isNoEmpty(projectType) && projectType.equals("全部")) {
            projectType = "";
        }
        MoreZbFunction.getInstance().searchZbList(getContext(), searchZb, projectType, small, large, pageSize, new OnZbBeanCallBack() {
            @Override
            public void onSuccess(ZbBean zbBean) {

                list.addAll(zbBean.getReList());
                zbAdapter.notifyDataSetChanged();
                xRefresh.stopRefresh();
                xRefresh.stopLoadMore();

                if (llNoData != null && list.size() == 0) {
                    llNoData.setVisibility(View.VISIBLE);
                } else {
                    llNoData.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFail() {
                xRefresh.stopRefresh();
                xRefresh.stopLoadMore();
            }
        });
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_DONE) {
            searchZb = etSearchZb.getText().toString().trim();
            if (!StringUtils.isNoEmpty(searchZb)) {
                ToastUtils.getMineToast("请输入搜索信息");
                return true;
            }
            projectType = "";
            tvTypeSelect.setText("项目类型");
            pageSize = 1;
            list.clear();
            getSearchZb();
            return true;
        }
        return false;
    }
}
