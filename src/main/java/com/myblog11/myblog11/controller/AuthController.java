package com.myblog11.myblog11.controller;

import com.myblog11.myblog11.entity.User;
import com.myblog11.myblog11.payload.SignUpDto;
import com.myblog11.myblog11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    //passwordEncoder is used to access the set the password in encrypted manner.
    //it returns the object of new BCryptPasswordEncoder();

    @Autowired
    private PasswordEncoder passwordEncoder;

    //1.created signUp Page here to sign-Up the User
    //url->http://localhost:8080/api/auth/signUp, set requesst-> POST
    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        if(userRepository.existsByUserName(signUpDto.getUserName())){
            return new ResponseEntity<>("User is already Exists", HttpStatus.BAD_REQUEST);
        }if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is Already Exist",HttpStatus.BAD_REQUEST);
        }
        User user =new User();
        user.setUserName(signUpDto.getUserName());
        user.setEmail(signUpDto.getEmail());
        user.setName(signUpDto.getName());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        userRepository.save(user);

        return new  ResponseEntity<>("User Registered Successfully",HttpStatus.CREATED);
    }
}
