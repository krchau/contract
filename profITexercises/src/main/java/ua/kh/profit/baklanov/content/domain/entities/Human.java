package ua.kh.profit.baklanov.content.domain.entities;

public class Human {
    private String fullName;
    private String address;

    public Human(String fullName, String adress) {
        this.fullName = fullName;
        this.address = adress;
    }

    public Human(Object person) {
        this.address=((Human) person).getAddress();
        this.fullName=((Human) person).getFullName();
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
