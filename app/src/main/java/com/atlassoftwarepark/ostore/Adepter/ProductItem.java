package com.atlassoftwarepark.ostore.Adepter;

import com.android.volley.toolbox.StringRequest;

public class ProductItem {
    private String P_categoryID;
    private String p_categoryName;
    private String p_registeredDate;
    private String p_action;

    public String getP_categoryID() {
        return P_categoryID;
    }

    public void setP_categoryID(String p_categoryID) {
        P_categoryID = p_categoryID;
    }

    public String getP_categoryName() {
        return p_categoryName;
    }

    public void setP_categoryName(String p_categoryName) {
        this.p_categoryName = p_categoryName;
    }

    public String getP_registeredDate() {
        return p_registeredDate;
    }

    public void setP_registeredDate(String p_registeredDate) {
        this.p_registeredDate = p_registeredDate;
    }

    public String getP_action() {
        return p_action;
    }

    public void setP_action(String p_action) {
        this.p_action = p_action;
    }
}
