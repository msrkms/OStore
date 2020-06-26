package com.atlassoftwarepark.ostore.Adepter;

public class DueItems {
    private String SlNo;
    private String customerName;
    private String SellDate;
    private String ammount;
    private String PhoneNumber;

    public String getSlNo() {
        return SlNo;
    }

    public void setSlNo(String slNo) {
        SlNo = slNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSellDate() {
        return SellDate;
    }

    public void setSellDate(String sellDate) {
        SellDate = sellDate;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
