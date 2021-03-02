package com.ivoronline.springboot_demo_jwtauthoritiesfromdb.security.authoritiesfromdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {

    //ALLOW ACCES TO H2 CONSOLE
    httpSecurity.authorizeRequests(authorize -> { authorize.antMatchers("/h2-console/**").permitAll(); });
    httpSecurity.headers().frameOptions().sameOrigin();
    httpSecurity.csrf().disable();

    //LOCK EVERYTHING ELSE (BEHIND LOG IN)
    httpSecurity.formLogin();

    //DISABLE CSRF
    httpSecurity.csrf().disable();

  }

}

