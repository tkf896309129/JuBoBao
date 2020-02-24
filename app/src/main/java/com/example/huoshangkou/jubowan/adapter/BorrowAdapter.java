package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.LoanDetailActivity;
import com.example.huoshangkou.jubowan.activity.LoanIouActivity;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.LoanListBean;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：BorrowAdapter
 * 描述：
 * 创建时间：2017-09-08  09:40
 */

public class BorrowAdapter extends BaseAbstractAdapter<LoanListBean> {
    public BorrowAdapter(Context context, List<LoanListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, final LoanListBean bean, int position) {
        //借款借据
        TextView tvLoanCard = holder.getView(R.id.tv_loan_card);
        TextView tvLoanDetail = holder.getView(R.id.tv_loan_detail);
        TextView tvLoanMoney = holder.getView(R.id.tv_loan_money);
        TextView tvLoanState = holder.getView(R.id.tv_loan_state);
        TextView tvLoanId = holder.getView(R.id.tv_loan_id);
        TextView tvLoanTime = holder.getView(R.id.tv_loan_time);

        tvLoanMoney.setText("授信额度：￥" + bean.getRzMoey());
        tvLoanState.setText(bean.getRzState());
        tvLoanId.setText(bean.getContractNO() + "");
        tvLoanTime.setText(bean.getAuditTimeYea());

        tvLoanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StringUtils.isNoEmpty(bean.getRzState()) && !bean.getRzState().equals("审核通过")) {
                    CopyIosDialogUtils.getInstance().getErrorDialog(context, "您的信用额度需要审核通过才能进行借款", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                    return;
                }

                IntentUtils.getInstance().toActivity(context, LoanIouActivity.class, bean.getContractNO(), bean.getID() + "");
            }
        });

        tvLoanDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.getInstance().toActivity(context, LoanDetailActivity.class, bean.getID() + "");
            }
        });
    }
}
