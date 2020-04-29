package com.example.huoshangkou.jubowan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ApproveAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveCheckBean;
import com.example.huoshangkou.jubowan.bean.ApproveCheckListBean;
import com.example.huoshangkou.jubowan.bean.BaseCheckListBean;
import com.example.huoshangkou.jubowan.constant.ApproveConstant;
import com.example.huoshangkou.jubowan.constant.Constant;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnGetCheckCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ApproveCheckListActivity
 * 描述：
 * 创建时间：2018-03-13  08:24
 */

public class ApproveCheckListActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    XRefreshView xRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;
    private ApproveAdapter approveAdapter;
    private List<ApproveCheckListBean> listData;

    private int pageApprove = 1;
    private String type = "";

    private String linkMan = "";
    private String startTime = "";
    private String endTime = "";
    private String keyWord = "";

    //申请还是审批
//    private String postStr = "";
//    //审批或者查询字段
//    private String checkStr = "";
//    private String checkStrType = "";
//    //审批 申请fie字段
//    private String fieType = "";

    //1 审批 0 申请
    private String checkType = "";

    @Override
    public int initLayout() {
        return R.layout.activity_approve_check_list;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {

        linkMan = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        keyWord = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        startTime = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        endTime = getIntent().getStringExtra(IntentUtils.getInstance().TXT);
        checkType = getIntent().getStringExtra(IntentUtils.getInstance().APPROVE_TYPE);

        //审批
        if (checkType.equals("1")) {
            tvTitle.setText("审批查询");
            type = "1";
//            checkStr = Constant.APPROVE;
//            checkStrType = ApproveConstant.getInstance().APPROVE;
//            //审批查询
//            postStr = PostConstant.getInstance().GET_APPROVE_DATA;
//            //审批完成
//            fieType = FieldConstant.getInstance().APPROVE_RESULT;

            //申请
        } else if (checkType.equals("0")) {
            tvTitle.setText("申请查询");
            type = "3";
            //审批查询
//            postStr = PostConstant.getInstance().GET_APPLY_LIST;
//            checkStrType = ApproveConstant.getInstance().APPLY;
//            审批完成
//            checkStr = Constant.APPLY;
//            fieType = FieldConstant.getInstance().APPLY_STATE;
        } else if (checkType.equals("2")) {
            tvTitle.setText("知会查询");
            type = "2";
            //审批查询
//            postStr = PostConstant.getInstance().GET_APPROVE_NOTIFY_LIST;
//            checkStrType = ApproveConstant.getInstance().MINE_ZH;
            //审批完成
//            checkStr = Constant.MINE_ZH;
//            fieType = FieldConstant.getInstance().APPROVE_RESULT;
        }

        listData = new ArrayList<>();
        approveAdapter = new ApproveAdapter(getContext(), listData, R.layout.item_approve_layout);
        lvRefresh.setAdapter(approveAdapter);
        lvRefresh.setDividerHeight(0);

        lvRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (type) {
                    case "2":
                    case "3":
                        //垫付款 13208009611 陈燕
//                        if (listData.get(i).getApprovalTypeID() == 1008) {
//                            IntentUtils.getInstance().toApproveCheck(getContext(), PadPaymentActivity.class, listData.get(i).getStatusID() + "", listData.get(i).getApprovalID() + ","
//                                    + listData.get(i).getApprovalTypeID() + "," + listData.get(i).getSellUserID() + "," + listData.get(i).getAdCounts(), Constant.APPROVE, type, listData.get(i).getUserName());
//                        } else if (listData.get(i).getApprovalTypeID() == 1101) {
//                            IntentUtils.getInstance().toYwActivity(getContext(), ApproveDetailActivity.class, listData.get(i).getApprovalID() + "",
//                                    ApproveConstant.getInstance().APPROVE, listData.get(i).getApprovalTypeID() + "", Constant.APPROVE, type, "yw", listData.get(i).getUserName());
//                        } else if (listData.get(i).getApprovalTypeID() == 1201) {
//                            //运营人员
//                            if (listData.get(i).getApprovalPeopleType() == 1) {
//                                //未审批
//                                if (listData.get(i).getApprovalOver() == -1) {
//                                    IntentUtils.getInstance().toActivity(getContext(), YwMoneyNewActivity.class, listData.get(i).getBorrowID() + "", "commit", listData.get(i).getUserName(), listData.get(i).getApprovalPeopleType() + "");
//                                    return;
//                                }
//                                IntentUtils.getInstance().toActivity(getContext(), YwNewDetailActivity.class, listData.get(i).getBorrowID() + "", "unCheck", listData.get(i).getUserName(), listData.get(i).getApprovalPeopleType() + "");
//                            } else {
//                                //未审批
//                                if (listData.get(i).getApprovalOver() == -1) {
//                                    IntentUtils.getInstance().toActivity(getContext(), YwNewDetailActivity.class, listData.get(i).getBorrowID() + "", "check", listData.get(i).getUserName(), listData.get(i).getApprovalPeopleType() + "");
//                                    return;
//                                }
//                                IntentUtils.getInstance().toActivity(getContext(), YwNewDetailActivity.class, listData.get(i).getBorrowID() + "", "unCheck", listData.get(i).getUserName(), listData.get(i).getApprovalPeopleType() + "");
//                            }
//                            //运营之后人员
//                        } else if (listData.get(i).getApprovalTypeID() == 1011 && listData.get(i).getApprovalPeopleType() >= 1) {
//                            //运营人员
//                            if (listData.get(i).getApprovalPeopleType() == 1) {
//                                //未审批
//                                if (listData.get(i).getApprovalOver() == -1) {
//                                    IntentUtils.getInstance().toActivity(getContext(), BtCheckDetailActivity.class, listData.get(i).getApprovalID() + "", listData.get(i).getApprovalTypeID() + "");
//                                    //已审批
//                                } else {
//                                    IntentUtils.getInstance().toActivity(getContext(), BtDetailCheckActivity.class, listData.get(i).getBorrowID() + "", listData.get(i).getApprovalTypeID() + "", "check");
//                                }
//                                //运营之后的人员
//                            } else if (listData.get(i).getApprovalPeopleType() > 1) {
//                                //未审批
//                                if (listData.get(i).getApprovalOver() == -1) {
//                                    IntentUtils.getInstance().toActivity(getContext(), BtDetailCheckActivity.class, listData.get(i).getBorrowID() + "", listData.get(i).getApprovalTypeID() + "", "unCheck");
//                                    //已审批
//                                } else {
//                                    IntentUtils.getInstance().toActivity(getContext(), BtDetailCheckActivity.class, listData.get(i).getBorrowID() + "", listData.get(i).getApprovalTypeID() + "", "check");
//                                }
//                            }
//                        } else {
//                            IntentUtils.getInstance().toYwActivity(getContext(), ApproveDetailActivity.class, listData.get(i).getApprovalID() + "",
//                                    ApproveConstant.getInstance().APPROVE, listData.get(i).getApprovalTypeID() + "", Constant.APPROVE, type, listData.get(i).getUserName());
//                        }

                        String idApply = "";
                        if (listData.get(i).getBorrowID() != 0) {
                            idApply = listData.get(i).getBorrowID() + "";
                        }
                        if (listData.get(i).getApprovalID() != 0) {
                            idApply = listData.get(i).getApprovalID() + "";
                        }
                        IntentUtils.getInstance().toActivity(getContext(), BaseCheckDetailActivity.class, ApproveConstant.getInstance().APPLY_TYPE, idApply, "" + listData.get(i).getApprovalTypeID(), listData.get(i).getUserName());


                        break;
                    case "1":

                        Context context = getContext();
                        String id = "";
                        if (listData.get(i).getBorrowID() != 0) {
                            id = listData.get(i).getBorrowID() + "";
                        }
                        if (listData.get(i).getApprovalID() != 0) {
                            id = listData.get(i).getApprovalID() + "";
                        }
                        switch (listData.get(i).getApprovalTypeID()) {
                            case 1011:
                                if (listData.get(i).getApprovalOver() == -1 && listData.get(i).getApprovalPeopleType() == 1) {
                                    IntentUtils.getInstance().toActivity(context, BaseCheckActivity.class, listData.get(i).getApprovalTypeID() + "", "白条用款", "1011", listData.get(i).getApprovalID() + "");
                                } else {
                                    IntentUtils.getInstance().toActivity(context, BaseCheckDetailActivity.class, ApproveConstant.getInstance().CHECK_TYPE, id, "" + listData.get(i).getApprovalTypeID(), listData.get(i).getUserName(), type, listData.get(i).getApprovalPeopleType());
                                }
                                break;
                            case 1201:
                                if (listData.get(i).getApprovalPeopleType() == 1 && listData.get(i).getApprovalOver() == -1) {
                                    IntentUtils.getInstance().toActivity(context, BaseCheckActivity.class, listData.get(i).getApprovalTypeID() + "", "业务用款", "1201", listData.get(i).getApprovalID() + "");
                                } else {
                                    IntentUtils.getInstance().toActivity(context, BaseCheckDetailActivity.class, ApproveConstant.getInstance().CHECK_TYPE, id, "" + listData.get(i).getApprovalTypeID(), listData.get(i).getUserName(), type);
                                }
                                break;
                            case 1301:
                                if (listData.get(i).getApprovalPeopleType() == 1 && listData.get(i).getApprovalOver() == -1) {
                                    IntentUtils.getInstance().toActivity(context, BaseCheckActivity.class, listData.get(i).getApprovalTypeID() + "", "垫付款", "1301", listData.get(i).getApprovalID() + "");
                                } else {
                                    IntentUtils.getInstance().toActivity(context, BaseCheckDetailActivity.class, ApproveConstant.getInstance().CHECK_TYPE, id, "" + listData.get(i).getApprovalTypeID(), listData.get(i).getUserName(), type);
                                }
                                break;
                            default:
                                IntentUtils.getInstance().toActivity(context, BaseCheckDetailActivity.class, ApproveConstant.getInstance().CHECK_TYPE, id, "" + listData.get(i).getApprovalTypeID(), listData.get(i).getUserName(), type);
                                break;
                        }

                        //垫付款
//                        if (listData.get(i).getApprovalTypeID() == 1008) {
//                            IntentUtils.getInstance().toApproveCheck(getContext(), PadPaymentActivity.class, listData.get(i).getStatusID() + "",
//                                    listData.get(i).getApprovalID() + "," + listData.get(i).getApprovalTypeID() + ","
//                                            + listData.get(i).getSellUserID() + "," + listData.get(i).getAdCounts(), Constant.APPROVE, type, listData.get(i).getUserName());
//                        } else if (listData.get(i).getApprovalTypeID() == 1101) {
//                            IntentUtils.getInstance().toYwActivity(getContext(), ApproveDetailActivity.class, listData.get(i).getApprovalID() + "",
//                                    ApproveConstant.getInstance().APPLY, listData.get(i).getApprovalTypeID() + "", Constant.APPLY, type, "yw", listData.get(i).getUserName());
//                        } else if (listData.get(i).getApprovalTypeID() == 1201) {
//                            //不同意可修改
////                    if(listApply.get(i).getApprovalOver()==0){
////                        IntentUtils.getInstance().toActivity(context, YwMoneyNewActivity.class, listApply.get(i).getBorrowID() + "", "operator");
////                        return;
////                    }
//                            Intent intent = new Intent(getContext(), YwNewDetailActivity.class);
//                            intent.putExtra(IntentUtils.getInstance().TYPE, listData.get(i).getBorrowID() + "");
//                            intent.putExtra(IntentUtils.getInstance().CHECK_APPROVE, listData.get(i).getApprovalOver() + "");
//                            intent.putExtra(IntentUtils.getInstance().STR, listData.get(i).getUserName() + "");
//                            intent.putExtra(IntentUtils.getInstance().TXT, listData.get(i).getApprovalPeopleType() + "");
//                            getContext().startActivity(intent);
//
////                    IntentUtils.getInstance().toActivity(context, YwNewDetailActivity.class, listApply.get(i).getBorrowID() + "");
//                        } else {
//                            IntentUtils.getInstance().toYwActivity(getContext(), ApproveDetailActivity.class, listData.get(i).getApprovalID() + "",
//                                    ApproveConstant.getInstance().APPLY, listData.get(i).getApprovalTypeID() + "", Constant.APPLY, type, listData.get(i).getUserName());
//                        }
                        break;
                }
            }
        });

//        lvRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                //垫付款
//                if (listApply.get(i).getApprovalTypeID() == 1008) {
//                    IntentUtils.getInstance().toActivity(context, PadPaymentActivity.class, listApply.get(i).getRoleName(),
//                            listApply.get(i).getApprovalID() + "," + listApply.get(i).getApprovalTypeID() + ","
//                                    + listApply.get(i).getSellUserID() + "," + listApply.get(i).getAdCounts(), Constant.APPROVE, type);
//                } else {
//                    IntentUtils.getInstance().toActivity(context, ApproveDetailActivity.class, listApply.get(i).getApprovalID() + "",
//                            ApproveConstant.getInstance().APPLY, listApply.get(i).getApprovalTypeID() + "", Constant.APPLY, type);
//                }
//            }
//        });

        getApproveData(getContext(), type, new OnGetCheckCallBack() {
            @Override
            public void onGetCheckBean(List<ApproveCheckListBean> checkBean) {
                listData.addAll(checkBean);
                approveAdapter.notifyDataSetChanged();
                if (listData.size() == 0) {
                    llNoData.setVisibility(View.VISIBLE);
                } else {
                    llNoData.setVisibility(View.GONE);
                }
                xRefresh.stopRefresh();
            }
        });

        xRefresh.setPullLoadEnable(true);
        xRefresh.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                pageApprove = 1;
                getApproveData(getContext(), type, new OnGetCheckCallBack() {
                    @Override
                    public void onGetCheckBean(List<ApproveCheckListBean> checkBean) {
                        listData.clear();
                        listData.addAll(checkBean);
                        approveAdapter.notifyDataSetChanged();

                        if (listData.size() == 0) {
                            llNoData.setVisibility(View.VISIBLE);
                        } else {
                            llNoData.setVisibility(View.GONE);
                        }
                        xRefresh.stopRefresh();
                    }
                });
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pageApprove++;
                getApproveData(getContext(), type, new OnGetCheckCallBack() {
                    @Override
                    public void onGetCheckBean(List<ApproveCheckListBean> checkBean) {
                        listData.addAll(checkBean);
                        approveAdapter.notifyDataSetChanged();

                        if (listData.size() == 0) {
                            llNoData.setVisibility(View.VISIBLE);
                        } else {
                            llNoData.setVisibility(View.GONE);
                        }
                        xRefresh.stopLoadMore();
                    }
                });
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }

    //获取审批信息  approveResult  -1未审批，0不同意，1同意  3获取同意和不同意的审批
    private void getApproveData(Context context, String approveResult, final OnGetCheckCallBack checkCallBack) {
//        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
//                + postStr
//                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
//                + fieType + "=" + approveResult + "&"
//                + FieldConstant.getInstance().LINK_MAN + "=" + EncodeUtils.getInstance().getEncodeString(linkMan) + "&"
//                + FieldConstant.getInstance().KEY_WORD + "=" + EncodeUtils.getInstance().getEncodeString(keyWord) + "&"
//                + FieldConstant.getInstance().START_TIME + "=" + EncodeUtils.getInstance().getEncodeString(startTime) + "&"
//                + FieldConstant.getInstance().END_TIME + "=" + EncodeUtils.getInstance().getEncodeString(endTime) + "&"
//                + FieldConstant.getInstance().PAGE_SIZE + "=" + pageApprove, new OkhttpCallBack() {
//            @Override
//            public void onSuccess(String json) {
//                ApproveCheckBean checkBean = JSON.parseObject(json, ApproveCheckBean.class);
//                checkCallBack.onGetCheckBean(checkBean);
//            }
//
//            @Override
//            public void onFail() {
//
//            }
//        });


        Map<String, Object> map = new HashMap<>();
        map.put("InfoType", type);
        map.put("UserID", PersonConstant.getInstance().getUserId());
        map.put("PageSize", pageApprove);
        if (type.equals("1") || type.equals("2")) {
            map.put("ApprovalOver", approveResult);
        } else {
            map.put("ApprovalState", "1");
        }
        map.put("LinkMan", linkMan);
        map.put("StartTime", startTime);
        map.put("EndTime", endTime);
        map.put("KeyWord", keyWord);
        String json = "{\n" +
                "\"model\":" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCall(context, json, UrlConstant.getInstance().APPROVE_LIST_URL, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                BaseCheckListBean checkBean = JSON.parseObject(str, BaseCheckListBean.class);
                checkCallBack.onGetCheckBean(checkBean.getD().getReList());
            }

            @Override
            public void onFail() {

            }
        });
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

}
