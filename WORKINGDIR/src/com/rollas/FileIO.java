package com.rollas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileIO {
    public static ArrayList<Passport> getPassport() {
        ArrayList<Passport> passportObjects = new ArrayList<>();
        try {
            File myObj = new File("WORKINGDIR/src/com/rollas/HW2_ApplicantsInfo.csv");
            // Scanner object init
            Scanner myReader = new Scanner(myObj);
            // Looping over the lines with boolean condition if reader object has next line to iterate.
            while (myReader.hasNextLine()) {
                // String object for console out stream
                String data = myReader.nextLine();
                String[] emptyStringArray = data.split(",");
                if (emptyStringArray[0].equals("S")) {
                    Passport newPasport = new Passport(emptyStringArray[1]);
                    newPasport.setPassaportNumber(emptyStringArray[2]);
                    newPasport.setExDate(emptyStringArray[3]);
                    passportObjects.add(newPasport);
                }


            }
            return passportObjects;
        } catch (FileNotFoundException error) {
            System.out.println("Path is incorrect pls check the path!!");
            error.printStackTrace();
            System.exit(-1);
        }
        return passportObjects;
    }

    public static ArrayList<Photo> getPhoto() {
        ArrayList<Photo> photoObjects = new ArrayList<>();
        try {
            File myObj = new File("WORKINGDIR/src/com/rollas/HW2_ApplicantsInfo.csv");
            // Scanner object init
            Scanner myReader = new Scanner(myObj);
            // Looping over the lines with boolean condition if reader object has next line to iterate.
            while (myReader.hasNextLine()) {
                // String object for console out stream
                String data = myReader.nextLine();
                String[] emptyStringArray = data.split(",");

                if (emptyStringArray[0].equals("P")) {
                    Photo newPhoto = new Photo(emptyStringArray[1]);
                    newPhoto.setResolution(emptyStringArray[2]);
                    newPhoto.setPhoto(emptyStringArray[3]);
                    photoObjects.add(newPhoto);
                }


            }
            return photoObjects;
        } catch (FileNotFoundException error) {
            System.out.println("Path is incorrect pls check the path!!");
            error.printStackTrace();
            System.exit(-1);
        }
        return photoObjects;
    }

    public static ArrayList<Finance> getStatus() {
        ArrayList<Finance> financeObjects = new ArrayList<>();
        try {
            File myObj = new File("WORKINGDIR/src/com/rollas/HW2_ApplicantsInfo.csv");
            // Scanner object init
            Scanner myReader = new Scanner(myObj);
            // Looping over the lines with boolean condition if reader object has next line to iterate.
            while (myReader.hasNextLine()) {
                // String object for console out stream
                String data = myReader.nextLine();
                String[] emptyStringArray = data.split(",");

                if (emptyStringArray[0].equals("F")) {
                    Finance newFinance = new Finance(emptyStringArray[1]);
                    newFinance.setIncome(Integer.parseInt(emptyStringArray[2]));
                    newFinance.setSavings(Integer.parseInt(emptyStringArray[3]));
                    financeObjects.add(newFinance);
                }


            }
            return financeObjects;
        } catch (FileNotFoundException error) {
            System.out.println("Path is incorrect pls check the path!!");
            error.printStackTrace();
            System.exit(-1);
        }
        return financeObjects;
    }

    public static ArrayList<Documents> getDocuments() {
        ArrayList<Documents> documentsObjects = new ArrayList<>();
        try {
            File myObj = new File("WORKINGDIR/src/com/rollas/HW2_ApplicantsInfo.csv");
            // Scanner object init

            // Looping over the lines with boolean condition if reader object has next line to iterate.

            ArrayList<String> person = getIDList();
            for(String per:person){
                Documents doc = new Documents(per);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()){
                    String line = myReader.nextLine();
                    String[] splitter = line.split(",");

                    if (splitter[0].equals("D")){
                        if(splitter[1].equals(per)){
                            doc.addDoc(splitter[2]);
                        }
                    }

                }
                documentsObjects.add(doc);
            }
            return documentsObjects;
        } catch (FileNotFoundException error) {
            System.out.println("Path is incorrect pls check the path!!");
            error.printStackTrace();
            System.exit(-1);
        }
        return documentsObjects;
    }

    public static ArrayList<String> getIDList() {
        ArrayList<String> idList = new ArrayList<>();
        try {
            File myObj = new File("WORKINGDIR/src/com/rollas/HW2_ApplicantsInfo.csv");
            // Scanner object init
            Scanner myReader = new Scanner(myObj);
            // Looping over the lines with boolean condition if reader object has next line to iterate.
            while (myReader.hasNextLine()) {
                // String object for console out stream
                String data = myReader.nextLine();
                String[] emptyStringArray = data.split(",");

                if (!idList.contains(emptyStringArray[1])) {
                    idList.add(emptyStringArray[1]);
                }


            }
            return idList;
        } catch (FileNotFoundException error) {
            System.out.println("Path is incorrect pls check the path!!");
            error.printStackTrace();
            System.exit(-1);
        }
        return idList;
    }
    public static String getNameFromId(String id){
        try {
            File myObj = new File("WORKINGDIR/src/com/rollas/HW2_ApplicantsInfo.csv");
            // Scanner object init
            Scanner myReader = new Scanner(myObj);
            // Looping over the lines with boolean condition if reader object has next line to iterate.
            while (myReader.hasNextLine()) {
                // String object for console out stream
                String data = myReader.nextLine();
                String[] emptyStringArray = data.split(",");

                if (emptyStringArray[0].equals("A")) {
                    if (emptyStringArray[1].equals(id)){
                        return emptyStringArray[2];
                    }
                }

            }
            return "";
        } catch (FileNotFoundException error) {
            System.out.println("Path is incorrect pls check the path!!");
            error.printStackTrace();

            System.exit(-1);
        }
        return "";
    }

}
