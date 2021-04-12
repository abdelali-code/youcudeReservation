package com.youcode.reservation.config;

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
                .mvcMatchers(("/singup"))
                .anonymous();
        http
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();
//        static ressource
        http
                .authorizeRequests()
                .mvcMatchers("/css/**", "/images/**")
                .permitAll();

//        http
//                .authorizeRequests()
//                .mvcMatchers("/api/**")
//                .hasAuthority("admin");

//        http
//                .authorizeRequests()
//                .mvcMatchers("/admin-dashboard")
//                .hasAuthority("admin");
        http
//                .cors()
//                .and()
                .authorizeRequests()
                .mvcMatchers("/admin/**", "/api/**", "/admin-dashboard/**")
                .hasAuthority("admin");

        http
                .authorizeRequests()
                .mvcMatchers( "password/reset")
                .permitAll();

//        http
//                .cors()
//                .disable()
//                .authorizeRequests()
//                .anyRequest()
//                .permitAll();

//
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated();

        //http.csrf().ignoringAntMatchers("/api/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}



