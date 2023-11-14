package com.example.play2game.Restcrud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}john")
                .roles("EMPLOYEE").build();

        UserDetails marry = User.builder()
                .username("marry")
                .password("{noop}marry")
                .roles("EMPLOYEE","MANAGER").build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}susan")
                .roles("EMPLOYEE","MANAGER","ADMIN").build();

        return new InMemoryUserDetailsManager(john, marry, susan);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configure->
                configure.requestMatchers(HttpMethod.GET, "api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "api/employees/**").hasRole("ADMIN"));


        // use HTTP basic authentication
        httpSecurity.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use PST, PUT DELETE and/or PATCH
        httpSecurity.csrf(csrf->csrf.disable());
        return httpSecurity.build();
    }
}
