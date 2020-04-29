package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.CompanyAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.CompanyBean;
import com.example.huoshangkou.jubowan.bean.CompanyListBean;
import com.example.huoshangkou.jubowan.bean.SelectManBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.widget.ContactItemInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：VisitorResetActivity
 * 描述：客户回访
 * 创建时间：2018-01-22  14:31
 */

public class VisitorResetActivity extends BaseActivity {
    @Bind(R.id.et_search)
    EditText editText;
    @Bind(R.id.lv_search)
    ListView listView;
    @Bind(R.id.ll_dialog_search)
    LinearLayout llDialogSearch;

    @Bind(R.id.tv_title)
    TextView tvTitle;

    private CompanyAdapter searchAdapter;

    //审批人员
    ArrayList<CompanyListBean> memberList;

    @Override
    public int initLayout() {
        return R.layout.activity_visitor_reset;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        memberList = new ArrayList<>();

        searchAdapter = new CompanyAdapter(this, memberList, R.layout.item_company_list);
        listView.setAdapter(searchAdapter);
        listView.setDividerHeight(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra(IntentUtils.getInstance().TYPE,memberList.get(i).getCompany());
                intent.putExtra(IntentUtils.getInstance().VALUE,memberList.get(i).getID());
                setResult(1,intent);
                VisitorResetActivity.this.finish();
            }
        });



        tvTitle.setText("拜访对象");



        //改变输入内容
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String search = s.toString().trim();
//                searchList.clear();
//                if (StringUtils.isNoEmpty(search)) {
//                    llDialogSearch.setVisibility(View.VISIBLE);
//                } else {
//                    llDialogSearch.setVisibility(View.INVISIBLE);
//                }
//                int num = selectList.size();
//
//                if (StringUtils.isNoEmpty(search) && selectList.size() == 0) {
//                    searchList.add(new SelectManBean("暂无搜索结果", "", ""));
//                    searchAdapter.notifyDataSetChanged();
//                    return;
//                }
//
//                for (int i = 0; i < num; i++) {
//
//                    if(selectList.get(i).getDisplayInfo().indexOf(search)!=-1){
//                        searchList.add(selectList.get(i));
//                    }
//                }
//                if (StringUtils.isNoEmpty(search) && searchList.size() == 0) {
//                    searchList.add(new SelectManBean("暂无搜索结果", "", ""));
//                }
//                searchAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }

    @OnClick({R.id.ll_back,R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
               this.finish();
                break;
            case R.id.tv_search:
                String search = editText.getText().toString().trim();
                getVisitor(search);
                break;
        }
    }

    public void getVisitor(final String keyWord){
        memberList.clear();
        OkhttpUtil.getInstance().setUnCacheData(this, getString(R.string.loading_message), UrlConstant.getInstance().URL + PostConstant.getInstance().GET_RELATE_COMPANY
                + FieldConstant.getInstance().USER_ID + "="+PersonConstant.getInstance().getUserId()+"&"
                +FieldConstant.getInstance().KEY_WORD+"="+ EncodeUtils.getInstance().getEncodeString(keyWord), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                CompanyBean memberBean = JSON.parseObject(json, CompanyBean.class);
                memberList.addAll(memberBean.getReList());
                searchAdapter.notifyDataSetChanged();

                if(memberList.size()==0){
                    CopyIosDialogUtils.getInstance().getIosDialogNoCancel(keyWord,getContext(), "暂无搜索结果，请手动输入", new StringCallBack() {
                        @Override
                        public void onSuccess(String str) {
                            Intent intent = new Intent();
                            intent.putExtra(IntentUtils.getInstance().TYPE,str);
                            intent.putExtra(IntentUtils.getInstance().VALUE,"0");
                            setResult(1,intent);
                            VisitorResetActivity.this.finish();
                        }

                        @Override
                        public void onFail() {

                        }
                    });
                }

            }

            @Override
            public void onFail() {

            }
        });
    }
}
