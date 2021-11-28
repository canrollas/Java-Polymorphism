package com.rollas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Worker extends Application {
    Passport pass;
    Photo pht;
    Finance fnc;
    Documents doc;
    String name;

    public Worker(Passport pass, Photo pht, Finance fnc, Documents doc, String name) {
        super(pass, pht, fnc, doc, name);
        this.pass = pass;
        this.pht = pht;
        this.fnc = fnc;
        this.doc = doc;
        this.name = name;
    }

    @Override
    public void printStatus() throws ParseException {

        if (checkgenStatus().equals("Accepted")) {
            System.out.println("Applicant ID:" + pass.getID() + ",Name:" + name + ", Visa Type:" + getVisaType() + ",Status:"
                    + checkgenStatus() + ",Visa Duration:" + calculateVisaDuration());
        }else {
            System.out.println("Applicant ID:" + pass.getID() + ",Name:" + name + ", Visa Type:" + getVisaType() + ",Status:"
                    + checkgenStatus() + ",Declined Reason:" + getDeclinedReason());

        }


    }

    @Override
    public String checkgenStatus() throws ParseException {
        if (!doc.getDocumentType().contains("LA")) {
            return "Declined";
        }
        else if (calculateVisaDuration().equals("Rejected!")){
            return "Declined";
        }
        else {
            if (checkFinance() && checkPhotoRes() && checkPhotoPosition() && checkPassportStatus() && checkPassportExDate()) {
                return "Accepted";
            }
            return "Declined";
        }
    }

    @Override
    public String getDeclinedReason() throws ParseException {
        if (!doc.getDocumentType().contains("LA")){
            return "Applicant does not have a Letter of Acceptance";
        }
        if (pass.getPassaportNumber().equals("") || pass.getExDate().equals("")) {
            return "Applicant does not have a passport";
        } else if (!checkPassportStatus()) {
            return "Passport is not valid";
        } else if (!checkPassportExDate()) {
            return "Passport expiration date is not valid";
        }
        if (pht.getPhoto().equals("") || pht.getResolution().equals("")) {
            return "Applicant does not have a photo";
        } else if (!checkPhotoRes()) {
            return "Resolution of photo is not valid";
        } else if (!checkPhotoPosition()) {
            return "Position in the photo is not valid";
        }
        if (fnc.getIncome() == -1 || fnc.getSavings() == -1) {
            return "Applicant does not have a financial status report";
        } else if (!checkFinance()) {
            return "Applicant does not have a stable financial status";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date firstDate = sdf.parse(pass.getExDate());
            Date secondDate = sdf.parse("2021-11-27");

            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            float exMonths = diff / 30;
            if (exMonths < 12) {
                return "Passport expiration date is not valid";
            }
            return "No reason";
        }
    }

    @Override
    public String calculateVisaDuration() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date firstDate = sdf.parse(pass.getExDate());
        Date secondDate = sdf.parse("2021-11-27");

        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        float exPassMonths = diff / 30;
        if (exPassMonths < 12) {
            return "Rejected!";
        } else if (exPassMonths >= 12 && exPassMonths < 24) {
            return "1 year";
        }
        if (exPassMonths >= 24 && exPassMonths < 48) {
            return "2 year";
        } else {
            return "5 year";
        }
    }

    @Override
    public String getVisaType() {
        return "Worker";
    }

    @Override
    public boolean checkFinance() {
        int savings = fnc.getSavings();
        return savings >= 4000;
    }
}
