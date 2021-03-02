package com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.repositories;

import com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
  Account findByUsername(String Username);
}
