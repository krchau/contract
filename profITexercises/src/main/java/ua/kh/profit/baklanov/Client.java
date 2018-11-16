package ua.kh.profit.baklanov;


public class Client {

    private boolean isHuman;
    private String fullName;
    private String nameOfOrg;
    private String address;


    public Client(boolean isHuman, String fullNameOrNameOfOrg, String address) {

        if (isHuman == true) {

            this.fullName = fullNameOrNameOfOrg;
        } else {
            this.nameOfOrg = fullNameOrNameOfOrg;
        }
        this.isHuman = isHuman;
        this.address = address;
    }

    public Client() {

    }


    public boolean isHuman() {
        return isHuman;
    }

    public void setHuman(boolean human) {
        isHuman = human;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

