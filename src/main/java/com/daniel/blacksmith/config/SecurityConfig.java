package com.daniel.blacksmith.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //HTTP 메소드를 지정해서 권한제어(~Matchers)하기 위함
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //basic authentication: 페이지가 아니라, 팝업으로 뜬다
        //postman에선 basic auth로 접근
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/**").permitAll() // allUser에게 허용
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
    }

    //in-memory user

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails dongsoo = User.builder().username("dongsoo")
            .password(passwordEncoder().encode("pw")).roles("USER").build();
        UserDetails admin = User.builder().username("admin")
            .password(passwordEncoder().encode("admin")).roles("ADMIN").build();
        //encoded format of pw
        return new InMemoryUserDetailsManager(dongsoo, admin);
    }
}
