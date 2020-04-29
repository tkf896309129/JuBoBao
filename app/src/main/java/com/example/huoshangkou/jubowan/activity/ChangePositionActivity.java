package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;

import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiAddrInfo;

import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiFilter;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ChangePositionAdapter;
import com.example.huoshangkou.jubowan.bean.PositionBean;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ChangePositionActivity
 * 描述：
 * 创建时间：2017-03-29  10:39
 */

public class ChangePositionActivity extends AppCompatActivity implements OnGetPoiSearchResultListener {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.map_view)
    MapView mMapView;
    @Bind(R.id.tv_get_position)
    ImageView tvGetPosition;
    @Bind(R.id.lv_position)
    ListView lvPosition;
    @Bind(R.id.tv_right)
    TextView tvRight;

    private double latitude;
    private double longitude;
    private String address = "";

    List<PositionBean> positionBeenList;
    private ChangePositionAdapter changePositionAdapter;
    private PositionBean positionBean;

    private BaiduMap mBaiduMap;
    private PoiSearch mPoiSearch;
    private String province = "";
    private String city = "";
    private String area = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_position);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.main_tab_blue));
        ButterKnife.bind(this);
        initMap();
        tvTitle.setText("地点微调");
        tvRight.setText("确定");
        // POI初始化搜索模块，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        String[] split = getIntent().getStringExtra(IntentUtils.getInstance().TYPE).split(",");

        province = getIntent().getStringExtra(IntentUtils.getInstance().PROVINCE);
        city = getIntent().getStringExtra(IntentUtils.getInstance().CITY);
        area = getIntent().getStringExtra(IntentUtils.getInstance().AREA);

        UiSettings settings=mBaiduMap.getUiSettings();
        settings.setAllGesturesEnabled(false);   //关闭一切手势操作
        settings.setOverlookingGesturesEnabled(false);//屏蔽双指下拉时变成3D地图
        settings.setZoomGesturesEnabled(false);//获取是否允许缩放手势返回:是否允许缩放手势
        // 不显示地图上比例尺
        mMapView.showScaleControl(false);

        // 不显示地图缩放控件（按钮控制栏）
        mMapView.showZoomControls(false);

        //绑定适配器数据
        positionBeenList = new ArrayList<>();
        changePositionAdapter = new ChangePositionAdapter(this, positionBeenList, R.layout.item_change_position);
        lvPosition.setAdapter(changePositionAdapter);
        lvPosition.setDividerHeight(0);

        if (split.length != 3) {
            return;
        }

        //经纬度
        latitude = Double.parseDouble(split[0]);
        longitude = Double.parseDouble(split[1]);
        address = split[2];
//        //移动地图
        changeMapPosition(latitude, longitude);
//        changeMap(latitude, longitude, address);

        lvPosition.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                changePositionAdapter.setItemClickPosition(i);
                setUnCheck(i);
                changePositionAdapter.notifyDataSetChanged();
                changeMapPosition(positionBeenList.get(i).getLat(), positionBeenList.get(i).getLon());
            }
        });
        setSearch();
    }

    //改变地图定位图层位置
    private void changeMapPosition(double lats, double lon) {
        mBaiduMap.clear();
        LatLng ll = new LatLng(lats, lon);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ll);
        markerOptions.visible(true);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.dizhi_icon_person));
        markerOptions.icon(bitmapDescriptor);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(18.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        mBaiduMap.addOverlay(markerOptions);

    }

    //设置为未选中
    private void setUnCheck(int position) {
        int size = positionBeenList.size();
        for (int i = 0; i < size; i++) {
            if (i != position) {
                positionBeenList.get(i).setCheck(false);
            } else {
                positionBeenList.get(i).setCheck(true);
            }
        }
    }

    //设置查询条件
    private void setSearch() {
        LogUtils.e("搜索开始");
        LogUtils.e(latitude + "   " + longitude);
        mPoiSearch.searchNearby(new PoiNearbySearchOption()
                .location(new LatLng(latitude, longitude))
                .radius(100)
                .keyword("公司")
                .pageCapacity(20)
                .pageNum(0));
    }


    //点击事件
    @OnClick({R.id.tv_right, R.id.ll_back, R.id.tv_get_position})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                setSearch();
                break;
            case R.id.tv_right:
                int num = positionBeenList.size();
                for (int i = 0; i < num; i++) {
                    if (positionBeenList.get(i).isCheck()) {
                        positionBean = positionBeenList.get(i);
                    }
                }
                if (positionBean == null) {
                    ToastUtils.getMineToast("请选择地址");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(IntentUtils.getInstance().TYPE, positionBean);
                setResult(1, intent);
                this.finish();
                break;
            case R.id.tv_get_position:
                //移动地图
                if (positionBeenList.size() == 0) {
                    return;
                }
                changePositionAdapter.setItemClickPosition(0);
                setUnCheck(0);
                changePositionAdapter.notifyDataSetChanged();

                changeMapPosition(latitude, longitude);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {

        }
    }

    private void initMap() {
        //获取地图控件引用
        mBaiduMap = mMapView.getMap();
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMyLocationEnabled(true);

        //默认显示普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //开启交通图
        //mBaiduMap.setTrafficEnabled(true);
        //开启热力图
        //mBaiduMap.setBaiduHeatMapEnabled(true);
        // 开启定位图层
//        mBaiduMap.setMyLocationEnabled(true);
//        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        //配置定位SDK参数
//        initLocation();
//        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        //开启定位
//        mLocationClient.start();
        //图片点击事件，回到定位点
//        mLocationClient.requestLocation();
    }


    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        LogUtils.e(poiResult.error + "  error");
        if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
            List<PoiInfo> pois = poiResult.getAllPoi();
            List<PoiAddrInfo> allAddr = poiResult.getAllAddr();
//                int size = pois.size();
            if (poiResult == null) {
                LogUtils.e("poiResult null");
            }
            if (pois == null) {
                LogUtils.e("pois null");
            }
            if (allAddr == null) {
                LogUtils.e("allAddr null");
            }
            positionBeenList.clear();
            int size = pois.size();
            LogUtils.e("搜索结果");
            for (int j = 0; j < size; j++) {
                PositionBean positionBean = new PositionBean();
                positionBean.setName(pois.get(j).getName());
                if (!pois.get(j).getAddress().contains("省") && !pois.get(j).getAddress().contains("市") && !pois.get(j).getAddress().contains("区")) {
                    positionBean.setAddress(province + city + area + pois.get(j).getAddress());
                } else if (!pois.get(j).getAddress().contains("省") && !pois.get(j).getAddress().contains("市")) {
                    positionBean.setAddress(province + city + pois.get(j).getAddress());
                } else if (!pois.get(j).getAddress().contains("省")) {
                    positionBean.setAddress(province + pois.get(j).getAddress());
                } else {
                    positionBean.setAddress(pois.get(j).getAddress());
                }
                positionBean.setLat(pois.get(j).getLocation().latitude);
                positionBean.setLon(pois.get(j).getLocation().longitude);
                positionBeenList.add(positionBean);
            }
//            changePositionAdapter.setItemClickPosition(0);
//            setUnCheck(0);
            changePositionAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }


    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
        mPoiSearch.destroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        ButterKnife.unbind(this);
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
    }
}
