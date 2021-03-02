package com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.startup;

import com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.entities.Account;
import com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Order(3)
public class AccountLoader implements CommandLineRunner {

  @Autowired private AccountRepository accountRepository;

  @Override
  @Transactional
  public void run(String... args) throws Exception {

    //ADMIN: ANDREW
    Account admin          = new Account();
            admin.username = "admin";
            admin.password = "adminpassword";
            admin.profile  = "ADMIN";

    //USER: URBAN
    Account user          = new Account();
            user.username = "user";
            user.password = "userpassword";
            user.profile  = "USER";

    //STORE ACCOUNT INTO DB
    accountRepository.save(admin);
    accountRepository.save(user);

  }

}
