package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.AllCustomerBean;
import com.example.huoshangkou.jubowan.bean.SuccessDBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：AddCustomerActivity
 * 描述：新增用户
 * 创建时间：2019-08-29  08:20
 */

public class AddCustomerActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.rb_un_marry)
    RadioButton rbUnMarry;
    @Bind(R.id.rb_married)
    RadioButton rbMarried;
    @Bind(R.id.et_job)
    EditText etJob;
    @Bind(R.id.et_hobby)
    EditText etHobby;
    @Bind(R.id.et_company_name)
    EditText etCompanyName;
    @Bind(R.id.et_link_phone)
    EditText etLinkPhone;
    @Bind(R.id.et_mail)
    EditText etMail;
    @Bind(R.id.et_address)
    EditText etAddress;
    @Bind(R.id.et_jb_account)
    EditText etJbAccount;
    @Bind(R.id.et_remark)
    EditText etRemark;
    @Bind(R.id.tv_customer_type)
    TextView tvCustomerType;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.rg_marry)
    RadioGroup rgMarry;

    private String customerType;
    private String name;
    private String age;
    private String marry;
    private String job;
    private String hobby;
    private String companyName;
    private String linkPhone;
    private String mail;
    private String address;
    private String jbAccount;
    private String remark;
    private int sex;
    private String id;

    List<String> listTitle = new ArrayList<>();
    List<String> listSex = new ArrayList<>();
    AllCustomerBean.DBean.ReListBean listBean;

    @Override
    public int initLayout() {
        return R.layout.activity_add_customer;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().customerList);
        tvTitle.setText("客户添加");

        listTitle.add("无意向客户");
        listTitle.add("风险客户");
        listTitle.add("一般客户");
        listTitle.add("主要客户");
        listTitle.add("重要客户");
        listTitle.add("全部客户");

        listSex.add("女");
        listSex.add("男");

        listBean = (AllCustomerBean.DBean.ReListBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);
        if (listBean != null) {
            id = listBean.getId() + "";
            changeView(listBean);
        }
        rgMarry.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_un_marry:
                        marry = "0";
                        break;
                    case R.id.rb_married:
                        marry = "1";
                        break;
//                    case R.id.rb_no_message:
//                        break;
                }
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.tv_add_customer, R.id.rl_customer_type, R.id.tv_age, R.id.tv_sex})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_add_customer:
                name = etName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(name)) {
                    ToastUtils.getMineToast("请输入姓名");
                    return;
                }
                if (!StringUtils.isNoEmpty(sex + "")) {
                    ToastUtils.getMineToast("请选择性别");
                    return;
                }
                if (!StringUtils.isNoEmpty(age)) {
                    ToastUtils.getMineToast("请选择出生日期");
                    return;
                }
                if (!StringUtils.isNoEmpty(marry)) {
                    ToastUtils.getMineToast("请选择是否婚配");
                    return;
                }
                job = etJob.getText().toString().trim();
                if (!StringUtils.isNoEmpty(job)) {
                    ToastUtils.getMineToast("请输入职业");
                    return;
                }
                hobby = etHobby.getText().toString().trim();
                if (!StringUtils.isNoEmpty(hobby)) {
                    ToastUtils.getMineToast("请输入爱好");
                    return;
                }
                companyName = etCompanyName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(companyName)) {
                    ToastUtils.getMineToast("请输入公司名称");
                    return;
                }
                linkPhone = etLinkPhone.getText().toString().trim();
                if (!StringUtils.isNoEmpty(linkPhone)) {
                    ToastUtils.getMineToast("请输入联系方式");
                    return;
                }
                mail = etMail.getText().toString().trim();
                if (!StringUtils.isNoEmpty(mail)) {
                    ToastUtils.getMineToast("请输入电子邮箱");
                    return;
                }
                address = etAddress.getText().toString().trim();
                if (!StringUtils.isNoEmpty(address)) {
                    ToastUtils.getMineToast("请输入地址");
                    return;
                }
                jbAccount = etJbAccount.getText().toString().trim();
                remark = etRemark.getText().toString().trim();

                addCustomer();
                break;
            case R.id.rl_customer_type:
                KeyBoardUtils.closeKeybord(etAddress, this);
                DialogUtils.getInstance().getBaseDialog(this, listTitle, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        customerType = position + "";
                        tvCustomerType.setText(str + "  ");
                    }
                });
                break;
            case R.id.tv_age:
                KeyBoardUtils.closeKeybord(etAddress, this);
                TimeDialogUtils.getInstance().getTime(this, new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        age = year;
                        tvAge.setText(year);
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });
                break;
            case R.id.tv_sex:
                KeyBoardUtils.closeKeybord(etAddress, this);
                DialogUtils.getInstance().getBaseDialog(this, listSex, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        sex = position;
                        tvSex.setText(str);
                    }
                });
                break;
        }
    }

    //添加客户
    public void addCustomer() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("Company", companyName);
        map.put("Name", name);
        map.put("Sex", sex);
        map.put("BirthDate", age);
        map.put("ContactMode", linkPhone);
        map.put("MaritalStatus", marry);
        map.put("Profession", job);
        map.put("Hobby", hobby);
        map.put("Provice", "");
        map.put("City", "");
        map.put("District", "");
        map.put("Address", address);
        map.put("Email", mail);
        map.put("JumboAccount", jbAccount);
        map.put("CustomerType", customerType);
        map.put("CustomerSourse", "");
        map.put("Remark", remark);
        map.put("SaleManId", PersonConstant.getInstance().getUserId());
        String json = JSON.toJSONString(map);
        String putJson = "{\"model\":" + json + "}";
        OkhttpUtil.getInstance().basePostCall(this, putJson, UrlConstant.getInstance().CUSTOMER_MANAGE_URL + "CustomerEdit", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                SuccessDBean dBean = JSON.parseObject(str, SuccessDBean.class);
                if (dBean.getD().getSuccess() == 1) {
                    ToastUtils.getMineToast("提交成功");
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().customerList);
                } else {
                    ToastUtils.getMineToast(dBean.getD().getErrMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //修改
    public void changeView(AllCustomerBean.DBean.ReListBean listBean) {
        customerType = listBean.getCustomerType() + "";
        switch (customerType) {
            case "0":
                tvCustomerType.setText("无意向客户");
                break;
            case "1":
                tvCustomerType.setText("风险客户");
                break;
            case "2":
                tvCustomerType.setText("一般客户");
                break;
            case "3":
                tvCustomerType.setText("主要客户");
                break;
            case "4":
                tvCustomerType.setText("重要客户");
                break;
        }
        name = listBean.getName();
        etName.setText(listBean.getName());
        sex = listBean.getSex();
        tvSex.setText(sex == 1 ? "男" : "女");
        age = DateUtils.getFormDesData(listBean.getBirthDate());
        tvAge.setText(age);
        marry = listBean.getMaritalStatus() + "";
        switch (listBean.getMaritalStatus()) {
            case 0:
                rbUnMarry.setChecked(true);
                break;
            case 1:
                rbMarried.setChecked(true);
                break;
        }
        job = listBean.getProfession();
        etJob.setText(job);
        hobby = listBean.getHobby();
        etHobby.setText(hobby);
        companyName = listBean.getCompany();
        etCompanyName.setText(companyName);
        linkPhone = listBean.getContactMode();
        etLinkPhone.setText(linkPhone);
        mail = listBean.getEmail();
        etMail.setText(mail);
        address = listBean.getAddress();
        etAddress.setText(address);
        jbAccount = listBean.getJumboAccount();
        etJbAccount.setText(jbAccount);
        etRemark.setText(listBean.getRemark());
    }
}
