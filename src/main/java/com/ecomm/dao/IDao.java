package com.ecomm.dao;

import java.util.List;

public interface IDao<T> {
    boolean add(T t);
    T get(int id); // For simplicity, assuming an integer ID. Adjust as needed.
    boolean update(T t);
    boolean delete(int id);
    List<T> findAll();
}
