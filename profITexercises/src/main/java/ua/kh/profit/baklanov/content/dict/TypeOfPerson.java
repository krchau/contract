package ua.kh.profit.baklanov.content.dict;

import ua.kh.profit.baklanov.content.service.Company;
import ua.kh.profit.baklanov.content.service.Human;

public enum TypeOfPerson {
    PHYSICAL,
    JURIDICAL;

    public Object getContract(String Name, String address) {
        if (this.name().equals(PHYSICAL.name())) {
            return new Human(Name, address);
        } else {
            return new Company(Name, address);
        }
    }
}
