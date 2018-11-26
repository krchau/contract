package ua.kh.profit.baklanov.content.dao;

import ua.kh.profit.baklanov.content.service.Contract;

public interface IDao<T> {

    void create(T entity);

    T read(long id);

    void update(T entity);

    void delete(long id);

    T createEntityThoughtArrayOfStringsWithData(T entity, String[] contractVars);

    void updateEntityThoughtArrayOfStringsWithData(Contract entity, String[] contractVars);
}