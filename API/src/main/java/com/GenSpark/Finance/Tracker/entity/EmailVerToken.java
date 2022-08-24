package com.GenSpark.Finance.Tracker.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "emailVerificationTokens")
public class EmailVerToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String token;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp timestamp;

    @Column(updatable = false)
    @Basic(optional = false)
    private LocalDateTime expiredAt;

    @ManyToOne
    private User user;

    @Transient
    private boolean isExpired;

    public EmailVerToken() {}

    public EmailVerToken(String token, Timestamp timestamp, LocalDateTime expiredAt, User user, boolean isExpired) {
        this.token = token;
        this.timestamp = timestamp;
        this.expiredAt = expiredAt;
        this.user = user;
        this.isExpired = isExpired;
    }

    public long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    @Override
    public boolean equals(Object o) {
        EmailVerToken that;

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        that = (EmailVerToken) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
