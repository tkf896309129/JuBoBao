package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.PjDetailBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.PjDetailsDialog;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.LocalImageHolderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：PjDetailsActivity
 * 描述：
 * 创建时间：2017-11-23  11:45
 */

public class PjDetailsActivity extends BaseActivity {
    @Bind(R.id.tool_banner)
    ConvenientBanner convenientBanner;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.iv_right)
    ImageView imgRight;

    //Banner图集合
    List<String> imgList;
    //类别id
    private String classId = "";
    //modeId
    private String modeId = "";


    @Override
    public int initLayout() {
        return R.layout.activity_tool_details;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("设备详情");

        imgRight.setImageResource(R.mipmap.buy_gwc_icon);

        classId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        modeId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);

        imgList = new ArrayList<>();


        getPjDetail(modeId);
    }

    @OnClick({R.id.ll_back, R.id.tv_add_car, R.id.tv_order_limit, R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_order_limit:
                PjDetailsDialog.getInstance().getPjDetailsDialog(getContext(), classId, modeId);
                break;
            case R.id.tv_add_car:
                PjDetailsDialog.getInstance().getPjDetailsDialog(getContext(), classId, modeId);
                break;
            case R.id.iv_right:
                IntentUtils.getInstance().toActivity(getContext(), PjShopCarActivity.class);
                break;
        }
    }

    //获取 配件详情
    public void getPjDetail(String modeId) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(getContext(),  UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_MODE_DETAIL + FieldConstant.getInstance().MODE_ID + "=" + modeId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                PjDetailBean detailBean = JSON.parseObject(json, PjDetailBean.class);
                tvPrice.setText("￥" + detailBean.getReObj().getPriceRange());
                tvName.setText(detailBean.getReObj().getModelTitle());
                tvContent.setText(detailBean.getReObj().getDescript());
                imgList.addAll(PhotoUtils.getInstance().getListImage(detailBean.getReObj().getPic()));
                convenientBanner.startTurning(3000);
                convenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, imgList)
                        .setPageIndicator(new int[]{R.mipmap.gray_dot, R.mipmap.white_dot})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
            }

            @Override
            public void onFail() {

            }
        });
    }
}


