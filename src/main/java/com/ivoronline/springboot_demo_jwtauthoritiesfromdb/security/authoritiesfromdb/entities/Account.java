package com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;
  public String  username;
  public String  password;

  //FOREIGN KEY
  public String profile;

}
