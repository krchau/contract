package ua.kh.profit.baklanov.content.validate;

import java.time.LocalDate;
import java.util.Map;

 public interface IValidate<T> {

    Map<String, String> validate(T entity);

    void validatePrice(int price);
}