package com.example.huoshangkou.jubowan.activity;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.SignDetailFunction;
import com.example.huoshangkou.jubowan.adapter.SignDetailAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.SignDayBean;
import com.example.huoshangkou.jubowan.bean.SignDetailBean;
import com.example.huoshangkou.jubowan.bean.SignMonthBean;
import com.example.huoshangkou.jubowan.bean.VisitorListBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ChooseDialogPositionCallBack;
import com.example.huoshangkou.jubowan.inter.OnSignDetailCallBack;
import com.example.huoshangkou.jubowan.inter.OnSignDetailClick;
import com.example.huoshangkou.jubowan.inter.OnStringPositionCallBack;
import com.example.huoshangkou.jubowan.inter.SignVistorCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：SignDetailsActivity
 * 描述：
 * 创建时间：2017-04-05  09:40
 */

public class SignDetailsActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.ep_sign_detail)
    ExpandableListView epListView;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_back)
    LinearLayout llBack;

    //头像
    private String pic = "";
    private SignDetailAdapter signDetailAdapter;
    private List<List<SignDayBean>> child_list;
    private List<SignMonthBean> group_list;
    TextView tvTime;
    TextView tvName;
    TextView tvSignTimes;
    RelativeLayout rlHead;
    private String time = "";
    private String id = "";
    private String company = "";
    //是否是拜访对象
    private boolean isVisitor = false;
    //是否是公司查询
    private boolean isCompany = false;
    private String postConstant = "";
    private List<VisitorListBean> listBean = new ArrayList<>();
    private List<String> listType = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_sign_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveCheckList);
        child_list = new ArrayList<>();
        group_list = new ArrayList<>();

        tvTitle.setText("考勤详情");
        time = DateUtils.getCurrentMonthDate();

        id = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        pic = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        String visitor = getIntent().getStringExtra(IntentUtils.getInstance().STR);

        addHeadView();

        if (StringUtils.isNoEmpty(visitor)) {
            isVisitor = true;
            if (visitor.equals("company")) {
                isCompany = true;
                company = id;
                rlHead.setVisibility(View.GONE);
            } else {
                isCompany = false;
            }
            postConstant = PostConstant.getInstance().VISIT_DETAIL;
//            tvTitle.setText();
        } else {
            postConstant = PostConstant.getInstance().GET_USER_TRACK;
        }

        signDetailAdapter = new SignDetailAdapter(group_list, getContext(), child_list);
        epListView.setAdapter(signDetailAdapter);
        epListView.setDividerHeight(0);
        llBack.setOnClickListener(this);
        signDetailAdapter.setSignVistorCallBack(new SignVistorCallBack() {
            @Override
            public void showVistor(final SignDayBean bean) {
                DialogUtils.getInstance().getBaseDialog(getContext(), listType, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        String url = listBean.get(position).getUrl() + "?userid=" + PersonConstant.getInstance().getUserId() + "&fromuserid=" + bean.getVisitingObjectsId() + "&companyname=" + EncodeUtils.getInstance().getEncodeString(bean.getCompany());
                        IntentUtils.getInstance().toWebActivity(getContext(), url, str);
                    }
                });
            }
        });

        int width = getWindowManager().getDefaultDisplay().getWidth();
        epListView.setIndicatorBounds(width, width);
        epListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });

        //点击事件
        signDetailAdapter.setSignDetailCallBack(new OnSignDetailClick() {
            @Override
            public void onChildClick(int groupPosition, int childPosition) {
                if (isCompany) {
                    return;
                }
                if (isVisitor || StringUtils.isNoEmpty(child_list.get(groupPosition).get(childPosition).getCompany())) {
                    IntentUtils.getInstance().toActivity(SignDetailsActivity.this, SignDetailsActivity.class, child_list.get(groupPosition).get(childPosition).getCompany(), pic, "company");
                    return;
                }

                IntentUtils.getInstance().toActivity(getContext(), SignMapActivity.class,
                        child_list.get(groupPosition).get(childPosition).getX() + "、"
                                + child_list.get(groupPosition).get(childPosition).getY() + "、"
                                + child_list.get(groupPosition).get(childPosition).getAddress() + "、"
                                + child_list.get(groupPosition).get(childPosition).getCreateTime());
            }
        });

        getSignData(time, postConstant, company);
        //获取拜访公司
        getVisitorUrl();
    }

    //添加头部
    private void addHeadView() {
        View view = LayoutInflater.from(this).inflate(R.layout.sign_detail_top, null);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
        rlHead = (RelativeLayout) view.findViewById(R.id.rl_head);
        ImageView imgSignPic = (ImageView) view.findViewById(R.id.iv_sign_pic);

        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvSignTimes = (TextView) view.findViewById(R.id.tv_sign_times);
        GlideUtils.getInstance().displayCricleImage(this, pic, imgSignPic);
        tvTime.setOnClickListener(this);

        try {
            epListView.addHeaderView(view);
        } catch (Exception e) {

        }
        tvTime.setText("  " + time + "  ");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_time:
                TimeDialogUtils.getInstance().getMonth(getContext(), new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        time = str;
                        if (tvTime != null) {
                            tvTime.setText("  " + time + "  ");
                        }
                        group_list.clear();
                        child_list.clear();
                        getSignData(time, postConstant, company);
                    }

                    @Override
                    public void onFail() {

                    }
                });
                break;
            case R.id.ll_back:
                this.finish();
                break;

        }
    }


    //获取签到信息
    public void getSignData(String times, String postConstant, String company) {
        //获取考勤详情
        SignDetailFunction.getInstance().getSignDetail(company, postConstant, times, this, id, new OnSignDetailCallBack() {
            @Override
            public void onSuccess(SignDetailBean detailBean) {

                tvName.setText(detailBean.getLinkMan());
                String times = "本月签到" + detailBean.getCounts() + "次";
                SpannableStringBuilder spannableString = new SpannableStringBuilder();
                spannableString.append(times);
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.main_tab_blue_all)), 4, times.length() - 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

                tvSignTimes.setText(spannableString);

                if (detailBean.getMonth() == null) {
                    llNoData.setVisibility(View.VISIBLE);
                    return;
                }
                group_list.addAll(detailBean.getMonth());
                int num = group_list.size();
                for (int i = 0; i < num; i++) {
                    child_list.add(group_list.get(i).getDay());
                }

                if (child_list.size() == 0) {
                    llNoData.setVisibility(View.VISIBLE);
                } else {
                    llNoData.setVisibility(View.GONE);
                }
                for (int i = 0; i < signDetailAdapter.getGroupCount(); i++) {
                    epListView.expandGroup(i);
                }
                if (isCompany && detailBean.getMonth().size() > 0 && detailBean.getMonth().get(0).getDay().size() > 0) {
                    tvTitle.setText(detailBean.getMonth().get(0).getDay().get(0).getCompany());
                }
                signDetailAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }

    //获取拜访链接
    public void getVisitorUrl() {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this, UrlConstant.getInstance().VISITOR_URL, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                List<VisitorListBean> list = JSONArray.parseArray(json, VisitorListBean.class);
                listBean.addAll(list);
                int num = list.size();
                for (int i = 0; i < num; i++) {
                    listType.add(list.get(i).getName());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
