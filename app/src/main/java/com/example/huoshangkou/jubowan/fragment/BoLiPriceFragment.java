package com.example.huoshangkou.jubowan.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.CalculatorFunction;
import com.example.huoshangkou.jubowan.adapter.BoLiPriceAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.inter.OnBoLiCallBack;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TwoPointUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：BoLiPriceFragment
 * 描述：建筑玻璃核价
 * 创建时间：2017-02-15  09:41
 */

public class BoLiPriceFragment extends BaseFragment {

    @Bind(R.id.rg_check)
    RadioGroup rgBoLi;
    @Bind(R.id.recyler_price)
    RecyclerView recyclerView;

    @Bind(R.id.tv_ton)
    TextView tvTon;
    @Bind(R.id.tv_weight_num)
    TextView tvWeightNum;
    @Bind(R.id.tv_kg)
    TextView tvKg;

    @Bind(R.id.et_area)
    EditText etArea;
    @Bind(R.id.et_thick)
    EditText etThick;

    List<String> standardList;
    BoLiPriceAdapter priceAdapter;

    //中空点击位置
    private int zkClickPosition = -1;

    //pvb点击位置
    private int pvbClickPosition = -1;

    //是否是中空玻璃
    private boolean isZk = true;

    private double thick = 0;
    private double area = 0;

    private double canKeyValue = 0;

    private List<Integer> zkList;
    private List<Integer> pvbList;

    private double pvbTon = 0;
    private double zkTon = 0;


    public static BoLiPriceFragment newInstance() {
        BoLiPriceFragment fragment = new BoLiPriceFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_boli_price;
    }

    @Override
    public void initData() {
        zkList = new ArrayList<>();
        pvbList = new ArrayList<>();
        standardList = new ArrayList<>();
        priceAdapter = new BoLiPriceAdapter(standardList, getActivity());
        recyclerView.setAdapter(priceAdapter);
        recyclerView.setLayoutManager(getLayoutManager());


        addZhonKong();
        RadioButton childAt = (RadioButton) rgBoLi.getChildAt(0);
        childAt.setChecked(true);

        rgBoLi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_boli:
                        isZk = true;
                        addZhonKong();
                        priceAdapter.setItemClick(zkClickPosition);
                        priceAdapter.notifyDataSetChanged();
                        break;
                    case R.id.rb_pvb:
                        isZk = false;
                        addPvb();
                        priceAdapter.setItemClick(pvbClickPosition);
                        priceAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });


        //单选点击事件
        priceAdapter.setCallBack(new OnBoLiCallBack() {
            @Override
            public void onClickBack(int position) {
                if (isZk) {
                    zkClickPosition = position;
                } else {
                    pvbClickPosition = position;
                }
                priceAdapter.setItemClick(position);
                priceAdapter.notifyDataSetChanged();
                getCalData();
            }
        });


        //面积
        etArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!StringUtils.isNoEmpty(charSequence.toString())) {
                    area = 0;
                    getCalData();
                    return;
                }

                area = Double.parseDouble(charSequence.toString());
                getCalData();
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
                    getCalData();
                    return;
                }

                thick = Double.parseDouble(charSequence.toString());
                getCalData();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }


    //中空玻璃
    private void addZhonKong() {
        standardList.clear();
        zkList.clear();
        String[] standard = CalculatorFunction.getInstance().standard;
        for (int i = 0; i < 4; i++) {
            standardList.add(standard[i]);
            zkList.add(i);
        }

        priceAdapter.notifyDataSetChanged();
    }

    //PVB
    private void addPvb() {
        standardList.clear();
        pvbList.clear();
        String[] standard = CalculatorFunction.getInstance().standard;
        int num = standard.length;
        for (int i = 4; i < num; i++) {
            standardList.add(standard[i]);
            pvbList.add(i);
        }

        priceAdapter.notifyDataSetChanged();
    }

    private StaggeredGridLayoutManager getLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        return layoutManager;
    }


    //计算
    private void getCalData() {
        double canValue = 0;

        if (isZk) {
            if (zkClickPosition == -1) {
                return;
            }
            canValue = CalculatorFunction.getInstance().standardKye[zkList.get(zkClickPosition)];
        } else {
            if (pvbClickPosition == -1) {
                return;
            }
            canValue = CalculatorFunction.getInstance().standardKye[pvbList.get(pvbClickPosition)];
        }

        double ton = CalculatorFunction.getInstance().buildPrice(area, thick, canValue);
        tvTon.setText(TwoPointUtils.getInstance().getTwoPoint(ton / 1000));
        tvWeightNum.setText(TwoPointUtils.getInstance().getTwoPoint(ton / 1000 * 20));
        tvKg.setText(TwoPointUtils.getInstance().getTwoPoint(ton));
    }


}
