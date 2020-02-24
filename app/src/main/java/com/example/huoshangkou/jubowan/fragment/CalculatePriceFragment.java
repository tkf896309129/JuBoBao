package com.example.huoshangkou.jubowan.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.GridView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.CalculatorFunction;
import com.example.huoshangkou.jubowan.adapter.CalculatorAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.CalculatorBean;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：CalculatePriceFragment
 * 描述：计算价格功能界面
 * 创建时间：2017-02-15  09:40
 */

public class CalculatePriceFragment extends BaseFragment {
    @Bind(R.id.et_price)
    EditText etPrice;
    @Bind(R.id.grid_weight_area)
    GridView gridView;


    private List<CalculatorBean> calculatorBeanList;
    private CalculatorAdapter calculatorAdapter;

    private double price = 0;

    public static CalculatePriceFragment newInstance() {
        CalculatePriceFragment fragment = new CalculatePriceFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_price;
    }

    @Override
    public void initData() {

        calculatorBeanList = new ArrayList<>();
        calculatorAdapter = new CalculatorAdapter(getActivity(), calculatorBeanList, R.layout.item_calculator, "price");
        gridView.setAdapter(calculatorAdapter);

        showData();
        etPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();
                if (!StringUtils.isNoEmpty(s)) {
                    price = 0;
                    showData();
                    return;
                }
                price = Double.parseDouble(s);
                showData();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void showData() {
        calculatorBeanList.clear();
        CalculatorFunction.getInstance().calculatorPriceList(calculatorBeanList, price, new OnCommonSuccessCallBack() {
            @Override
            public void onSuccess() {
                calculatorAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }
}
