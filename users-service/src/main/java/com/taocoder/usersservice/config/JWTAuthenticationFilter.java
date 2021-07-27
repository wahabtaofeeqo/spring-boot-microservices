/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taocoder.usersservice.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.taocoder.usersservice.Constants.*;
import com.taocoder.usersservice.models.User;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author user
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    @Autowired
    private AuthenticationManager manager;

    public JWTAuthenticationFilter() {
    }
    
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return manager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            
        } catch (IOException | AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        
        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
        
        String body = ((User) auth.getPrincipal()).getEmail()+ " " + token;

        response.getWriter().write(body);
        response.getWriter().flush();
    }
}