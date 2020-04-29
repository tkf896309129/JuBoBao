package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.AddressEditActivity;
import com.example.huoshangkou.jubowan.adapter.AddressListAdapter;
import com.example.huoshangkou.jubowan.bean.AddressBean;
import com.example.huoshangkou.jubowan.bean.AddressListBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.DeleteAddressBack;
import com.example.huoshangkou.jubowan.inter.EditAddressBack;
import com.example.huoshangkou.jubowan.inter.SetMorenBack;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.utils.AddressUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MineLoadingDialogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：AddressListFunction
 * 描述：
 * 创建时间：2017-02-22  11:38
 */

public class AddressListFunction {

    private static class AddressListHelper {
        private static AddressListFunction INSTANCE = new AddressListFunction();
    }

    public static AddressListFunction getInstance() {
        return AddressListHelper.INSTANCE;
    }

    private Context mContext;

    /**
     * 加载数据
     *
     * @param lvRefresh          刷新控件
     * @param addressListAdapter 地址刷新适配器
     * @param listAddress        地址数据集合
     * @param xRefresh           刷新
     */
    public void setMorenAddress(ListView lvRefresh, final AddressListAdapter addressListAdapter,
                                final List<AddressListBean> listAddress, final SmartRefreshLayout xRefresh,
                                Context context) {
        mContext = context;
        lvRefresh.setAdapter(addressListAdapter);
        lvRefresh.setDividerHeight(0);
        getAddressListData(listAddress, addressListAdapter, xRefresh);


        //设置成默认地址回调
        addressListAdapter.setMorenCheck(new SetMorenBack() {
            @Override
            public void onGetAddressId(final int AddressId, final String address, final String linkMan, final String linkPhone) {
                setAddressMoren(AddressId, listAddress, addressListAdapter, new SuccessCallBack() {
                    @Override
                    public void onSuccess() {
                        //默认地址本地保存
                        AddressUtils.getInstance().saveDefaultAddress(AddressId + "", address, linkMan, linkPhone);
                    }

                    @Override
                    public void onFail() {

                    }
                });
            }
        });

        //删除地址回调
        addressListAdapter.setDeleteAddressBack(new DeleteAddressBack() {
            @Override
            public void deleteAddress(int addressId, int position) {
                deleteAddressId(addressId, listAddress, addressListAdapter, position);
            }
        });

        //编辑地址回调
        addressListAdapter.setEditAddressBack(new EditAddressBack() {
            @Override
            public void setEditAddress(AddressListBean addressId) {
                eidtAddressId(addressId);
            }
        });

        xRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                listAddress.clear();
                getAddressListData(listAddress, addressListAdapter, xRefresh);
            }
        });
    }

    //加载地址数据
    public void getAddressListData(final List<AddressListBean> listAddress, final AddressListAdapter addressListAdapter,
                                   final SmartRefreshLayout xRefresh) {
        OkhttpUtil.getInstance().setUnCacheData(mContext, mContext.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_ADDRESS_LIST
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LogUtils.i(json);
                AddressBean addressBean = JSON.parseObject(json, AddressBean.class);
                listAddress.addAll(addressBean.getReList());
                addressListAdapter.notifyDataSetChanged();
                for (int i = 0; i < listAddress.size(); i++) {
                    if(listAddress.get(i).isDefaultAddress()){
                        //默认地址本地保存
                        AddressUtils.getInstance().saveDefaultAddress(listAddress.get(i).getAdressID() + "", listAddress.get(i).getProvinces()+listAddress.get(i).getDetailAddress(), listAddress.get(i).getLinkMan(), listAddress.get(i).getLinkTel());
                    }
                }
                if (xRefresh != null ) {
                    xRefresh.finishRefresh();
                }
            }

            @Override
            public void onFail() {
                ToastUtils.getMineToast("失败");

            }
        });
    }


    //设置默认收货地址
    private void setAddressMoren(int AddressId, final List<AddressListBean> listAddress, final AddressListAdapter addressListAdapter, final SuccessCallBack callBack) {

        OkhttpUtil.getInstance().setUnCacheData(mContext, mContext.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().SET_MOREN_ADDRESS
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().ID + "=" + AddressId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    listAddress.clear();
                    getAddressListData(listAddress, addressListAdapter, null);
                    callBack.onSuccess();
                    ToastUtils.getMineToast(mContext.getString(R.string.set_success));
                }
            }

            @Override
            public void onFail() {
callBack.onFail();
            }
        });
    }

    //删除收货地址
    private void deleteAddressId(int AddressId, final List<AddressListBean> listAddress, final AddressListAdapter addressListAdapter, final int position) {
        OkhttpUtil.getInstance().setUnCacheData(mContext, mContext.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().DELETE_ADDRSS
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().ID + "=" + AddressId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    listAddress.remove(position);
                    addressListAdapter.notifyDataSetChanged();
                    ToastUtils.getMineToast(mContext.getString(R.string.delete_success));
                    SharedPreferencesUtils.getInstance().put(mContext, SharedKeyConstant.getInstance().ADDRESS_DELETE,"delete");
                    AddressUtils.getInstance().saveDefaultAddress( "", "", "", "");
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //编辑地址
    private void eidtAddressId(AddressListBean AddressBean) {
        IntentUtils.getInstance().toActivity(mContext, AddressEditActivity.class, AddressBean);
    }

}
