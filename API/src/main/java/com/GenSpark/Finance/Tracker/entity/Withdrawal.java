package com.GenSpark.Finance.Tracker.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Withdrawal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int withdrawalID;
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

    public Withdrawal() {}

    public Withdrawal(Category category, User user, float amount, Date date, String userNote) {
        this.category = category;
        this.user = user;
        this.amount = amount;
        this.date = date;
        this.userNote = userNote;
    }

    public int getWithdrawalID() {
        return withdrawalID;
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
        Withdrawal that;

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        that = (Withdrawal) o;

        return withdrawalID == that.withdrawalID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(withdrawalID);
    }

    @Override
    public String toString() {
        return "Withdrawal{" +
                "withDrawalID=" + withdrawalID +
                ", category=" + category +
                ", user=" + user +
                ", amount=" + amount +
                ", date=" + date +
                ", userNote='" + userNote + '\'' +
                '}';
    }
}
