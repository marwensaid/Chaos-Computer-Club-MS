package com.chaos.services;

import com.chaos.entities.User;
import com.chaos.interfacesRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by marwen on 21/12/15.
 */

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
