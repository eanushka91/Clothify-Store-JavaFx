package edu.icet.repository.custom.impl;

import edu.icet.repository.custom.OrderDao;
import javafx.collections.ObservableList;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(Object o) {
        return false;
    }

    @Override
    public boolean update(Object o) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public ObservableList getAll() {
        return null;
    }

    @Override
    public Object search(String id) {
        return null;
    }


}
