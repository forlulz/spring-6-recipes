package com.apress.spring6recipes.court.domain;

public class Member {

  private String name;
  private String email;
  private String phone;

  public Member() {
  }

  public Member(String name, String phone, String email) {
    this.name = name;
    this.phone = phone;
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
