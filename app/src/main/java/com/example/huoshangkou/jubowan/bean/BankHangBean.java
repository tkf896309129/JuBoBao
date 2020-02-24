package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：BankHangBean
 * 描述：
 * 创建时间：2018-09-25  14:34
 */

public class BankHangBean {

    /**
     * ErrMsg :
     * ReObj : ["中信银行","中国邮政储蓄银行","中国银行","中国农业银行","中国农业发展银行","中国民生银行","中国进出口银行","中国建设银行","中国光大银行","中国工商银行","浙商银行","招商银行","友利银行（中国）","兴业银行","星展银行（中国）","新韩银行（中国）","天津银行","盛京银行","上海银行","上海浦东发展银行","厦门国际银行","瑞穗银行（中国）","企业银行（中国）","平安银行","宁波银行","南洋商业银行","南京银行","摩根士丹利国际银行（中国）","摩根大通银行（中国）","蒙特利尔银行（中国）","昆仑银行","锦州银行","交通银行","江苏银行","汇丰银行","华夏银行","华侨永亨银行（中国）","花旗银行（中国）","恒生银行","恒丰银行","杭州银行","韩亚银行（中国）","国民银行（中国）","国家开发银行","广发银行","富邦华一银行","法国兴业银行（中国）","法国巴黎银行（中国）","东亚银行","德意志银行（中国）","大连银行","大华银行（中国）","渤海银行","北京银行","北京延庆村镇银行","北京通州国开村镇银行","北京顺义银座村镇银行","北京平谷新华村镇银行","北京农村商业银行","北京密云汇丰村镇银行","北京门头沟珠江村镇银行","北京怀柔融兴村镇银行","北京房山沪农商村镇银行","北京大兴九银村镇银行","北京大兴华夏村镇银行","北京昌平包商村镇银行","包商银行","澳大利亚和新西兰银行（中国）"]
     * Success : 1
     */

    private String ErrMsg;
    private int Success;
    private List<String> ReObj;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int Success) {
        this.Success = Success;
    }

    public List<String> getReObj() {
        return ReObj;
    }

    public void setReObj(List<String> ReObj) {
        this.ReObj = ReObj;
    }
}
