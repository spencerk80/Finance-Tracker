package com.GenSpark.Finance.Tracker.entity;

public class NewUserRequest {
    private String email, fname, lname, password, passwordConfirm;

    public NewUserRequest() {
    }

    public NewUserRequest(String email, String fname, String lname, String password, String passwordConfirm) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public boolean validate() {
        //(Alphanumeric . - _)@(Alphanumeric).(Alphanumeric)
        if( ! email.matches("[a-zA-Z\\d._-]+@.+\\..+")) return false;
        if("".equals(fname)) return false;
        if("".equals(lname)) return false;
        //Requires the presence of at least one lower, one upper and one digit. Symbols allowed:
        //!@#$%^&*()_+-='";:,<.>\/?
        if( ! password.matches("(?=.+[a-z])(?=.+[A-Z])(?=.+\\d)[a-zA-Z\\d!@#$%^&*()_+-='\";:,<.>\\/?]{8,32}"))
            return false;

        return password.equals(passwordConfirm);
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "email='" + email + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                '}';
    }
}
