package ua.kh.profit.baklanov;

import java.time.LocalDate;

public class InsuredPerson {
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate dateofBirth;
    private int price;
    private int id;



    public InsuredPerson(String firstName, String lastName,String patronymic , LocalDate dateofBirth, int price, int id) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.patronymic=patronymic;
        this.dateofBirth=dateofBirth;
        this.id=id;
        if (price<0) {
            throw new IllegalArgumentException("Price can't be negative!");
        }
        else {
            this.price = price;
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public LocalDate getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(LocalDate dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String initials()
    {
        StringBuffer sb = new StringBuffer();
        return sb.append(lastName).append(" ").append(firstName.charAt(0)).append(". ").append(patronymic.charAt(0)).append(".").toString();
    }
}
