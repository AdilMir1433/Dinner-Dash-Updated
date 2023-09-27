package com.example.rotiscnz.utility;

public class Constants {
    public static final String[] AUTH_WHITELIST = {
            "/user/login",
            "/user/signup",
            "/items/get-items",
            "/category/get-categories",
            "items/get-item/**",
            "/cart/**",
            "/cart_item/**",
            "/order/get-order/**",
            "/api-docs",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs",
            "/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/configuration/ui",
            "/configuration/security",
            "/webjars/**",
            "/swagger-ui.html",
    };
    public static final String[] AUTHORITY_LIST = {
            "/items/save",
            "order/get-all-orders",
            "/category/create",
    };
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

}
