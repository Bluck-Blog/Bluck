package com.choo.blog.mail;

import java.util.List;

public interface MailProvider {
    void send(MailMessage mailMessage);

    void send(List<MailMessage> mailMessages);
}
