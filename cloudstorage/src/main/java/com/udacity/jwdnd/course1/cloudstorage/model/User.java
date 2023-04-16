package com.udacity.jwdnd.course1.cloudstorage.model;


import org.springframework.beans.factory.annotation.Autowired;

public class User {

    private Integer userid;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String salt;

    public User(Integer id, String username, String password, String firstName, String lastName, String salt) {
        this.userid = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salt = salt;
    }


    public Integer getId() {
        return userid;
    }

    public void setId(Integer id) {
        this.userid = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
