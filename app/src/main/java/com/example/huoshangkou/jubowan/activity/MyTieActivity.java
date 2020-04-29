package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.JuBoScoreBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：MyTieActivity
 * 描述：我的帖子界面
 * 创建时间：2017-02-13  08:44
 */

public class MyTieActivity extends BaseActivity {
    @Bind(R.id.tv_tie_score)
    TextView tvScore;
    @Bind(R.id.tv_tie_level)
    TextView tvLevel;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl_tie_manager)
    RelativeLayout rlTieManager;

    private String level = "";
    private String score = "";
    private String url = "";

    @Override
    public int initLayout() {
        return R.layout.activity_my_tie;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("我的论坛");
        getForumScore();
    }

    //点击事件
    @OnClick({R.id.ll_back, R.id.rl_score, R.id.rl_tie_active,
            R.id.rl_my_save, R.id.rl_my_tie, R.id.rl_my_back, R.id.rl_tie_manager,
            R.id.tv_score_more, R.id.tv_money_true, R.id.tv_score_rule})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            //积分历史
            case R.id.rl_score:
                IntentUtils.getInstance().toActivity(getContext(), ScoreHistoryActivity.class);
                break;
            //论坛活跃度
            case R.id.rl_tie_active:
                IntentUtils.getInstance().toActivity(getContext(), TieActiveActivity.class, level);
                break;
            //我的收藏
            case R.id.rl_my_save:
                IntentUtils.getInstance().toActivity(getContext(), MineReceiveTieActivity.class, "Receive");
                break;
            //我的帖子
            case R.id.rl_my_tie:
                IntentUtils.getInstance().toActivity(getContext(), MineReceiveTieActivity.class, "MineTie");
                break;
            //与我相关
            case R.id.rl_my_back:
                IntentUtils.getInstance().toActivity(getContext(), MineBackTieActivity.class);
                break;
            //论坛管理
            case R.id.rl_tie_manager:
                IntentUtils.getInstance().toActivity(getContext(), TieManagerActivity.class);
                break;
            //积分充值
            case R.id.tv_score_more:
                IntentUtils.getInstance().toActivity(getContext(), AddRepairScoreActivity.class, score);
                break;
            //积分提现
            case R.id.tv_money_true:
                IntentUtils.getInstance().toActivity(getContext(), GetRepairMoneyActivity.class, score);
                break;
            //积分规则
            case R.id.tv_score_rule:
                IntentUtils.getInstance().toWebActivity(getContext(), url, "积分规则");
                break;
        }
    }

    //获取论坛等级分数
    public void getForumScore() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_POST_LEVEL + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                JuBoScoreBean scoreBean = JSON.parseObject(json, JuBoScoreBean.class);
                url = scoreBean.getReObj().getGuiZeUrl();
                score = scoreBean.getReObj().getJubobi() + "";
                tvScore.setText(scoreBean.getReObj().getJubobi() + "");
                level = scoreBean.getReObj().getJuboLevelName();
                tvLevel.setText(level);

                if (scoreBean.getReObj().getVisible() == 0) {
                    rlTieManager.setVisibility(View.GONE);
                } else {
                    rlTieManager.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFail() {

            }
        });
    }
}
