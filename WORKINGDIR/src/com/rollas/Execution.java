package com.rollas;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

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
    ArrayList<Tourist> touristObjects = new ArrayList<>();
    ArrayList<Immigrant> immigrantObjects = new ArrayList<>();
    ArrayList<Student> studentObjects = new ArrayList<>();
    ArrayList<Worker> workerObjects = new ArrayList<>();
    public Execution() throws ParseException {
        Collections.sort(personlist);
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
            if (person.startsWith("11")){
                String nameOfApplicant = FileIO.getNameFromId(pass.getID());
                Tourist dat = new Tourist(pass,photo,finance,document,nameOfApplicant);
                touristObjects.add(dat);
            }
            if (person.startsWith("30")){
                String nameOfApplicant = FileIO.getNameFromId(pass.getID());
                Immigrant dat = new Immigrant(pass,photo,finance,document,nameOfApplicant);
                immigrantObjects.add(dat);
            }
            if (person.startsWith("25")){
                String nameOfApplicant = FileIO.getNameFromId(pass.getID());
                Student dat = new Student(pass,photo,finance,document,nameOfApplicant);
                studentObjects.add(dat);
            }
            if (person.startsWith("23")){
                String nameOfApplicant = FileIO.getNameFromId(pass.getID());
                Worker dat = new Worker(pass,photo,finance,document,nameOfApplicant);
                workerObjects.add(dat);
            }



        }
        for (Tourist genlis: touristObjects){
            genlis.printStatus();
        }
        for (Worker genlis: workerObjects){
            genlis.printStatus();
        }
        for (Student genlis: studentObjects){
            genlis.printStatus();
        }
        for (Immigrant genlis: immigrantObjects){
            genlis.printStatus();
        }

    }



}
