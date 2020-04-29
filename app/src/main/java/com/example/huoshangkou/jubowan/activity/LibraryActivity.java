package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.LibraryAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.JuSchoolBean;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：LibraryActivity
 * 描述：
 * 创建时间：2019-03-27  14:34
 */

public class LibraryActivity extends BaseActivity {
    //数据集合
    List<JuSchoolBean.DBean.ResultBean.BookTypeBean> mineList = new ArrayList<>();
    //个人中心类适配器
    LibraryAdapter typeAdapter;

    @Bind(R.id.lv_library)
    ListView lvLibrary;
    @Bind(R.id.iv_library)
    ImageView imgLibrary;

    @Override
    public int initLayout() {
        return R.layout.activity_library;
    }

    @Override
    protected boolean setIsFull() {
        return true;
    }

    @Override
    public void initData() {
        //适配器初始化
        typeAdapter = new LibraryAdapter(this, mineList, R.layout.item_mine_type);
        lvLibrary.setAdapter(typeAdapter);
        lvLibrary.setDividerHeight(0);

        lvLibrary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentUtils.getInstance().toActivity(getContext(), LibraryListActivity.class, mineList.get(i).getID() + "", mineList.get(i).getTypeName());
            }
        });
        getData();
    }


    public void getData() {
        //
        //http://192.168.10.120/webapi/ServiceInterface/JuboBao/JbCollege.asmx/GetBookType
        //UrlConstant.getInstance().JU_SCHOOL_URL + "GetBookType"
        OkhttpUtil.getInstance().basePostCall(this, UrlConstant.getInstance().JU_SCHOOL_URL + "GetBookType", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                JuSchoolBean schoolBean = JSON.parseObject(str, JuSchoolBean.class);
                if (schoolBean.getD().getErrorCode() == 0) {
                    mineList.addAll(schoolBean.getD().getResult().getBookType());
//                    GlideUtils.getInstance().displayLongImage(schoolBean.getD().getResult().getImg(), LibraryActivity.this, imgLibrary);
//                    String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554712023625&di=46121c87f1823867b69e08a61754bdbd&imgtype=0&src=http%3A%2F%2Fimg4q.duitang.com%2Fuploads%2Fitem%2F201205%2F31%2F20120531172127_VCWAz.thumb.700_0.jpeg";
//                    GlideUtils.getInstance().displayFitImage(url, LibraryActivity.this, imgLibrary);
                    GlideUtils.getInstance().displayFitImage(schoolBean.getD().getResult().getImg(), LibraryActivity.this, imgLibrary);
                }
                typeAdapter.notifyDataSetChanged();
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
