package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseApp;
import com.example.huoshangkou.jubowan.bean.ProvinceBean;
import com.example.huoshangkou.jubowan.inter.ChooseDialogCallBack;
import com.example.huoshangkou.jubowan.inter.ChooseDialogPositionCallBack;
import com.example.huoshangkou.jubowan.inter.PickPositonCallBack;

import java.util.ArrayList;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：PickDialogUtils
 * 描述：
 * 创建时间：2017-03-08  08:36
 */
public class PickDialogUtils {

    //类型选择器
    OptionsPickerView pvOptions;

    private static class PickDialogHelper {
        private static PickDialogUtils INSTANCE = new PickDialogUtils();
    }

    public static PickDialogUtils getInstance() {
        return PickDialogHelper.INSTANCE;
    }

    //弹出框
    public void getChooseDialog(Context context, String title, final ArrayList<String> chooseList, final PickPositonCallBack callBack) {

        pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                callBack.onClickSuccess(chooseList.get(options1), options1);
            }
        })
                .setCancelText(context.getString(R.string.cancel_btn))
                .setSubmitText(context.getString(R.string.sure_btn))
                .setTitleSize(15)
                .setSubCalSize(15)
                .setContentTextSize(18)
                .setLineSpacingMultiplier(1.6f)
                .setCancelColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setSubmitColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setDividerColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTextColorCenter(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTitleBgColor(context.getResources().getColor(R.color.white))
                .setTitleColor(context.getResources().getColor(R.color.address_black_key))
                .setBgColor(context.getResources().getColor(R.color.white))
                .setTitleText(title)
                .setOutSideCancelable(true)// default is true
                .build();
        pvOptions.setPicker(chooseList);
        pvOptions.show();
    }


    public void getChooseDialog(Context context, String title,
                                final ArrayList<String> chooseList, final ChooseDialogCallBack callBack) {

        pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (chooseList.size() == 0) {
                    return;
                }
                callBack.onClickSuccess(chooseList.get(options1));
            }
        })
                .setCancelText(context.getString(R.string.cancel_btn))
                .setSubmitText(context.getString(R.string.sure_btn))
                .setTitleSize(15)
                .setSubCalSize(15)
                .setContentTextSize(18)
                .setLineSpacingMultiplier(1.6f)
                .setCancelColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setSubmitColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setDividerColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTextColorCenter(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTitleBgColor(context.getResources().getColor(R.color.white))
                .setTitleColor(context.getResources().getColor(R.color.address_black_key))
                .setBgColor(context.getResources().getColor(R.color.white))
                .setTitleText(title)
                .setOutSideCancelable(true)// default is true
                .build();

        pvOptions.setPicker(chooseList);
        pvOptions.show();
    }

    public void getChoosePositionDialog(Context context, String title,
                                        final ArrayList<String> chooseList, final ChooseDialogPositionCallBack callBack) {
        pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                callBack.onGetMessagePosition(chooseList.get(options1), options1);
            }
        })
                .setCancelText(context.getString(R.string.cancel_btn))
                .setSubmitText(context.getString(R.string.sure_btn))
                .setTitleSize(15)
                .setSubCalSize(15)
                .setContentTextSize(18)
                .setLineSpacingMultiplier(1.6f)
                .setCancelColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setSubmitColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setDividerColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTextColorCenter(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTitleBgColor(context.getResources().getColor(R.color.white))
                .setTitleColor(context.getResources().getColor(R.color.address_black_key))
                .setBgColor(context.getResources().getColor(R.color.white))
                .setTitleText(title)
                .setOutSideCancelable(true)// default is true
                .build();

        pvOptions.setPicker(chooseList);
        pvOptions.show();
    }

    //弹出框 两级
    public void getChooseDialog(Context context, String title,
                                final ArrayList<String> chooseList_1, final ArrayList<ArrayList<String>> chooseList_2,
                                final ChooseDialogPositionCallBack callBack) {
        if (chooseList_1.size() == 0 || chooseList_2.size() == 0) {
            ToastUtils.getMineToast("数据获取失败");
            return;
        }
        pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (chooseList_1.size() < (options1 + 1)) {
                    return;
                }
                LogUtils.e(chooseList_1.get(options1) + "---" + chooseList_2.get(options1).get(options2));
                if (chooseList_2.get(options1).size() != 0) {
                    callBack.onGetMessagePosition(chooseList_1.get(options1) + "," + chooseList_2.get(options1).get(options2), options1);
                } else {
                    callBack.onGetMessagePosition(chooseList_1.get(options1) + ",", options1);
                }
            }
        })
                .setCancelText(context.getString(R.string.cancel_btn))
                .setSubmitText(context.getString(R.string.sure_btn))
                .setTitleSize(15)
                .setSubCalSize(15)
                .setContentTextSize(18)
                .setLineSpacingMultiplier(1.6f)
                .setCancelColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setSubmitColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setDividerColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTextColorCenter(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTitleBgColor(context.getResources().getColor(R.color.white))
                .setTitleColor(context.getResources().getColor(R.color.address_black_key))
                .setBgColor(context.getResources().getColor(R.color.white))
                .setTitleText(title)
                .setOutSideCancelable(true)// default is true
                .build();

        pvOptions.setPicker(chooseList_1, chooseList_2);
        pvOptions.show();
    }

    //弹出框 三级
    public void getThreePick(Context context, String title,
                             final ArrayList<String> options1Items, final ArrayList<ArrayList<String>> options2Items,
                             final ArrayList<ArrayList<ArrayList<String>>> options3Items,
                             final ChooseDialogCallBack callBack) {

        pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                //返回的分别是三个级别的选中位置  判断省 和 城市 是否重名
                String area = "";
                if (options1Items.get(options1).equals(options2Items.get(options1).get(options2))) {
                    area = options1Items.get(options1) + " "
                            + options3Items.get(options1).get(options2).get(options3) + "  ";
                } else {
                    area = options1Items.get(options1) + " "
                            + options2Items.get(options1).get(options2) + " "
                            + options3Items.get(options1).get(options2).get(options3) + "  ";
                }
                //获取所有的城市名称
                callBack.onClickSuccess(area);
            }
        })
                .setCancelText(context.getString(R.string.cancel_btn))
                .setSubmitText(context.getString(R.string.sure_btn))
                .setTitleSize(15)
                .setSubCalSize(15)
                .setContentTextSize(18)
                .setCancelColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setSubmitColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setDividerColor(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTextColorCenter(context.getResources().getColor(R.color.main_tab_blue_white))
                .setTitleBgColor(context.getResources().getColor(R.color.white))
                .setTitleColor(context.getResources().getColor(R.color.address_black_key))
                .setBgColor(context.getResources().getColor(R.color.white))
                .setLineSpacingMultiplier(1.6f)
                .setTitleText(title)
                .setOutSideCancelable(true)// default is true
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);

        pvOptions.show();
    }
}
