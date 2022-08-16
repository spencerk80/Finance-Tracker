package com.GenSpark.Finance.Tracker.entity;

import com.GenSpark.Finance.Tracker.enums.UserRole;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String fName;
    @Column(nullable = false)
    private String lName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(nullable = false)
    private boolean verified;

    public User() {}

    public User(String password, String fName, String lName, String email, UserRole role, boolean verified) {
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.role = role;
        this.verified = verified;
    }

    public int getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public boolean equals(Object o) {
        User user;

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        user = (User) o;

        return userID == user.userID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID);
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", password='" + password + '\'' +
                ", fName='" + fName + '\'' +
                ", lame='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", verified=" + verified +
                '}';
    }
}
