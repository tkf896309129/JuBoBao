package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.AddressEditFunction;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.base.HideBaseActivity;
import com.example.huoshangkou.jubowan.bean.AddressListBean;
import com.example.huoshangkou.jubowan.inter.CityNameInterface;
import com.example.huoshangkou.jubowan.inter.OnEditAddressBack;
import com.example.huoshangkou.jubowan.utils.CityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.KeyboardChangeListener;
import com.example.huoshangkou.jubowan.utils.PhoneFormatCheckUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：AddressEditActivity
 * 描述：编辑地址
 * 创建时间：2017-02-09  14:25
 */

public class AddressEditActivity extends HideBaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_area)
    TextView tvArea;
    @Bind(R.id.et_receive)
    EditText etReceive;
    @Bind(R.id.et_link_tel)
    EditText etLinkTel;
    @Bind(R.id.et_detail_address)
    EditText etDetailAddress;

    //省区地址
    private String provinces;
    private String linkMan;
    private String linkTel;
    private String detailAddress;

    private String addressId;

    private AddressListBean addressListBean;

    //键盘是否显示出来
    private boolean mIsShow = false;

    @Override
    public int initLayout() {
        return R.layout.activity_address_edit;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    protected void setIsNeedHideKey(boolean flag) {
        super.setIsNeedHideKey(true);
    }

    @Override
    public void initData() {
        //设置标题

        addressListBean = (AddressListBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);

        //编辑地址传过来的值
        if (addressListBean != null) {
            tvTitle.setText("编辑地址");

            linkMan = addressListBean.getLinkMan();
            linkTel = addressListBean.getLinkTel();
            detailAddress = addressListBean.getDetailAddress();
            provinces = addressListBean.getProvinces();
            addressId = addressListBean.getAdressID() + "";


            etLinkTel.setText(linkTel);
            etDetailAddress.setText(detailAddress);
            etReceive.setText(linkMan);
            tvArea.setText(provinces + "  ");
        } else {
            tvTitle.setText("添加地址");
        }


        if (!StringUtils.isNoEmpty(addressId)) {
            addressId = "";
        }

        new KeyboardChangeListener(this).setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                mIsShow = isShow;
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.rl_get_address, R.id.tv_receive_change})
    public void onClick(View view) {
        switch (view.getId()) {
            //返回
            case R.id.ll_back:
                this.finish();
                break;
            //获取地址
            case R.id.rl_get_address:
                KeyBoardUtils.closeKeybord(etDetailAddress, getContext());

                CityUtils.getInstance().getAllCity(this, new CityNameInterface() {
                    @Override
                    public void getCityName(String area) {
                        provinces = area;
                        tvArea.setText(provinces);
                    }
                });
                break;
            //保存修改
            case R.id.tv_receive_change:
                //收货人
                linkMan = etReceive.getText().toString().trim();
                if (!StringUtils.isNoEmpty(linkMan)) {
                    ToastUtils.getMineToast(getString(R.string.input_link_man));
                    return;
                }
                if (linkMan.length() < 2) {
                    ToastUtils.getMineToast("请输入正确的姓名");
                    return;
                }

                //联系方式
                linkTel = etLinkTel.getText().toString().trim();
                if (!StringUtils.isNoEmpty(linkTel)) {
                    ToastUtils.getMineToast(getString(R.string.input_link_tel));
                    return;
                }

//                if (!PhoneFormatCheckUtils.getInstance().isPhoneLegal(linkTel)) {
//                    ToastUtils.getMineToast("请输入正确的手机号");
//                    return;
//                }

                if (linkTel.length() != 11) {
                    ToastUtils.getMineToast("请输入正确的手机号");
                    return;
                }

                if (!StringUtils.isNoEmpty(provinces)) {
                    ToastUtils.getMineToast(getString(R.string.input_province));
                    return;
                }
                //详细地址
                detailAddress = etDetailAddress.getText().toString().trim();
                if (!StringUtils.isNoEmpty(detailAddress)) {
                    ToastUtils.getMineToast(getString(R.string.input_detail_address));
                    return;
                }
                //是否保存修改
                CopyIosDialogUtils.getInstance().getIosDialog(this, "是否保存", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        AddressEditFunction.getInstance().editAddress(AddressEditActivity.this, addressId, detailAddress, linkMan, linkTel, provinces, new OnEditAddressBack() {
                            @Override
                            public void onEditAddress() {
                                AddressEditActivity.this.finish();
                            }
                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
        }
    }
}
