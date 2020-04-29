package com.example.huoshangkou.jubowan.fragment.function;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.ApproveDetailActivity;
import com.example.huoshangkou.jubowan.activity.BaseCheckActivity;
import com.example.huoshangkou.jubowan.activity.BaseCheckDetailActivity;
import com.example.huoshangkou.jubowan.activity.BtCheckDetailActivity;
import com.example.huoshangkou.jubowan.activity.BtDetailCheckActivity;
import com.example.huoshangkou.jubowan.activity.DianFuCheckActivity;
import com.example.huoshangkou.jubowan.activity.DianFuFinishActivity;
import com.example.huoshangkou.jubowan.activity.DianFuNewActivity;
import com.example.huoshangkou.jubowan.activity.InCompanyMoneyDetailActivity;
import com.example.huoshangkou.jubowan.activity.PadPaymentActivity;
import com.example.huoshangkou.jubowan.activity.YwMoneyNewActivity;
import com.example.huoshangkou.jubowan.activity.YwNewDetailActivity;
import com.example.huoshangkou.jubowan.adapter.ApplyAdapter;
import com.example.huoshangkou.jubowan.adapter.ApproveAdapter;
import com.example.huoshangkou.jubowan.bean.ApproveCheckBean;
import com.example.huoshangkou.jubowan.bean.ApproveCheckListBean;
import com.example.huoshangkou.jubowan.bean.BaseCheckListBean;
import com.example.huoshangkou.jubowan.bean.GetApplyBean;
import com.example.huoshangkou.jubowan.bean.GetApplyListBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.ApproveConstant;
import com.example.huoshangkou.jubowan.constant.Constant;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnApplyCheckCallBack;
import com.example.huoshangkou.jubowan.inter.OnApplyDeleteCallBack;
import com.example.huoshangkou.jubowan.inter.OnDeleteBankCallBack;
import com.example.huoshangkou.jubowan.inter.OnGetCheckCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment.function
 * 类名：ApproveListFunction
 * 描述：审批工具类
 * 创建时间：2017-03-20  13:08
 */

public class ApproveListFunction {
    private static class ApproveHelper {
        private static ApproveListFunction INSTANCE = new ApproveListFunction();
    }

    private int pageApprove = 1;
    private int pageApply = 1;

    LinearLayout mLlNoData;

    public static ApproveListFunction getInstance() {
        return ApproveHelper.INSTANCE;
    }

    //审批数据列表操作
    public void getApproveListData(final SmartRefreshLayout xRefresh, ApproveAdapter approveAdapter, final Context context,
                                   final List<ApproveCheckListBean> listData, ListView lvRefresh,
                                   final String type, final LinearLayout llNoData) {
        approveAdapter = new ApproveAdapter(context, listData, R.layout.item_approve_layout);
        lvRefresh.setAdapter(approveAdapter);
        lvRefresh.setDividerHeight(0);

        pageApprove = 1;
        lvRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                             @Override
                                             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
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
                                                             IntentUtils.getInstance().toActivity(context, BaseCheckActivity.class, listData.get(i).getApprovalTypeID() + "", "白条审批", "1011", listData.get(i).getApprovalID() + "", listData.get(i).getApprovalPeopleType() + "");
                                                         } else {
                                                             IntentUtils.getInstance().toActivity(context, BaseCheckDetailActivity.class, ApproveConstant.getInstance().CHECK_TYPE, id, "" + listData.get(i).getApprovalTypeID(), listData.get(i).getUserName(), type, listData.get(i).getApprovalPeopleType());
                                                         }
                                                         break;
                                                     case 1201:
                                                         if (listData.get(i).getApprovalOver() == -1 && listData.get(i).getApprovalPeopleType() <= 1) {
                                                             IntentUtils.getInstance().toActivity(context, BaseCheckActivity.class, listData.get(i).getApprovalTypeID() + "", "业务用款", "1201", listData.get(i).getApprovalID() + "", listData.get(i).getApprovalPeopleType() + "");
                                                         } else {
                                                             IntentUtils.getInstance().toActivity(context, BaseCheckDetailActivity.class, ApproveConstant.getInstance().CHECK_TYPE, id, "" + listData.get(i).getApprovalTypeID(), listData.get(i).getUserName(), type, listData.get(i).getApprovalPeopleType());
                                                         }
                                                         break;
                                                     case 1301:
                                                         if (listData.get(i).getApprovalOver() == -1 && listData.get(i).getApprovalPeopleType() <= 1) {
                                                             IntentUtils.getInstance().toActivity(context, BaseCheckActivity.class, listData.get(i).getApprovalTypeID() + "", "垫付款", "1301", listData.get(i).getApprovalID() + "", listData.get(i).getApprovalPeopleType() + "");
                                                         } else {
                                                             IntentUtils.getInstance().toActivity(context, BaseCheckDetailActivity.class, ApproveConstant.getInstance().CHECK_TYPE, id, "" + listData.get(i).getApprovalTypeID(), listData.get(i).getUserName(), type, listData.get(i).getApprovalPeopleType());
                                                         }
                                                         break;
                                                     default:
                                                         IntentUtils.getInstance().toActivity(context, BaseCheckDetailActivity.class, ApproveConstant.getInstance().CHECK_TYPE, id, "" + listData.get(i).getApprovalTypeID(), listData.get(i).getUserName(), type);
                                                         break;
                                                 }


//                                                 switch (listData.get(i).getApprovalTypeID()) {
//                                                     //垫付款 13208009611 陈燕
//                                                     case 1008:
//                                                         IntentUtils.getInstance().toApproveCheck(context, PadPaymentActivity.class, listData.get(i).getStatusID() + "", listData.get(i).getApprovalID() + ","
//                                                                 + listData.get(i).getApprovalTypeID() + "," + listData.get(i).getSellUserID() + "," + listData.get(i).getAdCounts(), Constant.APPROVE, type, listData.get(i).getUserName());
//                                                         break;
//                                                     case 1101:
//                                                         IntentUtils.getInstance().toYwActivity(context, ApproveDetailActivity.class, listData.get(i).getApprovalID() + "",
//                                                                 ApproveConstant.getInstance().APPROVE, listData.get(i).getApprovalTypeID() + "", Constant.APPROVE, type, "yw", listData.get(i).getUserName());
//                                                         break;
//                                                     case 1201:
//                                                         //运营人员
//                                                         if (listData.get(i).getApprovalPeopleType() == 1) {
//                                                             //未审批
//                                                             if (listData.get(i).getApprovalOver() == -1) {
//                                                                 IntentUtils.getInstance().toActivity(context, YwMoneyNewActivity.class, listData.get(i).getBorrowID() + "", "commit", listData.get(i).getUserName(), listData.get(i).getApprovalPeopleType() + "");
//                                                                 return;
//                                                             }
//                                                             IntentUtils.getInstance().toActivity(context, YwNewDetailActivity.class, listData.get(i).getBorrowID() + "", "unCheck", listData.get(i).getUserName(), listData.get(i).getApprovalPeopleType() + "");
//                                                         } else {
//                                                             //未审批
//                                                             if (listData.get(i).getApprovalOver() == -1) {
//                                                                 IntentUtils.getInstance().toActivity(context, YwNewDetailActivity.class, listData.get(i).getBorrowID() + "", "check", listData.get(i).getUserName(), listData.get(i).getApprovalPeopleType() + "");
//                                                                 return;
//                                                             }
//                                                             IntentUtils.getInstance().toActivity(context, YwNewDetailActivity.class, listData.get(i).getBorrowID() + "", "unCheck", listData.get(i).getUserName(), listData.get(i).getApprovalPeopleType() + "");
//                                                         }
//                                                         break;
//                                                     case 1011:
//                                                         if (listData.get(i).getApprovalPeopleType() < 1) {
//                                                             IntentUtils.getInstance().toYwActivity(context, ApproveDetailActivity.class, listData.get(i).getApprovalID() + "",
//                                                                     ApproveConstant.getInstance().APPROVE, listData.get(i).getApprovalTypeID() + "", Constant.APPROVE, type, listData.get(i).getUserName());
//                                                             return;
//                                                         }
//                                                         //运营人员
//                                                         if (listData.get(i).getApprovalPeopleType() == 1) {
//                                                             //未审批
//                                                             if (listData.get(i).getApprovalOver() == -1) {
//                                                                 IntentUtils.getInstance().toBtDetailActivity(context, BtCheckDetailActivity.class, listData.get(i).getApprovalID() + "", listData.get(i).getApprovalTypeID() + "", "", listData.get(i).getUserName());
//                                                                 //已审批
//                                                             } else {
//                                                                 IntentUtils.getInstance().toBtDetailActivity(context, BtDetailCheckActivity.class, listData.get(i).getBorrowID() + "", listData.get(i).getApprovalTypeID() + "", "check", listData.get(i).getUserName());
//                                                             }
//                                                             //运营之后的人员
//                                                         } else if (listData.get(i).getApprovalPeopleType() > 1) {
//                                                             //未审批
//                                                             if (listData.get(i).getApprovalOver() == -1) {
//                                                                 IntentUtils.getInstance().toBtDetailActivity(context, BtDetailCheckActivity.class, listData.get(i).getBorrowID() + "", listData.get(i).getApprovalTypeID() + "", "unCheck", listData.get(i).getUserName());
//                                                                 //已审批
//                                                             } else {
//                                                                 IntentUtils.getInstance().toBtDetailActivity(context, BtDetailCheckActivity.class, listData.get(i).getBorrowID() + "", listData.get(i).getApprovalTypeID() + "", "check", listData.get(i).getUserName());
//                                                             }
//                                                         }
//                                                         break;
//                                                     case 1301:
//                                                         //未审批
//                                                         if (listData.get(i).getApprovalOver() == -1) {
//                                                             if (listData.get(i).getApprovalPeopleType() > 1) {
//                                                                 IntentUtils.getInstance().toActivity(context, DianFuFinishActivity.class, listData.get(i).getBorrowID() + "", "unCheck", listData.get(i).getUserName(), listData.get(i).getApprovalPeopleType() + "");
//                                                             } else {
//                                                                 IntentUtils.getInstance().toActivity(context, DianFuCheckActivity.class, listData.get(i).getBorrowID() + "");
//                                                             }
//                                                             //已审批
//                                                         } else {
//                                                             IntentUtils.getInstance().toActivity(context, DianFuFinishActivity.class, listData.get(i).getBorrowID() + "", "check", listData.get(i).getUserName(), listData.get(i).getApprovalPeopleType() + "");
//                                                         }
//                                                         break;
//                                                     case 1401:
//                                                         IntentUtils.getInstance().toActivity(context, InCompanyMoneyDetailActivity.class, listData.get(i).getBorrowID() + "", Constant.APPROVE, listData.get(i).getApprovalOver() + "", listData.get(i).getUserName());
//                                                         break;
//                                                     default:
//                                                         IntentUtils.getInstance().toYwActivity(context, ApproveDetailActivity.class, listData.get(i).getApprovalID() + "",
//                                                                 ApproveConstant.getInstance().APPROVE, listData.get(i).getApprovalTypeID() + "", Constant.APPROVE, type, listData.get(i).getUserName());
//                                                         break;
//                                                 }
                                             }
                                         }
        );

        //获取审批信息
        final ApproveAdapter finalApproveAdapter = approveAdapter;
        xRefresh.setOnRefreshListener(new

                                              OnRefreshListener() {
                                                  @Override
                                                  public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                                                      pageApprove = 1;
                                                      getApproveData(context, type, new OnGetCheckCallBack() {
                                                          @Override
                                                          public void onGetCheckBean(List<ApproveCheckListBean> checkBean) {
                                                              listData.clear();
                                                              listData.addAll(checkBean);
                                                              finalApproveAdapter.notifyDataSetChanged();
                                                              if (listData.size() == 0) {
                                                                  llNoData.setVisibility(View.VISIBLE);
                                                              } else {
                                                                  llNoData.setVisibility(View.GONE);
                                                              }
                                                              SmartUtils.finishSmart(xRefresh);
                                                          }
                                                      });
                                                  }
                                              }

        );
        xRefresh.setOnLoadMoreListener(new

                                               OnLoadMoreListener() {
                                                   @Override
                                                   public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                                                       pageApprove++;
                                                       getApproveData(context, type, new OnGetCheckCallBack() {
                                                           @Override
                                                           public void onGetCheckBean(List<ApproveCheckListBean> checkBean) {
                                                               listData.addAll(checkBean);
                                                               finalApproveAdapter.notifyDataSetChanged();

                                                               if (listData.size() == 0) {
                                                                   llNoData.setVisibility(View.VISIBLE);
                                                               } else {
                                                                   llNoData.setVisibility(View.GONE);
                                                               }
                                                               if (xRefresh == null) {
                                                                   return;
                                                               }
                                                               SmartUtils.finishSmart(xRefresh);
                                                           }
                                                       });
                                                   }
                                               }
        );

        pageApply = 1;
        getApproveData(context, type, new OnGetCheckCallBack() {
                    @Override
                    public void onGetCheckBean(List<ApproveCheckListBean> checkBean) {
                        listData.clear();
                        listData.addAll(checkBean);
                        finalApproveAdapter.notifyDataSetChanged();
                        if (listData.size() == 0) {
                            llNoData.setVisibility(View.VISIBLE);
                        } else {
                            llNoData.setVisibility(View.GONE);
                        }
                    }
                }
        );
    }

    //获取审批信息  approveResult  -1未审批，0不同意，1同意  3获取同意和不同意的审批
    private void getApproveData(Context context, String approveResult, final OnGetCheckCallBack checkCallBack) {
//        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
//                + PostConstant.getInstance().GET_APPROVE_DATA
//                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
//                + FieldConstant.getInstance().APPROVE_RESULT + "=" + approveResult + "&"
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
        map.put("InfoType", 1);
        map.put("UserID", PersonConstant.getInstance().getUserId());
        map.put("PageSize", pageApprove);
        map.put("ApprovalOver", approveResult);
//        map.put("ApprovalState", "");
        map.put("LinkMan", "");
        map.put("StartTime", "");
        map.put("EndTime", "");
        map.put("KeyWord", "");
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

    //获取申请信息
    private void getApplyData(Context context, String applyState, final OnApplyCheckCallBack applyCheckCallBack) {
//        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message),
//                UrlConstant.getInstance().URL + PostConstant.getInstance().GET_APPLY_LIST
//                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
//                        + FieldConstant.getInstance().APPLY_STATE + "=" + applyState + "&"
//                        + FieldConstant.getInstance().PAGE_SIZE + "=" + pageApply, new OkhttpCallBack() {
//                    @Override
//                    public void onSuccess(String json) {
//                        GetApplyBean applyBean = JSON.parseObject(json, GetApplyBean.class);
//                        applyCheckCallBack.onApplyCheckBean(applyBean);
//                    }
//
//                    @Override
//                    public void onFail() {
//
//                    }
//                });

        Map<String, Object> map = new HashMap<>();
        map.put("InfoType", 3);
        map.put("UserID", PersonConstant.getInstance().getUserId());
        map.put("PageSize", pageApply);
//        map.put("ApprovalOver", applyState);
        map.put("ApprovalState", applyState);
        map.put("LinkMan", "");
        map.put("StartTime", "");
        map.put("EndTime", "");
        map.put("KeyWord", "");
        String json = "{\n" +
                "\"model\":" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCall(context, json, UrlConstant.getInstance().APPROVE_LIST_URL, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                BaseCheckListBean checkBean = JSON.parseObject(str, BaseCheckListBean.class);
                applyCheckCallBack.onApplyCheckBean(checkBean.getD().getReList());
            }

            @Override
            public void onFail() {

            }
        });
    }

    //获取申请信息
    private void getMineZhData(Context context, String applyState, final OnApplyCheckCallBack applyCheckCallBack) {
//        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message),
//                UrlConstant.getInstance().URL + PostConstant.getInstance().GET_APPROVE_NOTIFY_LIST
//                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
//                        + FieldConstant.getInstance().APPROVE_RESULT + "=" + applyState + "&"
//                        + FieldConstant.getInstance().PAGE_SIZE + "=" + pageApply, new OkhttpCallBack() {
//                    @Override
//                    public void onSuccess(String json) {
//                        GetApplyBean applyBean = JSON.parseObject(json, GetApplyBean.class);
//                        applyCheckCallBack.onApplyCheckBean(applyBean);
//                    }
//
//                    @Override
//                    public void onFail() {
//
//                    }
//                });

        Map<String, Object> map = new HashMap<>();
        map.put("InfoType", 2);
        map.put("UserID", PersonConstant.getInstance().getUserId());
        map.put("PageSize", pageApply);
        map.put("ApprovalOver", applyState);
//        map.put("ApprovalState", applyState);
        map.put("LinkMan", "");
        map.put("StartTime", "");
        map.put("EndTime", "");
        map.put("KeyWord", "");
        String json = "{\n" +
                "\"model\":" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCall(context, json, UrlConstant.getInstance().APPROVE_LIST_URL, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                BaseCheckListBean checkBean = JSON.parseObject(str, BaseCheckListBean.class);
                applyCheckCallBack.onApplyCheckBean(checkBean.getD().getReList());
            }

            @Override
            public void onFail() {

            }
        });
    }

    //获取申请列表数据信息
    public void getApplyListData(final SmartRefreshLayout xRefresh, final Context context, final String type, ListView lvRefresh, ApplyAdapter applyAdapter,
                                 final List<ApproveCheckListBean> listApply, LinearLayout llNoData) {
        applyAdapter = new ApplyAdapter(context, listApply, R.layout.item_apply_layout);
        lvRefresh.setAdapter(applyAdapter);
        lvRefresh.setDividerHeight(0);
        mLlNoData = llNoData;
        pageApply = 1;

        lvRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = "";
                if (listApply.get(i).getBorrowID() != 0) {
                    id = listApply.get(i).getBorrowID() + "";
                }
                if (listApply.get(i).getApprovalID() != 0) {
                    id = listApply.get(i).getApprovalID() + "";
                }
                IntentUtils.getInstance().toActivity(context, BaseCheckDetailActivity.class, ApproveConstant.getInstance().APPLY_TYPE, id, "" + listApply.get(i).getApprovalTypeID(), listApply.get(i).getUserName(), type, listApply.get(i).getApprovalPeopleType());

                //垫付款
//                if (listApply.get(i).getApprovalTypeID() == 1008) {
//                    IntentUtils.getInstance().toApproveCheck(context, PadPaymentActivity.class, listApply.get(i).getStatusID() + "",
//                            listApply.get(i).getApprovalID() + "," + listApply.get(i).getApprovalTypeID() + ","
//                                    + listApply.get(i).getSellUserID() + "," + listApply.get(i).getAdCounts(), Constant.APPROVE, type, listApply.get(i).getUserName());
//                } else if (listApply.get(i).getApprovalTypeID() == 1101) {
//                    IntentUtils.getInstance().toYwActivity(context, ApproveDetailActivity.class, listApply.get(i).getApprovalID() + "",
//                            ApproveConstant.getInstance().APPLY, listApply.get(i).getApprovalTypeID() + "", Constant.APPLY, type, "yw", listApply.get(i).getUserName());
//                } else if (listApply.get(i).getApprovalTypeID() == 1201) {
//                    //不同意可修改
////                    if(listApply.get(i).getApprovalOver()==0){
////                        IntentUtils.getInstance().toActivity(context, YwMoneyNewActivity.class, listApply.get(i).getBorrowID() + "", "operator");
////                        return;
////                    }
//                    Intent intent = new Intent(context, YwNewDetailActivity.class);
//                    intent.putExtra(IntentUtils.getInstance().TYPE, listApply.get(i).getBorrowID() + "");
//                    intent.putExtra(IntentUtils.getInstance().VALUE, "apply");
//                    intent.putExtra(IntentUtils.getInstance().STR, listApply.get(i).getUserName());
//                    intent.putExtra(IntentUtils.getInstance().TXT, "");
//                    intent.putExtra(IntentUtils.getInstance().CHECK_APPROVE, listApply.get(i).getApprovalOver() + "");
//                    context.startActivity(intent);
//
////                    IntentUtils.getInstance().toActivity(context, YwNewDetailActivity.class, listApply.get(i).getBorrowID() + "");
//                } else if (listApply.get(i).getApprovalTypeID() == 1301) {
//                    IntentUtils.getInstance().toActivity(context, DianFuFinishActivity.class, listApply.get(i).getBorrowID() + "", "checkApply", listApply.get(i).getUserName(), listApply.get(i).getApprovalPeopleType() + "");
//                } else if (listApply.get(i).getApprovalTypeID() == 1401) {
//                    IntentUtils.getInstance().toActivity(context, InCompanyMoneyDetailActivity.class, listApply.get(i).getBorrowID() + "", Constant.APPLY, listApply.get(i).getApprovalOver() + "", listApply.get(i).getUserName());
//                } else {
//                    IntentUtils.getInstance().toYwActivity(context, ApproveDetailActivity.class, listApply.get(i).getApprovalID() + "",
//                            ApproveConstant.getInstance().APPLY, listApply.get(i).getApprovalTypeID() + "", Constant.APPLY, type, listApply.get(i).getUserName());
//                }
            }
        });

        //获取申请信息
        final ApplyAdapter finalApplyAdapter = applyAdapter;

        pageApply = 1;
        getApplyData(context, type, new OnApplyCheckCallBack() {
            @Override
            public void onApplyCheckBean(List<ApproveCheckListBean> checkBean) {
                listApply.clear();
                listApply.addAll(checkBean);
                finalApplyAdapter.notifyDataSetChanged();

                if (listApply.size() == 0) {
                    mLlNoData.setVisibility(View.VISIBLE);
                } else {
                    mLlNoData.setVisibility(View.GONE);
                }
            }
        });

        xRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageApply++;
                getApplyData(context, type, new OnApplyCheckCallBack() {
                    @Override
                    public void onApplyCheckBean(List<ApproveCheckListBean> checkBean) {
                        listApply.addAll(checkBean);
                        finalApplyAdapter.notifyDataSetChanged();
                        SmartUtils.finishSmart(xRefresh);

                        if (listApply.size() == 0) {
                            mLlNoData.setVisibility(View.VISIBLE);
                        } else {
                            mLlNoData.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        xRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageApply = 1;
                getApplyData(context, type, new OnApplyCheckCallBack() {
                    @Override
                    public void onApplyCheckBean(List<ApproveCheckListBean> checkBean) {
                        listApply.clear();
                        listApply.addAll(checkBean);
                        finalApplyAdapter.notifyDataSetChanged();
                        SmartUtils.finishSmart(xRefresh);

                        if (listApply.size() == 0) {
                            mLlNoData.setVisibility(View.VISIBLE);
                        } else {
                            mLlNoData.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        //删除申请订单
        applyAdapter.setDeleteCallBack(new OnApplyDeleteCallBack() {
            @Override
            public void onApplyDelete(final String id, final int position, final int post) {
                CopyIosDialogUtils.getInstance().getIosDialog(context, "是否删除该申请", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        //PostConstant.getInstance().DELETE_APPLY  PostConstant.getInstance().DEL_BORROWING
                        String postUrl = "";
                        //齐家借款删除
                        if (post == 1007) {
                            postUrl = PostConstant.getInstance().DEL_BORROWING;
                        } else {
                            postUrl = PostConstant.getInstance().DELETE_APPLY;
                        }
                        deleteOrder(context, postUrl, id, listApply.get(position).getApprovalTypeID()+"",new OnDeleteBankCallBack() {
                            @Override
                            public void onSuccess() {
                                listApply.remove(position);
                                finalApplyAdapter.notifyDataSetChanged();
                                ToastUtils.getMineToast(context.getString(R.string.delete_success));
                            }

                            @Override
                            public void onFail() {

                            }
                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });
    }

    //获取申请列表数据信息
    public void getMineZhApply(final SmartRefreshLayout xRefresh, final Context context, final String type, ListView lvRefresh, ApplyAdapter applyAdapter,
                               final List<ApproveCheckListBean> listApply, final LinearLayout llNoData) {
        applyAdapter = new ApplyAdapter(context, listApply, R.layout.item_apply_layout);
        lvRefresh.setAdapter(applyAdapter);
        lvRefresh.setDividerHeight(0);
        mLlNoData = llNoData;
        pageApply = 1;

        lvRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setReadMessage(context, listApply.get(i).getApprovalID() + "");

                String id = "";
                if (listApply.get(i).getBorrowID() != 0) {
                    id = listApply.get(i).getBorrowID() + "";
                }
                if (listApply.get(i).getApprovalID() != 0) {
                    id = listApply.get(i).getApprovalID() + "";
                }
                IntentUtils.getInstance().toActivity(context, BaseCheckDetailActivity.class, ApproveConstant.getInstance().ZH_TYPE, id, "" + listApply.get(i).getApprovalTypeID(), listApply.get(i).getUserName(), type, listApply.get(i).getApprovalPeopleType());


                //垫付款
//                if (listApply.get(i).getApprovalTypeID() == 1008) {
//                    IntentUtils.getInstance().toApproveCheck(context, PadPaymentActivity.class, listApply.get(i).getStatusID() + "",
//                            listApply.get(i).getApprovalID() + "," + listApply.get(i).getApprovalTypeID() + ","
//                                    + listApply.get(i).getSellUserID() + "," + listApply.get(i).getAdCounts(), Constant.APPROVE, type, listApply.get(i).getUserName());
//                } else if (listApply.get(i).getApprovalTypeID() == 1101) {
//                    IntentUtils.getInstance().toYwActivity(context, ApproveDetailActivity.class, listApply.get(i).getApprovalID() + "",
//                            ApproveConstant.getInstance().APPLY, listApply.get(i).getApprovalTypeID() + "", Constant.APPLY, type, "yw", listApply.get(i).getUserName());
//                } else if (listApply.get(i).getApprovalTypeID() == 1011 && listApply.get(i).getApprovalPeopleType() >= 1) {
//                    //运营人员 0  客服之前 1 客服  2客服之后
//                    if (listApply.get(i).getApprovalPeopleType() == 1) {
//                        IntentUtils.getInstance().toBtDetailActivity(context, BtDetailCheckActivity.class, listApply.get(i).getBorrowID() + "", listApply.get(i).getApprovalTypeID() + "", "check", listApply.get(i).getUserName());
//                        //运营之后的人员
//                    } else if (listApply.get(i).getApprovalPeopleType() > 1) {
//                        IntentUtils.getInstance().toBtDetailActivity(context, BtDetailCheckActivity.class, listApply.get(i).getBorrowID() + "", listApply.get(i).getApprovalTypeID() + "", "check", listApply.get(i).getUserName());
//                    }
//                } else if (listApply.get(i).getApprovalTypeID() == 1201) {
//                    IntentUtils.getInstance().toActivity(context, YwNewDetailActivity.class, listApply.get(i).getBorrowID() + "", "unCheck", listApply.get(i).getUserName(), listApply.get(i).getApprovalPeopleType() + "");
//                } else if (listApply.get(i).getApprovalTypeID() == 1301) {
//                    IntentUtils.getInstance().toActivity(context, DianFuFinishActivity.class, listApply.get(i).getBorrowID() + "", Constant.APPLY, listApply.get(i).getUserName(), listApply.get(i).getApprovalPeopleType() + "");
//                } else if (listApply.get(i).getApprovalTypeID() == 1401) {
//                    IntentUtils.getInstance().toActivity(context, InCompanyMoneyDetailActivity.class, listApply.get(i).getBorrowID() + "", Constant.APPLY, listApply.get(i).getApprovalOver() + "", listApply.get(i).getUserName());
//                } else {
//                    IntentUtils.getInstance().toYwActivity(context, ApproveDetailActivity.class, listApply.get(i).getApprovalID() + "",
//                            ApproveConstant.getInstance().APPLY, listApply.get(i).getApprovalTypeID() + "", Constant.APPLY, type, listApply.get(i).getUserName());
//                }
            }
        });

        //获取申请信息
        final ApplyAdapter finalApplyAdapter = applyAdapter;

        pageApply = 1;
        getMineZhData(context, type, new OnApplyCheckCallBack() {
            @Override
            public void onApplyCheckBean(List<ApproveCheckListBean> checkBean) {
                listApply.clear();
                listApply.addAll(checkBean);
                finalApplyAdapter.notifyDataSetChanged();
                if (listApply.size() == 0) {
                    llNoData.setVisibility(View.VISIBLE);
                } else {
                    llNoData.setVisibility(View.GONE);
                }
            }
        });

        xRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageApply = 1;
                getMineZhData(context, type, new OnApplyCheckCallBack() {
                    @Override
                    public void onApplyCheckBean(List<ApproveCheckListBean> checkBean) {
                        listApply.clear();
                        listApply.addAll(checkBean);
                        finalApplyAdapter.notifyDataSetChanged();
                        SmartUtils.finishSmart(xRefresh);

                        if (listApply.size() == 0) {
                            llNoData.setVisibility(View.VISIBLE);
                        } else {
                            llNoData.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        xRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageApply++;
                getMineZhData(context, type, new OnApplyCheckCallBack() {
                    @Override
                    public void onApplyCheckBean(List<ApproveCheckListBean> checkBean) {
                        listApply.addAll(checkBean);
                        finalApplyAdapter.notifyDataSetChanged();
                        SmartUtils.finishSmart(xRefresh);

                        if (listApply.size() == 0) {
                            llNoData.setVisibility(View.VISIBLE);
                        } else {
                            llNoData.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });


        //删除申请订单
        applyAdapter.setDeleteCallBack(new OnApplyDeleteCallBack() {
            @Override
            public void onApplyDelete(final String id, final int position, final int post) {
                CopyIosDialogUtils.getInstance().getIosDialog(context, context.getString(R.string.is_delete_order), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        //PostConstant.getInstance().DELETE_APPLY  PostConstant.getInstance().DEL_BORROWING
                        String postUrl = "";
                        //齐家借款删除
                        if (post == 1007) {
                            postUrl = PostConstant.getInstance().DEL_BORROWING;
                        } else {
                            postUrl = PostConstant.getInstance().DELETE_APPLY;
                        }

                        deleteOrder(context, postUrl, id,listApply.get(position).getApprovalTypeID()+"", new OnDeleteBankCallBack() {
                            @Override
                            public void onSuccess() {
                                listApply.remove(position);
                                finalApplyAdapter.notifyDataSetChanged();
                                ToastUtils.getMineToast(context.getString(R.string.delete_success));
                            }

                            @Override
                            public void onFail() {

                            }
                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });
    }

    //设置知会已读
    private void setReadMessage(final Context context, String id) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(context, UrlConstant.getInstance().URL
                + PostConstant.getInstance().NOTIFY_ALERADY
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().APPROVE_ID + "=" + id, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().APPROVE_AGREE, SharedValueConstant.getInstance().APPROVE_AGREE);
            }

            @Override
            public void onFail() {

            }
        });
    }

    //删除订单信息
    private void deleteOrder(Context context, String postApply, String id, String approveType, final OnDeleteBankCallBack callBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message),
                UrlConstant.getInstance().URL + postApply
                        + FieldConstant.getInstance().APPROVE_TYPE_ID + "=" + approveType + "&"
                        + FieldConstant.getInstance().ID + "=" + id, new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                        if (successBean.getSuccess() == 1) {
                            callBack.onSuccess();
                        } else {
                            callBack.onFail();
                            ToastUtils.getMineToast(successBean.getErrMsg());
                        }
                    }

                    @Override
                    public void onFail() {
                        callBack.onFail();
                    }
                });
    }
}
