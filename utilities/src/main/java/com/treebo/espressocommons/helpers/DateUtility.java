package com.treebo.espressocommons.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class DateUtility {

    /**
     * compareDates() method will compare two dates.
     * If does not match throws exception, else will not throw any error
     */
    public static void compareDates(String d1, String d2) {
        String tempDate = d1.substring(0, 2);
        String tempYear = d1.substring(d1.length()-4);
        String tempMonth = d1.substring(6,d1.length()-6);

        System.out.println("tempdate--"+tempDate);
        System.out.println("tempYear--"+tempYear);
        System.out.println("tempMonth--"+tempMonth);

        String tempDate2 = d2.substring(0, 2);
        String tempYear2 = d2.substring(d1.length()-4);
        String tempMonth2 = d2.substring(6,d1.length()-6);

        System.out.println("tempdate2--"+tempDate2);
        System.out.println("tempYear2--"+tempYear2);
        System.out.println("tempMonth2--"+tempMonth2);

        HashMap<String, String> month = new HashMap<String, String>() {{
            put("January", "01");
            put("February", "02");
            put("March", "03");
            put("April", "04");
            put("May", "05");
            put("June", "06");
            put("July", "07");
            put("August", "08");
            put("September", "09");
            put("October", "10");
            put("November", "11");
            put("December", "12");
        }};
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            String sdate1= tempYear+"-"+month.get(tempMonth)+"-"+tempDate;
            String sdate2= tempYear2+"-"+month.get(tempMonth2)+"-"+tempDate2;
            Date date1 = formatter.parse(sdate1);
            Date date2 = formatter.parse(sdate2);

            System.out.println("date1 : " + formatter.format(date1));
            System.out.println("date2 : " + formatter.format(date2));

            if (date1.compareTo(date2) < 0) {
                System.out.println("Date1 is before Date2");
            }
        }catch(Throwable t){
            System.out.println("Not a Valid Date");
        }
    }
}
