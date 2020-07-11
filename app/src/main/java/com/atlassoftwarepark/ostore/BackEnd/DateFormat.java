package com.atlassoftwarepark.ostore.BackEnd;

public class DateFormat {
    private static String[] split;

    public  String dateDecode(String hederText){
        split = hederText.split(",");
        System.out.println(split[0]);
        System.out.println(split[1]);
        String dm=split[0];
        String y=split[1];

      return  getFull(dm,y);

    }

    public String getFull(String dateMonth,String year){
        String[] splitDM=dateMonth.split("\\s");
        System.out.println(splitDM[0]);
        System.out.println(splitDM[1]);
        System.out.println(year);

     return   myDate(splitDM[0],splitDM[1],year);
    }

    public String myDate(String month,String day,String year){
        String m="";
        if(month.equals("Jan")){
             m="01";
        }
        if(month.equals("Feb")){
             m="02";
        }
        if(month.equals("Mar")){
             m="03";
        }
        if(month.equals("Apr")){
             m="04";
        }
        if(month.equals("May")){
             m="05";
        }
        if(month.equals("Jun")){
             m="06";
        }
        if(month.equals("Jul")){
             m="07";
        }
        if(month.equals("Aug")){
             m="08";
        }
        if(month.equals("Sep")){
             m="09";
        }
        if(month.equals("Oct")){
             m="10";
        }
        if(month.equals("Nov")){
             m="11";
        }
        if(month.equals("Dec")){
             m="12";
        }


        String decodedDate=day+"-"+m+"-"+year;
        String finalDate=decodedDate.replaceAll("\\s", "");
        System.out.println(finalDate);
        return finalDate;

    }

}
