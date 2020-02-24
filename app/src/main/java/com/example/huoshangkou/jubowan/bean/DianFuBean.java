package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：DianFuBean
 * 描述：
 * 创建时间：2019-10-09  08:40
 */

public class DianFuBean {


    /**
     * d : {"ReObj":{"__type":"Model.ApprovalOfPayment.PadPayment.CompanyApprovalRecord","SignAccountId":null,"SignSealData":null,"IsExistLegalDisputes":false,"IsExistBadCredit":false,"IsAdvancePayment":false,"Id":2,"Company":"某企业","LegalPerson":"法人信息","IDNumber":"444555221","LinkTel":"15623546111","IDCardPic":"","BusinessLicensePic":"","SocialCreditCode":"","OrganizationalCode":"","Address":"内蒙古自治区呼和浩特市市辖区"},"Success":1,"ErrMsg":"获取成功"}
     */

    private DBean d;

    public DBean getD() {
        return d;
    }

    public void setD(DBean d) {
        this.d = d;
    }

    public static class DBean {
        /**
         * ReObj : {"__type":"Model.ApprovalOfPayment.PadPayment.CompanyApprovalRecord","SignAccountId":null,"SignSealData":null,"IsExistLegalDisputes":false,"IsExistBadCredit":false,"IsAdvancePayment":false,"Id":2,"Company":"某企业","LegalPerson":"法人信息","IDNumber":"444555221","LinkTel":"15623546111","IDCardPic":"","BusinessLicensePic":"","SocialCreditCode":"","OrganizationalCode":"","Address":"内蒙古自治区呼和浩特市市辖区"}
         * Success : 1
         * ErrMsg : 获取成功
         */

        private ReObjBean ReObj;
        private int Success;
        private String ErrMsg;

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

        public String getErrMsg() {
            return ErrMsg;
        }

        public void setErrMsg(String ErrMsg) {
            this.ErrMsg = ErrMsg;
        }

        public static class ReObjBean {
            /**
             * __type : Model.ApprovalOfPayment.PadPayment.CompanyApprovalRecord
             * SignAccountId : null
             * SignSealData : null
             * IsExistLegalDisputes : false
             * IsExistBadCredit : false
             * IsAdvancePayment : false
             * Id : 2
             * Company : 某企业
             * LegalPerson : 法人信息
             * IDNumber : 444555221
             * LinkTel : 15623546111
             * IDCardPic :
             * BusinessLicensePic :
             * SocialCreditCode :
             * OrganizationalCode :
             * Address : 内蒙古自治区呼和浩特市市辖区
             */

            private String __type;
            private Object SignAccountId;
            private Object SignSealData;
            private boolean IsExistLegalDisputes;
            private boolean IsExistBadCredit;
            private boolean IsAdvancePayment;
            private int Id;
            private String Company;
            private String LegalPerson;
            private String IDNumber;
            private String LinkTel;
            private String IDCardPic;
            private String BusinessLicensePic;
            private String SocialCreditCode;
            private String OrganizationalCode;
            private String Address;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public Object getSignAccountId() {
                return SignAccountId;
            }

            public void setSignAccountId(Object SignAccountId) {
                this.SignAccountId = SignAccountId;
            }

            public Object getSignSealData() {
                return SignSealData;
            }

            public void setSignSealData(Object SignSealData) {
                this.SignSealData = SignSealData;
            }

            public boolean isIsExistLegalDisputes() {
                return IsExistLegalDisputes;
            }

            public void setIsExistLegalDisputes(boolean IsExistLegalDisputes) {
                this.IsExistLegalDisputes = IsExistLegalDisputes;
            }

            public boolean isIsExistBadCredit() {
                return IsExistBadCredit;
            }

            public void setIsExistBadCredit(boolean IsExistBadCredit) {
                this.IsExistBadCredit = IsExistBadCredit;
            }

            public boolean isIsAdvancePayment() {
                return IsAdvancePayment;
            }

            public void setIsAdvancePayment(boolean IsAdvancePayment) {
                this.IsAdvancePayment = IsAdvancePayment;
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

            public String getLegalPerson() {
                return LegalPerson;
            }

            public void setLegalPerson(String LegalPerson) {
                this.LegalPerson = LegalPerson;
            }

            public String getIDNumber() {
                return IDNumber;
            }

            public void setIDNumber(String IDNumber) {
                this.IDNumber = IDNumber;
            }

            public String getLinkTel() {
                return LinkTel;
            }

            public void setLinkTel(String LinkTel) {
                this.LinkTel = LinkTel;
            }

            public String getIDCardPic() {
                return IDCardPic;
            }

            public void setIDCardPic(String IDCardPic) {
                this.IDCardPic = IDCardPic;
            }

            public String getBusinessLicensePic() {
                return BusinessLicensePic;
            }

            public void setBusinessLicensePic(String BusinessLicensePic) {
                this.BusinessLicensePic = BusinessLicensePic;
            }

            public String getSocialCreditCode() {
                return SocialCreditCode;
            }

            public void setSocialCreditCode(String SocialCreditCode) {
                this.SocialCreditCode = SocialCreditCode;
            }

            public String getOrganizationalCode() {
                return OrganizationalCode;
            }

            public void setOrganizationalCode(String OrganizationalCode) {
                this.OrganizationalCode = OrganizationalCode;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String Address) {
                this.Address = Address;
            }
        }
    }
}
