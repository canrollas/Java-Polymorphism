package com.rollas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Application {

    private Passport pass;
    private Photo pht;
    private Finance fnc;
    private Documents doc;
    private String name;

    public Application(Passport pass, Photo pht, Finance fnc, Documents doc, String name) {
        this.pass = pass;
        this.pht = pht;
        this.fnc = fnc;
        this.doc = doc;
        this.name = name;
    }

    public void printStatus() throws ParseException {
        System.out.println(fnc);
        System.out.println(doc);
        System.out.println(pass);
        System.out.println(pht);

        if (!calculateVisaDuration().equals("Rejected!")) {
            if (checkgenStatus().equals("Accepted")) {
                System.out.println("Applicant ID:" + pass.getID() + ",Name:" + name + ", Visa Type:" + getVisaType() + ",Status:"
                        + checkgenStatus() + ",Visa Duration:" + calculateVisaDuration());
            }else {
                System.out.println("Applicant ID:" + pass.getID() + ",Name:" + name + ", Visa Type:" + getVisaType() + ",Status:"
                        + checkgenStatus() + ",Declined Reason:" + getDeclinedReason());

            }

        } else {
            System.out.println("Applicant ID:" + pass.getID() + ",Name:" + name + ", Visa Type:" + getVisaType() + ",Status:"
                    + checkgenStatus() + ",Declined Reason:" + "Passport expiration date is not valid");

        }


    }

    public String checkgenStatus() throws ParseException {
        if (checkFinance() && checkPhotoRes() && checkPhotoPosition() && checkDocument() && checkPassportStatus() && checkPassportExDate()) {
            return "Accepted";
        }
        return "Declined";
    }

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
        if (fnc.getIncome() == 0 || fnc.getSavings() == 0) {
            return "Applicant does not have a financial status report";
        } else if (!checkFinance()) {
            return "Applicant does not have a stable financial status";
        } else {
            if (getVisaType().equals("Worker")) {
                if (!doc.getDocumentType().contains("LA")) {
                    return "Applicant does not have a letter of acceptance";
                }
            } else if (getVisaType().equals("Educational")) {
                if (!doc.getDocumentType().contains("LA")) {
                    return "Applicant does not have a letter of acceptance";
                }
            }
        }
        return "No reason";
    }

    public String calculateVisaDuration() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        if (getVisaType().equals("Tourist")) {
            if (doc.getDocumentType().contains("IL")) {
                double DC = ((fnc.getIncome() - 2000) * 6 + fnc.getSavings()) / 6000;
                if (DC < 2 && 1 <= DC) {
                    return "6 Months";
                } else if (DC < 4 && DC >= 2) {
                    return "1 Year";
                } else {
                    return "5 Year";
                }
            } else {
                double DC = ((fnc.getIncome() - 2000) * 6 + fnc.getSavings()) / 12000;
                if (DC < 2 && 1 <= DC) {
                    return "6 Months";
                } else if (DC < 4 && DC >= 2) {
                    return "1 Year";
                } else {
                    return "5 Year";
                }
            }

        } else if (getVisaType().equals("Immigrant")) {
            return "Permanent";
        } else if (getVisaType().equals("Educational")) {

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
                return "4 year";
            }


        } else {

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


    }

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

    public String getVisaType() {
        if (doc.getID().startsWith("11")) {
            return "Tourist";
        } else if (doc.getID().startsWith("23")) {
            return "Worker";
        } else if (doc.getID().startsWith("25")) {
            return "Educational";
        } else return "Immigrant";
    }

    public boolean checkDocument() {

        if (doc.getID().startsWith("11")) {
            // turist
            return true;
        } else if (doc.getID().startsWith("23")) {
            // işçi
            return doc.getDocumentType().contains("LA");
        } else // göcmen
            if (doc.getID().startsWith("25")) {
                return doc.getDocumentType().contains("LA");
                // ögrenci
            } else return doc.getID().startsWith("30");
    }

    public boolean checkFinance() {
        if (fnc.getSavings() == -1 || fnc.getIncome() == -1) {
            return false;
        }
        int income = fnc.getIncome();
        int savings = fnc.getSavings();
        if (doc.getID().startsWith("11")) {
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
        } else if (doc.getID().startsWith("23")) {

            return savings >= 4000;
        } else if (doc.getID().startsWith("25")) {
            if (doc.getDocumentType().contains("IL")) {
                if (500 <= income && income <= 1000) {
                    return savings >= 3000;
                } else if (1000 <= income && income <= 1500) {
                    return savings >= 1500;
                } else return income >= 1500;

            } else {
                if (1000 <= income && income <= 2000) {
                    return savings >= 6000;
                } else if (3000 <= income && income <= 4000) {
                    return savings >= 3000;
                } else return income >= 3000;
            }
        } else {
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
}
