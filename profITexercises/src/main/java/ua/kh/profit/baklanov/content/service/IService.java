package ua.kh.profit.baklanov.content.service;

import ua.kh.profit.baklanov.content.dao.IDao;

public interface IService<T> {
    IDao<T> getDao();

    default void create(T entity) {
        getDao().create(entity);
    }

    default T read(long id) {
        return getDao().read(id);
    }

    default void update(T entity) { getDao().update(entity); }

    default void delete(long id) {
        getDao().delete(id);
    }

}
