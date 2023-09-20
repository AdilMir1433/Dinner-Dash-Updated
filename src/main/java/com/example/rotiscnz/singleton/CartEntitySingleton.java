package com.example.rotiscnz.singleton;

import com.example.rotiscnz.entities.CartEntity;
public class CartEntitySingleton {

    private static CartEntity instance;

    private CartEntitySingleton() {
    }
    public static CartEntity getInstance() {
        if (instance == null) {
            instance = new CartEntity();
        }
        return instance;
    }
    public static void resetInstance() {
        instance = null;
    }
}