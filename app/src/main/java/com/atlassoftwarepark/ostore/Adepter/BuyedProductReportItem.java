package com.atlassoftwarepark.ostore.Adepter;

public class BuyedProductReportItem {
    private String productCategory;
    private String productName;
    private String buyDate;
    private String Quantity;
    private String Vendor;
    private String unitPrice;
    private String Total;
    private String Action;

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public void setVendor(String vendor) {
        Vendor = vendor;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public String getQuantity() {
        return Quantity;
    }

    public String getVendor() {
        return Vendor;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public String getTotal() {
        return Total;
    }

    public String getAction() {
        return Action;
    }
}
