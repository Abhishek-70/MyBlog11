package com.myblog11.myblog11.Config;

import com.myblog11.myblog11.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private CustomUserDetailsService userDetailsService;


    // Define a UserDetailsService (you can replace this with your custom implementation)
    //This method is used to perform the InMemory Authentication by saving the Object Details into the Object.(UserDetailsService)
    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    // Configure authentication with BCrypt password encoder
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
 //               .antMatchers("/api/**").permitAll() // Permit access to /public/**
//                .antMatchers(HttpMethod.POST,"/api/post").hasRole("ADMIN") // Require ADMIN role for /api/**
//                .antMatchers("/api/comment/**").hasRole("USER") // Require USER role for /user/**
//                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/blog/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/blog/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/auth/signUp").permitAll()
                .antMatchers(HttpMethod.POST,"/api/auth/signIn").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

        @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    // Use BCryptPasswordEncoder for password hashing
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
