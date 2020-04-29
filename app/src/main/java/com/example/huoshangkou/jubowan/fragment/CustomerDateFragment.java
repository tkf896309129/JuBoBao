package com.example.huoshangkou.jubowan.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.AddDataActivity;
import com.example.huoshangkou.jubowan.activity.SignManActivity;
import com.example.huoshangkou.jubowan.adapter.CustomerDateAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.DatePlanBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.MainView;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import io.blackbox_vision.materialcalendarview.view.CalendarView;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：CustomerDateFragment
 * 描述：
 * 创建时间：2019-08-23  13:52
 */

public class CustomerDateFragment extends BaseFragment {
    CalendarView calendarView;
    @Bind(R.id.lv_customer_date)
    ListView lvCustomerDate;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_right)
    ImageView imgRight;
    @Bind(R.id.tv_right)
    TextView tvRight;
    List<DatePlanBean.DBean.ResultBean> list = new ArrayList<>();
    CustomerDateAdapter dateAdapter;

    private int page = 1;
    private String time = "";
    //是否是管理员查看
    private boolean isManage = false;
    private String type = "";
    private String roleID = "";
    private String userId = "";

    public static CustomerDateFragment newInstance() {
        CustomerDateFragment fragment = new CustomerDateFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_customer_date;
    }

    @Override
    public void initData() {
        tvTitle.setText("日程");
        String customerManageUserId = PersonConstant.getInstance().getCustomerManageUserId();
        if (StringUtils.isNoEmpty(customerManageUserId)) {
            userId = customerManageUserId;
            isManage = true;
        } else {
            userId = PersonConstant.getInstance().getUserId();
        }
//        if (roleTypeManager == 2) {
//            String roleId = (String) SharedPreferencesUtils.getInstance().get(getActivity(), SharedKeyConstant.getInstance().REOLE_ID, "");
//            IntentUtils.getInstance().toRoleTypeActivity(getActivity(), SignManActivity.class, "  ", "成员列表", "", roleTypeManager + "", "saleManage");
//        } else {
//        }
        type = getArguments().getString(IntentUtils.getInstance().ROLE_MANAGE_TYPE);
        roleID = getArguments().getString(IntentUtils.getInstance().ROLE_MANAGE_ID);
//        if (StringUtils.isNoEmpty(type) && (type.equals("2") || type.equals("3"))) {
//            tvRight.setText("查看所有");
//            tvRight.setVisibility(View.VISIBLE);

        if (!StringUtils.isNoEmpty(type) && isManage) {
            imgRight.setVisibility(View.GONE);
        }
//        }
        //管理员查看
//        if (StringUtils.isNoEmpty(customerManageUserId)) {
//            isManage = true;
//            imgRight.setVisibility(View.GONE);
//        }

        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.top_customer_date, null);
        calendarView = headView.findViewById(R.id.calenderView);
//        calendarView.setFocusedMonthDateColor(getResources().getColor(R.color.address_black_key));
//        calendarView.setWeekNumberColor(getResources().getColor(R.color.address_black_key));
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView calendarView,
//                                            int year, int month, int dayOfMonth) {
//                time = String.valueOf(year) + "/" +
//                        String.valueOf(month + 1) + "/" + String.valueOf(dayOfMonth);
//                page = 1;
//                getData();
//            }
//        });

        calendarView.shouldAnimateOnEnter(false)
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setOnDateClickListener(new CalendarView.OnDateClickListener() {
                    @Override
                    public void onDateClick(@NonNull Date selectedDate) {
                        time = DateUtils.getInstance().toFormDate(selectedDate);
                        page = 1;
                        getData();
                    }
                });

        if (calendarView.isMultiSelectDayEnabled()) {
            calendarView.setOnMultipleDaySelectedListener(new CalendarView.OnMultipleDaySelectedListener() {
                @Override
                public void onMultipleDaySelected(int month, @NonNull List<Date> dates) {

                }
            });
        }
        calendarView.update(Calendar.getInstance(Locale.getDefault()));

        dateAdapter = new CustomerDateAdapter(getActivity(), list, isManage, R.layout.item_customer_date);
        lvCustomerDate.setAdapter(dateAdapter);
        lvCustomerDate.setDividerHeight(0);
        lvCustomerDate.addHeaderView(headView);

        //获取当天的日程
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        time = dateFormat.format(new Date());
        getData();

        //删除日程
        dateAdapter.setOnDeletePositionCallBack(new OnPositionCallBack() {
            @Override
            public void onPositionClick(final int position) {
                CopyIosDialogUtils.getInstance().getIosDialog(getActivity(), "是否删除该日程", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        deleteData(list.get(position).getId() + "", position);
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });
        //修改日程
        dateAdapter.setOnChangePositionCallBack(new OnPositionCallBack() {
            @Override
            public void onPositionClick(int position) {
                IntentUtils.getInstance().toActivity(getActivity(), AddDataActivity.class, list.get(position).getId() + "");
            }
        });

    }


    @OnClick({R.id.ll_back, R.id.iv_right, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                getActivity().finish();
                break;
            case R.id.iv_right:
                IntentUtils.getInstance().toActivity(getActivity(), AddDataActivity.class);
                break;
            case R.id.iv_history:

                break;
            case R.id.tv_right:
                IntentUtils.getInstance().toRoleTypeActivity(getActivity(), SignManActivity.class, roleID, "成员列表", "", "2", "saleManage");
                break;
        }
    }

    //删除日程
    public void deleteData(String id, final int position) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(getActivity(), json, UrlConstant.getInstance().DELETE_DATA, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                list.remove(position);
                dateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }

    //获取日程
    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("pageIndex", page + "");
        map.put("pageSize", "20");
        map.put("date", time);

        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(getActivity(), json, UrlConstant.getInstance().GET_DATE_TYPE_URL, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                if (page == 1) {
                    list.clear();
                }
                DatePlanBean planBean = JSON.parseObject(str, DatePlanBean.class);
                list.addAll(planBean.getD().getResult());
                dateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }
}
