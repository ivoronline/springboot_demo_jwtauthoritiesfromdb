package com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.repositories;

import com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> { }
