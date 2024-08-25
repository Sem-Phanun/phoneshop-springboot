package com.project.phone_shop.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain chain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((request)-> {
//            request.requestMatchers("/", "index.html", "css/**", "js/**").permitAll()
//                    .anyRequest().authenticated();
//        });
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/", "index.html","css/**", "js/**").permitAll()
//                .requestMatchers(HttpMethod.POST, "/brands").hasAuthority(PermissionEnum.BRAND_WRITE.getDescription())
//                .requestMatchers(HttpMethod.GET, "/brands").hasAuthority(PermissionEnum.BRAND_READ.getDescription())
//                .requestMatchers(HttpMethod.GET, "/models").hasAuthority(PermissionEnum.MODEL_READ.getDescription())
//                .requestMatchers(HttpMethod.POST, "/models").hasAuthority(PermissionEnum.MODEL_WRITE.getDescription())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    protected UserDetailsService userDetailsService() {

        UserDetails user1 = User.builder()
                .username("seller")
                .password(passwordEncoder.encode("seller123"))
                .authorities(RoleEnum.SALE.getSimpleGrantedAuthoritySet())
                .build();

        UserDetails userDetails = User.builder()
                .username("bright")
                .password(passwordEncoder.encode("bro168"))
//                .roles("SALE")
                .authorities(RoleEnum.ADMIN.getSimpleGrantedAuthoritySet())
                .build();
        UserDetailsService userDetailsService = new InMemoryUserDetailsManager(user1 ,userDetails);
        return userDetailsService;
    }
}

