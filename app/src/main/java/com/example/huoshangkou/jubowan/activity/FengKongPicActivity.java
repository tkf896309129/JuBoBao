package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.FengKongPicAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.FengKongPicBean;
import com.example.huoshangkou.jubowan.bean.SuccessDBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：FengKongPicActivity
 * 描述：
 * 创建时间：2019-07-23  09:56
 */

public class FengKongPicActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.lv_fk_pic)
    ScrollListView lvFkPic;
    @Bind(R.id.grid_tie_photo)
    ScrollGridView gridTiePhoto;
    @Bind(R.id.tv_pic_type)
    TextView tvTypePic;
    List<FengKongPicBean.DBean.ResultBean.ModelBean> list = new ArrayList<>();
    FengKongPicAdapter picAdapter;

    private String type = "";
    private String title = "";
    private String pic = "";
    private List<String> imgList = new ArrayList<>();
    private ImageAddAdapter addAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_fk_pic;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        type = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        title = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        tvTitle.setText(title);
        tvTypePic.setText(title);
        tvRight.setText("提交");
        picAdapter = new FengKongPicAdapter(this, list, R.layout.item_fk_pic_list);
        lvFkPic.setAdapter(picAdapter);
        lvFkPic.setDividerHeight(0);

        imgList.add(PhotoConstant.getInstance().TIE_ADD_PHOTOT);
        addAdapter = new ImageAddAdapter(this, imgList, PhotoConstant.getInstance().TIE_TYPE);
        gridTiePhoto.setAdapter(addAdapter);

        //点击事件
        addAdapter.setAddPhotoImg(new ImageAddAdapter.addPhotoClick() {
            @Override
            public void onAddPthotoClick() {
                PhotoUtils.getInstance().updateDialog(FengKongPicActivity.this, new PhotoCallBack() {
                    @Override
                    public void getPhoto(String path) {
                        imgList.remove(imgList.size() - 1);
                        imgList.addAll(PhotoUtils.getInstance().getListImage(path));
                        imgList.add(PhotoConstant.getInstance().TIE_ADD_PHOTOT);
                        addAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        //删除图片
        addAdapter.setDeleteImg(new ImageAddAdapter.deleteClick() {
            @Override
            public void deleteImgClick(final int position) {
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否删除图片", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        imgList.remove(position);
                        addAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });

        //获取数据
        getPicData();
    }

    @OnClick({R.id.ll_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                if (imgList.size() == 0) {
                    return;
                }
                imgList.remove(imgList.size() - 1);
                List<String> localList = new ArrayList<>();
                final List<String> netList = new ArrayList<>();
                for (int i = 0; i < imgList.size(); i++) {
                    if (imgList.get(i).contains("http")) {
                        netList.add(imgList.get(i));
                    } else {
                        localList.add(imgList.get(i));
                    }
                }
                if (imgList.size() == 0) {
                    ToastUtils.getMineToast("请选择要上传的图片");
                    imgList.add(PhotoConstant.getInstance().TIE_ADD_PHOTOT);
                    return;
                }
                if (localList.size() == 0) {
                    String imageStr = PhotoUtils.getInstance().getImageStr(netList);
                    pic = imageStr;
                    putPicData();
                    return;
                }

                PhotoUtils.getInstance().mutilLocalImageUp(localList, getContext(), new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        LogUtils.e(str);
                        if (netList.size() != 0) {
                            String imageStr = PhotoUtils.getInstance().getImageStr(netList);
                            pic = imageStr + "," + str;
                        } else {
                            pic = str;
                        }
                        putPicData();
                    }

                    @Override
                    public void onFail() {

                    }
                });
                break;
        }
    }

    public void putPicData() {
        Map<String, String> map = new HashMap<>();
        map.put("UserId", PersonConstant.getInstance().getUserId());
        map.put("InfoType", type);
        map.put("InfoItem", pic);
        map.put("State", "0");
        String json = JSON.toJSONString(map);
        String putJson = "{\"model\":" + json + "}";
        OkhttpUtil.getInstance().basePostCall(this, putJson, UrlConstant.getInstance().ADD_FENG_KONG_PIC_URL, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                SuccessDBean successDBean = JSON.parseObject(str, SuccessDBean.class);
                if (successDBean.getD().getErrorCode() == 0) {
                    ToastUtils.getMineToast("提交成功");
                    FengKongPicActivity.this.finish();
                } else {
                    ToastUtils.getMineToast(successDBean.getD().getErrMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    public void getPicData() {
        Map<String, String> map = new HashMap<>();
        map.put("userid", PersonConstant.getInstance().getUserId());
        map.put("type", type);
        String json = JSON.toJSONString(map);
//        String putJson = "{\"model\":" + json + "}";
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().GET_FENG_KONG_PIC_URL, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                FengKongPicBean picBean = JSON.parseObject(str, FengKongPicBean.class);
                FengKongPicBean.DBean.ResultBean.ModelBean model = picBean.getD().getResult().getModel();

                if (model != null && model.getFileItems() != null && model.getFileItems().size() != 0) {
                    imgList.remove(imgList.size() - 1);
                    imgList.addAll(model.getFileItems());
                    imgList.add(PhotoConstant.getInstance().TIE_ADD_PHOTOT);
                    addAdapter.notifyDataSetChanged();
                }
                List<FengKongPicBean.DBean.ResultBean.ModelBean> listModel = picBean.getD().getResult().getList();
                if (listModel != null && listModel.size() != 0) {
                    list.addAll(listModel);
                    picAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
