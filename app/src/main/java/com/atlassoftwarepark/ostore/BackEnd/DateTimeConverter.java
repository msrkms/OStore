package com.atlassoftwarepark.ostore.BackEnd;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateTimeConverter {

    public String dateConvert(String Date){
        String sDate=Date;

        try{
            DateFormat format = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
            Date date = format.parse(Date);
            String pattern2 = "dd-MM-yyyy";
            DateFormat dateFormat = new SimpleDateFormat(pattern2);
            String strDate = dateFormat.format(date);
            sDate=strDate;
        }catch (Exception e){
        }

        return sDate;
    }
}
