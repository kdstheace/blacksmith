// package com.daniel.blacksmith.config;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
// import com.daniel.blacksmith.security.CustomerUserDetailsService;
// import com.daniel.blacksmith.security.JwtAuthenticationEntryPoint;
// import com.daniel.blacksmith.security.JwtAuthenticationFilter;
//
// @Configuration //빈등록
// @EnableWebSecurity //SpringSecurity활성화
// @EnableGlobalMethodSecurity(prePostEnabled = true) //Controller에서 특정페이지에 특정권한이 있는 유저만 접근허용할 경우, @PreAuthorize어노테이션 사용. 이를 활성화시키는 것HTTP 메소드를 지정해서 권한제어(~Matchers)하기 위함
// public class SecurityConfigDEPRECATED extends WebSecurityConfigurerAdapter {
//
//     private CustomerUserDetailsService customerUserDetailsService;
//     private JwtAuthenticationEntryPoint authenticationEntryPoint;
//
//     public SecurityConfigDEPRECATED(CustomerUserDetailsService customerUserDetailsService,
//                                     JwtAuthenticationEntryPoint authenticationEntryPoint){
//         this.customerUserDetailsService = customerUserDetailsService;
//         this.authenticationEntryPoint = authenticationEntryPoint;
//     }
//
//     @Bean
//     public JwtAuthenticationFilter  jwtAuthenticationFilter(){
//         return new JwtAuthenticationFilter();
//     }
//
//     @Bean
//     PasswordEncoder passwordEncoder(){
//         return new BCryptPasswordEncoder();
//     }
//
//     @Override
//     @Bean
//     public AuthenticationManager authenticationManagerBean() throws Exception {
//         return super.authenticationManagerBean();
//     }
//
//     //WebSecurity: FilterChainProxy를 생성하는 필터, 다양한 filter설정가능. - web.ignoring().antMatchers("/css/**", "/js/**", "/lib/**);
//     //HttpSecurity: HTTP요청에 대한 보안 설정
//     //전자는 정적리소스에 대한 보안예외처리를 주로 하고, 후자는 보안처리를 담당한다. 둘다 설정하면 WebSecurity가 HttpSecurity보다 우선적으로 고려됨
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         //basic authentication: 페이지가 아니라, 팝업으로 뜬다
//         //postman에선 basic auth로 접근
//         //authorizeRequests() - HttpServletRequest요청URL에 따라 접근권한 설정
//         //antMatchers("/paths") - 엔드포인트 경로를 지정한다
//         //authenticated() - 인증된 유저만 접근허용
//         //permitAll() - 모든 유저에게 접근 허용
//         //anonymous() - 인증되지 않는 유저만 허용
//         //denyAll() - 모든 유저 접근 불가
//         http
//             .csrf().disable() //1
//             .exceptionHandling() //2
//             .authenticationEntryPoint(authenticationEntryPoint)
//             .and()
//             .sessionManagement() //3
//             .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //4
//             .and()
//             .authorizeRequests() //5
//             .antMatchers(HttpMethod.GET, "/api/**").permitAll() // allUser에게 허용
//             .antMatchers("/api/auth/**").permitAll()
//             .antMatchers("/api/admin/**").hasRole("ADMIN") //ADMIN에게만 제한
//             .anyRequest()
//             .authenticated(); //바로 위와 해당 줄 주석처리하면, 인증이 되지 않은 접근도 permitAll()인 이상 접근가능
//             //basic authentication
//             // .and()
//             // .httpBasic();
//         http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); //6
//     }
//
//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(customerUserDetailsService)
//             .passwordEncoder(passwordEncoder());
//     }
//
//
//
//
//     //in-memory user
//     // @Override
//     // @Bean
//     // protected UserDetailsService userDetailsService() {
//     //     UserDetails dongsoo = User.builder().username("dongsoo")
//     //         .password(passwordEncoder().encode("pw")).roles("USER").build();
//     //     UserDetails admin = User.builder().username("admin")
//     //         .password(passwordEncoder().encode("admin")).roles("ADMIN").build();
//     //     //encoded format of pw
//     //     return new InMemoryUserDetailsManager(dongsoo, admin);
//     // }
// }
