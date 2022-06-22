package com.daniel.blacksmith.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.daniel.blacksmith.entity.Role;
import com.daniel.blacksmith.entity.User;
import com.daniel.blacksmith.payload.JWTAuthResponse;
import com.daniel.blacksmith.payload.LoginDto;
import com.daniel.blacksmith.payload.SignUpDto;
import com.daniel.blacksmith.repository.RoleRepository;
import com.daniel.blacksmith.repository.UserRepository;
import com.daniel.blacksmith.security.JwtTokenProvider;

import java.util.Collections;

@Api(value="Auth controller exposes signin and signup RESTAPI")
@RestController
@RequestMapping("/api")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwtTokenProvider
    ){
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @ApiOperation(value = "REST API to signIn user")
    @PostMapping("/v1/auth/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken upatoken = new UsernamePasswordAuthenticationToken(
            loginDto.getUsernameOrEmail(),
            loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(upatoken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //get token from JwtTokenProvider class
        String token = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));
        // return new ResponseEntity<>("User signed-in successfully!", HttpStatus.OK);
    }

    @ApiOperation(value = "REST API to register user")
    @PostMapping("/v1/auth/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        //Check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        //Check for email exists in a DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already exist!", HttpStatus.BAD_REQUEST);
        }
        //Create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("ADMIN").get();
        user.setRoles(Collections.singleton(roles)); //원소가 1개밖에 없는데 set에 넣어야 하는 경우 사용한다.

        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

}
