package com.example.huoshangkou.jubowan.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：BaseCheckDetailBean
 * 描述：
 * 创建时间：2019-12-17  14:13
 */

public class BaseCheckDetailBean {

    /**
     * d : {"ReObj":{"ApprovalOfMsgs":[{"ID":0,"ApprovalID":33047,"ApprovalUserID":1298,"ApprovalUserName":"来一军","ApprovalOver":1,"Approvalremark":"","ApprovalPic":",http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20191111/20191111190521_3234.png","CreateTime":"2019-11-11 19:05:21","HeadPic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20170630/20170630172256_9347.jpg"},{"ID":0,"ApprovalID":33047,"ApprovalUserID":10100,"ApprovalUserName":"高君岩","ApprovalOver":4,"Approvalremark":"","ApprovalPic":"","CreateTime":"2019-11-11 18:52:24","HeadPic":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLImSrH6BTTVib5uHHRYQTk1FM4vzv7JZEpmt0noI8FOw9UqHheTgz50jbc61pTII9xsVFzmW4jdyg/132"},{"ID":0,"ApprovalID":33047,"ApprovalUserID":10873,"ApprovalUserName":"芮小娟","ApprovalOver":4,"Approvalremark":"","ApprovalPic":"","CreateTime":"2019-11-11 19:05:21","HeadPic":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIzGGthScz54oA9bdXqDgNyWR9SUib3bj7SBMPsYAHqSlnoGLsjyU7FfZe5iaNfJdc6Vjd6OzQDicibSg/132"}],"ID":33047,"ApprovalTypeID":3,"TypeName":"年假","TypePrice":0,"OriginalTypePrice":null,"Time":"/Date(1573469544010)/","Address":"","StartTime":"/Date(1573488000000)/","Startslot":"上午","EndTime":"/Date(1573488000000)/","Endslot":"下午","Invoice":0,"remark":"腰不大舒服，去医院检查下","ContractNO":"","Pics":"","UserID":8004,"ApprovalState":1,"CreateTime":"/Date(1573469544307)/","UserName":"吴济时","TimeSpan":"1.0天","Contact":null,"OperatingTime":0,"TEL":null,"Email":null,"Loan":0,"LoanPeriod":null,"LoanPurPose":null,"Borrowers":null,"AdvanceOrderID":null,"SellPrice":null,"SellCashOrAccept":null,"Advance":null,"SellUserID":null,"PurchasePrice":null,"PurchaseCashOrAccept":null,"RemitDircetion":"","RemitAccount":"","ProceedsAccount":"","Profit":"0.00","FreightTotalPrice":null,"FundWay":"","MoneyCurrency":"","SpBankID":"0","UpYuanpian":null,"ApprovalMsgList":null,"RzDueBill":null,"IousUserApproval":null,"IousPayApproval":null,"ApprovalPeopleType":0,"IsSee_XiaoShou":0,"IsDaChu":"0","OrderTable":"","YYZX":"","AccountType":"","KuanXiangWay":"","RuZhangXingZhi":"","RuZhangPrice":"0.00","RuKuanWay":"","RuZhangCompany":""},"Success":1,"ErrMsg":""}
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
         * ReObj : {"ApprovalOfMsgs":[{"ID":0,"ApprovalID":33047,"ApprovalUserID":1298,"ApprovalUserName":"来一军","ApprovalOver":1,"Approvalremark":"","ApprovalPic":",http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20191111/20191111190521_3234.png","CreateTime":"2019-11-11 19:05:21","HeadPic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20170630/20170630172256_9347.jpg"},{"ID":0,"ApprovalID":33047,"ApprovalUserID":10100,"ApprovalUserName":"高君岩","ApprovalOver":4,"Approvalremark":"","ApprovalPic":"","CreateTime":"2019-11-11 18:52:24","HeadPic":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLImSrH6BTTVib5uHHRYQTk1FM4vzv7JZEpmt0noI8FOw9UqHheTgz50jbc61pTII9xsVFzmW4jdyg/132"},{"ID":0,"ApprovalID":33047,"ApprovalUserID":10873,"ApprovalUserName":"芮小娟","ApprovalOver":4,"Approvalremark":"","ApprovalPic":"","CreateTime":"2019-11-11 19:05:21","HeadPic":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIzGGthScz54oA9bdXqDgNyWR9SUib3bj7SBMPsYAHqSlnoGLsjyU7FfZe5iaNfJdc6Vjd6OzQDicibSg/132"}],"ID":33047,"ApprovalTypeID":3,"TypeName":"年假","TypePrice":0,"OriginalTypePrice":null,"Time":"/Date(1573469544010)/","Address":"","StartTime":"/Date(1573488000000)/","Startslot":"上午","EndTime":"/Date(1573488000000)/","Endslot":"下午","Invoice":0,"remark":"腰不大舒服，去医院检查下","ContractNO":"","Pics":"","UserID":8004,"ApprovalState":1,"CreateTime":"/Date(1573469544307)/","UserName":"吴济时","TimeSpan":"1.0天","Contact":null,"OperatingTime":0,"TEL":null,"Email":null,"Loan":0,"LoanPeriod":null,"LoanPurPose":null,"Borrowers":null,"AdvanceOrderID":null,"SellPrice":null,"SellCashOrAccept":null,"Advance":null,"SellUserID":null,"PurchasePrice":null,"PurchaseCashOrAccept":null,"RemitDircetion":"","RemitAccount":"","ProceedsAccount":"","Profit":"0.00","FreightTotalPrice":null,"FundWay":"","MoneyCurrency":"","SpBankID":"0","UpYuanpian":null,"ApprovalMsgList":null,"RzDueBill":null,"IousUserApproval":null,"IousPayApproval":null,"ApprovalPeopleType":0,"IsSee_XiaoShou":0,"IsDaChu":"0","OrderTable":"","YYZX":"","AccountType":"","KuanXiangWay":"","RuZhangXingZhi":"","RuZhangPrice":"0.00","RuKuanWay":"","RuZhangCompany":""}
         * Success : 1
         * ErrMsg :
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

        public static class ReObjBean implements Serializable {
            /**
             * ApprovalOfMsgs : [{"ID":0,"ApprovalID":33047,"ApprovalUserID":1298,"ApprovalUserName":"来一军","ApprovalOver":1,"Approvalremark":"","ApprovalPic":",http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20191111/20191111190521_3234.png","CreateTime":"2019-11-11 19:05:21","HeadPic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20170630/20170630172256_9347.jpg"},{"ID":0,"ApprovalID":33047,"ApprovalUserID":10100,"ApprovalUserName":"高君岩","ApprovalOver":4,"Approvalremark":"","ApprovalPic":"","CreateTime":"2019-11-11 18:52:24","HeadPic":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLImSrH6BTTVib5uHHRYQTk1FM4vzv7JZEpmt0noI8FOw9UqHheTgz50jbc61pTII9xsVFzmW4jdyg/132"},{"ID":0,"ApprovalID":33047,"ApprovalUserID":10873,"ApprovalUserName":"芮小娟","ApprovalOver":4,"Approvalremark":"","ApprovalPic":"","CreateTime":"2019-11-11 19:05:21","HeadPic":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIzGGthScz54oA9bdXqDgNyWR9SUib3bj7SBMPsYAHqSlnoGLsjyU7FfZe5iaNfJdc6Vjd6OzQDicibSg/132"}]
             * ID : 33047
             * ApprovalTypeID : 3
             * TypeName : 年假
             * TypePrice : 0.0
             * OriginalTypePrice : null
             * Time : /Date(1573469544010)/
             * Address :
             * StartTime : /Date(1573488000000)/
             * Startslot : 上午
             * EndTime : /Date(1573488000000)/
             * Endslot : 下午
             * Invoice : 0
             * remark : 腰不大舒服，去医院检查下
             * ContractNO :
             * Pics :
             * UserID : 8004
             * ApprovalState : 1
             * CreateTime : /Date(1573469544307)/
             * UserName : 吴济时
             * TimeSpan : 1.0天
             * Contact : null
             * OperatingTime : 0
             * TEL : null
             * Email : null
             * Loan : 0
             * LoanPeriod : null
             * LoanPurPose : null
             * Borrowers : null
             * AdvanceOrderID : null
             * SellPrice : null
             * SellCashOrAccept : null
             * Advance : null
             * SellUserID : null
             * PurchasePrice : null
             * PurchaseCashOrAccept : null
             * RemitDircetion :
             * RemitAccount :
             * ProceedsAccount :
             * Profit : 0.00
             * FreightTotalPrice : null
             * FundWay :
             * MoneyCurrency :
             * SpBankID : 0
             * UpYuanpian : null
             * ApprovalMsgList : null
             * RzDueBill : null
             * IousUserApproval : null
             * IousPayApproval : null
             * ApprovalPeopleType : 0
             * IsSee_XiaoShou : 0
             * IsDaChu : 0
             * OrderTable :
             * YYZX :
             * AccountType :
             * KuanXiangWay :
             * RuZhangXingZhi :
             * RuZhangPrice : 0.00
             * RuKuanWay :
             * RuZhangCompany :
             */

            private int ID;
            private int ApprovalTypeID;
            private String TypeName;
            private double TypePrice;
            private Object OriginalTypePrice;
            private String Time;
            private String Address;
            private String StartTime;
            private String Startslot;
            private String EndTime;
            private String Endslot;
            private int Invoice;
            private String remark;
            private String ContractNO;
            private String Pics;
            private int UserID;
            private int ApprovalState;
            private String CreateTime;
            private String UserName;
            private String TimeSpan;
            private Object Contact;
            private int OperatingTime;
            private Object TEL;
            private Object Email;
            private int Loan;
            private Object LoanPeriod;
            private Object LoanPurPose;
            private Object Borrowers;
            private Object AdvanceOrderID;
            private Object SellPrice;
            private Object SellCashOrAccept;
            private Object Advance;
            private Object SellUserID;
            private Object PurchasePrice;
            private Object PurchaseCashOrAccept;
            private String RemitDircetion;
            private String RemitAccount;
            private String ProceedsAccount;
            private Object Profit;
            private Object FreightTotalPrice;
            private String FundWay;
            private String MoneyCurrency;
            private String SpBankID;
            private Object UpYuanpian;
            private Object RzDueBill;
            private IousUserApprovalBean IousUserApproval;
            private int ApprovalPeopleType;
            private int IsSee_XiaoShou;
            private String IsDaChu;
            private String OrderTable;
            private String YYZX;
            private String AccountType;
            private String KuanXiangWay;
            private String RuZhangXingZhi;
            private String RuZhangPrice;
            private String RuKuanWay;
            private String RuZhangCompany;
            private String PaymentUnitAccount;
            private int BorrowingId;
            private double PaymentAmount;
            private String PaymentMethod;
            private String ReceiptUnitAccount;
            private String PayingCustomers;
            private String CustomerType;
            private String ParentTrader;
            private String CustomerAccountType;
            private String Payer;
            private String CustomerPaymentMethod;
            private double CustmerPaymentAmount;
            private String CustomerGoodsMoneyNature;
            private String CustomerRemark;
            private String PlatformInMoneyAccount;
            private String PlatformInMoneyMethod;
            private String PlatformOutMoneyAccount;
            private String PlatformGoodsMoneyNature;
            private String GoodsMoneyPurpose;
            private double PayableAmount;
            private String PlatformOutMoneyMethod;
            private String IsOutMoney;
            private String SupplierNature;
            private String InMoneySupplier;
            private String IsOpenInvoice;
            private String Operator;
            private int CustomerServiceID;
            private String BackMoneyTime;
            private String OrderId;
            private String ApplyUnit;
            private String Legalperson;
            private String LoanUsage;
            private double LoanAmount;
            private String LoanDate;
            private String BackMoneyDate;
            private String PlatformOutMoneyNature;
            private double PayAmount;
            private String PaymentProvePic;

            //            private String Address;
//            private String ApplyUnit;
            private String IdCard;
            private String IousAmount;
            private String IsEditAmount;
            private String LinkMan;
            private String PersonnelSituation;
            private String Scale;

            public String getIdCard() {
                return IdCard;
            }

            public void setIdCard(String idCard) {
                IdCard = idCard;
            }

            public String getIousAmount() {
                return IousAmount;
            }

            public void setIousAmount(String iousAmount) {
                IousAmount = iousAmount;
            }

            public String getIsEditAmount() {
                return IsEditAmount;
            }

            public void setIsEditAmount(String isEditAmount) {
                IsEditAmount = isEditAmount;
            }

            public String getLinkMan() {
                return LinkMan;
            }

            public void setLinkMan(String linkMan) {
                LinkMan = linkMan;
            }

            public String getPersonnelSituation() {
                return PersonnelSituation;
            }

            public void setPersonnelSituation(String personnelSituation) {
                PersonnelSituation = personnelSituation;
            }

            public String getScale() {
                return Scale;
            }

            public void setScale(String scale) {
                Scale = scale;
            }

            public String getOrderId() {
                return OrderId;
            }

            public void setOrderId(String orderId) {
                OrderId = orderId;
            }

            public String getApplyUnit() {
                return ApplyUnit;
            }

            public void setApplyUnit(String applyUnit) {
                ApplyUnit = applyUnit;
            }

            public String getLegalperson() {
                return Legalperson;
            }

            public void setLegalperson(String legalperson) {
                Legalperson = legalperson;
            }

            public String getLoanUsage() {
                return LoanUsage;
            }

            public void setLoanUsage(String loanUsage) {
                LoanUsage = loanUsage;
            }

            public double getLoanAmount() {
                return LoanAmount;
            }

            public void setLoanAmount(double loanAmount) {
                LoanAmount = loanAmount;
            }

            public String getLoanDate() {
                return LoanDate;
            }

            public void setLoanDate(String loanDate) {
                LoanDate = loanDate;
            }

            public String getBackMoneyDate() {
                return BackMoneyDate;
            }

            public void setBackMoneyDate(String backMoneyDate) {
                BackMoneyDate = backMoneyDate;
            }

            public String getPlatformOutMoneyNature() {
                return PlatformOutMoneyNature;
            }

            public void setPlatformOutMoneyNature(String platformOutMoneyNature) {
                PlatformOutMoneyNature = platformOutMoneyNature;
            }

            public double getPayAmount() {
                return PayAmount;
            }

            public void setPayAmount(double payAmount) {
                PayAmount = payAmount;
            }

            public String getPaymentProvePic() {
                return PaymentProvePic;
            }

            public void setPaymentProvePic(String paymentProvePic) {
                PaymentProvePic = paymentProvePic;
            }

            /**
             * IousPayApproval : {"OrderId":"20201915731242301269","ApplyUnit":"浙江火山口网络科技有限公司","LegalPerson":"康明柱","AccountBank":"浙江聚马物流有限公司","BankOpenAccount":"中国银行萧山城中支行","AccountNum":"374070630793","LoanPurposes":"赊购","Money":"19555.56","IousTotalAmount":"100000000.00","IousRemainingAmount":"100000000.00","CreateTime":"2019-12-10 13:47","NeedTransfer":"不打出","SupplierNature":"","CollectionCompany":"","IsHaveInvoice":"无发票","TransferDate":"","Profit":"0","OperationPersonnel":"","AmountOfPayment":"19555.56"}
             */


            private IousPayApprovalBean IousPayApproval;


            public String getPayingCustomers() {
                return PayingCustomers;
            }

            public void setPayingCustomers(String payingCustomers) {
                PayingCustomers = payingCustomers;
            }

            public String getCustomerType() {
                return CustomerType;
            }

            public void setCustomerType(String customerType) {
                CustomerType = customerType;
            }

            public String getParentTrader() {
                return ParentTrader;
            }

            public void setParentTrader(String parentTrader) {
                ParentTrader = parentTrader;
            }

            public String getCustomerAccountType() {
                return CustomerAccountType;
            }

            public void setCustomerAccountType(String customerAccountType) {
                CustomerAccountType = customerAccountType;
            }

            public String getPayer() {
                return Payer;
            }

            public void setPayer(String payer) {
                Payer = payer;
            }

            public String getCustomerPaymentMethod() {
                return CustomerPaymentMethod;
            }

            public void setCustomerPaymentMethod(String customerPaymentMethod) {
                CustomerPaymentMethod = customerPaymentMethod;
            }

            public double getCustmerPaymentAmount() {
                return CustmerPaymentAmount;
            }

            public void setCustmerPaymentAmount(double custmerPaymentAmount) {
                CustmerPaymentAmount = custmerPaymentAmount;
            }

            public String getCustomerGoodsMoneyNature() {
                return CustomerGoodsMoneyNature;
            }

            public void setCustomerGoodsMoneyNature(String customerGoodsMoneyNature) {
                CustomerGoodsMoneyNature = customerGoodsMoneyNature;
            }

            public String getCustomerRemark() {
                return CustomerRemark;
            }

            public void setCustomerRemark(String customerRemark) {
                CustomerRemark = customerRemark;
            }

            public String getPlatformInMoneyAccount() {
                return PlatformInMoneyAccount;
            }

            public void setPlatformInMoneyAccount(String platformInMoneyAccount) {
                PlatformInMoneyAccount = platformInMoneyAccount;
            }

            public String getPlatformInMoneyMethod() {
                return PlatformInMoneyMethod;
            }

            public void setPlatformInMoneyMethod(String platformInMoneyMethod) {
                PlatformInMoneyMethod = platformInMoneyMethod;
            }

            public String getPlatformOutMoneyAccount() {
                return PlatformOutMoneyAccount;
            }

            public void setPlatformOutMoneyAccount(String platformOutMoneyAccount) {
                PlatformOutMoneyAccount = platformOutMoneyAccount;
            }

            public String getPlatformGoodsMoneyNature() {
                return PlatformGoodsMoneyNature;
            }

            public void setPlatformGoodsMoneyNature(String platformGoodsMoneyNature) {
                PlatformGoodsMoneyNature = platformGoodsMoneyNature;
            }

            public String getGoodsMoneyPurpose() {
                return GoodsMoneyPurpose;
            }

            public void setGoodsMoneyPurpose(String goodsMoneyPurpose) {
                GoodsMoneyPurpose = goodsMoneyPurpose;
            }

            public double getPayableAmount() {
                return PayableAmount;
            }

            public void setPayableAmount(double payableAmount) {
                PayableAmount = payableAmount;
            }

            public String getPlatformOutMoneyMethod() {
                return PlatformOutMoneyMethod;
            }

            public void setPlatformOutMoneyMethod(String platformOutMoneyMethod) {
                PlatformOutMoneyMethod = platformOutMoneyMethod;
            }

            public String getIsOutMoney() {
                return IsOutMoney;
            }

            public void setIsOutMoney(String isOutMoney) {
                IsOutMoney = isOutMoney;
            }

            public String getSupplierNature() {
                return SupplierNature;
            }

            public void setSupplierNature(String supplierNature) {
                SupplierNature = supplierNature;
            }

            public String getInMoneySupplier() {
                return InMoneySupplier;
            }

            public void setInMoneySupplier(String inMoneySupplier) {
                InMoneySupplier = inMoneySupplier;
            }

            public String getIsOpenInvoice() {
                return IsOpenInvoice;
            }

            public void setIsOpenInvoice(String isOpenInvoice) {
                IsOpenInvoice = isOpenInvoice;
            }

            public String getOperator() {
                return Operator;
            }

            public void setOperator(String operator) {
                Operator = operator;
            }

            public int getCustomerServiceID() {
                return CustomerServiceID;
            }

            public void setCustomerServiceID(int customerServiceID) {
                CustomerServiceID = customerServiceID;
            }

            public String getBackMoneyTime() {
                return BackMoneyTime;
            }

            public void setBackMoneyTime(String backMoneyTime) {
                BackMoneyTime = backMoneyTime;
            }

            private List<ApproveListBean> ApprovalOfMsgs;

            public String getPaymentUnitAccount() {
                return PaymentUnitAccount;
            }

            public void setPaymentUnitAccount(String paymentUnitAccount) {
                PaymentUnitAccount = paymentUnitAccount;
            }

            public int getBorrowingId() {
                return BorrowingId;
            }

            public void setBorrowingId(int borrowingId) {
                BorrowingId = borrowingId;
            }

            public double getPaymentAmount() {
                return PaymentAmount;
            }

            public void setPaymentAmount(double paymentAmount) {
                PaymentAmount = paymentAmount;
            }

            public String getPaymentMethod() {
                return PaymentMethod;
            }

            public void setPaymentMethod(String paymentMethod) {
                PaymentMethod = paymentMethod;
            }

            public String getReceiptUnitAccount() {
                return ReceiptUnitAccount;
            }

            public void setReceiptUnitAccount(String receiptUnitAccount) {
                ReceiptUnitAccount = receiptUnitAccount;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public int getApprovalTypeID() {
                return ApprovalTypeID;
            }

            public void setApprovalTypeID(int ApprovalTypeID) {
                this.ApprovalTypeID = ApprovalTypeID;
            }

            public String getTypeName() {
                return TypeName;
            }

            public void setTypeName(String TypeName) {
                this.TypeName = TypeName;
            }

            public double getTypePrice() {
                return TypePrice;
            }

            public void setTypePrice(double TypePrice) {
                this.TypePrice = TypePrice;
            }

            public Object getOriginalTypePrice() {
                return OriginalTypePrice;
            }

            public void setOriginalTypePrice(Object OriginalTypePrice) {
                this.OriginalTypePrice = OriginalTypePrice;
            }

            public String getTime() {
                return Time;
            }

            public void setTime(String Time) {
                this.Time = Time;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String Address) {
                this.Address = Address;
            }

            public String getStartTime() {
                return StartTime;
            }

            public void setStartTime(String StartTime) {
                this.StartTime = StartTime;
            }

            public String getStartslot() {
                return Startslot;
            }

            public void setStartslot(String Startslot) {
                this.Startslot = Startslot;
            }

            public String getEndTime() {
                return EndTime;
            }

            public void setEndTime(String EndTime) {
                this.EndTime = EndTime;
            }

            public String getEndslot() {
                return Endslot;
            }

            public void setEndslot(String Endslot) {
                this.Endslot = Endslot;
            }

            public int getInvoice() {
                return Invoice;
            }

            public void setInvoice(int Invoice) {
                this.Invoice = Invoice;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getContractNO() {
                return ContractNO;
            }

            public void setContractNO(String ContractNO) {
                this.ContractNO = ContractNO;
            }

            public String getPics() {
                return Pics;
            }

            public void setPics(String Pics) {
                this.Pics = Pics;
            }

            public int getUserID() {
                return UserID;
            }

            public void setUserID(int UserID) {
                this.UserID = UserID;
            }

            public int getApprovalState() {
                return ApprovalState;
            }

            public void setApprovalState(int ApprovalState) {
                this.ApprovalState = ApprovalState;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getTimeSpan() {
                return TimeSpan;
            }

            public void setTimeSpan(String TimeSpan) {
                this.TimeSpan = TimeSpan;
            }

            public Object getContact() {
                return Contact;
            }

            public void setContact(Object Contact) {
                this.Contact = Contact;
            }

            public int getOperatingTime() {
                return OperatingTime;
            }

            public void setOperatingTime(int OperatingTime) {
                this.OperatingTime = OperatingTime;
            }

            public Object getTEL() {
                return TEL;
            }

            public void setTEL(Object TEL) {
                this.TEL = TEL;
            }

            public Object getEmail() {
                return Email;
            }

            public void setEmail(Object Email) {
                this.Email = Email;
            }

            public int getLoan() {
                return Loan;
            }

            public void setLoan(int Loan) {
                this.Loan = Loan;
            }

            public Object getLoanPeriod() {
                return LoanPeriod;
            }

            public void setLoanPeriod(Object LoanPeriod) {
                this.LoanPeriod = LoanPeriod;
            }

            public Object getLoanPurPose() {
                return LoanPurPose;
            }

            public void setLoanPurPose(Object LoanPurPose) {
                this.LoanPurPose = LoanPurPose;
            }

            public Object getBorrowers() {
                return Borrowers;
            }

            public void setBorrowers(Object Borrowers) {
                this.Borrowers = Borrowers;
            }

            public Object getAdvanceOrderID() {
                return AdvanceOrderID;
            }

            public void setAdvanceOrderID(Object AdvanceOrderID) {
                this.AdvanceOrderID = AdvanceOrderID;
            }

            public Object getSellPrice() {
                return SellPrice;
            }

            public void setSellPrice(Object SellPrice) {
                this.SellPrice = SellPrice;
            }

            public Object getSellCashOrAccept() {
                return SellCashOrAccept;
            }

            public void setSellCashOrAccept(Object SellCashOrAccept) {
                this.SellCashOrAccept = SellCashOrAccept;
            }

            public Object getAdvance() {
                return Advance;
            }

            public void setAdvance(Object Advance) {
                this.Advance = Advance;
            }

            public Object getSellUserID() {
                return SellUserID;
            }

            public void setSellUserID(Object SellUserID) {
                this.SellUserID = SellUserID;
            }

            public Object getPurchasePrice() {
                return PurchasePrice;
            }

            public void setPurchasePrice(Object PurchasePrice) {
                this.PurchasePrice = PurchasePrice;
            }

            public Object getPurchaseCashOrAccept() {
                return PurchaseCashOrAccept;
            }

            public void setPurchaseCashOrAccept(Object PurchaseCashOrAccept) {
                this.PurchaseCashOrAccept = PurchaseCashOrAccept;
            }

            public String getRemitDircetion() {
                return RemitDircetion;
            }

            public void setRemitDircetion(String RemitDircetion) {
                this.RemitDircetion = RemitDircetion;
            }

            public String getRemitAccount() {
                return RemitAccount;
            }

            public void setRemitAccount(String RemitAccount) {
                this.RemitAccount = RemitAccount;
            }

            public String getProceedsAccount() {
                return ProceedsAccount;
            }

            public void setProceedsAccount(String ProceedsAccount) {
                this.ProceedsAccount = ProceedsAccount;
            }

            public Object getProfit() {
                return Profit;
            }

            public void setProfit(Object Profit) {
                this.Profit = Profit;
            }

            public Object getFreightTotalPrice() {
                return FreightTotalPrice;
            }

            public void setFreightTotalPrice(Object FreightTotalPrice) {
                this.FreightTotalPrice = FreightTotalPrice;
            }

            public String getFundWay() {
                return FundWay;
            }

            public void setFundWay(String FundWay) {
                this.FundWay = FundWay;
            }

            public String getMoneyCurrency() {
                return MoneyCurrency;
            }

            public void setMoneyCurrency(String MoneyCurrency) {
                this.MoneyCurrency = MoneyCurrency;
            }

            public String getSpBankID() {
                return SpBankID;
            }

            public void setSpBankID(String SpBankID) {
                this.SpBankID = SpBankID;
            }

            public Object getUpYuanpian() {
                return UpYuanpian;
            }

            public void setUpYuanpian(Object UpYuanpian) {
                this.UpYuanpian = UpYuanpian;
            }


            public Object getRzDueBill() {
                return RzDueBill;
            }

            public void setRzDueBill(Object RzDueBill) {
                this.RzDueBill = RzDueBill;
            }

            public IousUserApprovalBean getIousUserApproval() {
                return IousUserApproval;
            }

            public void setIousUserApproval(IousUserApprovalBean IousUserApproval) {
                this.IousUserApproval = IousUserApproval;
            }


            public int getApprovalPeopleType() {
                return ApprovalPeopleType;
            }

            public void setApprovalPeopleType(int ApprovalPeopleType) {
                this.ApprovalPeopleType = ApprovalPeopleType;
            }

            public int getIsSee_XiaoShou() {
                return IsSee_XiaoShou;
            }

            public void setIsSee_XiaoShou(int IsSee_XiaoShou) {
                this.IsSee_XiaoShou = IsSee_XiaoShou;
            }

            public String getIsDaChu() {
                return IsDaChu;
            }

            public void setIsDaChu(String IsDaChu) {
                this.IsDaChu = IsDaChu;
            }

            public String getOrderTable() {
                return OrderTable;
            }

            public void setOrderTable(String OrderTable) {
                this.OrderTable = OrderTable;
            }

            public String getYYZX() {
                return YYZX;
            }

            public void setYYZX(String YYZX) {
                this.YYZX = YYZX;
            }

            public String getAccountType() {
                return AccountType;
            }

            public void setAccountType(String AccountType) {
                this.AccountType = AccountType;
            }

            public String getKuanXiangWay() {
                return KuanXiangWay;
            }

            public void setKuanXiangWay(String KuanXiangWay) {
                this.KuanXiangWay = KuanXiangWay;
            }

            public String getRuZhangXingZhi() {
                return RuZhangXingZhi;
            }

            public void setRuZhangXingZhi(String RuZhangXingZhi) {
                this.RuZhangXingZhi = RuZhangXingZhi;
            }

            public String getRuZhangPrice() {
                return RuZhangPrice;
            }

            public void setRuZhangPrice(String RuZhangPrice) {
                this.RuZhangPrice = RuZhangPrice;
            }

            public String getRuKuanWay() {
                return RuKuanWay;
            }

            public void setRuKuanWay(String RuKuanWay) {
                this.RuKuanWay = RuKuanWay;
            }

            public String getRuZhangCompany() {
                return RuZhangCompany;
            }

            public void setRuZhangCompany(String RuZhangCompany) {
                this.RuZhangCompany = RuZhangCompany;
            }


            public List<ApproveListBean> getApprovalOfMsgs() {
                return ApprovalOfMsgs;
            }

            public void setApprovalOfMsgs(List<ApproveListBean> approvalOfMsgs) {
                ApprovalOfMsgs = approvalOfMsgs;
            }

            public IousPayApprovalBean getIousPayApproval() {
                return IousPayApproval;
            }

            public void setIousPayApproval(IousPayApprovalBean IousPayApproval) {
                this.IousPayApproval = IousPayApproval;
            }


            public static class ApprovalOfMsgsBean {
                /**
                 * ID : 0
                 * ApprovalID : 33047
                 * ApprovalUserID : 1298
                 * ApprovalUserName : 来一军
                 * ApprovalOver : 1
                 * Approvalremark :
                 * ApprovalPic : ,http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20191111/20191111190521_3234.png
                 * CreateTime : 2019-11-11 19:05:21
                 * HeadPic : http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20170630/20170630172256_9347.jpg
                 */

                private int ID;
                private int ApprovalID;
                private int ApprovalUserID;
                private String ApprovalUserName;
                private int ApprovalOver;
                private String Approvalremark;
                private String ApprovalPic;
                private String CreateTime;
                private String HeadPic;

                public int getID() {
                    return ID;
                }

                public void setID(int ID) {
                    this.ID = ID;
                }

                public int getApprovalID() {
                    return ApprovalID;
                }

                public void setApprovalID(int ApprovalID) {
                    this.ApprovalID = ApprovalID;
                }

                public int getApprovalUserID() {
                    return ApprovalUserID;
                }

                public void setApprovalUserID(int ApprovalUserID) {
                    this.ApprovalUserID = ApprovalUserID;
                }

                public String getApprovalUserName() {
                    return ApprovalUserName;
                }

                public void setApprovalUserName(String ApprovalUserName) {
                    this.ApprovalUserName = ApprovalUserName;
                }

                public int getApprovalOver() {
                    return ApprovalOver;
                }

                public void setApprovalOver(int ApprovalOver) {
                    this.ApprovalOver = ApprovalOver;
                }

                public String getApprovalremark() {
                    return Approvalremark;
                }

                public void setApprovalremark(String Approvalremark) {
                    this.Approvalremark = Approvalremark;
                }

                public String getApprovalPic() {
                    return ApprovalPic;
                }

                public void setApprovalPic(String ApprovalPic) {
                    this.ApprovalPic = ApprovalPic;
                }

                public String getCreateTime() {
                    return CreateTime;
                }

                public void setCreateTime(String CreateTime) {
                    this.CreateTime = CreateTime;
                }

                public String getHeadPic() {
                    return HeadPic;
                }

                public void setHeadPic(String HeadPic) {
                    this.HeadPic = HeadPic;
                }
            }

            public static class IousUserApprovalBean {
                private String Address;
                private String ApplyUnit;
                private String IdCard;
                private String IousAmount;
                private String IsEditAmount;
                private String LegalPerson;
                private String LinkMan;
                private String PersonnelSituation;
                private String Scale;


                public String getAddress() {
                    return Address;
                }

                public void setAddress(String address) {
                    Address = address;
                }

                public String getApplyUnit() {
                    return ApplyUnit;
                }

                public void setApplyUnit(String applyUnit) {
                    ApplyUnit = applyUnit;
                }

                public String getIdCard() {
                    return IdCard;
                }

                public void setIdCard(String idCard) {
                    IdCard = idCard;
                }

                public String getIousAmount() {
                    return IousAmount;
                }

                public void setIousAmount(String iousAmount) {
                    IousAmount = iousAmount;
                }

                public String getIsEditAmount() {
                    return IsEditAmount;
                }

                public void setIsEditAmount(String isEditAmount) {
                    IsEditAmount = isEditAmount;
                }

                public String getLegalPerson() {
                    return LegalPerson;
                }

                public void setLegalPerson(String legalPerson) {
                    LegalPerson = legalPerson;
                }

                public String getLinkMan() {
                    return LinkMan;
                }

                public void setLinkMan(String linkMan) {
                    LinkMan = linkMan;
                }

                public String getPersonnelSituation() {
                    return PersonnelSituation;
                }

                public void setPersonnelSituation(String personnelSituation) {
                    PersonnelSituation = personnelSituation;
                }

                public String getScale() {
                    return Scale;
                }

                public void setScale(String scale) {
                    Scale = scale;
                }
            }

            public static class IousPayApprovalBean {
                /**
                 * OrderId : 20201915731242301269
                 * ApplyUnit : 浙江火山口网络科技有限公司
                 * LegalPerson : 康明柱
                 * AccountBank : 浙江聚马物流有限公司
                 * BankOpenAccount : 中国银行萧山城中支行
                 * AccountNum : 374070630793
                 * LoanPurposes : 赊购
                 * Money : 19555.56
                 * IousTotalAmount : 100000000.00
                 * IousRemainingAmount : 100000000.00
                 * CreateTime : 2019-12-10 13:47
                 * NeedTransfer : 不打出
                 * SupplierNature :
                 * CollectionCompany :
                 * IsHaveInvoice : 无发票
                 * TransferDate :
                 * Profit : 0
                 * OperationPersonnel :
                 * AmountOfPayment : 19555.56
                 */

                private String OrderId;
                private String ApplyUnit;
                private String LegalPerson;
                private String AccountBank;
                private String BankOpenAccount;
                private String AccountNum;
                private String LoanPurposes;
                private String Money;
                private String IousTotalAmount;
                private String IousRemainingAmount;
                private String CreateTime;
                private String NeedTransfer;
                private String SupplierNature;
                private String CollectionCompany;
                private String IsHaveInvoice;
                private String TransferDate;
                private String Profit;
                private String OperationPersonnel;
                private String AmountOfPayment;

                public String getOrderId() {
                    return OrderId;
                }

                public void setOrderId(String orderId) {
                    OrderId = orderId;
                }

                public String getApplyUnit() {
                    return ApplyUnit;
                }

                public void setApplyUnit(String applyUnit) {
                    ApplyUnit = applyUnit;
                }

                public String getLegalPerson() {
                    return LegalPerson;
                }

                public void setLegalPerson(String legalPerson) {
                    LegalPerson = legalPerson;
                }

                public String getAccountBank() {
                    return AccountBank;
                }

                public void setAccountBank(String accountBank) {
                    AccountBank = accountBank;
                }

                public String getBankOpenAccount() {
                    return BankOpenAccount;
                }

                public void setBankOpenAccount(String bankOpenAccount) {
                    BankOpenAccount = bankOpenAccount;
                }

                public String getAccountNum() {
                    return AccountNum;
                }

                public void setAccountNum(String accountNum) {
                    AccountNum = accountNum;
                }

                public String getLoanPurposes() {
                    return LoanPurposes;
                }

                public void setLoanPurposes(String loanPurposes) {
                    LoanPurposes = loanPurposes;
                }

                public String getMoney() {
                    return Money;
                }

                public void setMoney(String money) {
                    Money = money;
                }

                public String getIousTotalAmount() {
                    return IousTotalAmount;
                }

                public void setIousTotalAmount(String iousTotalAmount) {
                    IousTotalAmount = iousTotalAmount;
                }

                public String getIousRemainingAmount() {
                    return IousRemainingAmount;
                }

                public void setIousRemainingAmount(String iousRemainingAmount) {
                    IousRemainingAmount = iousRemainingAmount;
                }

                public String getCreateTime() {
                    return CreateTime;
                }

                public void setCreateTime(String createTime) {
                    CreateTime = createTime;
                }

                public String getNeedTransfer() {
                    return NeedTransfer;
                }

                public void setNeedTransfer(String needTransfer) {
                    NeedTransfer = needTransfer;
                }

                public String getSupplierNature() {
                    return SupplierNature;
                }

                public void setSupplierNature(String supplierNature) {
                    SupplierNature = supplierNature;
                }

                public String getCollectionCompany() {
                    return CollectionCompany;
                }

                public void setCollectionCompany(String collectionCompany) {
                    CollectionCompany = collectionCompany;
                }

                public String getIsHaveInvoice() {
                    return IsHaveInvoice;
                }

                public void setIsHaveInvoice(String isHaveInvoice) {
                    IsHaveInvoice = isHaveInvoice;
                }

                public String getTransferDate() {
                    return TransferDate;
                }

                public void setTransferDate(String transferDate) {
                    TransferDate = transferDate;
                }

                public String getProfit() {
                    return Profit;
                }

                public void setProfit(String profit) {
                    Profit = profit;
                }

                public String getOperationPersonnel() {
                    return OperationPersonnel;
                }

                public void setOperationPersonnel(String operationPersonnel) {
                    OperationPersonnel = operationPersonnel;
                }

                public String getAmountOfPayment() {
                    return AmountOfPayment;
                }

                public void setAmountOfPayment(String amountOfPayment) {
                    AmountOfPayment = amountOfPayment;
                }
            }
        }
    }
}
