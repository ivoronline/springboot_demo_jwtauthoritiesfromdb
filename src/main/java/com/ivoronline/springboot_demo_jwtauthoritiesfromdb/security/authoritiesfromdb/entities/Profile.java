package com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Profile {

  @Id
  public String name;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "profile")
  public Set<Account> account = new HashSet<>();

  @ManyToMany(cascade = CascadeType.ALL)
  public Set<Authority> authorities = new HashSet<>();

}
