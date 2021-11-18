package com.rollas;
public class Passport extends ApplianceInformation{

    private String passportId;
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

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }
}
