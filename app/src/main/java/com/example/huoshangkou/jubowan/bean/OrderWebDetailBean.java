package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：OrderWebDetailBean
 * 描述：
 * 创建时间：2020-04-17  13:32
 */

public class OrderWebDetailBean {

    /**
     * ErrMsg :
     * ReObj : {"BrandName":"安全(沙河)","CategoryName":null,"ClassName":"白玻","Description":"<p>\n\t<span style=\"font-size:16px;\"><\/span><img alt=\"\" src=\"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20161230/20161230101222_3974.jpg\" /><img alt=\"\" src=\"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20161230/20161230101222_7099.jpg\" /> \n<\/p>\n<p>\n\t<span style=\"font-size:18px;font-family:'Microsoft YaHei';color:#000000;line-height:2;\">玻璃质量稳定，应力均匀消除，钢化自爆率低； 再加工成品率高，可切割性好，光学质量优异、斑纹角良好；表面平整、厚度均匀，再加工后反射影像失真小。<\/span><span style=\"font-size:16px;\"><span style=\"font-family:'Microsoft YaHei';font-size:18px;color:#000000;line-height:2;\">透光率高85%-90.8%，无视觉变形，2-4mm被广泛应用于电子玻璃；3mm IPO产品，透过率≥90.3%，产品应用于扫描仪、复印机、LCD显示屏等产品；15mm 和 19mm 生产工艺在国内处于领先地位，可见光透过率分别达到87% 和85.7%，最小厚度偏差可控制在+/-0.08mm以内。产品规格多样化，最大规格可生产3300x10000mm，能够满足高档家具玻璃，高档建筑玻璃的加工要求；厚度均匀性好，最小厚度偏差可控制在+/-0.05mm以内；切割尺寸精度高，可控制在+/-1mm以<\/span><span style=\"font-family:'Microsoft YaHei';font-size:18px;color:#000000;line-height:2;\">内<\/span><span style=\"font-family:'Microsoft YaHei';font-size:18px;color:#000000;line-height:2;\">。<\/span><\/span> \n<\/p>","GuigeName":null,"Id":"103024","LevelName":"二等品","MoxiName":"","NameUnit":"吨","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20160819/20160819152802_0663.jpg","Price":"0","ProductName":null,"ProductType":0,"Reserve":"1000","SaleNum":"1","Weight":12,"Xy":"8888*6666"}
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
        /**
         * BrandName : 安全(沙河)
         * CategoryName : null
         * ClassName : 白玻
         * Description : <p>
         * <span style="font-size:16px;"></span><img alt="" src="http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20161230/20161230101222_3974.jpg" /><img alt="" src="http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20161230/20161230101222_7099.jpg" />
         * </p>
         * <p>
         * <span style="font-size:18px;font-family:'Microsoft YaHei';color:#000000;line-height:2;">玻璃质量稳定，应力均匀消除，钢化自爆率低； 再加工成品率高，可切割性好，光学质量优异、斑纹角良好；表面平整、厚度均匀，再加工后反射影像失真小。</span><span style="font-size:16px;"><span style="font-family:'Microsoft YaHei';font-size:18px;color:#000000;line-height:2;">透光率高85%-90.8%，无视觉变形，2-4mm被广泛应用于电子玻璃；3mm IPO产品，透过率≥90.3%，产品应用于扫描仪、复印机、LCD显示屏等产品；15mm 和 19mm 生产工艺在国内处于领先地位，可见光透过率分别达到87% 和85.7%，最小厚度偏差可控制在+/-0.08mm以内。产品规格多样化，最大规格可生产3300x10000mm，能够满足高档家具玻璃，高档建筑玻璃的加工要求；厚度均匀性好，最小厚度偏差可控制在+/-0.05mm以内；切割尺寸精度高，可控制在+/-1mm以</span><span style="font-family:'Microsoft YaHei';font-size:18px;color:#000000;line-height:2;">内</span><span style="font-family:'Microsoft YaHei';font-size:18px;color:#000000;line-height:2;">。</span></span>
         * </p>
         * GuigeName : null
         * Id : 103024
         * LevelName : 二等品
         * MoxiName :
         * NameUnit : 吨
         * Pic : http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20160819/20160819152802_0663.jpg
         * Price : 0
         * ProductName : null
         * ProductType : 0
         * Reserve : 1000
         * SaleNum : 1
         * Weight : 12.0
         * Xy : 8888*6666
         */

        private String BrandName;
        private Object CategoryName;
        private String ClassName;
        private String Description;
        private Object GuigeName;
        private String Id;
        private String LevelName;
        private String MoxiName;
        private String NameUnit;
        private String Pic;
        private String Price;
        private Object ProductName;
        private int ProductType;
        private String Reserve;
        private String SaleNum;
        private double Weight;
        private String Xy;

        public String getBrandName() {
            return BrandName;
        }

        public void setBrandName(String BrandName) {
            this.BrandName = BrandName;
        }

        public Object getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(Object CategoryName) {
            this.CategoryName = CategoryName;
        }

        public String getClassName() {
            return ClassName;
        }

        public void setClassName(String ClassName) {
            this.ClassName = ClassName;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public Object getGuigeName() {
            return GuigeName;
        }

        public void setGuigeName(Object GuigeName) {
            this.GuigeName = GuigeName;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getLevelName() {
            return LevelName;
        }

        public void setLevelName(String LevelName) {
            this.LevelName = LevelName;
        }

        public String getMoxiName() {
            return MoxiName;
        }

        public void setMoxiName(String MoxiName) {
            this.MoxiName = MoxiName;
        }

        public String getNameUnit() {
            return NameUnit;
        }

        public void setNameUnit(String NameUnit) {
            this.NameUnit = NameUnit;
        }

        public String getPic() {
            return Pic;
        }

        public void setPic(String Pic) {
            this.Pic = Pic;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public Object getProductName() {
            return ProductName;
        }

        public void setProductName(Object ProductName) {
            this.ProductName = ProductName;
        }

        public int getProductType() {
            return ProductType;
        }

        public void setProductType(int ProductType) {
            this.ProductType = ProductType;
        }

        public String getReserve() {
            return Reserve;
        }

        public void setReserve(String Reserve) {
            this.Reserve = Reserve;
        }

        public String getSaleNum() {
            return SaleNum;
        }

        public void setSaleNum(String SaleNum) {
            this.SaleNum = SaleNum;
        }

        public double getWeight() {
            return Weight;
        }

        public void setWeight(double Weight) {
            this.Weight = Weight;
        }

        public String getXy() {
            return Xy;
        }

        public void setXy(String Xy) {
            this.Xy = Xy;
        }
    }
}
