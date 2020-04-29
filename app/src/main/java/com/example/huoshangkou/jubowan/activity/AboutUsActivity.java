package com.example.huoshangkou.jubowan.activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.CompanyQuaBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhoneUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.UpdateDialogUtils;
import com.example.huoshangkou.jubowan.utils.VersionUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：AboutUsActivity
 * 描述：关于我们界面
 * 创建时间：2017-02-08  08:46
 */

public class AboutUsActivity extends BaseActivity {
    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_version_num)
    TextView tvVersionNum;
    @Bind(R.id.tv_version_update)
    TextView tvUpdateIntro;
    @Bind(R.id.rl_help)
    RelativeLayout rlHelp;

    private String version;
    private String versionName;
    private int versionCode;

    private String url = "";
    private String helpUrl = "http://www.atjubo.com/H5page/OperationalManual/index.html";
//    private String helpUrl = "http://www.atjubo.com/H5page/OperationalManual/word.html?url=https://atjubo.oss-cn-hangzhou.aliyuncs.com/Atjubo/Document/%E8%B4%B8%E6%98%93%E7%AE%A1%E5%AE%B6%E5%AE%A2%E6%88%B7%E7%AB%AF%E6%93%8D%E4%BD%9C%E6%89%8B%E5%86%8C.docx";

    @Override
    public int initLayout() {
        return R.layout.activity_about_us;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        version = VersionUtils.getInstance().getVersonFromXml();

        //关于聚玻宝
        tvTitle.setText(R.string.my_about_jb);
        //版本名称
        versionName = version.substring(0, version.indexOf(","));
        //版本号设置
        tvVersionNum.setText("版本号：V" + versionName);
        //版本号
        versionCode = Integer.parseInt(version.substring(version.indexOf(",") + 1));

        //版本更新判断
        if (versionCode < VersionUtils.getInstance().getVersionNo(this)) {
            tvUpdateIntro.setText(R.string.need_update);
        } else if (versionCode == VersionUtils.getInstance().getVersionNo(this)) {
            tvUpdateIntro.setText(getString(R.string.is_newest));
        }

        url = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().RULE_URL, "");

        getQuaHelp();
    }

    @OnClick({R.id.ll_back, R.id.rl_version_init, R.id.rl_help, R.id.rl_suggest_back, R.id.rl_link_us, R.id.rl_about_us})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            //关于我们
            case R.id.rl_about_us:
                IntentUtils.getInstance().toWebActivity(getContext(), url, "关于我们");
                break;
            //版本更新
            case R.id.rl_version_init:
                if (versionCode < VersionUtils.getInstance().getVersionNo(this)) {
                    UpdateDialogUtils.updataDialogShow(this, VersionUtils.getInstance().getVersionIntro(this));
                } else if (versionCode == VersionUtils.getInstance().getVersionNo(this)) {
                    ToastUtils.getMineToast(getString(R.string.is_newest));
                }
                break;
            //意见反馈
            case R.id.rl_suggest_back:
                IntentUtils.getInstance().toActivity(this, SuggestBackActivity.class);
                break;
            //联系我们
            case R.id.rl_link_us:
                PhoneUtils.getInstance().callPhoe(getContext());
                break;
            //帮助中心
            case R.id.rl_help:
                IntentUtils.getInstance().toWebActivity(this, helpUrl, "帮助中心");
                break;
        }
    }

    //帮助中心权限
    public void getQuaHelp() {
        Map<String, String> map = new HashMap<>();
        map.put("userId", PersonConstant.getInstance().getUserId());
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().ABOUT_US_MANAGE + "GetUserJurisdictionInfo", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                //CompanyNature:用户公司性质  （-1 未知，1 聚玻网旗下公司，2客户公司）
                //UserJurisdiction：用户权限（-1 权限未知，0普通成员，1管理员，2超级管理员）

                CompanyQuaBean quaBean = JSON.parseObject(str, CompanyQuaBean.class);
                if(quaBean.getD().getData().getCompanyNature() ==1){
                    rlHelp.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1010:
                UpdateDialogUtils.downLoadApk(this);
                break;
        }
    }
}
