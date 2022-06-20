package com.daniel.blacksmith.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.daniel.blacksmith.security.CustomerUserDetailsService;
import com.daniel.blacksmith.security.JwtAuthenticationEntryPoint;
import com.daniel.blacksmith.security.JwtAuthenticationFilter;
import com.daniel.blacksmith.security.JwtTokenProvider;

@Configuration //빈등록
@EnableWebSecurity //SpringSecurity활성화
@EnableGlobalMethodSecurity(prePostEnabled = true) //Controller에서 특정페이지에 특정권한이 있는 유저만 접근허용할 경우, @PreAuthorize어노테이션 사용. 이를 활성화시키는 것HTTP 메소드를 지정해서 권한제어(~Matchers)하기 위함
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomerUserDetailsService customerUserDetailsService;
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    public SecurityConfig(CustomerUserDetailsService customerUserDetailsService,
                          JwtAuthenticationEntryPoint authenticationEntryPoint){
        this.customerUserDetailsService = customerUserDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public JwtAuthenticationFilter  jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //WebSecurity: FilterChainProxy를 생성하는 필터, 다양한 filter설정가능. - web.ignoring().antMatchers("/css/**", "/js/**", "/lib/**);
    //HttpSecurity: HTTP요청에 대한 보안 설정
    //
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //basic authentication: 페이지가 아니라, 팝업으로 뜬다
        //postman에선 basic auth로 접근
        http
            .csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/**").permitAll() // allUser에게 허용
            .antMatchers("/api/auth/**").permitAll()
            .anyRequest()
            .authenticated();
            //basic authentication
            // .and()
            // .httpBasic();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailsService)
            .passwordEncoder(passwordEncoder());
    }




    //in-memory user
    // @Override
    // @Bean
    // protected UserDetailsService userDetailsService() {
    //     UserDetails dongsoo = User.builder().username("dongsoo")
    //         .password(passwordEncoder().encode("pw")).roles("USER").build();
    //     UserDetails admin = User.builder().username("admin")
    //         .password(passwordEncoder().encode("admin")).roles("ADMIN").build();
    //     //encoded format of pw
    //     return new InMemoryUserDetailsManager(dongsoo, admin);
    // }
}
