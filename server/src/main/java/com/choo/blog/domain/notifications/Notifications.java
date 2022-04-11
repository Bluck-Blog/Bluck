package com.choo.blog.domain.notifications;

import com.choo.blog.domain.users.User;

import javax.persistence.*;

@Entity
public class Notifications {
    @Id @GeneratedValue
    @Column(name = "notification_id")
    private Long id;

    @Enumerated
    private NotificationType type;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
}
