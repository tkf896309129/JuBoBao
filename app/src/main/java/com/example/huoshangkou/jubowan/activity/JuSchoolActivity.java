package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.JuSchoolAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.JuSchoolBean;
import com.example.huoshangkou.jubowan.bean.JuSchoolHomeBean;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.OkhttuCacheUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：JuSchoolActivity
 * 描述：
 * 创建时间：2019-03-26  13:23
 */

public class JuSchoolActivity extends BaseActivity {
    @Bind(R.id.lv_ju_school)
    ListView lvJuSchool;
    @Bind(R.id.iv_school_pic)
    ImageView imgSchoolPic;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_eng)
    TextView tvEng;

    JuSchoolAdapter schoolAdapter;
    List<JuSchoolHomeBean.DBean.ResultBean.JuboSchoolPagesBean> list = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_ju_school;
    }

    @Override
    protected boolean setIsFull() {
        return true;
    }

    @Override
    public void initData() {
        schoolAdapter = new JuSchoolAdapter(this, list, R.layout.item_mine_type);
        lvJuSchool.setAdapter(schoolAdapter);
        lvJuSchool.setDividerHeight(0);
        getData();

        lvJuSchool.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (list.get(i).getName().equals("数字图书馆")) {
                    IntentUtils.getInstance().toActivity(getContext(), LibraryActivity.class);
                    return;
                }
                IntentUtils.getInstance().toWebActivity(JuSchoolActivity.this, list.get(i).getLink(), list.get(i).getName());
            }
        });
    }

    @OnClick({R.id.rl_school_intro, R.id.rl_link_us, R.id.ll_back, R.id.rl_library})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_school_intro:
                IntentUtils.getInstance().toActivity(this, SchoolIntroActivity.class);
                break;
            case R.id.rl_link_us:
                IntentUtils.getInstance().toActivity(this, SchoolLinkUsActivity.class);
                break;
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.rl_library:
                IntentUtils.getInstance().toActivity(this, LibraryActivity.class);
                break;
        }
    }

    public void getData() {
        //http://192.168.10.120/webapi/ServiceInterface/JuboBao/JbCollege.asmx/GetBookType
        //UrlConstant.getInstance().JU_SCHOOL_URL + "GetBookType"
        OkhttpUtil.getInstance().basePostCall(this, UrlConstant.getInstance().JU_SCHOOL_URL + "GetNavList", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                JuSchoolHomeBean homeBean = JSON.parseObject(str, JuSchoolHomeBean.class);
                list.addAll(homeBean.getD().getResult().getJuboSchoolPages());
                schoolAdapter.notifyDataSetChanged();
                tvName.setText(homeBean.getD().getResult().getCNTxt());
                tvEng.setText(homeBean.getD().getResult().getENTxt());
                GlideUtils.getInstance().displayFitImage(homeBean.getD().getResult().getPics().get(0), JuSchoolActivity.this, imgSchoolPic);
            }

            @Override
            public void onFail() {

            }
        });
    }
}
