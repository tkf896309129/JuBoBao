package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.DianTabBean;
import com.example.huoshangkou.jubowan.inter.OnStringPositionCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.DoubleUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：DianTabAdapter
 * 描述：
 * 创建时间：2018-06-07  11:44
 */

public class DianTabAdapter extends BaseAbstractAdapter<DianTabBean> {

    TextView tvWeightTotal;
    TextView tvThjTotal;
    TextView tvTotal;
    TextView tvXsjTotal;
    TextView tvYlTotal;
    OnStringPositionCallBack stringCallBack;
    boolean isEdit = false;

    public DianTabAdapter(Context context, List<DianTabBean> listData, TextView tvWeightTotal,
                          TextView tvThjTotal, TextView tvTotal, TextView tvXsjTotal,
                          TextView tvYlTotal, boolean isEdit, int resId) {
        super(context, listData, resId);
        this.isEdit = isEdit;
        this.tvWeightTotal = tvWeightTotal;
        this.tvThjTotal = tvThjTotal;
        this.tvTotal = tvTotal;
        this.tvXsjTotal = tvXsjTotal;
        this.tvYlTotal = tvYlTotal;
    }

    @Override
    public void convert(ViewHolder holder, final DianTabBean bean, final int position) {
        EditText tvWeight = holder.getView(R.id.tv_weight);
        EditText tvThj = holder.getView(R.id.tv_thj);
        EditText etCbj = holder.getView(R.id.et_cbj);
        EditText tvXsj = holder.getView(R.id.tv_xsj);
        EditText tvYl = holder.getView(R.id.tv_yl);
        TextView tvIntro = holder.getView(R.id.tv_intro);

        tvWeight.clearFocus();
        tvThj.clearFocus();
        etCbj.clearFocus();
        tvXsj.clearFocus();
        tvYl.clearFocus();

        //先清除之前的文本改变监听
        if (tvWeight.getTag() instanceof TextWatcher) {
            tvWeight.removeTextChangedListener((TextWatcher) (tvWeight.getTag()));
        }
        if (tvThj.getTag() instanceof TextWatcher) {
            tvThj.removeTextChangedListener((TextWatcher) (tvThj.getTag()));
        }
        if (etCbj.getTag() instanceof TextWatcher) {
            etCbj.removeTextChangedListener((TextWatcher) (etCbj.getTag()));
        }
        if (tvXsj.getTag() instanceof TextWatcher) {
            tvXsj.removeTextChangedListener((TextWatcher) (tvXsj.getTag()));
        }
        if (tvYl.getTag() instanceof TextWatcher) {
            tvYl.removeTextChangedListener((TextWatcher) (tvYl.getTag()));
        }


        tvWeight.setText(StringUtils.getNoNullStr(bean.getWeight()));
        tvThj.setText(StringUtils.getNoNullStr(bean.getTiHuoPrice()));
        tvIntro.setText(StringUtils.getNoNullStr(bean.getTableRemark()));
        tvXsj.setText(StringUtils.getNoNullStr(bean.getXiaoShouPrice()));
        tvYl.setText(StringUtils.getNoNullStr(bean.getYingLiPrice()));

        if (!isEdit) {
            tvWeight.setClickable(false);
            tvWeight.setFocusable(false);
            tvThj.setClickable(false);
            tvThj.setFocusable(false);
            tvXsj.setClickable(false);
            tvXsj.setFocusable(false);
            tvYl.setClickable(false);
            tvYl.setFocusable(false);
            etCbj.setClickable(false);
            etCbj.setFocusable(false);
        } else {
            tvWeight.setClickable(true);
            tvWeight.setFocusable(true);
            tvThj.setClickable(true);
            tvThj.setFocusable(true);
            tvXsj.setClickable(true);
            tvXsj.setFocusable(true);
            tvYl.setClickable(true);
            tvYl.setFocusable(true);
            etCbj.setClickable(true);
            etCbj.setFocusable(true);
        }
        final TextWatcher weightWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    bean.setWeight(null);
                } else {
                    bean.setWeight(StringUtils.getNoSapceStr(String.valueOf(editable)));
                }
            }
        };
        final TextWatcher thjWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    bean.setTiHuoPrice(null);
                } else {
                    bean.setTiHuoPrice(StringUtils.getNoSapceStr(String.valueOf(editable)));
                }
            }
        };
        final TextWatcher introWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    bean.setTableRemark(null);
                } else {
                    bean.setTableRemark(StringUtils.getNoSapceStr(String.valueOf(editable)));
                }
            }
        };
        final TextWatcher xsjWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    bean.setXiaoShouPrice(null);
                } else {
                    bean.setXiaoShouPrice(StringUtils.getNoSapceStr(String.valueOf(editable)));
                }
            }
        };
        final TextWatcher ylWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    bean.setYingLiPrice(null);
                } else {
                    bean.setYingLiPrice(StringUtils.getNoSapceStr(String.valueOf(editable)));
                }
            }
        };
        final TextWatcher oneChjWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    bean.setChengBenPrice(null);
                } else {
                    bean.setChengBenPrice(StringUtils.getNoSapceStr(String.valueOf(editable)));
                }

//                totalTable();
            }
        };


//        tvWeight.setText(StringUtils.getNoNullStr(bean.getWeight()));
//        tvThj.setText(StringUtils.getNoNullStr(bean.getTiHuoPrice()));
//        tvIntro.setText(StringUtils.getNoNullStr(bean.getTableRemark()));
//        tvXsj.setText(StringUtils.getNoNullStr(bean.getXiaoShouPrice()));
//        tvYl.setText(StringUtils.getNoNullStr(bean.getYingLiPrice()));

        tvWeight.addTextChangedListener(weightWatcher);
        tvWeight.setTag(weightWatcher);
        tvWeight.setText(TextUtils.isEmpty(bean.getWeight()) ? "" : bean.getWeight());

        tvThj.addTextChangedListener(thjWatcher);
        tvThj.setTag(thjWatcher);
        tvThj.setText(TextUtils.isEmpty(bean.getTiHuoPrice()) ? "" : bean.getTiHuoPrice());

        tvXsj.addTextChangedListener(xsjWatcher);
        tvXsj.setTag(xsjWatcher);
        tvXsj.setText(TextUtils.isEmpty(bean.getXiaoShouPrice()) ? "" : bean.getXiaoShouPrice());

        tvYl.addTextChangedListener(ylWatcher);
        tvYl.setTag(ylWatcher);
        tvYl.setText(TextUtils.isEmpty(bean.getYingLiPrice()) ? "" : bean.getYingLiPrice());

        etCbj.addTextChangedListener(oneChjWatcher);
        etCbj.setTag(oneChjWatcher);
        etCbj.setText(TextUtils.isEmpty(bean.getChengBenPrice()) ? "" : bean.getChengBenPrice());


        tvIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 0) {
                    stringCallBack.onClick("", position);
                }
            }
        });
    }

    //计算表格总金额
    private void totalTable() {


        double weightTotal = 0;
        double tihuoTotal = 0;
        double chenbenTotal = 0;
        double saleTotal = 0;
        double ylTotal = 0;

        int num = listData.size();
        for (int i = 0; i < num; i++) {
            weightTotal += DoubleUtils.getDouble(listData.get(i).getWeight());
            tihuoTotal += DoubleUtils.getDouble(listData.get(i).getTiHuoPrice());
            chenbenTotal += DoubleUtils.getDouble(listData.get(i).getChengBenPrice());
            saleTotal += DoubleUtils.getDouble(listData.get(i).getXiaoShouPrice());
            ylTotal += DoubleUtils.getDouble(listData.get(i).getYingLiPrice());
        }

        tvWeightTotal.setText(weightTotal + "");
        tvThjTotal.setText(tihuoTotal + "");
        tvTotal.setText(chenbenTotal + "");
        tvXsjTotal.setText(saleTotal + "");
        tvYlTotal.setText(ylTotal + "");
    }

    public void setStringCallBack(OnStringPositionCallBack stringCallBack) {
        this.stringCallBack = stringCallBack;
    }
}
