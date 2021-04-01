package com.youcode.reservation.config;

import com.youcode.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/", true)
                .permitAll();

        http
                .authorizeRequests()
                .mvcMatchers("/","/admin", "/api/**")
                .permitAll();
        http
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();
//        static ressource
        http
                .authorizeRequests()
                .mvcMatchers("/css/**", "/images/**")
                .permitAll();

        http.csrf().ignoringAntMatchers("/api/**");

        http.cors()
                .and()
                .authorizeRequests()
                .anyRequest()
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}



