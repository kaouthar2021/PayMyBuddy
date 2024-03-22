package com.project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

       http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/login/oauth2/**","/register")
                        .permitAll()
                        .requestMatchers("/addContact","/contact", "/transfer","/profile","/transferToMyBankAccount")
                        .authenticated())

                .formLogin((form) -> form
                        .loginPage("/login")

                        .permitAll())
               .logout((logout) ->logout
                      .permitAll())
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login/oauth2"));



        return http.build();

    }
    }






