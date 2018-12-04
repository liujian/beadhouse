package com.beadhouse.in;

public class NotifyParam extends BasicIn {


    private String token;
    private int notifyType;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(int notifyType) {
        this.notifyType = notifyType;
    }
}
