package com.chaos.interfacesRepository;

import com.chaos.entities.EmailSubscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by marwen on 21/12/15.
 */
public interface EmailSubscriberRepository extends JpaRepository<EmailSubscriber, Integer> {

    EmailSubscriber findByEmail(String email);

}
