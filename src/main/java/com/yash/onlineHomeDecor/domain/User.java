/**
 * @author Darshan soni
 * @version 1.0
 */
package com.yash.onlineHomeDecor.domain;


import java.util.Date;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private Date dateOfBirth;
    private String role;//cutomer or seller

    //constructor

    public User(){}

        public User(int id,String name,String email,String password,String address,Date dateOfBirth,String role){

            this.id = id;
            this.name = name;
            this.email = email;
            this.password = password;
            this.address = address;
            this.dateOfBirth = dateOfBirth;
            this.role = role;

        }

    //getters and setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //toString method

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", role='" + role + '\'' +
                '}';
    }
}
