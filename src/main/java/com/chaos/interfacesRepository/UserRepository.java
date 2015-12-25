package com.chaos.interfacesRepository;

import com.chaos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by marwen on 23/12/15.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);

}
