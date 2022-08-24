package com.GenSpark.Finance.Tracker.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int depositID;
    @ManyToOne
    private Category category;
    @OneToOne
    private User user;
    @Column(nullable = false)
    private float amount;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    private String userNote;

    public Deposit() {}

    public Deposit(Category category, User user, float amount, Date date, String userNote) {
        this.category = category;
        this.user = user;
        this.amount = amount;
        this.date = date;
        this.userNote = userNote;
    }

    public int getDepositID() {
        return depositID;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    @Override
    public boolean equals(Object o) {
        Deposit that;

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        that = (Deposit) o;

        return depositID == that.depositID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(depositID);
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "depositID=" + depositID +
                ", category=" + category +
                ", user=" + user +
                ", amount=" + amount +
                ", date=" + date +
                ", userNote='" + userNote + '\'' +
                '}';
    }
}
