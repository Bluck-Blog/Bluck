package com.choo.blog.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("test")
@Service
@Slf4j
public class MailProviderTestImpl implements MailProvider{
    @Override
    public void send(MailMessage mailMessage) {
        log.info("Test MailProvider Send!!");
    }

    @Override
    public void send(List<MailMessage> mailMessages) {

    }
}
