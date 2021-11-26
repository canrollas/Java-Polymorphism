package com.rollas;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Passport> trialData = FileIO.getPassport();
		System.out.println("Pasaport objelerinin bastırılması!!");
		System.out.println("___________________________________");
		for (Passport passportData:trialData){
			System.out.println(passportData);
		}
		ArrayList<Photo> trialData2 = FileIO.getPhoto();
		System.out.println("Fotograf objelerinin bastırılması!!");
		System.out.println("___________________________________");
		for (Photo photoData:trialData2){
			System.out.println(photoData);
		}
		ArrayList<Finance> trialData3 = FileIO.getStatus();
		System.out.println("Finans  objelerinin bastırılması!!");
		System.out.println("___________________________________");
		for (Finance financeData:trialData3){
			System.out.println(financeData);
		}
		System.out.println(DateParser.getDiffrence("2027-03-21","2021-11-25"));

	}

}
