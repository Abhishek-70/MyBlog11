package com.myblog11.myblog11.security;

import com.myblog11.myblog11.entity.Role;
import com.myblog11.myblog11.entity.User;
import com.myblog11.myblog11.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //This method is overloaded from the spring security import,
    //this method Automatically takes the userName From the authentication(), From the UserNamePasswordAuthenticationClass is give the
    //UserName value to this Method() the userName containt.
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with This userName" + usernameOrEmail));

        //Here we Used the different User Inbuilt class from the springSecurity package in specific convention
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRoleToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRoleToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
