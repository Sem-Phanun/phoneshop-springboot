package com.project.phone_shop.config.security;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceFakeImpl implements UserService {

    private final List<AuthUser> authUser = List.of();
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserDetails> getLoadUserByUsername(String username) {

        AuthUser authUser1 = new AuthUser("Goji", passwordEncoder.encode("goji123"), RoleEnum.SALE.getSimpleGrantedAuthority(), )
        return Optional.empty();
    }
}
