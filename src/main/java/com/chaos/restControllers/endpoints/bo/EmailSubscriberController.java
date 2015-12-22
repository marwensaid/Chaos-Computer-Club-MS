package com.chaos.restControllers.endpoints.bo;

/**
 * Created by marwen on 21/12/15.
 */

import com.chaos.entities.EmailSubscriber;
import com.chaos.interfacesRepository.EmailSubscriberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/admin")
public class EmailSubscriberController {

    private final static Logger logger = LoggerFactory.getLogger(EmailSubscriberController.class);

    @Autowired
    private EmailSubscriberRepository emailSubscriberRepository;

    @RequestMapping(value="/emailSubscribers", method= RequestMethod.GET)
    public List<EmailSubscriber> findAllEmailSubscribers()
    {
        return emailSubscriberRepository.findAll();
    }

    @RequestMapping(value="/emailSubscribers/{email}", method=RequestMethod.DELETE)
    public void deleteEmailSubscriber(@PathVariable("email") String email)
    {
        logger.debug("Deleting EmailSubscriber : "+email);
        EmailSubscriber subscriber = emailSubscriberRepository.findByEmail(email);
        if(subscriber != null)
        {
            emailSubscriberRepository.delete(subscriber);
        }
    }
}
