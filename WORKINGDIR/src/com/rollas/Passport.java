package com.rollas;
public class Passport extends ApplianceInformation{

    private String passportNumber;
    private String exDate;

    public Passport(String givenID) {
        super(givenID);
    }


    public String getExDate() {
        return exDate;
    }

    public void setExDate(String exDate) {
        this.exDate = exDate;
    }

    public String getPassaportNumber() {
        return passportNumber;
    }

    public void setPassaportNumber(String passportId) {
        this.passportNumber = passportId;
    }

    public String toString() {
        String r = "PASSPORT\n" + super.toString() + " passportNumber : " + getPassaportNumber() + " expiration date : " + getExDate();
        return r;

    }
}
