package com.atlassoftwarepark.ostore.Adepter;

public class ProductListItem {
    public String getSL() {
        return SL;
    }

    public void setSL(String SL) {
        this.SL = SL;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getVendor() {
        return Vendor;
    }

    public void setVendor(String vendor) {
        Vendor = vendor;
    }

    public String getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    private String SL;
    private String Date;
    private String Amount;
    private String Vendor;
    private String UnitPrice;
    private String TotalPrice;

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    private String Action;
}
