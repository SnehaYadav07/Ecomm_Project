package com.ecomm.service;

public interface AuthService<T> {
    T getUserByEmailAndPassword(String email, String password) throws Exception;
    T register(T user) throws Exception;
}
