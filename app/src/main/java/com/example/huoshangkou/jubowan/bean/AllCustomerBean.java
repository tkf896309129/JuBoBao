package com.example.huoshangkou.jubowan.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：AllCustomerBean
 * 描述：
 * 创建时间：2019-09-03  09:34
 */

public class AllCustomerBean implements Serializable  {

    private DBean d;

    public DBean getD() {
        return d;
    }

    public void setD(DBean d) {
        this.d = d;
    }

    public static class DBean implements Serializable {


        private String TotalSum;
        private int TotalCount;
        private int PageCount;
        private int PageIndex;
        private int Success;
        private String ErrMsg;
        private List<ReListBean> ReList;

        public String getTotalSum() {
            return TotalSum;
        }

        public void setTotalSum(String TotalSum) {
            this.TotalSum = TotalSum;
        }

        public int getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(int TotalCount) {
            this.TotalCount = TotalCount;
        }

        public int getPageCount() {
            return PageCount;
        }

        public void setPageCount(int PageCount) {
            this.PageCount = PageCount;
        }

        public int getPageIndex() {
            return PageIndex;
        }

        public void setPageIndex(int PageIndex) {
            this.PageIndex = PageIndex;
        }

        public int getSuccess() {
            return Success;
        }

        public void setSuccess(int Success) {
            this.Success = Success;
        }

        public String getErrMsg() {
            return ErrMsg;
        }

        public void setErrMsg(String ErrMsg) {
            this.ErrMsg = ErrMsg;
        }

        public List<ReListBean> getReList() {
            return ReList;
        }

        public void setReList(List<ReListBean> ReList) {
            this.ReList = ReList;
        }

        public static class ReListBean implements Serializable {
            /**
             * __type : Model.ViewModel.Sale.CustomerViewModel
             * Id : 3027
             * Company : 泸州荣腾贸易有限公司
             * Name : 王春兰
             * Sex : 0
             * Gender : null
             * BirthDate : /Date(763228800000)/
             * CreateTime : /Date(1567136630180)/
             * ContactMode : 18666673929
             * MaritalStatus : 0
             * MaritalStatu : null
             * Profession : null
             * Hobby : null
             * Provice : 四川省
             * City : 泸州市
             * District : 泸县
             * Address : 四川省泸州市泸县泸县玄滩镇泸荣街
             * Email : 1051746920@qq.com
             * JumboAccountId : 1380
             * JumboAccount : 18666673929
             * CustomerType : 3
             * CustomerSourse : null
             * SaleManId : 10445
             * SaleMan : 吴先生
             * Remark : null
             * RecentlyOrderTime : /Date(1479279416743)/
             * RecentlyContactTime : /Date(253402271999999)/
             */

            private String __type;
            private int Id;
            private String Company;
            private String Name;
            private int Sex;
            private String Gender;
            private String BirthDate;
            private String CreateTime;
            private String ContactMode;
            private int MaritalStatus;
            private String MaritalStatu;
            private String Profession;
            private String Hobby;
            private String Provice;
            private String City;
            private String District;
            private String Address;
            private String Email;
            private int JumboAccountId;
            private int AskState;
            private String JumboAccount;
            private int CustomerType;
            private String CustomerSourse;
            private int SaleManId;
            private String SaleMan;
            private String Remark;
            private String RecentlyOrderTime;
            private String RecentlyContactTime;
            private String RecentlyOrderTimeString;
            private String RecentlyContactTimeString;
            private String BirthDateString;

            public String getRecentlyOrderTimeString() {
                return RecentlyOrderTimeString;
            }

            public void setRecentlyOrderTimeString(String recentlyOrderTimeString) {
                RecentlyOrderTimeString = recentlyOrderTimeString;
            }

            public String getRecentlyContactTimeString() {
                return RecentlyContactTimeString;
            }

            public void setRecentlyContactTimeString(String recentlyContactTimeString) {
                RecentlyContactTimeString = recentlyContactTimeString;
            }

            public String getBirthDateString() {
                return BirthDateString;
            }

            public void setBirthDateString(String birthDateString) {
                BirthDateString = birthDateString;
            }

            public int getAskState() {
                return AskState;
            }

            public void setAskState(int askState) {
                AskState = askState;
            }

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getCompany() {
                return Company;
            }

            public void setCompany(String Company) {
                this.Company = Company;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public int getSex() {
                return Sex;
            }

            public void setSex(int Sex) {
                this.Sex = Sex;
            }

            public String getGender() {
                return Gender;
            }

            public void setGender(String Gender) {
                this.Gender = Gender;
            }

            public String getBirthDate() {
                return BirthDate;
            }

            public void setBirthDate(String BirthDate) {
                this.BirthDate = BirthDate;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getContactMode() {
                return ContactMode;
            }

            public void setContactMode(String ContactMode) {
                this.ContactMode = ContactMode;
            }

            public int getMaritalStatus() {
                return MaritalStatus;
            }

            public void setMaritalStatus(int MaritalStatus) {
                this.MaritalStatus = MaritalStatus;
            }

            public String getMaritalStatu() {
                return MaritalStatu;
            }

            public void setMaritalStatu(String MaritalStatu) {
                this.MaritalStatu = MaritalStatu;
            }

            public String getProfession() {
                return Profession;
            }

            public void setProfession(String Profession) {
                this.Profession = Profession;
            }

            public String getHobby() {
                return Hobby;
            }

            public void setHobby(String Hobby) {
                this.Hobby = Hobby;
            }

            public String getProvice() {
                return Provice;
            }

            public void setProvice(String Provice) {
                this.Provice = Provice;
            }

            public String getCity() {
                return City;
            }

            public void setCity(String City) {
                this.City = City;
            }

            public String getDistrict() {
                return District;
            }

            public void setDistrict(String District) {
                this.District = District;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String Address) {
                this.Address = Address;
            }

            public String getEmail() {
                return Email;
            }

            public void setEmail(String Email) {
                this.Email = Email;
            }

            public int getJumboAccountId() {
                return JumboAccountId;
            }

            public void setJumboAccountId(int JumboAccountId) {
                this.JumboAccountId = JumboAccountId;
            }

            public String getJumboAccount() {
                return JumboAccount;
            }

            public void setJumboAccount(String JumboAccount) {
                this.JumboAccount = JumboAccount;
            }

            public int getCustomerType() {
                return CustomerType;
            }

            public void setCustomerType(int CustomerType) {
                this.CustomerType = CustomerType;
            }

            public String getCustomerSourse() {
                return CustomerSourse;
            }

            public void setCustomerSourse(String CustomerSourse) {
                this.CustomerSourse = CustomerSourse;
            }

            public int getSaleManId() {
                return SaleManId;
            }

            public void setSaleManId(int SaleManId) {
                this.SaleManId = SaleManId;
            }

            public String getSaleMan() {
                return SaleMan;
            }

            public void setSaleMan(String SaleMan) {
                this.SaleMan = SaleMan;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
            }

            public String getRecentlyOrderTime() {
                return RecentlyOrderTime;
            }

            public void setRecentlyOrderTime(String RecentlyOrderTime) {
                this.RecentlyOrderTime = RecentlyOrderTime;
            }

            public String getRecentlyContactTime() {
                return RecentlyContactTime;
            }

            public void setRecentlyContactTime(String RecentlyContactTime) {
                this.RecentlyContactTime = RecentlyContactTime;
            }
        }
    }
}
