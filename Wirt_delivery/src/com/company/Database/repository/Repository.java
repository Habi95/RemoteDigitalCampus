package com.company.Database.repository;

import java.util.ArrayList;

public interface Repository<T> {
    public ArrayList<T> findAll();
    public T findOne(int id);
    public void create(T entity);
}
