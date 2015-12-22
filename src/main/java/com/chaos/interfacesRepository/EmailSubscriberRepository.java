package com.chaos.interfacesRepository;

import com.chaos.entities.EmailSubscriber;

import java.util.List;

/**
 * Created by marwen on 21/12/15.
 */
public interface EmailSubscriberRepository {
    EmailSubscriber save(EmailSubscriber subscriber);

    List<EmailSubscriber> findAll();

    EmailSubscriber findByEmail(String email);

    void delete(EmailSubscriber subscriber);
}
