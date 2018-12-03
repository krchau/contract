package ua.kh.profit.baklanov.content.validate;

import java.util.Map;

 public interface IValidate<T> {

    Map<String, String> validate(T entity);

}