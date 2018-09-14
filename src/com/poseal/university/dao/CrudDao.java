package com.poseal.university.dao;

import java.io.Serializable;
import java.util.List;

public interface CrudDao<T, PK extends Serializable> {

    List<T> findAll();

    T findOne(PK id);

    T create(T model);

    void remove(PK id);

    void update(T model);
}
