package com.youcode.reservation.config;

import com.youcode.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email");
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.logout().permitAll();
        http.authorizeRequests().anyRequest().authenticated();


//        http.formLogin()
//                .defaultSuccessUrl("/", true);
//        http.authorizeRequests()
//                .anyRequest().permitAll();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}



