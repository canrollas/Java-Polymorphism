package com.rollas;

import java.text.ParseException;

public class Immigrant extends Application {
    Passport pass;
    Photo pht;
    Finance fnc;
    Documents doc;
    String name;
    public Immigrant(Passport pass, Photo pht, Finance fnc, Documents doc, String name) {
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
        }
        return "No reason";
    }

    @Override
    public String calculateVisaDuration() throws ParseException {
        return "Permanent";
    }

    @Override
    public String getVisaType() {
        return "Immigrant";
    }

    @Override
    public boolean checkFinance() {
        int savings = fnc.getSavings();
        if (doc.getDocumentType().contains("IL")) {
            if (doc.getDocumentType().contains("GC")) {
                return savings >= 2000;
            }
            return savings >= 25000;
        } else {
            if (doc.getDocumentType().contains("GC")) {
                return savings >= 4000;
            }
            return savings >= 50000;
        }
    }
}
