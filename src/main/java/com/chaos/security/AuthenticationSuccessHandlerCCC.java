package com.chaos.security;

import com.chaos.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by marwen on 25/12/15.
 */

@Component
public class AuthenticationSuccessHandlerCCC implements AuthenticationSuccessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationSuccessHandlerCCC.class);
    private ObjectMapper mapper;

    @Autowired
    public AuthenticationSuccessHandlerCCC(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
        User user = userDetails.getDomainUser();

        LOGGER.info(userDetails.getUsername() + " got is connected ");

        PrintWriter writer = httpServletResponse.getWriter();
        mapper.writeValue(writer, user);
        writer.flush();
    }
}
