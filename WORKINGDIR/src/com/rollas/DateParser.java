package com.rollas;

public class DateParser {

    // This is basic structure of DateParser class and methods for homework
    private static int[] parser(String date) {
        /*
        This method gets a string date as argument and parse in to integer array and returns it.
         */
        try {
            String[] parsedDate = date.split("-");
            int day = Integer.parseInt(parsedDate[0]);
            int year = Integer.parseInt(parsedDate[2])+2000;
            int month = Integer.parseInt(parsedDate[1]);
            return new int[]{day, month, year};
        } catch (Exception e) {
            System.out.println("DateParse error occured!!!");
            e.printStackTrace();
        }
        return new int[0];


    }

    // This method makes months in days -> sth like ---> 3.month -> 58 days includes minimum

    private static int getMonthInDays(int month) {
        int changerCounter = 0;
        int dayCounter = 0;
        for (int i = 0; i < month; i++) {
            if (i != 2){
                if (changerCounter%2 == 0){
                    dayCounter = dayCounter+31;
                }else{
                    dayCounter =dayCounter+30;
                }
            }else{
                dayCounter = dayCounter+28;
            }
            changerCounter++;

        }
        return dayCounter;
    }
    // This method counts leap year and returns it .
    private static int countLeapYear(int year ,int month){
        int years = year;
        if (month<= 2)
        {
            years--;
        }
        return years / 4 - years / 100 + years / 400;
    }

    public static int getDiffrence(String date1, String date2) {
        /*
        This method finds date diff. in days in given dates
        ! This method is static -> there is no calling object in every usage
         */
        int[] intArrayDate1 = parser(date1);
        int[] intArrayDate2 = parser(date2);
        int dater1  =intArrayDate1[0]+intArrayDate1[2]*365+getMonthInDays(intArrayDate1[1])+countLeapYear(intArrayDate1[2],intArrayDate1[1]);
        int dater2  =intArrayDate2[0]+intArrayDate2[2]*365+getMonthInDays(intArrayDate2[1])+countLeapYear(intArrayDate2[2],intArrayDate2[1]);
        return dater2-dater1;

    }
}