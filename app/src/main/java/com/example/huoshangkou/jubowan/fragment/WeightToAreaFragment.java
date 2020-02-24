package com.example.huoshangkou.jubowan.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

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
import butterknife.ButterKnife;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：WeightToAreaFragment
 * 描述：重量转面积功能界面
 * 创建时间：2017-02-15  09:37
 */

public class WeightToAreaFragment extends BaseFragment {

    @Bind(R.id.et_tons)
    EditText etTons;
    @Bind(R.id.tv_kg)
    TextView tvKg;
    @Bind(R.id.tv_weight_num)
    TextView tvWeightNum;
    @Bind(R.id.grid_weight_area)
    GridView gridWeightArea;

    private double tons = 0;

    List<CalculatorBean> areaList;
    private CalculatorAdapter calculatorAdapter;

    public static WeightToAreaFragment newInstance() {
        WeightToAreaFragment fragment = new WeightToAreaFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_weight_area;
    }

    @Override
    public void initData() {

        areaList = new ArrayList<>();

        calculatorAdapter = new CalculatorAdapter(getActivity(), areaList, R.layout.item_calculator);
        gridWeightArea.setAdapter(calculatorAdapter);

        showCalculatorResult();
//        CalculatorFunction.getInstance().
        etTons.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();
                if (!StringUtils.isNoEmpty(s)) {
                    tons = 0;
                    showCalculatorResult();
                    return;
                }
                tons = Double.parseDouble(s);
                showCalculatorResult();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    //计算并显示
    public void showCalculatorResult() {
        tvWeightNum.setText(tons * 20 + "");
        tvKg.setText(tons * 1000 + "");

        CalculatorFunction.getInstance().getAreaList(tons, areaList, new OnCommonSuccessCallBack() {
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
