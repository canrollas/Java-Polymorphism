package com.rollas;

import java.text.ParseException;
import java.util.ArrayList;

public class Execution {
    ArrayList<Passport> trialData = FileIO.getPassport();
    ArrayList<Photo> trialData2 = FileIO.getPhoto();
    ArrayList<Finance> trialData3 = FileIO.getStatus();
    ArrayList<Documents> trialData4 = FileIO.getDocuments();
    ArrayList<String> personlist = FileIO.getIDList();
    Passport pass;
    Photo photo;
    Finance finance;
    Documents document;
    ArrayList<Application> lister = new ArrayList<>();


    public Execution() throws ParseException {

        for (String person : personlist) {

            for (Passport passportData : trialData) {
                if (passportData.getID().equals(person)) {
                    pass = passportData;
                }
            }
            for (Photo photoData : trialData2) {
                if (photoData.getID().equals(person)) {
                    photo = photoData;
                }

            }
            for (Finance financeData : trialData3) {
                if (financeData.getID().equals(person)) {
                    finance = financeData;
                }

            }
            for (Documents doc : trialData4) {
                if (doc.getID().equals(person)) {
                    document = doc;

                }
            }
            if (!document.getID().equals(person)){
                document = new Documents(person);
            }
            if (!finance.getID().equals(person)){
                finance = new Finance(person);
                finance.setIncome(-1);
                finance.setSavings(-1);
            }
            if (!photo.getID().equals(person)){
                photo = new Photo(person);
                photo.setPhoto("");
                photo.setResolution("");
            }
            if (!pass.getID().equals(person)){
                pass = new Passport(person);
                pass.setPassaportNumber("");
                pass.setExDate("");

            }
            String nameOfApplicant = FileIO.getNameFromId(pass.getID());
            Application dat = new Application(pass,photo,finance,document,nameOfApplicant);
            lister.add(dat);
        }
        for (Application genlis:lister){
            genlis.printStatus();
        }
    }



}
