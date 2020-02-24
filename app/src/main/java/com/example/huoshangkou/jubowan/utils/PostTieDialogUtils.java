package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.ImageShowActivity;
import com.example.huoshangkou.jubowan.activity.PostTieActivity;
import com.example.huoshangkou.jubowan.adapter.PostTieTypeAdapter;
import com.example.huoshangkou.jubowan.bean.TieTypeBean;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.widget.zoonview.PhotoView;
import cn.finalteam.galleryfinal.widget.zoonview.PhotoViewAttacher;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：PostTieDialogUtils
 * 描述：
 * 创建时间：2017-05-05  10:15
 */

public class PostTieDialogUtils {

    private static class PostTieDialogHelper {
        private static PostTieDialogUtils INSTANCE = new PostTieDialogUtils();
    }

    public static PostTieDialogUtils getInstance() {
        return PostTieDialogHelper.INSTANCE;
    }

    private AlertDialog dialog;
    private Context mContext;
    private PostTieTypeAdapter mTieAdapter;
    private PostTieTypeAdapter mTieAdapter_2;
    List<TieTypeBean> mTieTypeList;
    List<TieTypeBean> mTieTypeList_2;
    List<PostTieTypeAdapter>  adapterList;

    public void getPostTieDialogUtils(final Context context) {
        dialog = new AlertDialog.Builder(context).create();
        mContext = context;
        mTieTypeList = new ArrayList<>();
        mTieTypeList_2 = new ArrayList<>();
        adapterList = new ArrayList<>();

        initPostTieData();
        mTieAdapter = new PostTieTypeAdapter(context, mTieTypeList, R.layout.item_post_tie);
        mTieAdapter_2 = new PostTieTypeAdapter(context, mTieTypeList_2, R.layout.item_post_tie);
        adapterList.add(mTieAdapter);
        adapterList.add(mTieAdapter_2);

        dialog.show();
        dialog.getWindow().setContentView(R.layout.post_tie_dialog);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setWindowAnimations(R.style.PopupAnimation);
        Window view = dialog.getWindow();

        WindowManager.LayoutParams lp = view.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        view.setAttributes(lp);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.vp_post_tie);

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                GridView gridView = new GridView(context);
                gridView.setNumColumns(4);
                gridView.setAdapter(adapterList.get(position));
                container.addView(gridView);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        dialog.dismiss();
                        IntentUtils.getInstance().toActivity(mContext, PostTieActivity.class, mTieTypeList.get(i).getId(), mTieTypeList.get(i).getType());
                    }
                });

                return gridView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });


        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);

        LinearLayout llDismiss = (LinearLayout) view.findViewById(R.id.ll_dismiss);

        llDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }


    //初始化发帖标签
    public void initPostTieData() {

//        TieTypeBean typeBean_zixun = new TieTypeBean();
//        typeBean_zixun.setType("聚玻早报");
//        typeBean_zixun.setId("10");
//        typeBean_zixun.setImage(R.mipmap.zq_jubonews);
//        mTieTypeList.add(typeBean_zixun);

        TieTypeBean typeBean_repair = new TieTypeBean();
        typeBean_repair.setType("维修维保");
        typeBean_repair.setId("9");
        typeBean_repair.setImage(R.mipmap.zq_weixiu);
        mTieTypeList.add(typeBean_repair);

        TieTypeBean typeBean_yuan = new TieTypeBean();
        typeBean_yuan.setType("原片交流");
        typeBean_yuan.setId("1");
        typeBean_yuan.setImage(R.mipmap.zq_yuanpian);
        mTieTypeList.add(typeBean_yuan);

        TieTypeBean typeBean_fu = new TieTypeBean();
        typeBean_fu.setType("辅料交流");
        typeBean_fu.setId("2");
        typeBean_fu.setImage(R.mipmap.zq_fuliao);
        mTieTypeList.add(typeBean_fu);

        TieTypeBean typeBean_repair_trans = new TieTypeBean();
        typeBean_repair_trans.setType("物流专区");
        typeBean_repair_trans.setId("3");
        typeBean_repair_trans.setImage(R.mipmap.zq_wuliu);
        mTieTypeList.add(typeBean_repair_trans);

        TieTypeBean typeBean_repair_news = new TieTypeBean();
        typeBean_repair_news.setType("时事新闻");
        typeBean_repair_news.setId("4");
        typeBean_repair_news.setImage(R.mipmap.zq_news);
        mTieTypeList.add(typeBean_repair_news);

        TieTypeBean typeBean_repair_fun = new TieTypeBean();
        typeBean_repair_fun.setType("搞笑内涵");
        typeBean_repair_fun.setId("5");
        typeBean_repair_fun.setImage(R.mipmap.zq_gaoxiao);
        mTieTypeList.add(typeBean_repair_fun);

        TieTypeBean typeBean_repair_water = new TieTypeBean();
        typeBean_repair_water.setType("热门话题");
        typeBean_repair_water.setId("6");
        typeBean_repair_water.setImage(R.mipmap.zq_huati);
        mTieTypeList.add(typeBean_repair_water);

        TieTypeBean typeBean_repair_life = new TieTypeBean();
        typeBean_repair_life.setType("秀一秀");
        typeBean_repair_life.setId("7");
        typeBean_repair_life.setImage(R.mipmap.zq_xiuyixiu);
        mTieTypeList.add(typeBean_repair_life);

    }
}
