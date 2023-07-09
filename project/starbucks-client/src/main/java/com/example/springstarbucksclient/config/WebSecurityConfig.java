package com.example.springstarbucksclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.headers().frameOptions().sameOrigin();
                http.headers().defaultsDisabled(); // do not use any default headers unless explicitly listed
                http.headers().disable(); // disable headers security
                SecurityFilterChain ret = http
                                .csrf().disable()
                                .formLogin(
                                                form -> form
                                                                .loginPage("/login")
                                                                .permitAll())
                                .authorizeRequests()
                                .antMatchers("/").hasRole("USER")
                                .and()
                                .build();
                return ret;
        }

}