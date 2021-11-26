package com.rollas;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Passport> trialData = FileIO.getPassport();
		System.out.println("Pasaport objelerinin bastırılması!!");
		System.out.println("___________________________________");
		for (Passport passportData:trialData){
			System.out.println(passportData.getPassaportNumber());
			System.out.println(passportData.getExDate());
			System.out.println("");
		}
		ArrayList<Photo> trialData2 = FileIO.getPhoto();
		System.out.println("Fotograf objelerinin bastırılması!!");
		System.out.println("___________________________________");
		for (Photo photoData:trialData2){
			System.out.println(photoData.getResolution());
			System.out.println(photoData.getPhoto());
			System.out.println("");
		}
		ArrayList<Finance> trialData3 = FileIO.getStatus();
		System.out.println("Finans  objelerinin bastırılması!!");
		System.out.println("___________________________________");
		for (Finance financeData:trialData3){
			System.out.println(financeData.getIncome()+"$");
			System.out.println(financeData.getSavings()+"$");
			System.out.println("");
		}

	}

}
