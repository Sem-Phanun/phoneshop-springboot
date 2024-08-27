package com.project.phone_shop.config.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
   Optional<UserDetails>  getLoadUserByUsername(String username);
}
