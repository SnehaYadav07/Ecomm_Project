package com.ecomm.dao;

import java.util.List;

public interface IDao<T> {
    void add(T item) throws Exception;
    void update(T item) throws Exception;
    void delete(T item) throws Exception;
    T findOne(int id) throws Exception;
    List<T> findAll() throws Exception;
}
