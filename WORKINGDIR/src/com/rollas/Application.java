package com.rollas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public abstract class Application {
    Passport pass;
    Photo pht;
    Finance fnc;
    Documents doc;
    String name;

    public Application(Passport pass, Photo pht, Finance fnc, Documents doc, String name) {
        this.pass = pass;
        this.pht = pht;
        this.fnc = fnc;
        this.doc = doc;
        this.name = name;
    }

    public abstract void printStatus() throws ParseException;

    public abstract String checkgenStatus() throws ParseException;

    public abstract String getDeclinedReason() throws ParseException;

    public abstract String calculateVisaDuration() throws ParseException;

    public boolean checkPassportStatus() {
        if (pass.getPassaportNumber().startsWith("P")) {
            if (pass.getPassaportNumber().length() == 10) {
                String[] dataTokens = pass.getPassaportNumber().split("");
                int counter = 0;
                for (String genkey : dataTokens) {
                    try {
                        Double.parseDouble(genkey);
                        counter++;
                    } catch (NumberFormatException ignored) {

                    }
                }
                return counter >= 3;
            } else {
                return false;
            }

        } else if (pass.getPassaportNumber().isEmpty()) {
            return false;
        }
        return false;
    }

    public boolean checkPassportExDate() throws ParseException {
        if (pass.getExDate().equals("")) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date firstDate = sdf.parse(pass.getExDate());
        Date secondDate = sdf.parse("2021-11-27");

        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff >= 180;
    }

    public boolean checkPhotoRes() {
        if (pht.getResolution().equals("")) {
            return false;
        }
        String[] splitter = pht.getResolution().split("x");
        boolean dat1 = 600 <= Integer.parseInt(splitter[0]);
        boolean dat2 = Integer.parseInt(splitter[0]) <= 1200;
        boolean dat3 = 600 <= Integer.parseInt(splitter[1]);
        boolean dat4 = Integer.parseInt(splitter[1]) <= 1200;
        return (splitter[0].equals(splitter[1]) && dat1 && dat2 && dat3 && dat4);
    }

    public boolean checkPhotoPosition() {
        return (pht.getPhoto().equals("Natural Smile") || pht.getPhoto().equals("Neutral Face"));
    }

    public abstract String getVisaType();



    public abstract boolean checkFinance();

}
