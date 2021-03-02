package com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.repositories;

import com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> { }
