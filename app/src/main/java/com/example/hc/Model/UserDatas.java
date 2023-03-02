package com.example.hc.Model;

public class UserDatas {
    private String id;
    private String Name;
    private String imageUrl;

    public UserDatas(String id, String name, String imageUrl) {
        this.id = id;
        Name = name;
        this.imageUrl = imageUrl;

    }

    public UserDatas() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
