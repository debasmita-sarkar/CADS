package com.cads.db.models;


import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

@Entity
@Table(name = "signup")
public class Signup {
    @Id
    @GeneratedValue()
    private int id = 0;

    @Column
    @NotNull
    private String firstName;

    @Column
    @NotNull
    private String lastName;

    @Column
    @NotNull
    private String email;

    @Column
    @NotNull
    private String buildingName;

    @Column
    @NotNull
    private String buildingCity;

    @Column
    @NotNull
    private String buildingCountry;

    @Column
    @NotNull
    private String buildingPin;

    @Column
    @NotNull
    private String buildingAddress;

    @Column
    private String signupId;

    @Column
    private Timestamp signupTm;

    /* Signup state indicates which state
       the sign up process is currently in.
       This values may change in the future.
       TODO: Use enums?
       1 -> Registration done, email sent.
       2 -> Email verification done, password not set.
       3 -> Password set. After this the user can directly go to
            home. So when signupState == 3, sign up GET should redirect
            to Login.
     */
    @Column
    private int signupState;

    public int getSignupState() {
        return signupState;
    }

    public void setSignupState(int signupState) {
        if ((signupState < 1) || (signupState > 3))
            throw new IllegalArgumentException("The value must be between 1, 2 and 3");
        this.signupState = signupState;
    }

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

    public String getSignupId() {
        return signupId;
    }

    public void setSignupId(String signupId) {
        this.signupId = signupId;
    }

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
                ", signupState=" + signupState +
                '}';
    }

    public Signup()
    {

    }

    public Signup(Signup other) {
        this.id = other.id;
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.email = other.email;
        this.buildingName = other.buildingName;
        this.buildingCity = other.buildingCity;
        this.buildingCountry = other.buildingCountry;
        this.buildingPin = other.buildingPin;
        this.buildingAddress = other.buildingAddress;
        this.signupId = other.signupId;
        this.signupTm = other.signupTm;
        this.signupState = other.signupState;
    }

    public void regenerateSignupId() {
        UUID signupId = UUID.randomUUID();
        Timestamp now = new Timestamp(Calendar.getInstance().getTime().getTime());
        this.setSignupId(signupId.toString());
        this.setSignupTm(now);
        this.setSignupState(1);
    }
}
