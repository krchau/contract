package ua.kh.profit.baklanov.content.dao;


import ua.kh.profit.baklanov.content.service.Contract;

public interface IDao<T> {

    Contract create(T entity);

    T read(long id);

    void update(T entity);

    void delete(long id);

}