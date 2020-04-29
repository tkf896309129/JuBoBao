package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ChildGroupBean
 * 描述：
 * 创建时间：2019-05-29  10:21
 */

public class ChildGroupBean {


    /**
     * ErrMsg :
     * ReObj : [{"ID":1,"IsManager":0,"ParentId":null,"RoleName":"总裁办","UserID":670},{"ID":2,"IsManager":0,"ParentId":1,"RoleName":"行政部","UserID":670},{"ID":3,"IsManager":0,"ParentId":null,"RoleName":"技术部                                               ","UserID":670},{"ID":4,"IsManager":0,"ParentId":1053,"RoleName":"财务部","UserID":670},{"ID":5,"IsManager":0,"ParentId":null,"RoleName":"销售部                                               ","UserID":670},{"ID":6,"IsManager":0,"ParentId":null,"RoleName":"运营部                                               ","UserID":670},{"ID":8,"IsManager":0,"ParentId":5,"RoleName":"成都运营部","UserID":670},{"ID":9,"IsManager":0,"ParentId":5,"RoleName":"北京运营部","UserID":670},{"ID":10,"IsManager":0,"ParentId":5,"RoleName":"武汉运营部","UserID":670},{"ID":11,"IsManager":0,"ParentId":null,"RoleName":"物流部                                               ","UserID":670},{"ID":12,"IsManager":0,"ParentId":5,"RoleName":"广州运营部","UserID":670},{"ID":13,"IsManager":0,"ParentId":1053,"RoleName":"金融部","UserID":670},{"ID":14,"IsManager":0,"ParentId":5,"RoleName":"西安运营部","UserID":670},{"ID":15,"IsManager":0,"ParentId":null,"RoleName":"维修部                                               ","UserID":670},{"ID":17,"IsManager":0,"ParentId":null,"RoleName":"技术部                                               ","UserID":3},{"ID":18,"IsManager":0,"ParentId":null,"RoleName":"财务部                                               ","UserID":4},{"ID":21,"IsManager":0,"ParentId":5,"RoleName":"昆明运营部","UserID":670},{"ID":22,"IsManager":0,"ParentId":5,"RoleName":"苏州运营部","UserID":670},{"ID":23,"IsManager":0,"ParentId":null,"RoleName":"财务部                                               ","UserID":3},{"ID":24,"IsManager":0,"ParentId":null,"RoleName":"行政部                                               ","UserID":226},{"ID":25,"IsManager":0,"ParentId":null,"RoleName":"财务部                                               ","UserID":226},{"ID":26,"IsManager":0,"ParentId":null,"RoleName":"总裁办                                               ","UserID":226},{"ID":27,"IsManager":0,"ParentId":null,"RoleName":"采购部                                               ","UserID":226},{"ID":28,"IsManager":0,"ParentId":null,"RoleName":"市场部                                               ","UserID":6341},{"ID":29,"IsManager":0,"ParentId":null,"RoleName":"采购部                                               ","UserID":670},{"ID":30,"IsManager":0,"ParentId":null,"RoleName":"ceo                                               ","UserID":2742},{"ID":31,"IsManager":0,"ParentId":null,"RoleName":"天体部                                               ","UserID":6346},{"ID":32,"IsManager":0,"ParentId":null,"RoleName":"公关部                                               ","UserID":670},{"ID":33,"IsManager":0,"ParentId":null,"RoleName":"行政人事部                                             ","UserID":6345},{"ID":34,"IsManager":0,"ParentId":null,"RoleName":"总经理                                               ","UserID":6345},{"ID":35,"IsManager":0,"ParentId":null,"RoleName":"财务部                                               ","UserID":6345},{"ID":36,"IsManager":0,"ParentId":null,"RoleName":"生产部                                               ","UserID":6345},{"ID":37,"IsManager":0,"ParentId":null,"RoleName":"后勤部                                               ","UserID":6345},{"ID":38,"IsManager":0,"ParentId":null,"RoleName":"餐饮部                                               ","UserID":6345},{"ID":39,"IsManager":0,"ParentId":null,"RoleName":"市场部                                               ","UserID":6345},{"ID":40,"IsManager":0,"ParentId":null,"RoleName":"采购部                                               ","UserID":6345},{"ID":41,"IsManager":0,"ParentId":null,"RoleName":"项目部                                               ","UserID":6345},{"ID":42,"IsManager":0,"ParentId":null,"RoleName":"总经理                                               ","UserID":4},{"ID":43,"IsManager":0,"ParentId":null,"RoleName":"行政部                                               ","UserID":6574},{"ID":44,"IsManager":0,"ParentId":null,"RoleName":"                                                  ","UserID":4862},{"ID":45,"IsManager":0,"ParentId":5,"RoleName":"重庆运营部","UserID":670},{"ID":46,"IsManager":0,"ParentId":null,"RoleName":"神奇部门在哪里","UserID":5446},{"ID":49,"IsManager":0,"ParentId":null,"RoleName":"技术部                                               ","UserID":10445},{"ID":50,"IsManager":0,"ParentId":null,"RoleName":"图书馆                                               ","UserID":13451},{"ID":51,"IsManager":0,"ParentId":57,"RoleName":"技术事业部                                             ","UserID":670},{"ID":57,"IsManager":0,"ParentId":null,"RoleName":"虹蚁","UserID":670},{"ID":69,"IsManager":0,"ParentId":6,"RoleName":"客服一组","UserID":670},{"ID":72,"IsManager":0,"ParentId":6,"RoleName":"客服二组","UserID":670},{"ID":73,"IsManager":0,"ParentId":6,"RoleName":"客服三组","UserID":670},{"ID":74,"IsManager":0,"ParentId":6,"RoleName":"综合客服组","UserID":670},{"ID":77,"IsManager":0,"ParentId":6,"RoleName":"幕墙业务组","UserID":670},{"ID":78,"IsManager":0,"ParentId":6,"RoleName":"门窗业务组","UserID":670},{"ID":82,"IsManager":0,"ParentId":6,"RoleName":"西北客服组","UserID":670},{"ID":91,"IsManager":0,"ParentId":6,"RoleName":"采购组","UserID":670},{"ID":107,"IsManager":0,"ParentId":null,"RoleName":"国检中心","UserID":670},{"ID":108,"IsManager":0,"ParentId":57,"RoleName":"客服组","UserID":670},{"ID":1051,"IsManager":0,"ParentId":1051,"RoleName":"权威                                                ","UserID":670},{"ID":1053,"IsManager":0,"ParentId":null,"RoleName":"风控部","UserID":670},{"ID":2057,"IsManager":0,"ParentId":2052,"RoleName":"cs12","UserID":670},{"ID":2058,"IsManager":0,"ParentId":null,"RoleName":"聚玻学院2","UserID":670},{"ID":2059,"IsManager":0,"ParentId":1,"RoleName":"行政2部","UserID":670},{"ID":2060,"IsManager":0,"ParentId":1,"RoleName":"行政3部","UserID":670},{"ID":2061,"IsManager":0,"ParentId":1,"RoleName":"行政3组","UserID":670},{"ID":2062,"IsManager":0,"ParentId":1,"RoleName":"行政4组","UserID":670},{"ID":2063,"IsManager":0,"ParentId":1,"RoleName":"眼睛","UserID":670},{"ID":2064,"IsManager":0,"ParentId":1,"RoleName":"行政6组","UserID":670},{"ID":2065,"IsManager":0,"ParentId":413,"RoleName":"风控2部","UserID":670},{"ID":2066,"IsManager":0,"ParentId":null,"RoleName":"聚玻学院1","UserID":670},{"ID":2067,"IsManager":0,"ParentId":null,"RoleName":"聚玻早报1","UserID":670},{"ID":2068,"IsManager":0,"ParentId":null,"RoleName":"聚玻学院子级1","UserID":670},{"ID":2069,"IsManager":0,"ParentId":null,"RoleName":"聚玻学院-技术部","UserID":670},{"ID":2070,"IsManager":0,"ParentId":null,"RoleName":"聚玻-技术","UserID":670},{"ID":2071,"IsManager":0,"ParentId":null,"RoleName":"123","UserID":670},{"ID":2072,"IsManager":0,"ParentId":2066,"RoleName":"聚玻院校1","UserID":670},{"ID":3052,"IsManager":0,"ParentId":3,"RoleName":"移动组","UserID":670},{"ID":3053,"IsManager":0,"ParentId":8,"RoleName":"child","UserID":670},{"ID":4053,"IsManager":0,"ParentId":3,"RoleName":"前端组","UserID":670}]
     * Success : 1
     */

    private String ErrMsg;
    private int Success;
    private List<ReObjBean> ReObj;

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

    public List<ReObjBean> getReObj() {
        return ReObj;
    }

    public void setReObj(List<ReObjBean> ReObj) {
        this.ReObj = ReObj;
    }

    public static class ReObjBean {
        /**
         * ID : 1
         * IsManager : 0
         * ParentId : null
         * RoleName : 总裁办
         * UserID : 670
         */

        private int ID;
        private int IsHaveChild;
        private int IsHaveChildChecked;
        private int IsManager;
        private int ParentId;
        private String RoleName;
        private int UserID;

        public int getIsHaveChildChecked() {
            return IsHaveChildChecked;
        }

        public void setIsHaveChildChecked(int isHaveChildChecked) {
            IsHaveChildChecked = isHaveChildChecked;
        }

        public int getIsHaveChild() {
            return IsHaveChild;
        }

        public void setIsHaveChild(int isHaveChild) {
            IsHaveChild = isHaveChild;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getIsManager() {
            return IsManager;
        }

        public void setIsManager(int IsManager) {
            this.IsManager = IsManager;
        }

        public int getParentId() {
            return ParentId;
        }

        public void setParentId(int ParentId) {
            this.ParentId = ParentId;
        }

        public String getRoleName() {
            return RoleName;
        }

        public void setRoleName(String RoleName) {
            this.RoleName = RoleName;
        }

        public int getUserID() {
            return UserID;
        }

        public void setUserID(int UserID) {
            this.UserID = UserID;
        }
    }
}
