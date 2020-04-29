package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.CheckApplyAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.CheckHintBean;
import com.example.huoshangkou.jubowan.bean.QuaMenuBean;
import com.example.huoshangkou.jubowan.constant.ApproveConstant;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：CheckApplyActivity
 * 描述：我的审批界面
 * 创建时间：2017-02-15  09:50
 */

public class CheckApplyActivity extends BaseActivity {
    //标题
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.lv_check_apply)
    ScrollGridView listView;
    @Bind(R.id.tv_red_num)
    TextView tvRedNum;
    @Bind(R.id.tv_red_num_copy)
    TextView tvRedNumCopy;

    //是有有资格申请垫付款
    private boolean isQuality = false;
    //是否是运营人员
    private boolean isOperator = false;
    //是否是财务
    private boolean isCaiWu = false;
    //HeYan2501556976
    CheckApplyAdapter applyAdapter;
    List<QuaMenuBean.DBean.ReListBean> applyBeanList = new ArrayList<>();
    //是否有业务用款功能
    private boolean isNeedYw = false;
    private String checkId = "";

    @Override
    public int initLayout() {
        return R.layout.activity_check_apply;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        //标题
        tvTitle.setText("我的审批");
        checkId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        applyAdapter = new CheckApplyAdapter(CheckApplyActivity.this, applyBeanList, R.layout.item_check_apply);
        listView.setAdapter(applyAdapter);
        getQuaMenu();
        getCheckData();
    }

    //点击事件
    @OnClick({R.id.ll_back, R.id.rl_mine_approve, R.id.rl_mine_apply, R.id.rl_mine_zh})
    public void onClick(View view) {
        switch (view.getId()) {
            //返回按钮
            case R.id.ll_back:
                this.finish();
                break;
            //我的申请
            case R.id.rl_mine_apply:
                IntentUtils.getInstance().toActivity(this, MineApproveActivity.class, ApproveConstant.getInstance().APPLY);
                break;
            //我的审批
            case R.id.rl_mine_approve:
                IntentUtils.getInstance().toActivity(this, MineApproveActivity.class, ApproveConstant.getInstance().APPROVE);
                break;
            //我的知会
            case R.id.rl_mine_zh:
                IntentUtils.getInstance().toActivity(this, MineApproveActivity.class, ApproveConstant.getInstance().MINE_ZH);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getApproveNum();
    }

    //获取待审批的个数
    public void getApproveNum() {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this,
                UrlConstant.getInstance().URL
                        + PostConstant.getInstance().GETSP_NUMBERS
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        LogUtils.e(json);
                        CheckHintBean hintBean = JSON.parseObject(json, CheckHintBean.class);
                        String num = hintBean.getReObj().getNumber();
                        if (tvRedNum == null || tvRedNumCopy == null || listView == null) {
                            return;
                        }
                        if (!StringUtils.isNoEmpty(hintBean.getReObj().getCopyNumber()) || hintBean.getReObj().getCopyNumber().equals("0")) {
                            tvRedNumCopy.setVisibility(View.GONE);
                        } else {
                            tvRedNumCopy.setVisibility(View.VISIBLE);
                            tvRedNumCopy.setText(hintBean.getReObj().getCopyNumber());
                        }
                        if (!StringUtils.isNoEmpty(num) || num.equals("0")) {
                            tvRedNum.setVisibility(View.GONE);
                        } else {
                            tvRedNum.setText(num);
                            tvRedNum.setVisibility(View.VISIBLE);
                        }
                        //1:销售部    2：运营部   3：其它
                        if (hintBean.getReObj().getIsOK().equals("1")) {
                            isQuality = true;
                        } else {
                            isQuality = false;
                        }
                        if (hintBean.getReObj().getIsOK().equals("2")) {
                            isOperator = true;
                        } else {
                            isOperator = false;
                        }
                        if (hintBean.getReObj().getIsOK().equals("1") || hintBean.getReObj().getIsOK().equals("2")) {
                            isNeedYw = true;
                        }
                        if (hintBean.getReObj().getIsOK().equals("101")) {
                            isCaiWu = true;
                        } else {
                            isCaiWu = false;
                        }
                        getCheckData();
                        SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().KEY_MAN_ID, hintBean.getReObj().getIsOK());
                    }

                    @Override
                    public void onFail() {
                        getCheckData();
                    }
                });
    }

    //初始化数据  
    private void getCheckData() {
        //点击事件
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                switch (applyBeanList.get(i).getId()) {
//                    //报销申请
//                    case 10:
//                        IntentUtils.getInstance().toActivity(getContext(), MoneyUserActivity.class, ApproveConstant.getInstance().BX_MONEY);
//                        break;
//                    //用款申请
//                    case 11:
//                        IntentUtils.getInstance().toActivity(getContext(), MoneyUserActivity.class, ApproveConstant.getInstance().USE_MONEY);
//                        break;
//                    //请假申请
//                    case 12:
//                        IntentUtils.getInstance().toActivity(getContext(), HolidayActivity.class);
//                        break;
//                    //出差申请
//                    case 13:
//                        IntentUtils.getInstance().toActivity(getContext(), OutWorkActivity.class);
//                        break;
//                    //承兑申请
//                    case 14:
//                        IntentUtils.getInstance().toActivity(getContext(), MoneyUserActivity.class, ApproveConstant.getInstance().CD_MONEY);
//                        break;
//                    //用款申请
//                    case 15:
//                        IntentUtils.getInstance().toActivity(getContext(), MoneyUserActivity.class, ApproveConstant.getInstance().OTHER);
//                        break;
//                    //业务用款
//                    case 16:
//                        if (isOperator) {
//                            IntentUtils.getInstance().toActivity(getContext(), YwMoneyNewActivity.class, "0", "operator");
//                            return;
//                        }
//                        IntentUtils.getInstance().toActivity(getContext(), YwMoneyNewActivity.class, "0", "sale");
//                        break;
//                    //内部往来款
//                    case 17:
//                        IntentUtils.getInstance().toActivity(getContext(), InCompanyMoneyActivity.class);
//                        break;
//                }
//            }
//        });
        if (listView == null) {
            return;
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (applyBeanList.get(i).getId()) {
                    //报销申请
                    case 10:
                        IntentUtils.getInstance().toActivity(getContext(), BaseCheckActivity.class, applyBeanList.get(i).getId() + "", "费用报销", "1");
                        break;
                    //用款申请
                    case 11:
                        IntentUtils.getInstance().toActivity(getContext(), BaseCheckActivity.class, applyBeanList.get(i).getId() + "", "普通用款", "2");
                        break;
                    //请假申请
                    case 12:
                        IntentUtils.getInstance().toActivity(getContext(), BaseCheckActivity.class, applyBeanList.get(i).getId() + "", "请假", "3");
                        break;
                    //出差申请
                    case 13:
                        IntentUtils.getInstance().toActivity(getContext(), BaseCheckActivity.class, applyBeanList.get(i).getId() + "", "出差", "4");
                        break;
                    //承兑申请
                    case 14:
                        IntentUtils.getInstance().toActivity(getContext(), BaseCheckActivity.class, applyBeanList.get(i).getId() + "", "承兑申请", "1006");
                        break;
                    //用款申请
                    case 15:
                        IntentUtils.getInstance().toActivity(getContext(), BaseCheckActivity.class, applyBeanList.get(i).getId() + "", "其他审批", "1100");
                        break;
                    //业务用款
                    case 16:
                        IntentUtils.getInstance().toActivity(getContext(), BaseCheckActivity.class, applyBeanList.get(i).getId() + "", "业务用款", "1201");
                        break;
                    //内部往来款
                    case 17:
                        IntentUtils.getInstance().toActivity(getContext(), BaseCheckActivity.class, applyBeanList.get(i).getId() + "", "内部往来款", "1401");
                        break;
                }
            }
        });
    }

    //获取权限菜单
    public void getQuaMenu() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", PersonConstant.getInstance().getUserId());
        map.put("id", 0);
        map.put("tier", 2);
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().QUA_MENU + "GetFunctionList", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                QuaMenuBean menuBean = JSON.parseObject(str, QuaMenuBean.class);
                applyBeanList.addAll(menuBean.getD().getReList());
                applyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }
}
