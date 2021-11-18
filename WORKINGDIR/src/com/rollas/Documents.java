package com.rollas;

public abstract class Documents implements IApplianceInformation {
    String id;
    String doc;

    public Documents(String givenID,String document) {
        id = givenID;
        doc = document;
    }

    public String getDocumentType() {
        return doc;
    }

    public String getID() {
        return id;
    }

    public boolean belongsSameApplicant(Object other) {
        if (other == null) {
            System.out.println("error : argument object is null");

            System.exit(-1);

        } else {
            if (other instanceof IApplianceInformation) {
                IApplianceInformation temp = (IApplianceInformation) other;
                return temp.getID() == this.getID();
            } else {
                System.out.println("argument object is not same type with calling object. Argument object's type is : " + other.getClass());
                System.exit(-1);
            }
        }
        return false;

    }
}
