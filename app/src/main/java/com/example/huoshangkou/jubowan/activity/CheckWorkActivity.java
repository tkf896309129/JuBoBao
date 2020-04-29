package com.example.huoshangkou.jubowan.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.SignTodayAdapter;
import com.example.huoshangkou.jubowan.bean.PositionBean;
import com.example.huoshangkou.jubowan.bean.SignTimesBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.NetUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
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
 * 类名：CheckWorkActivity
 * 描述：
 * 创建时间：2017-03-28  11:35
 */

public class CheckWorkActivity extends AppCompatActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    //    @Bind(R.id.map_view)
    //    MapView mMapView;
    @Bind(R.id.bd_map_view)
    MapView bdMapView;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.tv_time)
    TextView tvTime;
    //签到
    @Bind(R.id.ll_sign)
    LinearLayout llSign;
    @Bind(R.id.tv_sign_time)
    TextView tvSignTime;
    @Bind(R.id.tv_sign_times)
    TextView tvSignTimes;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_bottom_line)
    TextView tvLinea;
    @Bind(R.id.ll_sign_manager)
    LinearLayout llSignManager;

    //考勤管理
    @Bind(R.id.tv_check_manager)
    TextView tvManager;
    @Bind(R.id.tv_visitor_reset)
    TextView tvVisitor;

    @Bind(R.id.lv_sign_today)
    ListView lvSignToday;
    //    AMap aMap;
//    AMapLocation mAmapLocation;
//    //声明AMapLocationClient类对象
//    public AMapLocationClient mLocationClient = null;
//    //声明AMapLocationClientOption对象
//    public AMapLocationClientOption mLocationOption = null;
    //回访客户
    private String backVisitor = "";
    private String backVisitorId = "";
    //获取经纬度
    private String latLong = "";
    private String address = "";
    private String poi = "";
    private String lat = "";
    private String lon = "";
    private final int SEND_CODE = 1;
    private final int COMPANY = 2;
    //是否定位成功
    private boolean isLocationSuccess = false;
    //兴趣点
    private String poinName = "";
    //0普通用户 1 管理员 2超级管理员
    private int roleType = 0;
    private String approveId = "";
    private String approveMan = "";
    private int GPS_REQUEST_CODE = 10;
    //是否可以打卡
    private boolean isSign = true;
    //是否进行地点微调
    private boolean isAddressTiao = false;
    private BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    private LatLng latLng;
    private boolean isFirstLoc = true; // 是否首次定位
    public MyLocationListener myListener = new MyLocationListener();
    private String province = "";
    private String city = "";
    private String area = "";

    private String currentTime = "";
    private SignTodayAdapter todayAdapter;
    private List<SignTimesBean.UserTrackBean> todayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_work);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.main_tab_blue));
        ButterKnife.bind(this);
        tvTitle.setText("考勤");
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
//        mMapView.onCreate(savedInstanceState);
//        aMap = mMapView.getMap();
        initMap();
        if (ContextCompat.checkSelfPermission(CheckWorkActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CheckWorkActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 102);
        } else {
            initLocation();
        }
        // 不显示地图上比例尺
        bdMapView.showScaleControl(false);

        // 不显示地图缩放控件（按钮控制栏）
        bdMapView.showZoomControls(false);
        currentTime = DateUtils.getCurrentTime();
        tvTime.setText("  " +currentTime );
        tvSignTime.setText(DateUtils.getCurrentMinute());



        todayAdapter = new SignTodayAdapter(this, todayList, R.layout.item_sign_today);
        lvSignToday.setAdapter(todayAdapter);
        lvSignToday.setDividerHeight(0);
    }

    @OnClick({R.id.ll_back, R.id.rl_change_position,
            R.id.tv_sign_detail, R.id.ll_sign, R.id.tv_right, R.id.tv_change_position, R.id.tv_check_manager,
            R.id.rl_visitor_reset})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            //地点微调
            case R.id.tv_change_position:
            case R.id.rl_change_position:
                if (!StringUtils.isNoEmpty(latLong)) {
                    ToastUtils.getMineToast("获取位置失败，请稍等");
                    return;
                }
                isAddressTiao = true;
                Intent intent = new Intent(this, ChangePositionActivity.class);
                intent.putExtra(IntentUtils.getInstance().TYPE, latLong);
                intent.putExtra(IntentUtils.getInstance().PROVINCE, province);
                intent.putExtra(IntentUtils.getInstance().CITY, city);
                intent.putExtra(IntentUtils.getInstance().AREA, area);
                startActivityForResult(intent, SEND_CODE);
                break;
            case R.id.tv_sign_detail:
                IntentUtils.getInstance().toActivity(this, SignDetailsActivity.class, PersonConstant.getInstance().getUserId(), PersonConstant.getInstance().getHeadPic(CheckWorkActivity.this));
                break;
            //上报
            case R.id.ll_sign:
                String phoneTypeId = PersonConstant.getInstance().getPhoneTypeId();
                if (!StringUtils.isNoEmpty(phoneTypeId)) {
                    CopyIosDialogUtils.getInstance().getNoCancelIosDialog(CheckWorkActivity.this, "设备号获取失败、是否重新获取", new CopyIosDialogUtils.iosDialogClick() {
                        @Override
                        public void confimBtn() {
                            if (ContextCompat.checkSelfPermission(CheckWorkActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                                    && ContextCompat.checkSelfPermission(CheckWorkActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(CheckWorkActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 103);
                            } else {
                                //获取设备唯一标识
                                TelephonyManager mTelephony = (TelephonyManager) CheckWorkActivity.this.getSystemService(Context.TELEPHONY_SERVICE);
                                String id = "";
                                if (mTelephony.getDeviceId() != null) {
                                    id = mTelephony.getDeviceId();
                                } else {
                                    //android.provider.Settings;
                                    id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                                }
                                String deviceId = PersonConstant.getInstance().getPhoneTypeId();
                                if (!StringUtils.isNoEmpty(deviceId)) {
                                    SharedPreferencesUtils.getInstance().put(CheckWorkActivity.this, SharedKeyConstant.getInstance().ONLY_ONE_DEVICE, id);
                                }
                                getDaySignTimes();
                                if (!isLocationSuccess) {
                                    return;
                                }
                                if (!isSign) {
                                    return;
                                }
                                IntentUtils.getInstance().toActivity(CheckWorkActivity.this, SignActivity.class, address + "、" + lat + "、" + lon, poinName, backVisitor, backVisitorId);
                            }
                        }

                        @Override
                        public void cancelBtn() {
                            CheckWorkActivity.this.finish();
                        }
                    });
                    return;
                }
                if (!isLocationSuccess) {
                    return;
                }
                if (!isSign) {
                    return;
                }
                IntentUtils.getInstance().toActivity(this, SignActivity.class, address + "、" + lat + "、" + lon, poinName, backVisitor, backVisitorId,currentTime);
                break;
            case R.id.tv_right:
                IntentUtils.getInstance().toActivity(this, AddSignActivity.class, approveId, approveMan);
                break;
//            case R.id.rl_check_sign:
//                IntentUtils.getInstance().toActivity(this, CheckSignActivity.class, approveId);
//                break;
            //考勤管理
            case R.id.tv_check_manager:
                IntentUtils.getInstance().toRoleTypeActivity(this, SignManActivity.class, roleType + "");
                break;
            case R.id.rl_visitor_reset:
                Intent intent1 = new Intent(CheckWorkActivity.this, VisitorResetActivity.class);
                startActivityForResult(intent1, COMPANY);
//                IntentUtils.getInstance().toActivity(CheckWorkActivity.this,VisitorResetActivity.class);
                break;
        }
    }

    /**
     * 检测GPS是否打开
     *
     * @return
     */
    private boolean checkGPSIsOpen() {
        boolean isOpen;
        LocationManager locationManager = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);
        isOpen = locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
        return isOpen;
    }

    /**
     * 跳转GPS设置
     */
    private void openGPSSettings() {
        if (checkGPSIsOpen()) {
            initLocation(); //自己写的定位方法
        } else {
            //没有打开则弹出对话框
            new AlertDialog.Builder(this)
                    .setTitle(R.string.notifyTitle)
                    .setMessage(R.string.gpsNotifyMsg)
                    // 拒绝, 退出应用
                    .setNegativeButton(R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                    .setPositiveButton(R.string.setting,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //跳转GPS设置界面
                                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivityForResult(intent, GPS_REQUEST_CODE);
                                }
                            })
                    .setCancelable(false)
                    .show();
        }
    }

    private void initMap() {
        tvContent.setText("  定位中...");
        //获取地图控件引用
        mBaiduMap = bdMapView.getMap();
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
        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        //配置定位SDK参数
        initLocation();
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        //开启定位
        mLocationClient.start();
        //图片点击事件，回到定位点
        mLocationClient.requestLocation();
    }

    //配置定位SDK参数
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation
        // .getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        option.setOpenGps(true); // 打开gps

        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        if (mLocationClient != null) {
            mLocationClient.setLocOption(option);
        }

        if (ContextCompat.checkSelfPermission(CheckWorkActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(CheckWorkActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 102);
            tvContent.setText("定位失败,请点击打开位置权限");
        }
        tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(CheckWorkActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CheckWorkActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 102);
                }
            }
        });
    }

    //实现BDLocationListener接口,BDLocationListener为结果监听接口，异步获取定位结果
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            latLng = new LatLng(location.getLatitude(), location.getLongitude());
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            // 设置定位数据
//            mBaiduMap.setMyLocationData(locData);

            latLong = location.getLatitude() + "," + location.getLongitude() + "," + location.getDistrict();
            address = location.getAddress().address;
            poi = location.getDistrict();
            isLocationSuccess = true;
            lat = location.getLatitude() + "";
            lon = location.getLongitude() + "";

            province = location.getProvince();
            city = location.getCity();
            area = location.getDistrict();

            llSign.setBackground(getResources().getDrawable(R.mipmap.kaoqin_btn));
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null && list.size() != 0) {
                poinName = list.get(0).getName();
                tvContent.setText("  " + poinName);
            } else {
                poinName = location.getLocationDescribe();
                tvContent.setText("  " + poinName);
            }

            // 当不需要定位图层时关闭定位图层
            //mBaiduMap.setMyLocationEnabled(false);
//            if (isFirstLoc) {
//                isFirstLoc = false;
//                LatLng ll = new LatLng(location.getLatitude(),
//                        location.getLongitude());
//                MarkerOptions markerOptions = new MarkerOptions();
//                markerOptions.position(ll);
//                markerOptions.visible(true);
//                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.dizhi_icon_person));
//                markerOptions.icon(bitmapDescriptor);
//                MapStatus.Builder builder = new MapStatus.Builder();
//                builder.target(ll).zoom(18.0f);
//                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//                mBaiduMap.addOverlay(markerOptions);
//            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == SEND_CODE) {
            PositionBean positionBean = (PositionBean) data.getSerializableExtra(IntentUtils.getInstance().TYPE);
//            changeMapPosition(positionBean.getLat(), positionBean.getLon(), positionBean.getName());
            address = positionBean.getAddress();
            poinName = positionBean.getName();
            tvContent.setText("  " + poinName);
        } else if (requestCode == COMPANY) {
            backVisitor = data.getStringExtra(IntentUtils.getInstance().TYPE);
            backVisitorId = data.getStringExtra(IntentUtils.getInstance().VALUE);
            tvVisitor.setText(backVisitor + " ");
        } else if (requestCode == GPS_REQUEST_CODE) {
            //做需要做的事情，比如再次检测是否打开GPS了 或者定位
            openGPSSettings();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 101:
                //获取设备唯一标识
                TelephonyManager mTelephony = (TelephonyManager) CheckWorkActivity.this.getSystemService(Context.TELEPHONY_SERVICE);
                String id = "";
                if (mTelephony.getDeviceId() != null) {
                    id = mTelephony.getDeviceId();
                } else {
                    //android.provider.Settings;
                    id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                }
                String deviceId = PersonConstant.getInstance().getPhoneTypeId();
                if (!StringUtils.isNoEmpty(deviceId)) {
                    SharedPreferencesUtils.getInstance().put(CheckWorkActivity.this, SharedKeyConstant.getInstance().ONLY_ONE_DEVICE, id);
                }
                //判断你是否有上报权限
                getDaySignTimes();
                break;
            case 102:
                initLocation();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mLocationClient.stop();
//        bdMapView.setMyLocationEnab  led(false);
        bdMapView.onDestroy();
        bdMapView = null;
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
//        if (null != mLocationClient) {
//            mLocationClient.onDestroy();
//        }
        ButterKnife.unbind(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.e("onRestart");
    }

    @Override
    protected void onResume() {
        bdMapView.onResume();
        super.onResume();
        LogUtils.e("onStart");
        if (!NetUtils.isConnected(this)) {
            CopyIosDialogUtils.getInstance().getErrorDialogNoCancel(this, "当前网络错误，请检查网络", new CopyIosDialogUtils.ErrDialogCallBack() {
                @Override
                public void confirm() {
                    //跳转设置界面
                    Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    startActivityForResult(intent, GPS_REQUEST_CODE);
                }
            });
            return;
        }
        //判断是否有设备号
        String phoneTypeId = PersonConstant.getInstance().getPhoneTypeId();
        if (!StringUtils.isNoEmpty(phoneTypeId)) {
            CopyIosDialogUtils.getInstance().getNoCancelIosDialog(CheckWorkActivity.this, "设备号获取失败、是否重新获取", new CopyIosDialogUtils.iosDialogClick() {
                @Override
                public void confimBtn() {
                    if (ContextCompat.checkSelfPermission(CheckWorkActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                            && ContextCompat.checkSelfPermission(CheckWorkActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(CheckWorkActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE}, 101);
                    } else {
                        //获取设备唯一标识
                        TelephonyManager mTelephony = (TelephonyManager) CheckWorkActivity.this.getSystemService(Context.TELEPHONY_SERVICE);
                        String id = "";
                        if (mTelephony.getDeviceId() != null) {
                            id = mTelephony.getDeviceId();
                        } else {
                            //android.provider.Settings;  
                            id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                        }//
                        String deviceId = PersonConstant.getInstance().getPhoneTypeId();
                        if (!StringUtils.isNoEmpty(deviceId)) {
                            SharedPreferencesUtils.getInstance().put(CheckWorkActivity.this, SharedKeyConstant.getInstance().ONLY_ONE_DEVICE, id);
                        }
                    }
                }

                @Override
                public void cancelBtn() {
                    CheckWorkActivity.this.finish();
                }
            });
            return;
        } else {
            //判断你是否有上报权限
            getDaySignTimes();
        }
    }

    @Override
    protected void onPause() {
        bdMapView.onPause();
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
//        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
//        mMapView.onSaveInstanceState(outState);
        bdMapView.onSaveInstanceState(outState);
    }

    //获取当天的考勤次数
    public void getDaySignTimes() {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this,
                UrlConstant.getInstance().URL
                        + PostConstant.getInstance().GET_DAY_COUNT
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                        + FieldConstant.getInstance().PHONE_TYPE + "=" + PersonConstant.getInstance().getPhoneTypeId(), new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        LogUtils.e(json);
                        SignTimesBean timesBean = JSON.parseObject(json, SignTimesBean.class);
                        approveId = timesBean.getApprovalUserID() + "";
                        approveMan = timesBean.getApprovalUserIDName();
                        todayList.clear();
                        if (timesBean.getUserTrack() != null) {
                            todayList.addAll(timesBean.getUserTrack());
                            todayAdapter.notifyDataSetChanged();
                        }
//                        1 匹配 0 不匹配 2 未绑定  您的账号与设备号
                        switch (timesBean.getIsMatch()) {
                            case 0:
                                CopyIosDialogUtils.getInstance().getErrorDialogNoCancel(CheckWorkActivity.this, "您的账号和设备不匹配,请使用原设备进行考勤,如更换设备请进行以下操作\n 我的审批 ->其他审批，填写设备更换申请 转交至行政", new CopyIosDialogUtils.ErrDialogCallBack() {
                                    @Override
                                    public void confirm() {
                                        CheckWorkActivity.this.finish();
                                    }
                                });
                                isSign = false;
                                break;
                            case 1:

                                break;
                            case 2:
                                CopyIosDialogUtils.getInstance().getNoCancelIosDialog(CheckWorkActivity.this, "现需要您的账号进行设备绑定,绑定后只能用该设备进行考勤。", new CopyIosDialogUtils.iosDialogClick() {
                                    @Override
                                    public void confimBtn() {
                                        bindPhone();
                                    }

                                    @Override
                                    public void cancelBtn() {
                                        CheckWorkActivity.this.finish();
                                    }
                                });
                                break;
                            case 3:
                                isSign = false;
                                CopyIosDialogUtils.getInstance().getErrorDialogNoCancel(CheckWorkActivity.this, "由于该账号已经与其他设备绑定，不能使用该设备进行打卡。", new CopyIosDialogUtils.ErrDialogCallBack() {
                                    @Override
                                    public void confirm() {
                                        CheckWorkActivity.this.finish();
                                    }
                                });
                                break;
                        }
                        if (timesBean.getCounts() == 0) {
                            tvSignTimes.setText("今日你还未上报");
                        } else {
                            tvSignTimes.setText("今日已上报" + StringUtils.getNoNullStr(timesBean.getCounts() + "") + "次");
                        }

                        //判断是否是超级管理员   0普通用户 1 管理员 2超级管理员
                        roleType = timesBean.getAdministrator();
//                        roleType = 2;
                        switch (roleType) {
                            case 0:
//                                tvLinea.setVisibility(View.GONE);
//                                llSignManager.setVisibility(View.GONE);
                                break;
                            case 1:
                            case 2:
                                tvLinea.setVisibility(View.VISIBLE);
                                llSignManager.setVisibility(View.VISIBLE);
                                break;
                        }
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }

    //绑定手机号
    public void bindPhone() {
        OkhttpUtil.getInstance().setUnCacheData(this, getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().BIND_PHONE_TYPE
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().PHONE_TYPE + "=" + PersonConstant.getInstance().getPhoneTypeId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("绑定成功");
                    isSign = true;
                } else {
                    CopyIosDialogUtils.getInstance().getErrorDialogNoCancel(CheckWorkActivity.this, successBean.getErrMsg(), new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {
                            CheckWorkActivity.this.finish();
                        }
                    });
//                    CheckWorkActivity.this.finish();
                }
            }

            @Override
            public void onFail() {
                ToastUtils.getMineToast("绑定失败");
                CheckWorkActivity.this.finish();
            }
        });
    }

    public void isSelectedMoniLocal(Context mContext) {
        if (Build.VERSION.SDK_INT > 22) {
//            //6.0以上版本
            if (isOpenCurApp(mContext)) {
                CopyIosDialogUtils.getInstance().getErrorDialogNoCancel(CheckWorkActivity.this, "为保证位置精确性，请不要允许此应用使用模拟定位", new CopyIosDialogUtils.ErrDialogCallBack() {
                    @Override
                    public void confirm() {
                        CheckWorkActivity.this.finish();
                    }
                });
            }
        }
    }

    /**
     * 6.0以上系统判定是否允许当前应用使用模拟定位
     * true--允许
     * false--禁止
     *
     * @param mContext
     * @return
     */
    public boolean isOpenCurApp(Context mContext) {
        boolean isOpen = Settings.Secure.getInt(mContext.getContentResolver(), Settings.Secure.ALLOW_MOCK_LOCATION, 0) != 0;
        return isOpen;
    }
}
