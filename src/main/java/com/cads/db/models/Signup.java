package com.cads.db.models;


import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Generated;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "signup")
public class Signup {
    @Id
    @GeneratedValue()
    private int id = 0;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String buildingName;

    @Column
    private String buildingCity;

    @Column
    private String buildingCountry;

    @Column
    private String buildingPin;

    @Column
    private String buildingAddress;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "signupid", columnDefinition = "VARCHAR(255)")
    private UUID signupId;

    @Column
    private Timestamp signupTm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingCity() {
        return buildingCity;
    }

    public void setBuildingCity(String buildingCity) {
        this.buildingCity = buildingCity;
    }

    public String getBuildingCountry() {
        return buildingCountry;
    }

    public void setBuildingCountry(String buildingCountry) {
        this.buildingCountry = buildingCountry;
    }

    public String getBuildingPin() {
        return buildingPin;
    }

    public void setBuildingPin(String buildingPin) {
        this.buildingPin = buildingPin;
    }

    public String getBuildingAddress() {
        return buildingAddress;
    }

    public void setBuildingAddress(String buildingAddress) {
        this.buildingAddress = buildingAddress;
    }

//    public UUID getSignupId() {
//        return signupId;
//    }
//
//    public void setSignupId(UUID signupId) {
//        this.signupId = signupId;
//    }

    public Timestamp getSignupTm() {
        return signupTm;
    }

    public void setSignupTm(Timestamp signupTm) {
        this.signupTm = signupTm;
    }

    @Override
    public String toString() {
        return "Signup{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", buildingCity='" + buildingCity + '\'' +
                ", buildingCountry='" + buildingCountry + '\'' +
                ", buildingPin='" + buildingPin + '\'' +
                ", buildingAddress='" + buildingAddress + '\'' +
                ", signupId='" + signupId + '\'' +
                ", signupTm=" + signupTm +
                '}';
    }
}
