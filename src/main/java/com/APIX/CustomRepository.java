package com.APIX;

import java.util.List;

public interface CustomRepository<T, ID> {
    T  getById(ID id);

    boolean save(T entity);

    List<T> getAll();

    boolean update(T updatedEntity);

    boolean delete(ID id);
}