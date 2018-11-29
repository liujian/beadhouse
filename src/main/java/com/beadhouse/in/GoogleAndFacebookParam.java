package com.beadhouse.in;

public class GoogleAndFacebookParam extends BasicIn {

    private String firstName;

    private String lastName;

    private String phone;

    private String userAvatar;

    private String fireBaseToken;

    private int type; //1-google,,,2-facebook

    private String googleLoginId;

    private String faceBookLoginId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getFireBaseToken() {
        return fireBaseToken;
    }

    public void setFireBaseToken(String fireBaseToken) {
        this.fireBaseToken = fireBaseToken;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGoogleLoginId() {
        return googleLoginId;
    }

    public void setGoogleLoginId(String googleLoginId) {
        this.googleLoginId = googleLoginId;
    }

    public String getFaceBookLoginId() {
        return faceBookLoginId;
    }

    public void setFaceBookLoginId(String faceBookLoginId) {
        this.faceBookLoginId = faceBookLoginId;
    }
}
