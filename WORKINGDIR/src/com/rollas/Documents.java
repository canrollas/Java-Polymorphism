package com.rollas;


import java.util.ArrayList;

public class Documents extends ApplianceInformation{
    ArrayList<String> doc = new ArrayList<>();
    private int exDate = 0;
    public Documents(String givenID) {
        super(givenID);
    }

    public int getExDate() {
        return exDate;
    }

    public void setExDate(int exDate) {
        this.exDate = exDate;
    }

    public ArrayList<String> getDocumentType() {
        return doc;
    }
    public void addDoc(String docType){
        doc.add(docType);
    }
    public String getID() {
        return id;
    }


    @Override
    public String toString() {
        return "Documents {" +
                "doc='" + doc + '\'' +
                "id='" + super.getID() + '\'' +
                '}';
    }
}
