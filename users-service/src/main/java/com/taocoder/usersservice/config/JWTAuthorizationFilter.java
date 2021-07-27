/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taocoder.usersservice.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.taocoder.usersservice.Constants;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 *
 * @author user
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        String header = request.getHeader(Constants.HEADER_STRING);
        if (header == null || !header.startsWith(Constants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        
        UsernamePasswordAuthenticationToken authenticationToken = getToken(request);
        
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    
    private UsernamePasswordAuthenticationToken getToken(HttpServletRequest request) {
        
        String token = request.getHeader(Constants.HEADER_STRING);
        
        if (token != null) {
            
            String user = JWT.require(Algorithm.HMAC512(Constants.SECRET.getBytes())).build().verify(token.replace(Constants.TOKEN_PREFIX, "")).getSubject();
        
            if (user != null) 
                return new UsernamePasswordAuthenticationToken(user, null);
        }

           
        return null;
    }
}
