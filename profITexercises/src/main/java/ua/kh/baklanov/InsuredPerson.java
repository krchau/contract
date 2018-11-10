package ua.kh.baklanov;

import java.time.LocalDate;

public class InsuredPerson {
    private String firstName;
    private String lastName;
    private LocalDate dateofBirth;
    private int price;

    public InsuredPerson(String firstName, String lastName, int price) {
        this.firstName=firstName;
        this.lastName=lastName;
        if (price<0) {
            throw new IllegalArgumentException("Price can't be negative!");
        }
        else {
            this.price = price;
        }
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
}
