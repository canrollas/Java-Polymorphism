package com.rollas;

import java.text.ParseException;


public class Tourist extends Application {

    private final Passport pass;
    private final Documents doc;
    private final Finance fnc;
    private final Photo pht;
    private final String name;
    public Tourist(Passport pass, Photo pht, Finance fnc, Documents doc, String name) {
        super(pass,pht,fnc,doc,name);
        this.doc = super.doc;
        this.fnc = super.fnc;
        this.pht = super.pht;
        this.pass = super.pass;
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
        if (checkFinance() && checkPhotoRes() && checkPhotoPosition() &&  checkPassportStatus() && checkPassportExDate()) {
            return "Accepted";
        }
        return "Declined";
    }


    @Override
    public String getDeclinedReason() throws ParseException {
        if (pass.getPassaportNumber().equals("") || pass.getExDate().equals("")) {
            return "Applicant does not have a passport";
        } else if (!checkPassportStatus()) {
            return "Passport is not valid";
        } else if (!checkPassportExDate()) {
            return "Passport expiration date is not valid";
        }
        else if (pht.getPhoto().equals("") || pht.getResolution().equals("")) {
            return "Applicant does not have a photo";
        } else if (!checkPhotoRes()) {
            return "Resolution of photo is not valid";
        } else if (!checkPhotoPosition()) {
            return "Position in the photo is not valid";
        }
        else if (fnc.getIncome() == -1 || fnc.getSavings() == -1) {
            return "Applicant does not have a financial status report";
        } else if (!checkFinance()) {
            return "Applicant does not have a stable financial status";
        }
        return "No reason";
    }

    @Override
    public String calculateVisaDuration() {
        if (doc.getDocumentType().contains("IL")) {
            double DC = ((fnc.getIncome() - 2000) * 6 + fnc.getSavings()) / 6000;
            if (DC < 2 && 1 <= DC) {
                return "6 Months";
            } else if (DC < 4 && DC >= 2) {
                return "1 Year";
            } else {
                return "5 Year";
            }
        }else {
            double DC = ((fnc.getIncome() - 2000) * 6 + fnc.getSavings()) / 12000;
            if (DC < 2 && 1 <= DC) {
                return "6 Months";
            } else if (DC < 4 && DC >= 2) {
                return "1 Year";
            } else {
                return "5 Year";
            }
        }

    }

    @Override
    public String getVisaType() {
        return "Tourist";
    }


    @Override
    public boolean checkFinance() {
        if (fnc.getSavings() == -1 || fnc.getIncome() == -1) {
            return false;
        }
        int income = fnc.getIncome();
        int savings = fnc.getSavings();
        if (doc.getDocumentType().contains("IL")) {
            if (1000 <= income && income <= 1500) {
                return savings >= 6000;
            } else if (1500 <= income && income <= 2000) {
                return savings >= 3000;
            } else return income >= 2000;

        } else {
            if (2000 <= income && income <= 3000) {
                return savings >= 12000;
            } else if (3000 <= income && income <= 4000) {
                return savings >= 6000;
            } else return income >= 4000;
        }

    }
}
