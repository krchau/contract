package ua.kh.profit.baklanov.content.dict;

import ua.kh.profit.baklanov.content.domain.entities.Company;
import ua.kh.profit.baklanov.content.domain.entities.Human;

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
