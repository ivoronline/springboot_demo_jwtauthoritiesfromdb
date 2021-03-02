package com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.startup;

import com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.entities.Authority;
import com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.entities.Profile;
import com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.repositories.AuthorityRepository;
import com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Order(2)
public class ProfileLoader implements CommandLineRunner {

  @Autowired
  private ProfileRepository profileRepository;

  @Autowired
  private AuthorityRepository authorityRepository;

  @Override
  @Transactional
  public void run(String... args) throws Exception {

    //GET AUTHORITIES.
    Authority bookCreate = authorityRepository.findById("book.create").get();
    Authority bookRead   = authorityRepository.findById("book.read"  ).get();
    Authority bookUpdate = authorityRepository.findById("book.update").get();
    Authority bookDelete = authorityRepository.findById("book.delete").get();

    //USER
    Profile user      = new Profile();
            user.name = "USER";
            user.authorities.add(bookRead);

    //ADMIN
    Profile admin      = new Profile();
            admin.name = "ADMIN";
            admin.authorities.add(bookCreate);
            admin.authorities.add(bookRead);
            admin.authorities.add(bookUpdate);
            admin.authorities.add(bookDelete);

    //STORE PROFILES INTO DB
    profileRepository.save(user);
    profileRepository.save(admin);

  }

}
