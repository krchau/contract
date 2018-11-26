package ua.kh.profit.baklanov.content.service;

import ua.kh.profit.baklanov.content.validate.IValidate;

import java.time.LocalDate;
import java.util.Map;
import java.util.logging.Logger;

public class InsuredPerson implements IValidate{
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate dateofBirth;
    private int price;
    private int id;
    private static Logger log = Logger.getLogger(InsuredPerson.class.getName());


    public InsuredPerson(String firstName, String lastName, String patronymic, LocalDate dateofBirth, int price, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.dateofBirth=dateofBirth;
        this.id = id;
        validatePrice(price);
    }

    public String initials() {
        StringBuilder sb = new StringBuilder();
        return sb.append(lastName).append(" ").append(firstName.charAt(0)).append(". ").append(patronymic.charAt(0)).append(".").toString();
    }

    @Override
    public Map<String, String> validate(Object entity) { return null; }

    @Override
    public void validatePrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be negative!");
        } else {
            this.price = price;
        }
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
