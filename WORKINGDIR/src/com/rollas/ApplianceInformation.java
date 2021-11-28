package com.rollas;

public abstract class ApplianceInformation implements IApplianceInformation {
    String id;

    public ApplianceInformation(String givenID) {
        id = givenID;
    }

    public String getID() {
        return id;
    }


    public String toString() {
        return "applicant id : " + id;
    }

}
