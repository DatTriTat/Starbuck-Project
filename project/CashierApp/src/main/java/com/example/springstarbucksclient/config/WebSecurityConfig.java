package com.example.springstarbucksclient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/images/**", "/css/**", "**/*.css", "/styles.css", "/*.css");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { 
        http
                .csrf().disable()  .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/signin/**", "/register/**", "/signin", "/logout", "/admin/**").permitAll() // (3)
                .anyRequest().authenticated() 
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .httpBasic(); 
    }
}
