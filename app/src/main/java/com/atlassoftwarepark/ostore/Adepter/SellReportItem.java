package com.atlassoftwarepark.ostore.Adepter;

public class SellReportItem {

    private String invoiceno;
    private String productname;
    private String selldate;
    private String quantity;
    private String unitprice;
    private String total;



    public String getInvoiceno() {
        return invoiceno;
    }

    public String getProductname() {
        return productname;
    }

    public String getSelldate() {
        return selldate;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getUnitprice() {
        return unitprice;
    }

    public String getTotal() {
        return total;
    }

    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setSelldate(String selldate) {
        this.selldate = selldate;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setUnitprice(String unitprice) {
        this.unitprice = unitprice;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
