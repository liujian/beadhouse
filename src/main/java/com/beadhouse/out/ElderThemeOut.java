package com.beadhouse.out;

public class ElderThemeOut {

  private String logo;
  private String color;
  private int versionCode;

  public ElderThemeOut(String logo, String color, int versionCode) {
    this.logo = logo;
    this.color = color;
    this.versionCode = versionCode;
  }

  public String getLogo() {
    return logo;
  }
  public void setLogo(String logo) {
    this.logo = logo;
  }
  public String getColor() {
    return color;
  }
  public void setColor(String color) {
    this.color = color;
  }

  public int getVersionCode() {
    return versionCode;
  }

  public void setVersionCode(int versionCode) {
    this.versionCode = versionCode;
  }
}
