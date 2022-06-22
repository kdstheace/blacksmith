package com.daniel.blacksmith;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.daniel.blacksmith.entity.Role;
import com.daniel.blacksmith.repository.RoleRepository;

@SpringBootApplication
public class BlacksmithApp implements CommandLineRunner {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("USER");
        roleRepository.save(userRole);
    }

    public static void main(String ... args){
        SpringApplication.run(BlacksmithApp.class, args);
    }


}
