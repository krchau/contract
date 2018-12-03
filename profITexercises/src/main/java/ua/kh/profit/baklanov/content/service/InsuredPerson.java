package ua.kh.profit.baklanov.content.service;

import ua.kh.profit.baklanov.content.validate.IValidate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class InsuredPerson implements IValidate{
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate dateofBirth;
    private int price;
    private int id;


    public InsuredPerson(String firstName, String lastName, String patronymic, LocalDate dateofBirth, int price, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.dateofBirth=dateofBirth;
        this.id = id;
        this.price=price;
    }

    public String initials() {
        StringBuilder sb = new StringBuilder();
        return sb.append(lastName).append(" ").append(firstName.charAt(0)).append(". ").append(patronymic.charAt(0)).append(".").toString();
    }

    @Override
    public Map<String, String> validate(Object entity) {
        Map map = new HashMap< String, String>();
        if (((InsuredPerson) entity).getPrice() < 0) {
            map.put("price", "price can't be less then 0");
            return map;
        }
        return null;
    }



    /*GETTERS and SETTERS*/

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) { this.price = price; }

    public LocalDate getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(LocalDate dateofBirth) { this.dateofBirth = dateofBirth; }
}
