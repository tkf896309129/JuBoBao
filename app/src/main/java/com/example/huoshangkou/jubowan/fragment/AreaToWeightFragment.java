package com.example.huoshangkou.jubowan.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.CalculatorFunction;
import com.example.huoshangkou.jubowan.adapter.CalculatorAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TwoPointUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：AreaToWeightFragment
 * 描述：面积转重量
 * 创建时间：2017-02-15  09:38
 */

public class AreaToWeightFragment extends BaseFragment {
    @Bind(R.id.et_area)
    EditText etArea;
    @Bind(R.id.et_thick)
    EditText etThick;
    @Bind(R.id.tv_ton)
    TextView tvTon;
    @Bind(R.id.tv_weight_num)
    TextView tvWeightNum;
    @Bind(R.id.tv_kg)
    TextView tvKg;

    private double area = 0;
    private double thick = 0;


    public static AreaToWeightFragment newInstance() {
        AreaToWeightFragment fragment = new AreaToWeightFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_area_weight;
    }

    @Override
    public void initData() {


        //面积
        etArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!StringUtils.isNoEmpty(charSequence.toString())) {
                    area = 0;
                    showCalculatorResult();
                    return;
                }
                area = Double.parseDouble(charSequence.toString());

                showCalculatorResult();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //厚度
        etThick.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!StringUtils.isNoEmpty(charSequence.toString())) {
                    thick = 0;
                    showCalculatorResult();
                    return;
                }
                thick = Double.parseDouble(charSequence.toString());
                showCalculatorResult();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    //计算并显示
    public void showCalculatorResult() {
        double v = CalculatorFunction.getInstance().areaToWeight(area, thick);
        tvTon.setText(TwoPointUtils.getInstance().getTwoPoint(v / 1000 ));
        tvWeightNum.setText(TwoPointUtils.getInstance().getTwoPoint(v / 1000 * 20 ));
        tvKg.setText(TwoPointUtils.getInstance().getTwoPoint(v));
    }


}
