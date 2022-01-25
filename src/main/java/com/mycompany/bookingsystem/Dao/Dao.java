package com.mycompany.bookingsystem.Dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    public int add(T t)
            throws SQLException;
    public void delete(int id)
            throws SQLException;
    public T get(int id)
            throws SQLException;
    public List<T> getAll()
            throws SQLException;
    public List<T> select(String query)
            throws SQLException;
    public void update(T t)
            throws SQLException;
}
