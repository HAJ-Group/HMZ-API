package com.hmz.dao.repository;

import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MemoryRepository<T> implements Repository<T> {

    Map<Integer, T> data;

    public MemoryRepository(Map<Integer, T> data) {
        this.data = data;
    }

    @Override
    public List<T> findAll() {
        return new Vector<>(data.values());
    }

    @Override
    public T findById(int id) {
        return data.get(id);
    }

    @Override
    public T save(T data) {
        System.err.println("Operation is not implemented ! Use JDBC instead");
        return data;
    }

    @Override
    public void deleteAll() {
        System.err.println("Operation is not implemented ! Use JDBC instead");
    }

    @Override
    public void deleteById(int id) {
        System.err.println("Operation is not implemented ! Use JDBC instead");
    }

    @Override
    public String getError_message() {
        System.err.println("Operation is not implemented ! Use JDBC instead");
        return null;
    }
}
