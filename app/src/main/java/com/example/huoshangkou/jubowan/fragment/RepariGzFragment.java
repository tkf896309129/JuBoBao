package com.example.huoshangkou.jubowan.fragment;

import android.widget.GridView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.LoginActivity;
import com.example.huoshangkou.jubowan.activity.MyRepairOrderActivity;
import com.example.huoshangkou.jubowan.activity.RepairRepliyActivity;
import com.example.huoshangkou.jubowan.adapter.RepairGzAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.RepairTypeListBean;
import com.example.huoshangkou.jubowan.inter.OnRepairClick;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LoginUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：RepariGzFragment
 * 描述：
 * 创建时间：2018-06-08  08:21
 */

public class RepariGzFragment extends BaseFragment {

    @Bind(R.id.grid_view)
    GridView gridView;

    List<String> list;
    private ArrayList<RepairTypeListBean> wXList;
    RepairGzAdapter gridAdapter;


    public static RepariGzFragment newInstance() {
        RepariGzFragment fragment = new RepariGzFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_repair_gz;
    }

    @Override
    public void initData() {
        wXList = getArguments().getParcelableArrayList(IntentUtils.getInstance().LIST);
        if (wXList == null) {
            return;
        }
        gridAdapter = new RepairGzAdapter(getActivity(), wXList, R.layout.item_repair_gz);
        gridView.setAdapter(gridAdapter);

        gridAdapter.setRepairClick(new OnRepairClick() {
            //维修申报
            @Override
            public void onTopClick(int position) {
                if (!LoginUtils.getInstance().isLogin(getActivity())) {
                    IntentUtils.getInstance().toActivity(getActivity(), LoginActivity.class);
                    return;
                }
                IntentUtils.getInstance().toActivity(getActivity(), RepairRepliyActivity.class, wXList.get(position), "规格改造");
            }

            //查看维修订单
            @Override
            public void onBottomClick(int position) {
                if (!LoginUtils.getInstance().isLogin(getActivity())) {
                    IntentUtils.getInstance().toActivity(getActivity(), LoginActivity.class);
                    return;
                }
                //暂时隐藏
//                IntentUtils.getInstance().toActivity(getActivity(), MyRepairOrderActivity.class, wXList.get(position).getID() + "","规格改造");
            }
        });
    }
}
