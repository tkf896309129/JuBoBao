package com.example.huoshangkou.jubowan.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//我感觉
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.constant.EventBusConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.simple.eventbus.EventBus;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.wxapi
 * 类名：WXPayEntryActivity
 * 描述：
 * 创建时间：2017-07-17  09:44
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    private IWXAPI api;
    private static final String APP_ID = "your app id";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        api = WXAPIFactory.createWXAPI(this, APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    /**
     * 得到支付结果回调
     */
    @Override
    public void onResp(BaseResp resp) {
        LogUtils.i("onPayFinish, errCode = " + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int code = resp.errCode;
            switch (code) {
                case 0:
                    CopyIosDialogUtils.getInstance().getIosDialog(WXPayEntryActivity.this, "支付成功", new CopyIosDialogUtils.iosDialogClick() {
                        @Override
                        public void confimBtn() {
                            EventBus.getDefault().post("initOrder", "initOrder");
                            WXPayEntryActivity.this.finish();
                            EventBus.getDefault().post(EventBusConstant.getInstance().WX_PAY, EventBusConstant.getInstance().WX_PAY);

                        }//

                        @Override
                        public void cancelBtn() {

                        }
                    });
                    break;//
                case -1:
                    CopyIosDialogUtils.getInstance().getIosDialog(WXPayEntryActivity.this, "支付失败", new CopyIosDialogUtils.iosDialogClick() {
                        @Override
                        public void confimBtn() {

                        }

                        @Override
                        public void cancelBtn() {

                        }
                    });
                    finish();
                    break;
                case -2:
                    ToastUtils.getMineToast("支付取消");
                    finish();
                    break;
                default:
                    ToastUtils.getMineToast("支付失败");
                    setResult(RESULT_OK);
                    finish();
                    break;
            }
        }
    }
}
