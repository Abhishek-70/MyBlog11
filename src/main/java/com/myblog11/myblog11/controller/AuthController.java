package com.myblog11.myblog11.controller;

import com.myblog11.myblog11.entity.Role;
import com.myblog11.myblog11.entity.User;
import com.myblog11.myblog11.payload.LogInDto;
import com.myblog11.myblog11.payload.SignUpDto;
import com.myblog11.myblog11.repository.RoleRepository;
import com.myblog11.myblog11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    //passwordEncoder is used to access the set the password in encrypted manner.
    //it returns the object of new BCryptPasswordEncoder();

    @Autowired
    private PasswordEncoder passwordEncoder;

    //using the User Role in the api to which he got to perform in the application
    @Autowired
    private RoleRepository roleRepository;

    //2.Here we created the feature of signIn to understand the concept of Authentication.
    @PostMapping("/signIn")
    public ResponseEntity<?> authentication(@RequestBody LogInDto logInDto){
        new UsernamePasswordAuthenticationToken(logInDto.getUserNameOrEmail(),logInDto.getPassword());
        //this will supply userName to the CustomUserService Layer-> in loadByUserName(String userName).


    }

    //1.created signUp Page here to sign-Up the User
    //url->http://localhost:8080/api/auth/signUp, set requesst-> POST
    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        if (userRepository.existsByUserName(signUpDto.getUserName())) {
            return new ResponseEntity<>("User is already Exists", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is Already Exist", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUserName(signUpDto.getUserName());
        user.setEmail(signUpDto.getEmail());
        user.setName(signUpDto.getName());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

//Here we came after the concept of Role class is defined as we came here to set the User Role,
        //using which we have Use the object of roleRepository
        Role roles = roleRepository.findByName(user.getRoleType()).get();

        //her the return type is SET<> then we must have to convert it into set<>

        Set<Role> convertToSet = new HashSet<>();
        convertToSet.add(roles);
        user.setRoles(convertToSet);
        //or using the (Collection.singleton(roles)); we can set our userRole Founded.
        //user.setRoles(Collection.singleton(roles));

        userRepository.save(user);
        return new ResponseEntity<>("User Registered Successfully", HttpStatus.CREATED);


    }
}
