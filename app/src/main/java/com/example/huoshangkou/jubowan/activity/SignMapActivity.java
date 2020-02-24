package com.example.huoshangkou.jubowan.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：SignMapActivity
 * 描述：
 * 创建时间：2017-04-05  14:25
 */

public class SignMapActivity extends AppCompatActivity {
    @Bind(R.id.map_view)
    MapView mMapView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    BaiduMap aMap;

    private double lat;
    private double lon;

    private String address;
    private String time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_map);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.main_tab_blue));
        ButterKnife.bind(this);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        aMap = mMapView.getMap();
        tvTitle.setText("上报位置");

        String latLon = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        String[] split = latLon.split("、");
        if (split == null || split.length != 4) {
            return;
        }
        lat = Double.parseDouble(split[0]);
        lon = Double.parseDouble(split[1]);
        address = split[2];
        time = split[3];

        changeMapPosition(lat, lon);
    }

    //改变地图定位图层位置
    private void changeMapPosition(double lats, double lon) {
        LatLng ll = new LatLng(lats, lon);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ll);
        markerOptions.visible(true);
        markerOptions.title(address + "\n" + time);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.dingwei_icon));
        markerOptions.icon(bitmapDescriptor);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(18.0f);
        aMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        aMap.addOverlay(markerOptions);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
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
