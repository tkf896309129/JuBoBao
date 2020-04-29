package com.example.huoshangkou.jubowan.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.MoreZbFunction;
import com.example.huoshangkou.jubowan.adapter.ZbNewAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.TieTypeBean;
import com.example.huoshangkou.jubowan.bean.ZbBean;
import com.example.huoshangkou.jubowan.bean.ZbListBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.inter.OnZbBeanCallBack;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LoginUtils;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：ZbNewActivity
 * 描述：
 * 创建时间：2019-04-03  10:19
 */

public class ZbNewActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.tv_select_type)
    TextView tvSelectType;
    @Bind(R.id.tv_standard)
    TextView tvStandard;
    @Bind(R.id.grid_zb_new)
    GridView gridZbNew;
    @Bind(R.id.smart)
    SmartRefreshLayout smart;
    @Bind(R.id.ll_put_zb)
    LinearLayout llPutZb;
    private List<ZbListBean> list = new ArrayList<>();
    private ZbNewAdapter zbNewAdapter;
    private List<String> zbTypeList = new ArrayList<>();
    //项目类型
    private String projectType = "";
    //最小规模
    private String large = "";
    //最大规模
    private String small = "";
    private String searchZb = "";
    private int pageSize = 1;
    private List<String> listType = new ArrayList<>();
    private List<String> listSpec = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_zb_new;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("项目招标");
        zbTypeList.add("幕墙招标");
        zbTypeList.add("系统门窗");
        zbTypeList.add("光伏幕墙");
        zbTypeList.add("门窗定制招标");
        ivRight.setImageResource(R.mipmap.search_icon_2);
        zbNewAdapter = new ZbNewAdapter(this, list, R.layout.item_new_zb);
        gridZbNew.setAdapter(zbNewAdapter);
        projectType = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        if(StringUtils.isNoEmpty(projectType)){
            tvSelectType.setText(projectType + "  ");
        }
        getSearchZb();
        gridZbNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                IntentUtils.getInstance().toActivity(getContext(), ZbPriceActivity.class);
                switch (list.get(i).getProjectType()) {
                    case "幕墙招标":
                        IntentUtils.getInstance().toActivity(getContext(), NewZbDetailActivity.class, IntentUtils.getInstance().MUQ_ZB, list.get(i).getRequestID() + "","check");
                        break;
                    case "系统门窗":
                        IntentUtils.getInstance().toActivity(getContext(), NewZbDetailActivity.class, IntentUtils.getInstance().SYSTEM_ZB, list.get(i).getRequestID() + "","check");
                        break;
                    case "光伏幕墙":
                        IntentUtils.getInstance().toActivity(getContext(), NewZbDetailActivity.class, IntentUtils.getInstance().GF_ZB, list.get(i).getRequestID() + "","check");
                        break;
                    case "门窗定制招标":
                        IntentUtils.getInstance().toActivity(getContext(), NewZbDetailActivity.class, IntentUtils.getInstance().DOOR_WINDOW, list.get(i).getRequestID() + "","check");
                        break;
                }
            }
        });

        initTypeData();
        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageSize++;
                getSearchZb();
            }
        });
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageSize = 1;
                getSearchZb();
            }
        });

        String roleType = PersonConstant.getInstance().getRoleType(this);
        if(roleType.equals("1")){
            llPutZb.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.ll_back, R.id.ll_put_zb, R.id.ll_select_tie, R.id.ll_standard,R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.ll_put_zb:
                if (!LoginUtils.getInstance().isLogin(this)) {
                    IntentUtils.getInstance().toActivity(this, LoginActivity.class);
                    return;
                }
                DialogUtils.getInstance().getBaseDialog(this, zbTypeList, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        switch (str) {
                            case "幕墙招标":
                                IntentUtils.getInstance().toActivity(getContext(), GfZbActivity.class, IntentUtils.getInstance().MUQ_ZB);
                                break;
                            case "系统门窗":
                                IntentUtils.getInstance().toActivity(getContext(), GfZbActivity.class, IntentUtils.getInstance().SYSTEM_ZB);
                                break;
                            case "光伏幕墙":
                                IntentUtils.getInstance().toActivity(getContext(), GfZbActivity.class, IntentUtils.getInstance().GF_ZB);
                                break;
                            case "门窗定制招标":
//                                IntentUtils.getInstance().toActivity(getContext(), DoorWindowActivity.class);
                                IntentUtils.getInstance().toActivity(getContext(), GfZbActivity.class, IntentUtils.getInstance().DOOR_WINDOW);
                                break;
                        }
                    }
                });
                break;
            case R.id.ll_select_tie:
                DialogUtils.getInstance().getBaseDialog(this, listType, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        tvSelectType.setText(str + "  ");
                        if (str.equals("全部")) {
                            projectType = "";
                        } else {
                            projectType = str;
                        }
                        searchZb = "";
                        pageSize = 1;
                        list.clear();
                        getSearchZb();
                    }
                });
                break;
            case R.id.ll_standard:
                DialogUtils.getInstance().getBaseDialog(this, listSpec, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        tvStandard.setText(str + "  ");
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
                });//
                break;
            //搜索
            case R.id.iv_right:
//                searchZb = etSearchZb.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(searchZb)) {
//                    ToastUtils.getMineToast("请输入搜索信息");
//                    return;
//                }
//                projectType = "";
//                tvTypeSelect.setText("项目类型");
//                pageSize = 1;
//                list.clear();
//                getSearchZb();
                IntentUtils.getInstance().toActivity(this,ZbSearchActivity.class);
                break;
        }
    }

    public void initTypeData() {
        listType.add("幕墙招标");
        listType.add("系统门窗");
        listType.add("光伏幕墙");
        listType.add("门窗定制招标");
        listType.add("其他");
        listType.add("全部");

        listSpec.add("1000-5000");
        listSpec.add("5000-10000");
        listSpec.add("15000-30000");
        listSpec.add("30000-50000");
        listSpec.add(">50000");
        listSpec.add("全部");
    }

    //搜索招标数据
    public void getSearchZb() {
        if (StringUtils.isNoEmpty(projectType) && projectType.equals("全部")) {
            projectType = "";
        }
        MoreZbFunction.getInstance().searchZbList(getContext(), searchZb, projectType, small, large, pageSize, new OnZbBeanCallBack() {
            @Override
            public void onSuccess(ZbBean zbBean) {
                if (pageSize == 1) {
                    list.clear();
                }
                list.addAll(zbBean.getReList());
                zbNewAdapter.notifyDataSetChanged();
                SmartUtils.finishSmart(smart);
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(smart);
            }
        });
    }
}
