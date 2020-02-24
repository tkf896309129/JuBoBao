package com.example.huoshangkou.jubowan.inter;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnRepairWorkCallBack
 * 描述：
 * 创建时间：2017-03-02  14:56
 */

public interface OnRepairWorkCallBack {

    void onStartTime(int position);


    void onEndTime(int position);

    //删除工作经历
    void deleteWork(int position);

    void onAddWork();


    //添加公司
    void onAddCompany(int position);

    //添加职位
    void onAddWorkType(int position);

}
