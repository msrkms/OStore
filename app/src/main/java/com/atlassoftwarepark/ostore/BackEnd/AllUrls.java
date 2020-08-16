package com.atlassoftwarepark.ostore.BackEnd;

public class AllUrls {
    private static  final String Domain="http://ostore.xyz";
    private static String API=Domain+"/api/";

    public static final String Login=API+"submitlogin";
    public static final String Registraion=API+"submitreg";

    public static final String GetVendor=API+"getvendor/";

    public static final String GetCategory=API+"getcategory/";

    public static final String GetProduct=API+"getproduct/";

    public static final String GetCustomer=API+"customer/";

    public static final String QuickSell=API+"qsellsubmit";

}
