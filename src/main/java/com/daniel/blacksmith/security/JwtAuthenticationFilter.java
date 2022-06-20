package com.daniel.blacksmith.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    //Inject dependencies
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    // public JwtAuthenticationFilter(){}
    //
    // @Autowired
    // public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
    //                                CustomerUserDetailsService customerUserDetailsService
    //                                ){
    //     this.jwtTokenProvider = jwtTokenProvider;
    //     this.customerUserDetailsService = customerUserDetailsService;
    // }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        //get JWT(token) from http request
        String token = getJWTFromToken(httpServletRequest);

        //validate the token
        if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)){
            //get username from the token
            String username = jwtTokenProvider.getUsernameFromJWT(token);

            //load user associated with the token
            UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            //set the user to spring security
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    //Bearer <accessToken>
    private String getJWTFromToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
