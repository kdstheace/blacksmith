package com.daniel.blacksmith.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.daniel.blacksmith.security.CustomerUserDetailsService;
import com.daniel.blacksmith.security.JwtAuthenticationEntryPoint;
import com.daniel.blacksmith.security.JwtAuthenticationFilter;
import com.daniel.blacksmith.security.JwtTokenProvider;

@Configuration //빈등록
@EnableWebSecurity //SpringSecurity활성화
@EnableGlobalMethodSecurity(prePostEnabled = true) //Controller에서 특정페이지에 특정권한이 있는 유저만 접근허용할 경우, @PreAuthorize어노테이션 사용. 이를 활성화시키는 것HTTP 메소드를 지정해서 권한제어(~Matchers)하기 위함
public class SecurityConfig{

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

    //WebSecurity: FilterChainProxy를 생성하는 필터, 다양한 filter설정가능. - web.ignoring().antMatchers("/css/**", "/js/**", "/lib/**);
    //HttpSecurity: HTTP요청에 대한 보안 설정
    //전자는 정적리소스에 대한 보안예외처리를 주로 하고, 후자는 보안처리를 담당한다. 둘다 설정하면 WebSecurity가 HttpSecurity보다 우선적으로 고려됨
    //basic authentication: 페이지가 아니라, 팝업으로 뜬다
    //postman에선 basic auth로 접근
    //authorizeRequests() - HttpServletRequest요청URL에 따라 접근권한 설정
    //antMatchers("/paths") - 엔드포인트 경로를 지정한다
    //authenticated() - 인증된 유저만 접근허용
    //permitAll() - 모든 유저에게 접근 허용
    //anonymous() - 인증되지 않는 유저만 허용
    //denyAll() - 모든 유저 접근 불가
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests((authorize) -> authorize
                .antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/v2/api-docs/**").permitAll() //http://localhost:8080/v2/api-docs
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest()
                .authenticated()
            );
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }





}
