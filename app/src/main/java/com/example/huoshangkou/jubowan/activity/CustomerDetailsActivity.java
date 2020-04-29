package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.AddCustomerAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.AllCustomerBean;
import com.example.huoshangkou.jubowan.bean.CustomerDetailBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：CustomerDetailsActivity
 * 描述：
 * 创建时间：2019-08-29  10:14
 */

public class CustomerDetailsActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_customer_detail)
    ListView lvCustomerDetail;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_role_name)
    TextView tvRoleName;
    @Bind(R.id.tv_remark)
    TextView tvRemark;
    @Bind(R.id.tv_visitor_write)
    TextView tvVisitorWrite;
    @Bind(R.id.iv_right)//这个好吃吗 我感觉很好吃啊 我靠
    ImageView imgRight;
    @Bind(R.id.img_state)
    ImageView imgState;

    List<CustomerDetailBean> list = new ArrayList<>();
    AddCustomerAdapter customerAdapter;
    AllCustomerBean.DBean.ReListBean listBean;

    private boolean isManage = false;
    private String name = "";
    private String id = "";

    @Override
    public int initLayout() {
        return R.layout.activity_customer_details;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().customerList);
        imgRight.setImageResource(R.mipmap.white_edit);
        //管理员查看
        String customerManageUserId = PersonConstant.getInstance().getCustomerManageUserId();
        String typeData = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        if (StringUtils.isNoEmpty(customerManageUserId)) {
            isManage = true;
            imgRight.setVisibility(View.GONE);
            tvVisitorWrite.setVisibility(View.GONE);
        }
        tvTitle.setText("客户详情");
        listBean = (AllCustomerBean.DBean.ReListBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);
        if (listBean == null) {
            return;
        }
        name = listBean.getName();
        id = listBean.getId() + "";
        tvName.setText(listBean.getName());
        tvRoleName.setText(StringUtils.getNoEmptyStr(listBean.getProfession()));

        switch (listBean.getCustomerType()) {
            case 0:
                break;
            case 1:
                imgState.setVisibility(View.VISIBLE);
                imgState.setImageResource(R.mipmap.dangerous_customer);
                break;
            case 2:
            case 3:
                imgState.setVisibility(View.VISIBLE);
                imgState.setImageResource(R.mipmap.main_customer);
                break;
            case 4:
                imgState.setVisibility(View.VISIBLE);
                imgState.setImageResource(R.mipmap.important_customer);
                break;
        }

        list.add(new CustomerDetailBean("爱好", listBean.getHobby()));
        list.add(new CustomerDetailBean("邮箱", listBean.getEmail()));
        list.add(new CustomerDetailBean("地址", listBean.getAddress()));
        list.add(new CustomerDetailBean("客户来源", listBean.getCustomerSourse()));
        list.add(new CustomerDetailBean("最近下单时间", listBean.getRecentlyOrderTimeString()));
        list.add(new CustomerDetailBean("上次联系时间", listBean.getRecentlyContactTimeString()));
        tvRemark.setText(listBean.getRemark());

        customerAdapter = new AddCustomerAdapter(this, list, R.layout.item_add_customer);
        lvCustomerDetail.setAdapter(customerAdapter);
        lvCustomerDetail.setDividerHeight(0);
    }

    @OnClick({R.id.ll_back, R.id.tv_trade_record, R.id.tv_visitor_record, R.id.iv_right, R.id.tv_visitor_write})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_trade_record:
                IntentUtils.getInstance().toActivity(this, CustomerTradeRecordActivity.class, listBean.getId() + "",listBean.getSaleManId()+"");
                break;
            case R.id.tv_visitor_record:
                IntentUtils.getInstance().toActivity(this, CustomerVisitorRecordActivity.class, listBean.getId() + "");
                break;
            case R.id.iv_right:
                IntentUtils.getInstance().toActivity(this, AddCustomerActivity.class, listBean);
                break;
            case R.id.tv_visitor_write:
                IntentUtils.getInstance().toActivity(this, VisitorRegisterActivity.class, id, name);
                break;
        }
    }
}
