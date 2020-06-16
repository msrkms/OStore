package com.atlassoftwarepark.ostore.Adepter;

public class ProfitLossItem {
    private String productName;
    private String totalBuyRate;
    private String totalSellRate;
    private String status;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTotalBuyRate() {
        return totalBuyRate;
    }

    public void setTotalBuyRate(String totalBuyRate) {
        this.totalBuyRate = totalBuyRate;
    }

    public String getTotalSellRate() {
        return totalSellRate;
    }

    public void setTotalSellRate(String totalSellRate) {
        this.totalSellRate = totalSellRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
