package com.rollas;

public class Photo extends ApplianceInformation{
    private String photo;
    private String resolution;

    public Photo(String givenID) {
        super(givenID);
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String toString(){
        String r = "PHOTO\n" + super.toString() + " resolution : " + getResolution() + " position : " + getPhoto();
        return r;
    }
}
