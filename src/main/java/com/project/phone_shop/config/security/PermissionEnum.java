package com.project.phone_shop.config.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PermissionEnum {
    BRAND_WRITE("brand_write"),
    BRAND_READ("brand_read"),
    MODEL_WRITE("model_write"),
    MODEL_READ("model_read"),;


    private String description;
}
