package com.GenSpark.Finance.Tracker.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BlacklistedJwt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String jwt;
    @Temporal(value = TemporalType.TIMESTAMP)
    Date expiresAt;

    public BlacklistedJwt() {}

    public BlacklistedJwt(String jwt, Date expiresAt) {
        this.jwt = jwt;
        this.expiresAt = expiresAt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}
