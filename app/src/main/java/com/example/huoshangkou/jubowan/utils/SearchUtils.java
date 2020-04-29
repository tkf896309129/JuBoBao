package com.example.huoshangkou.jubowan.utils;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.CheckSearchAdapter;
import com.example.huoshangkou.jubowan.adapter.CompanySearchAdapter;
import com.example.huoshangkou.jubowan.adapter.SyYuanAdapter;
import com.example.huoshangkou.jubowan.bean.SelectManBean;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.inter.ChooseCheckMan;
import com.example.huoshangkou.jubowan.inter.ChooseCheckTwoMan;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.inter.OnCompanyCallBack;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.OnStringPositionCallBack;
import com.example.huoshangkou.jubowan.widget.ContactItemInterface;

import org.simple.eventbus.EventBus;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：SearchUtils
 * 描述：搜索工具类
 * 创建时间：2016-12-13  13:18
 */

public class SearchUtils {
    private static CheckSearchAdapter searchAdapter;

    private static class SerachHelper {
        private static final SearchUtils INSTANCE = new SearchUtils();
    }

    public static SearchUtils getInstance() {
        return SerachHelper.INSTANCE;
    }

    String pic = "";

    public void searchDialog(final Context context, final List<ContactItemInterface> searchList,
                             final List<SelectManBean> selectList, final String isLinkCard,
                             final String title, final boolean isAddGroup, final OnStringPositionCallBack positionClick) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_dialog, null);
        final PopupWindow popupSearchWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);

        //让Popupwindow上面的控件获取焦点
        popupSearchWindow.setFocusable(true);
        //防止其他的控件没有焦点
        popupSearchWindow.setBackgroundDrawable(new BitmapDrawable());
        popupSearchWindow.setOutsideTouchable(true);
        //在底部显示Popupwindow
        popupSearchWindow.showAtLocation(view, Gravity.CENTER, 0, 10);

        final EditText editText = (EditText) view.findViewById(R.id.et_search);
        ListView listView = (ListView) view.findViewById(R.id.lv_search);
        final LinearLayout llDialogSearch = (LinearLayout) view.findViewById(R.id.ll_dialog_search);
        LinearLayout llCancel = (LinearLayout) view.findViewById(R.id.ll_back);

        searchAdapter = new CheckSearchAdapter(context, searchList, isLinkCard, R.layout.item_search_check);
        listView.setAdapter(searchAdapter);
        listView.setDividerHeight(0);

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                KeyBoardUtils.closeKeybord(editText, context);
                if (searchList.get(position).getDisplayInfo().equals("暂无搜索结果")) {
                    ToastUtils.getMineToast("暂无搜索结果");
                    return;
                }
                if (isAddGroup) {
                    if (positionClick != null) {
                        positionClick.onClick(searchList.get(position).getId(), position);
                    }
                    popupSearchWindow.dismiss();
                    return;
                }


                if (StringUtils.isNoEmpty(isLinkCard)) {
                    CopyIosDialogUtils.getInstance().getIosDialog(context, "是否拨打：" + searchList.get(position).getPhone(), new CopyIosDialogUtils.iosDialogClick() {
                        @Override
                        public void confimBtn() {
                            PhoneUtils.getInstance().callDialog(searchList.get(position).getPhone(), context);
                        }

                        @Override
                        public void cancelBtn() {

                        }
                    });
                    return;
                }
                final ContactItemInterface bean = searchList.get(position);

                if (bean.getNowPattern() == 1 && title.equals("审批人")) {
                    DialogUtils.getInstance().chooseCheckMan(context, "温馨提示", bean.getDisplayInfo() + "当前在飞行中,可能无法及时审批您的单子,建议您重新选择" + title, "选为抄送人后继续选择其他审批人", "执意选择该审批人", new ChooseCheckTwoMan() {
                        @Override
                        public void onCheck() {
                            EventBus.getDefault().post(bean.getId() + "," + bean.getDisplayInfo() + "," + bean.getHeadImagePic(), "checkManId");
                            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().memberList);
                            String pic = "";
                            if (StringUtils.isNoEmpty(bean.getHeadImagePic())) {
                                pic = bean.getHeadImagePic();
                            } else {
                                pic = "noPic";
                            }
                            EventBus.getDefault().post(bean.getId() + "," + bean.getDisplayInfo() + "," + pic, "checkManId");
                        }

                        @Override
                        public void onCancel() {
//                            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().memberList);
//                            csMan = bean.getID() + "," + bean.getLinkMan() + "," + pic + ",csId";
                            SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().METING_CS_MAN, bean.getId() + "," + bean.getDisplayInfo() + "," + bean.getHeadImagePic() + ",csId");
                            ToastUtils.getMineToast("选为抄送人成功,请继续选择其他审批人");
                        }
                    });
                    return;
                }
                if (bean.getNowPattern() == 2 && title.equals("审批人")) {
                    DialogUtils.getInstance().chooseCheckMan(context, "温馨提示", bean.getDisplayInfo() + "当前在会议中,可能无法及时审批您的单子,建议您重新选择" + title, "选为抄送人后继续选择其他审批人", "执意选择该审批人", new ChooseCheckTwoMan() {
                        @Override
                        public void onCheck() {
                            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().memberList);
                            String pic = "";
                            if (StringUtils.isNoEmpty(bean.getHeadImagePic())) {
                                pic = bean.getHeadImagePic();
                            } else {
                                pic = "noPic";
                            }
                            EventBus.getDefault().post(bean.getId() + "," + bean.getDisplayInfo() + "," + pic, "checkManId");
//                            EventBus.getDefault().post(bean.getId() + "," + bean.getDisplayInfo() + "," + bean.getHeadImagePic(), "checkManId");
                        }

                        @Override
                        public void onCancel() {
//                            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().memberList);
//                            csMan = bean.getID() + "," + bean.getLinkMan() + "," + pic + ",csId";
                            SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().METING_CS_MAN, bean.getId() + "," + bean.getDisplayInfo() + "," + bean.getHeadImagePic() + ",csId");
                            ToastUtils.getMineToast("选为抄送人成功,请继续选择其他审批人");
//                            EventBus.getDefault().post(bean.getId() + "," + bean.getDisplayInfo() + "," + bean.getHeadImagePic() + ",csId", "checkManIdMetting");
//                            ToastUtils.getMineToast("选为抄送人成功,请继续选择其他审批人");
                        }
                    });
                    return;
                }


                if (StringUtils.isNoEmpty(searchList.get(position).getHeadImagePic())) {
                    pic = searchList.get(position).getHeadImagePic();
                } else {
                    pic = "noPic";
                }


                DialogUtils.getInstance().chooseCheckMan(context, "温馨提示", "是否选择 (" + searchList.get(position).getDisplayInfo() + ") 作为" + title, new ChooseCheckMan() {
                    @Override
                    public void onCheck() {
                        EventBus.getDefault().post(searchList.get(position).getId() + "," + searchList.get(position).getDisplayInfo() + "," + pic, "checkManId");
                    }
                });
            }
        });

        llCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupSearchWindow.dismiss();
            }
        });

        //改变输入内容
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String search = s.toString().trim();
                searchList.clear();
                if (StringUtils.isNoEmpty(search)) {
                    llDialogSearch.setVisibility(View.VISIBLE);
                } else {
                    llDialogSearch.setVisibility(View.INVISIBLE);
                }
                int num = selectList.size();

                if (StringUtils.isNoEmpty(search) && selectList.size() == 0) {
                    searchList.add(new SelectManBean("暂无搜索结果", "", ""));
                    searchAdapter.notifyDataSetChanged();
                    return;
                }
                //1：上班  2：休息  3：请假 4：出差 7:下班 8：旷工 9 值班
                for (int i = 0; i < num; i++) {
                    int state = 0;
                    switch (search) {
                        case "上班":
                            state = 1;
                            break;
                        case "休息":
                            state = 2;
                            break;
                        case "请假":
                            state = 3;
                            break;
                        case "出差":
                            state = 4;
                            break;
                        case "下班":
                            state = 7;
                            break;
                        case "旷工":
                            state = 8;
                            break;
                        case "值班":
                            state = 9;
                            break;
                    }
                    if (selectList.get(i).getDisplayInfo().indexOf(search) >= 0
                            || selectList.get(i).getPhone().indexOf(search) >= 0
                            || selectList.get(i).getNowState() == state) {
                        searchList.add(selectList.get(i));
                    }
                }
                if (StringUtils.isNoEmpty(search) && searchList.size() == 0) {
                    searchList.add(new SelectManBean("暂无搜索结果", "", ""));
                }
                searchAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    /**
     * 判断集合中是否已经有了该数据
     */
    public static boolean isHaveData(List<ContactItemInterface> searchList, String data) {
        int num = searchList.size();
        for (int i = 0; i < num; i++) {
            if (data.equals(searchList.get(i).getDisplayInfo())) {
                return true;
            }
        }
        return false;
    }

    CompanySearchAdapter yuanAdapter;

    //收款单位
    public void companyDialog(final Context context, String title, final List<ContactItemInterface> searchList,
                              final List<SelectManBean> selectList, final OnCompanyCallBack successCallBack) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_search_dialog, null);
        final PopupWindow popupSearchWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);

        //让Popupwindow上面的控件获取焦点
        popupSearchWindow.setFocusable(true);
        //防止其他的控件没有焦点
        popupSearchWindow.setBackgroundDrawable(new BitmapDrawable());
        popupSearchWindow.setOutsideTouchable(true);
        //在底部显示Popupwindow
        popupSearchWindow.showAtLocation(view, Gravity.CENTER, 0, 10);

        final EditText editText = (EditText) view.findViewById(R.id.et_search);
        ListView listView = (ListView) view.findViewById(R.id.lv_search);
        final LinearLayout llDialogSearch = (LinearLayout) view.findViewById(R.id.ll_dialog_search);
        LinearLayout llCancel = (LinearLayout) view.findViewById(R.id.ll_back);

        yuanAdapter = new CompanySearchAdapter(context, title, searchList, R.layout.city_item);
        listView.setAdapter(yuanAdapter);

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (searchList.get(position).getDisplayInfo().equals("暂无搜索结果")) {
                    ToastUtils.getMineToast("暂无搜索结果");
                    return;
                }
                KeyBoardUtils.closeKeybord(editText, context);
                successCallBack.onSuccess(searchList.get(position));
            }
        });

        llCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupSearchWindow.dismiss();
            }
        });

        //改变输入内容
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String search = s.toString().trim();
                searchList.clear();
                if (StringUtils.isNoEmpty(search)) {
                    llDialogSearch.setVisibility(View.VISIBLE);
                } else {
                    llDialogSearch.setVisibility(View.INVISIBLE);
                }
                int num = selectList.size();

                if (StringUtils.isNoEmpty(search) && selectList.size() == 0) {
                    searchList.add(new SelectManBean("暂无搜索结果", "", ""));
                    searchAdapter.notifyDataSetChanged();
                    return;
                }

                for (int i = 0; i < num; i++) {
//                    String[] strings = selectList.get(i).getDisplayInfo().split("");
//                    String[] string2 = search.split("");
//                    int num2 = strings.length;
//                    int num3 = string2.length;
//                    for (int m = 0; m < num3; m++) {
                    //判断是否已经有相同的字
//                        if (m > 1 && m < num3 && string2[m].equals(string2[m - 1])) {
//                            break;
//                        }
//                        for (int j = 0; j < num2; j++) {
//                            if (StringUtils.isNoEmpty(string2[m]) && strings[j].equals(string2[m])) {
//                                if (!isHaveData(searchList, selectList.get(i).getDisplayInfo())) {
//                                    searchList.add(selectList.get(i));
//                                }
//                                break;
//                            }
//                        }

//                    }
                    if (selectList.get(i).getDisplayInfo().indexOf(search) >= 0) {
                        searchList.add(selectList.get(i));
                    }
                }
                if (StringUtils.isNoEmpty(search) && searchList.size() == 0) {
                    searchList.add(new SelectManBean("暂无搜索结果", "", ""));
                }
                yuanAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
