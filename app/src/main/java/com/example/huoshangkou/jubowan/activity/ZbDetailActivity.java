package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.adapter.ZbDetailAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ZbDetailBean;
import com.example.huoshangkou.jubowan.bean.ZbMessageListBean;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ZbDetailActivity
 * 描述：
 * 创建时间：2017-04-18  13:22
 */

public class ZbDetailActivity extends BaseActivity {
    ZbDetailAdapter detailAdapterTop;
    ZbDetailAdapter detailAdapterBottom;
    List<ZbDetailBean> detailBeanListTop;
    List<ZbDetailBean> detailBeanListBottom;
    ZbMessageListBean messageListBean;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_zb_base)
    ScrollListView lvZbBase;
    @Bind(R.id.lv_zb_detail)
    ScrollListView lvZbDetail;
    @Bind(R.id.grid_zb_image)
    ScrollGridView gridZbImage;

    private String isNeedYp = "";
    private String isNeedAl = "";

    private List<String> imgList;

    private ImageAddAdapter addAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_zb_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("招标详情");
        imgList = new ArrayList<>();
        messageListBean = (ZbMessageListBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);
        //初始化数据
        if (messageListBean == null) {
            return;
        }

        detailBeanListTop = new ArrayList<>();
        detailBeanListBottom = new ArrayList<>();

        addAdapter = new ImageAddAdapter(getContext(), imgList);
        gridZbImage.setAdapter(addAdapter);

        getZbData(messageListBean);

        detailAdapterTop = new ZbDetailAdapter(getContext(), detailBeanListTop, R.layout.item_zb_detail);
        detailAdapterBottom = new ZbDetailAdapter(getContext(), detailBeanListBottom, R.layout.item_zb_detail);

        lvZbBase.setAdapter(detailAdapterTop);
        lvZbDetail.setAdapter(detailAdapterBottom);
        lvZbDetail.setDividerHeight(0);
        lvZbBase.setDividerHeight(0);


    }


    public void getZbData(ZbMessageListBean listBean) {

        if (listBean.getIsNeedAnli() == 0) {
            isNeedAl = "不需要案例";
        } else {
            isNeedAl = "需要案例";
        }

        if (listBean.getIsNeedYp() == 0) {
            isNeedYp = "不需要样品";
        } else {
            isNeedYp = "需要样品";
        }

        ZbDetailBean zbName = new ZbDetailBean();
        zbName.setType("项目名称");
        zbName.setValue(listBean.getProjectTitle());
        detailBeanListTop.add(zbName);

        ZbDetailBean zbType = new ZbDetailBean();
        zbType.setType("工程类型");
        zbType.setValue(listBean.getProjectType());
        detailBeanListTop.add(zbType);

        ZbDetailBean zbStandard = new ZbDetailBean();
        zbStandard.setType("项目规模");
        zbStandard.setValue(listBean.getArea() + "-" + listBean.getArea1() + "平方米");
        detailBeanListTop.add(zbStandard);

        ZbDetailBean zbLocation = new ZbDetailBean();
        zbLocation.setType("项目所在地");
        zbLocation.setValue(listBean.getProjectAddress());
        detailBeanListTop.add(zbLocation);

        ZbDetailBean zbDays = new ZbDetailBean();
        zbDays.setType("工期要求");
        zbDays.setValue(listBean.getNeedDay() + "天");
        detailBeanListTop.add(zbDays);

        ZbDetailBean zbPrice = new ZbDetailBean();
        zbPrice.setType("报价要求");
        zbPrice.setValue("需报价," + isNeedYp + "," + isNeedAl);
        detailBeanListTop.add(zbPrice);

        ZbDetailBean zbGonYi = new ZbDetailBean();
        zbGonYi.setType("玻璃工艺");
        zbGonYi.setValue(listBean.getFuliaos());
        detailBeanListBottom.add(zbGonYi);

        ZbDetailBean zbLow = new ZbDetailBean();
        zbLow.setType("膜系");
        zbLow.setValue(listBean.getMoxiName());
        detailBeanListBottom.add(zbLow);

        ZbDetailBean zbColor = new ZbDetailBean();
        zbColor.setType("颜色");
        zbColor.setValue(listBean.getColorVal());
        detailBeanListBottom.add(zbColor);

        ZbDetailBean zbTgValue = new ZbDetailBean();
        zbTgValue.setType("透光率");
        zbTgValue.setValue(listBean.getTouGuangVal());
        detailBeanListBottom.add(zbTgValue);

        ZbDetailBean zbKValue = new ZbDetailBean();
        zbKValue.setType("K值");
        zbKValue.setValue(listBean.getKval());
        detailBeanListBottom.add(zbKValue);

        ZbDetailBean zbZyNum = new ZbDetailBean();
        zbZyNum.setType("遮阳系数");
        zbZyNum.setValue(listBean.getZheyanVal());
        detailBeanListBottom.add(zbZyNum);

        ZbDetailBean zbFsInDoor = new ZbDetailBean();
        zbFsInDoor.setType("室内反射率");
        zbFsInDoor.setValue(listBean.getFanseInVal() + "");
        detailBeanListBottom.add(zbFsInDoor);

        ZbDetailBean zbFsOutDoor = new ZbDetailBean();
        zbFsOutDoor.setType("室外反射率");
        zbFsOutDoor.setValue(listBean.getFanseOutVal() + "");
        detailBeanListBottom.add(zbFsOutDoor);

        String[] split = listBean.getGongyis().split(";");
        String gonyi = "";
        int num = split.length;
        for (int i = 0; i < num; i++) {
            if (split[i].length() > 2) {
                gonyi += split[i];
            }
        }

        ZbDetailBean zbJgGy = new ZbDetailBean();
        zbJgGy.setType("加工工艺");
        zbJgGy.setValue(gonyi);
        detailBeanListBottom.add(zbJgGy);

        imgList.addAll(PhotoUtils.getInstance().getListImage(listBean.getRequestPics()));
        addAdapter.notifyDataSetChanged();

    }


    @OnClick({R.id.ll_back, R.id.tv_get_zb})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_get_zb:
                ToastUtils.getMineToast("立即抢标");
                break;
        }
    }
}
