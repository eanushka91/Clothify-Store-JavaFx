package edu.icet.repository;


import javafx.collections.ObservableList;

public interface CrudDao<T> extends SuperDao {
    boolean save(T t);
    ObservableList<T> getAll();
    boolean delete(String id);
    T search(String id);
    boolean update(T t);
}