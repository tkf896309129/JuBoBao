package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.RedPacketAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.RedBean;
import com.example.huoshangkou.jubowan.bean.RedListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：RedpacketDetailActivity
 * 描述：
 * 创建时间：2017-10-17  09:09
 */

public class RedpacketDetailActivity extends BaseActivity {

    @Bind(R.id.lv_red_packet)
    ListView listView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    List<RedListBean> list;
    private String times = "";

    private String price;
    RedPacketAdapter redPacketAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_redpacket_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        list = new ArrayList<>();

        tvTitle.setText("红包详情");

        redPacketAdapter = new RedPacketAdapter(getContext(), list, R.layout.item_red_packet);
        listView.setAdapter(redPacketAdapter);
        listView.setDividerHeight(0);

        price = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        times = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);

        View view = getLayoutInflater().inflate(R.layout.item_redpacket_top, null);
        TextView tvPrice = (TextView) view.findViewById(R.id.tv_price);
        ImageView imgPic = (ImageView) view.findViewById(R.id.iv_pic);
        GlideUtils.getInstance().displayCricleImage(getContext(), R.mipmap.new_icon, imgPic);
        tvPrice.setText(price);
        listView.addHeaderView(view);

        getRedPacket();

    }

    //抢红包列表接口
    public void getRedPacket() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().RED_PACKET_LIST+ FieldConstant.getInstance().TIMES+"="+times, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                RedBean redBean = JSON.parseObject(json, RedBean.class);
                list.addAll(redBean.getReList());
                redPacketAdapter.notifyDataSetChanged();
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
