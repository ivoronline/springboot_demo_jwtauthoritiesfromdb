package com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

  //USED TO CREATE & DECODE JWT
  public final static String SECRET_KEY = "mysecretkey";

  //========================================================================
  // CREATE JWT
  //========================================================================
  public String createJWT(String username, String role) {

    //HEADER (SPECIFY ALGORITHM)
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    //PAYLOAD (SPECIFY CLAIMS)
    Map<String, Object> customClaims = new HashMap<>();
                        customClaims.put("username"   , username);
                        customClaims.put("authorities", role);
    JwtBuilder builder = Jwts.builder().setClaims(customClaims);

    //SIGNATURE (SPECIFY SECRET KEY)
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
    Key    signingKey        = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    //EXTRACT JWT
    String jwt = builder.signWith(signatureAlgorithm, signingKey).compact();

    //RETURN JWT
    return jwt;

  }

  //========================================================================
  // EXTRACT JWT FROM AUTHORIZATION HEADER
  //========================================================================
  public String extractJWTFromAuthorizationHeader(String authorization) {

    //GET AUTHORIZATION HEADER
    if (authorization == null || !authorization.startsWith("Bearer ")) {
      System.out.println("Authorization Header not found");
      return null;
    }

    //EXTRACT JWT
    String jwt = authorization.substring(7);

    //RETURN JWT
    return jwt;

  }

  //========================================================================
  // GET CLAIMS
  //========================================================================
  public Claims getClaims(String jwt) {

    //GET CLAIMS
    Claims claims = Jwts.parser()
      .setSigningKey(DatatypeConverter
      .parseBase64Binary(SECRET_KEY))
      .parseClaimsJws(jwt)
      .getBody();

    //RETURN CLAIMS
    return claims;

  }

  //========================================================================
  // GET USERNAME
  //========================================================================
  public String getUsername(String jwt) {
    Claims claims   = getClaims(jwt);
    String username = (String) claims.get("username");
    return username;
  }

}
