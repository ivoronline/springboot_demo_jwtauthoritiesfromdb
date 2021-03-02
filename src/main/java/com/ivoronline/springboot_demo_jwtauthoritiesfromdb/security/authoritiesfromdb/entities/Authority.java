package com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Authority {

  @Id
  public String name;

  //CONSTRUCTORS
  public Authority() { }                               //Forced by @Entity
  public Authority(String name) { this.name = name; }  //To simplify AuthorityLoader

}
