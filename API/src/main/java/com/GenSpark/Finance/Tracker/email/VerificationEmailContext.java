package com.GenSpark.Finance.Tracker.email;

import java.util.HashMap;
import java.util.Map;

public class VerificationEmailContext {
    private String url, token, to, from, subject;
    private Map<String, Object> context;

    public VerificationEmailContext() {
        context = new HashMap<>();
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        context.put("url", url + "/" + token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        context.put("token", token);
    }
}
