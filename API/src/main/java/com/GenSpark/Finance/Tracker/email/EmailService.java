package com.GenSpark.Finance.Tracker.email;

import javax.mail.MessagingException;

public interface EmailService {
    void sendMail(VerificationEmailContext emailContext) throws MessagingException;
}
