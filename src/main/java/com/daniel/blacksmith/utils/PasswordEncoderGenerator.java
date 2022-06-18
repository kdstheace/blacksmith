package com.daniel.blacksmith.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class PasswordEncoderGenerator {
    public static void main(String... args){
        // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // System.out.println(passwordEncoder.encode("admin"));
        collectionsSingletonUsage();

    }
    public static void collectionsSingletonUsage(){
        String[] myList = {"Geeks", "code", "Practice", "Error",  "Java",
                           "Class", "Error", "Practice", "Java"};
        List arrList = new ArrayList(Arrays.asList(myList));
        arrList.remove("Error"); //[Geeks, code, Practice,  Error, Java, Class, Practice, Java]
        //arrList.removeAll(Collections.singleton("Error")); //[Geeks, code, Practice, Java, Class, Practice, Java]

        System.out.println(arrList);
    }

}
