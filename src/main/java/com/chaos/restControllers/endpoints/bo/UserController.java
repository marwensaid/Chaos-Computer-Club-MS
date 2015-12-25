package com.chaos.restControllers.endpoints.bo;

import com.chaos.restControllers.model.AuthenticatedUser;
import com.chaos.security.SecurityUser;
import com.chaos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * Created by marwen on 21/12/15.
 */

@RestController
@RequestMapping(value = "/api/users/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authenticatedUser", method = RequestMethod.GET)
    public ResponseEntity<AuthenticatedUser> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object userDetails = authentication.getPrincipal();
            if (userDetails != null && userDetails instanceof SecurityUser) {
                SecurityUser securityUser = (SecurityUser) userDetails;
                String username = securityUser.getUsername();
                List<String> roles = Collections.singletonList(securityUser.getDomainUser().getRole());
                AuthenticatedUser authenticatedUser = new AuthenticatedUser(username, roles);
                return new ResponseEntity<AuthenticatedUser>(authenticatedUser, HttpStatus.OK);
            }
        }
        return new ResponseEntity<AuthenticatedUser>(HttpStatus.UNAUTHORIZED);

    }
}
