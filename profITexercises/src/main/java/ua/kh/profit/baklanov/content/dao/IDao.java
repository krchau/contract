package ua.kh.profit.baklanov.content.dao;

import ua.kh.profit.baklanov.content.service.Contract;

import javax.sql.DataSource;

public interface IDao<T> {
    void setDataSource(DataSource dataSource);

    Contract create(T entity);

    T read(long id);

    void update(T entity);

    void delete(long id);
}
