package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.BaoRequirAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.BaoReqBean;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：BaoRequirActivity
 * 描述：
 * 创建时间：2019-05-14  16:47
 */

public class BaoRequirActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_zb_yq)
    ListView lvBaoReq;
    @Bind(R.id.tv_right)
    TextView tvRight;

    List<BaoReqBean> list = new ArrayList<>();
    BaoRequirAdapter baoRequirAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_bao_requir;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("报价要求");
        tvRight.setText("提交");

        BaoReqBean baoReqBean1 = new BaoReqBean();
        baoReqBean1.setContent("需报价");
        baoReqBean1.setCheck(true);
        BaoReqBean baoReqBean2 = new BaoReqBean();
        baoReqBean2.setContent("需送样品");
        BaoReqBean baoReqBean3 = new BaoReqBean();
        baoReqBean3.setContent("需提供案例");
        list.add(baoReqBean1);
        list.add(baoReqBean2);
        list.add(baoReqBean3);

        baoRequirAdapter = new BaoRequirAdapter(this, list, R.layout.item_bao_requir);
        lvBaoReq.setAdapter(baoRequirAdapter);
        lvBaoReq.setDividerHeight(0);

        lvBaoReq.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    return;
                }
                if (list.get(i).isCheck()) {
                    list.get(i).setCheck(false);
                } else {
                    list.get(i).setCheck(true);
                }
                baoRequirAdapter.notifyDataSetChanged();
            }
        });

    }

    @OnClick({R.id.ll_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                String str = "";
                int num = list.size();
                for (int i = 0; i < num; i++) {
                    if (list.get(i).isCheck()) {
                        str += list.get(i).getContent() + ",";
                    }
                }
                Intent intent = new Intent();
                intent.putExtra(IntentUtils.getInstance().TYPE, str);
                setResult(1, intent);
                this.finish();
                break;
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

}
