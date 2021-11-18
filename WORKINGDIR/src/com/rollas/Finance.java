package com.rollas;

public class Finance extends ApplianceInformation{

    int income;
    int savings;

    public Finance(String givenID) {
        super(givenID);
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getIncome() {
        return income;
    }

    public int getSavings() {
        return savings;
    }

    public void setSavings(int savings) {
        this.savings = savings;
    }
}
