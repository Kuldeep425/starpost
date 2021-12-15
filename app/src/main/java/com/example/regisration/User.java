package com.example.regisration;

public class User {
  private  String uid,name,userphonenumber,uprofileimage;

    public User(){


    }
    public User(String uid, String name, String userphonenumber, String uprofileimage) {
        this.uid = uid;
        this.name = name;
        this.userphonenumber = userphonenumber;
        this.uprofileimage = uprofileimage;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserphonenumber() {
        return userphonenumber;
    }

    public void setUserphonenumber(String userphonenumber) {
        this.userphonenumber = userphonenumber;
    }

    public String getUprofileimage() {
        return uprofileimage;
    }

    public void setUprofileimage(String uprofileimage) {
        this.uprofileimage = uprofileimage;
    }
}
