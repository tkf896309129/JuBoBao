package com.example.huoshangkou.jubowan.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.SearchToolsActivity;
import com.example.huoshangkou.jubowan.activity.ToolKindActivity;
import com.example.huoshangkou.jubowan.adapter.BuyToolGridAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.ToolNewClassBean;
import com.example.huoshangkou.jubowan.bean.ToolOldClassBean;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：NewToolFragment
 * 描述：
 * 创建时间：2018-06-07  14:29
 */

public class NewToolFragment extends BaseFragment {
    @Bind(R.id.grid_view)
    GridView gridView;

    private ArrayList<ToolNewClassBean> newClass;
    BuyToolGridAdapter gridAdapter;


    public static NewToolFragment newInstance() {
        NewToolFragment fragment = new NewToolFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_new_tool;
    }

    @Override
    public void initData() {
        newClass = getArguments().getParcelableArrayList(IntentUtils.getInstance().LIST);
        if(newClass==null){
            return;
        }
        gridAdapter = new BuyToolGridAdapter(getActivity(), newClass, R.layout.item_buy_tool);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentUtils.getInstance().toActivity(getContext(), ToolKindActivity.class, newClass.get(i).getID() + "");
            }
        });

    }
}
