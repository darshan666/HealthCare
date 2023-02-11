package com.example.hc;

public class DataStore {
    String name,username,phoneno;

    public DataStore() {
    }

    public DataStore(String name, String username,String phoneno) {
        this.name = name;
        this.username = username;

        this.phoneno = phoneno;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }


}
