package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：CheckGroupBean
 * 描述：
 * 创建时间：2019-02-19  15:04
 */

public class CheckGroupBean {


    /**
     * ErrMsg :
     * ReObj : {"DepList":[{"ID":18,"ParentId":null,"RoleName":"财务部                                               ","UserID":4},{"ID":42,"ParentId":null,"RoleName":"总经理                                               ","UserID":4}],"UserList":[{"Administrator":0,"ID":6456,"LinkMan":"测试\u2014总经理","LoginName":"15142586580","ParentId":null,"Pic1":"","Role":"总经理","RoleID":42,"Sort":0,"Type":2},{"Administrator":2,"ID":4,"LinkMan":"建筑商（测试帐号勿改）","LoginName":"15988112226","ParentId":null,"Pic1":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20170613/20170613153816_6080.png","Role":"总经理","RoleID":42,"Sort":4,"Type":2}]}
     * Success : 1
     */

    private String ErrMsg;
    private ReObjBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public ReObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(ReObjBean ReObj) {
        this.ReObj = ReObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int Success) {
        this.Success = Success;
    }

    public static class ReObjBean {
        private List<DepListBean> DepList;
        private List<UserListBean> UserList;

        public List<DepListBean> getDepList() {
            return DepList;
        }

        public void setDepList(List<DepListBean> DepList) {
            this.DepList = DepList;
        }

        public List<UserListBean> getUserList() {
            return UserList;
        }

        public void setUserList(List<UserListBean> UserList) {
            this.UserList = UserList;
        }

        public static class DepListBean {
            /**
             * ID : 18
             * ParentId : null
             * RoleName : 财务部
             * UserID : 4
             */

            private int ID;
            private int NumberOfEmployees;
            private int ParentId;
            private String RoleName;
            private int UserID;

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public int getNumberOfEmployees() {
                return NumberOfEmployees;
            }

            public void setNumberOfEmployees(int numberOfEmployees) {
                NumberOfEmployees = numberOfEmployees;
            }

            public int getParentId() {
                return ParentId;
            }

            public void setParentId(int parentId) {
                ParentId = parentId;
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

        public static class UserListBean {
            /**
             * Administrator : 0
             * ID : 6456
             * LinkMan : 测试—总经理
             * LoginName : 15142586580
             * ParentId : null
             * Pic1 :
             * Role : 总经理
             * RoleID : 42
             * Sort : 0
             * Type : 2
             */

            private int Administrator;
            private int ID;
            private String LinkMan;
            private String LoginName;
            private Object ParentId;
            private String Pic1;
            private String Role;
            private int NowPattern;
            private int NowState;
            private int RoleID;
            private int Sort;
            private int Type;
            private boolean isCheck = false;

            public int getNowPattern() {
                return NowPattern;
            }

            public void setNowPattern(int nowPattern) {
                NowPattern = nowPattern;
            }

            public int getNowState() {
                return NowState;
            }

            public void setNowState(int nowState) {
                NowState = nowState;
            }

            public boolean isCheck() {
                return isCheck;
            }

            public void setCheck(boolean check) {
                isCheck = check;
            }

            public int getAdministrator() {
                return Administrator;
            }

            public void setAdministrator(int Administrator) {
                this.Administrator = Administrator;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getLinkMan() {
                return LinkMan;
            }

            public void setLinkMan(String LinkMan) {
                this.LinkMan = LinkMan;
            }

            public String getLoginName() {
                return LoginName;
            }

            public void setLoginName(String LoginName) {
                this.LoginName = LoginName;
            }

            public Object getParentId() {
                return ParentId;
            }

            public void setParentId(Object ParentId) {
                this.ParentId = ParentId;
            }

            public String getPic1() {
                return Pic1;
            }

            public void setPic1(String Pic1) {
                this.Pic1 = Pic1;
            }

            public String getRole() {
                return Role;
            }

            public void setRole(String Role) {
                this.Role = Role;
            }

            public int getRoleID() {
                return RoleID;
            }

            public void setRoleID(int RoleID) {
                this.RoleID = RoleID;
            }

            public int getSort() {
                return Sort;
            }

            public void setSort(int Sort) {
                this.Sort = Sort;
            }

            public int getType() {
                return Type;
            }

            public void setType(int Type) {
                this.Type = Type;
            }
        }
    }
}
