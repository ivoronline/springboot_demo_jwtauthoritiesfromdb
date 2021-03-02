package com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JWTController {

  @Autowired JWTUtil                 jwtUtil;
  @Autowired MyAuthenticationManager myAuthenticationManager;

  //==================================================================
  // GET JWT
  //==================================================================
  @ResponseBody
  @RequestMapping("/GetJWT")
  public String getJWT(@RequestParam String enteredUsername, @RequestParam String enteredPassword) {

    //LOG
    System.out.println("GetJWT ======================================================================= ");

    //AUTHENTICATE
    Authentication enteredAuth  = new UsernamePasswordAuthenticationToken(enteredUsername, enteredPassword);
    Authentication returnedAuth = myAuthenticationManager.authenticate(enteredAuth);

    System.out.println("returnedAuth = " + returnedAuth);

    //CHECK AUTHENTICATION
    if(returnedAuth == null) { return "User is NOT Authenticated"; }

    //CREATE JWT
    String username    = (String) returnedAuth.getPrincipal();
    String authorities = returnedAuth.getAuthorities().toString();

    System.out.println("authorities = " + authorities);

    String jwt = jwtUtil.createJWT(username, authorities);

    //RETURN JWT
    return jwt;

  }

}