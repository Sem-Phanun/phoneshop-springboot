package com.project.phone_shop.config.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
    ADMIN(Set.of(PermissionEnum.BRAND_WRITE,PermissionEnum.BRAND_READ, PermissionEnum.MODEL_WRITE, PermissionEnum.MODEL_READ)),
    SALE(Set.of(PermissionEnum.BRAND_READ,PermissionEnum.MODEL_READ));

    private final Set<PermissionEnum> permissions;

    public Set<SimpleGrantedAuthority> getSimpleGrantedAuthority() {
        Set<SimpleGrantedAuthority> grantedAuthority = this.permissions.stream()
                .map(permissionEnum -> new SimpleGrantedAuthority(permissionEnum.getDescription()))
                .collect(Collectors.toSet());

        SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_" + this.name());
        grantedAuthority.add(role);
        return grantedAuthority;
    }
}
