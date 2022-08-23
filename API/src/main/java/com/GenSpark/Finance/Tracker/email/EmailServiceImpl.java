package com.GenSpark.Finance.Tracker.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender        mailSender;
    private final SpringTemplateEngine  templateEngine;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailsender, SpringTemplateEngine templateEngine) {
        this.mailSender = emailsender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendMail(VerificationEmailContext emailContext) throws MessagingException {
        MimeMessage         message = mailSender.createMimeMessage();
        Context             context = new Context();
        String              emailContent;
        MimeMessageHelper   helper = new MimeMessageHelper(
                message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name()
        );
        context.setVariables(emailContext.getContext());
        emailContent = templateEngine.process("/templates/email.html, context", context);

        helper.setTo(emailContext.getTo());
        helper.setSubject(emailContext.getSubject());
        helper.setFrom(emailContext.getFrom());
        helper.setText(emailContent, true);
        mailSender.send(message);
    }
}
