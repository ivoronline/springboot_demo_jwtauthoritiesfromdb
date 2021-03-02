package com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MyFilter implements Filter {

  @Autowired private JWTUtil jwtUtil;

  //==================================================================================
  // DO FILTER
  //==================================================================================
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
    throws IOException, ServletException {

    //LOG
    System.out.println("doFilter ======================================================================= ");

    //CAST REQUEST TO GET ACCESS TO HEADERS
    HttpServletRequest httpRequest = (HttpServletRequest) request;

    //GET AUTHORIZATION HEADER
    String authorizationHeader = httpRequest.getHeader("Authorization");

    //IF AUTHORIZATION HEADER PRESENT USE JWT TO PUT AUTHENTICATION OBJECT INTO CONTEXT
    if(authorizationHeader != null) { addAuthenticationObjectIntoContext(authorizationHeader); }

    //FORWARD REQUEST
    filterchain.doFilter(request, response);

  }

  //==================================================================================
  // ADD AUTHENTICATION OBJECT INTO CONTEXT
  //==================================================================================
  private void addAuthenticationObjectIntoContext(String authorizationHeader) {

    //EXTRACT JWT FROM AUTHORIZATION HEADER
    String jwt = jwtUtil.extractJWTFromAuthorizationHeader(authorizationHeader);

    //GET CLAIMS
    Claims claims         = jwtUtil.getClaims(jwt);
    String username       = (String) claims.get("username");
    String authoritiesJWT = (String) claims.get("authorities"); //"[book.read, book.delete]"

    //CREATE AUTHORITIES
    String   authoritiesString = authoritiesJWT.replace("[","").replace("]","").replace(" ",""); //"book.read,book.delete"
    String[] authoritiesArray  = authoritiesString.split(",");
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for(String authority : authoritiesArray) {
      authorities.add(new SimpleGrantedAuthority(authority));
    }

    //AUTHENTICATE
    Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

    System.out.println("authentication = " + authentication);

    //STORE AUTHENTICATION INTO CONTEXT (SESSION)
    SecurityContextHolder.getContext().setAuthentication(authentication);

  }

}
