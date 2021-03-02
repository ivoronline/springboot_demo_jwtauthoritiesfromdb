package com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.jwt;

import com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationManager implements AuthenticationManager {

  @Autowired MyUserDetailsService myUserDetailsService;

  @Override
  public Authentication authenticate(Authentication enteredAuthentication) {

    //GET ENTERED CREDENTIALS
    String enteredUsername = (String) enteredAuthentication.getPrincipal();   //USERNAME
    String enteredPassword = (String) enteredAuthentication.getCredentials(); //PASSWORD

    //GET USER FROM DB
    UserDetails userDetails = myUserDetailsService.loadUserByUsername(enteredUsername);

    //AUTHENTICATE USER
    if ( userDetails == null                              ) { System.out.println("Username not found"); return null; }
    if (!enteredPassword.equals(userDetails.getPassword())) { System.out.println("Incorrect Password"); return null; }

    //CREATE VALIDATED AUTHENTICATION
    Authentication validatedAuth = new UsernamePasswordAuthenticationToken(enteredUsername, null, userDetails.getAuthorities());
    System.out.println("validatedAuth = " + validatedAuth);

    //RETURN VALIDATED AUTHENTICATION
    return validatedAuth;

  }

}
