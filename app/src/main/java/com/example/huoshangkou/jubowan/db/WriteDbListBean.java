package com.example.huoshangkou.jubowan.db;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.db
 * 类名：WriteDbListBean
 * 描述：
 * 创建时间：2017-08-15  09:52
 */

public class WriteDbListBean {

    private String id;
    private String baseMessage;
    private String problem;
    private String toolDesc;
    private String toolList;
    private String servicePrice;
    private String conclusion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaseMessage() {
        return baseMessage;
    }

    public void setBaseMessage(String baseMessage) {
        this.baseMessage = baseMessage;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getToolDesc() {
        return toolDesc;
    }

    public void setToolDesc(String toolDesc) {
        this.toolDesc = toolDesc;
    }

    public String getToolList() {
        return toolList;
    }

    public void setToolList(String toolList) {
        this.toolList = toolList;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
}
