package com.sarad.client.event.listener;

import com.sarad.client.entity.User;
import com.sarad.client.event.RegistrationCompleteEvent;
import com.sarad.client.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //Create the verification token for User with Link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        userService.saveVerificationTokenForUser(token, user);

        //Send mail to user
        String url =
                event.getApplicationUrl() + "/verifyRegistration?token=" + token;

        //Send verification email method to user
        //Mocking instead
        log.info("Click the link to verify your account: {}", url);
    }
}
