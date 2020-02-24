package com.example.huoshangkou.jubowan.fragment.function;

import android.content.Context;
import android.content.Intent;

import com.example.huoshangkou.jubowan.activity.RepairPriceActivity;
import com.example.huoshangkou.jubowan.activity.function.RepairApproveFunction;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：RepairFunction
 * 描述：
 * 创建时间：2017-05-11  13:52
 */

public class RepairFunction {

    private static class RepairHelper {
        private static RepairFunction INSTANCE = new RepairFunction();
    }

    public static RepairFunction getInstance() {
        return RepairHelper.INSTANCE;
    }

    public void toPirceRepair(String orderId, Context context) {
        String urlRepair = UrlConstant.getInstance().URL + PostConstant.getInstance().SHOW_MAINTAIN_PRICE
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().ORDER_ID + "=" + orderId;

        Intent intent = new Intent(context, RepairPriceActivity.class);
        intent.putExtra(IntentUtils.getInstance().TYPE, urlRepair);
        intent.putExtra(IntentUtils.getInstance().VALUE, orderId);
        context.startActivity(intent);
    }

    public void toPirceRepair(String orderId, Context context, int position) {
        String urlRepair = UrlConstant.getInstance().URL + PostConstant.getInstance().SHOW_MAINTAIN_PRICE
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().ORDER_ID + "=" + orderId;

        Intent intent = new Intent(context, RepairPriceActivity.class);
        intent.putExtra(IntentUtils.getInstance().TYPE, urlRepair);
        intent.putExtra(IntentUtils.getInstance().VALUE, orderId);
        intent.putExtra(IntentUtils.getInstance().POSITION, position);
        context.startActivity(intent);
    }


}
