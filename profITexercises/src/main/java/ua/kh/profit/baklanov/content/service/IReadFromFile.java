package ua.kh.profit.baklanov.content.service;

public interface IReadFromFile<T>  {

    T createEntityThoughtArrayOfStringsWithData(T entity, String[] contractVars);

    void updateEntityThoughtArrayOfStringsWithData(Contract entity, String[] contractVars);
}
