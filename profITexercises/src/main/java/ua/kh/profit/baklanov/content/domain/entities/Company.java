package ua.kh.profit.baklanov.content.domain.entities;

public class Company {
    private String nameOfOrg;
    private String address;

    public Company(String nameOfOrg, String address) {
        this.nameOfOrg = nameOfOrg;
        this.address = address;
    }

    public Company(Object person) {
        this.address=((Company) person).getAddress();
        this.nameOfOrg=((Company) person).getNameOfOrg();
    }

    public String getNameOfOrg() {
        return nameOfOrg;
    }

    public void setNameOfOrg(String nameOfOrg) {
        this.nameOfOrg = nameOfOrg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
