package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.dao.UserDao;
import com.GenSpark.Finance.Tracker.email.EmailService;
import com.GenSpark.Finance.Tracker.email.VerificationEmailContext;
import com.GenSpark.Finance.Tracker.entity.EmailVerToken;
import com.GenSpark.Finance.Tracker.entity.User;
import com.GenSpark.Finance.Tracker.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao               userDao;
    private final EmailVerTokenService  emailVerTokenService;
    private final EmailService          emailService;
    private final String                tokenServiceUrl,
                                        from;

    @Autowired
    public UserServiceImpl(
            UserDao userDao, EmailVerTokenService emailVerTokenService, EmailService emailService,
            @Value("emailVerToken.service.url") String tokenServiceUrl, @Value("spring.mail.username") String from
    ) {
        this.userDao = userDao;
        this.emailVerTokenService = emailVerTokenService;
        this.emailService = emailService;
        this.tokenServiceUrl = tokenServiceUrl;
        this.from = from;
    }

    @Override
    public User getUserByID(int userID) {
        return userDao.findById(userID).orElseThrow(() -> new ResourceNotFoundException("No User Found With Id: " + userID));
    }

    @Override
    public String saveUser(User user) {
        userDao.save(user);
        return "Successfully added the category";
    }

    @Override
    public String updateUser(User user) {
        userDao.save(user);
        return "Successfully updated the category";
    }

    @Override
    public String deleteUserByID(int userID) {
        userDao.deleteById(userID);
        return "Successfully deleted the category";
    }

    @Override
    public void sendRegConfEmail(User user) {
        EmailVerToken token = emailVerTokenService.createToken();
        VerificationEmailContext context = new VerificationEmailContext();

        token.setUser(user);
        emailVerTokenService.saveToken(token);

        context.setToken(token.getToken());
        context.setUrl(this.tokenServiceUrl);
        context.setFrom(from);
        context.setSubject("Finish your account set up!");

        try {
            emailService.sendMail(context);
        } catch(MessagingException e) {
            e.printStackTrace(); //for now
        }
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userDao.findUserByEmail(email);
        if (user.isPresent()) return user.get();
        else throw new RuntimeException("User with email: " + email + " not found.");
    }
}
