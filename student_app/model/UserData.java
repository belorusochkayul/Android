package com.example.student_app.model;

public class UserData {
    String username;
    int age;
    Integer imageUrl;

    public UserData(String username, int age, Integer imageUrl) {
        this.username = username;
        this.age = age;
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }
}
