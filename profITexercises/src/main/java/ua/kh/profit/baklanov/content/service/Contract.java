package ua.kh.profit.baklanov.content.service;

import ua.kh.profit.baklanov.content.dict.TypeOfPerson;
import java.util.*;
import java.time.LocalDate;
import java.util.logging.Logger;

public class Contract {
    private static Logger log = Logger.getLogger(Contract.class.getName());
    private int id;
    private LocalDate dateOfStart;
    private LocalDate dateOfFinish;
    private LocalDate conclusionDate;
    private List<InsuredPerson> listOfInsuredPersons = new ArrayList<>();
    TypeOfPerson typeOfPerson;
    private Object person;

    //adding insured persons
    public void add() {
        this.listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName1", "SimpleHumanSurname", "SimpleHumanPatronymic1", LocalDate.of(1999, 2, 3), 100, 1));
        this.listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName2", "SimpleHumanSurname", "SimpleHumanPatronymic2", LocalDate.of(1999, 2, 3), 100, 1));
        this.listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName3", "ItisSimpleHumanSurname", "SimpleHumanPatronymic3", LocalDate.of(1999, 2, 3), 100, 2));
        this.listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName4", "IsItSimpleHumanSurname", "SimpleHumanPatronymic4", LocalDate.of(1999, 2, 3), 100, 3));
        this.listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName5", "B", "SimpleHumanPatronymic5", LocalDate.of(1999, 2, 3), 100, 10));
        this.listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName6", "AB", "SimpleHumanPatronymic6", LocalDate.of(1999, 2, 3), 100, 11));
    }

    public int sumFor() {
        int sum = 0;
        for (int i = 0; i < listOfInsuredPersons.size(); i++) {
            sum += listOfInsuredPersons.get(i).getPrice();
        }
        return sum;
    }

    public int sumIterator() {
        int sum = 0;
        for (Iterator<InsuredPerson> i = listOfInsuredPersons.iterator(); i.hasNext(); ) {
            sum += i.next().getPrice();
        }
        return sum;
    }

    public int sumForEach() {
        int sum = 0;
        for (InsuredPerson temp : listOfInsuredPersons) {
            sum += temp.getPrice();
        }
        return sum;
    }

    public int sumLambda() { return  listOfInsuredPersons.stream().mapToInt(value -> value.getPrice()).sum(); }

    private List<InsuredPerson> sortLastNameLambda(List<InsuredPerson> listOfInsuredPersons) {
        Collections.sort(listOfInsuredPersons, (Comparator<InsuredPerson>) (o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
        return listOfInsuredPersons;
    }

    private List<InsuredPerson> sortDateOfBirthLambda(List<InsuredPerson> listOfInsuredPersons) {
        Collections.sort(listOfInsuredPersons, (Comparator<InsuredPerson>) (o1, o2) -> o1.getDateofBirth().compareTo(o2.getDateofBirth()));
        return listOfInsuredPersons;
    }

    public String search(int id) {
        String result = "";
        for (InsuredPerson temp : listOfInsuredPersons) {
            if (temp.getId() == id) {
                result = temp.getLastName();
                log.fine("Found a match!");
            }
        }
        return result;
    }

    public void add(InsuredPerson insuredPerson) {
        listOfInsuredPersons.add(insuredPerson);
    }

    public static void main(String[] args) {
        Contract humanContract = new Contract(1, LocalDate.of(2019, 2, 3), LocalDate.of(2019, 5, 6), LocalDate.now(),TypeOfPerson.PHYSICAL, TypeOfPerson.PHYSICAL.getContract("abc", "123"));
        Contract companyContract = new Contract(1, LocalDate.of(2019, 2, 3),LocalDate.of(2019, 5, 6), LocalDate.now(),TypeOfPerson.JURIDICAL, TypeOfPerson.JURIDICAL.getContract("def", "456"));
        humanContract.add();
        companyContract.add();
        humanContract.sortLastNameLambda(humanContract.listOfInsuredPersons);
        humanContract.sortDateOfBirthLambda(humanContract.listOfInsuredPersons);
        humanContract.search(10);
    }

    /* CONSTRUCTORS*/

    public Contract() {
    }

    public Contract(int id, LocalDate dateOfStart, LocalDate dateOfFinish, LocalDate conclusionDate) {
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
        this.conclusionDate = conclusionDate;
        this.id = id;
    }

    public Contract(int id, LocalDate dateOfStart, LocalDate dateOfFinish, LocalDate conclusionDate,TypeOfPerson typeOfPerson, Object person) {
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
        this.conclusionDate = conclusionDate;
        this.id = id;
        this.typeOfPerson=typeOfPerson;
        this.person = person;
    }

    public Contract( LocalDate dateOfStart, LocalDate dateOfFinish, LocalDate conclusionDate,TypeOfPerson typeOfPerson, Object person) {
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
        this.conclusionDate = conclusionDate;
        this.typeOfPerson=typeOfPerson;
        this.person = person;
    }
    /* GETTERS and SETTERS*/

    public LocalDate getConclusionDate() { return conclusionDate; }

    public void setConclusionDate(LocalDate conclusionDate) { this.conclusionDate = conclusionDate; }

    public LocalDate getDateOfStart() { return dateOfStart; }

    public void setDateOfStart(LocalDate dateOfStart) { this.dateOfStart = dateOfStart; }

    public LocalDate getDateOfFinish() { return dateOfFinish; }

    public void setDateOfFinish(LocalDate dateOfFinish) { this.dateOfFinish = dateOfFinish; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public List<InsuredPerson> getListOfInsuredPersons() { return listOfInsuredPersons; }

    public void setListOfInsuredPersons(List<InsuredPerson> listOfInsuredPersons) { this.listOfInsuredPersons = listOfInsuredPersons; }

    public Object getPerson() { return person; }

    public void setPerson(Object person) { this.person = person; }

    public TypeOfPerson getTypeOfPerson() { return typeOfPerson; }

    public void setTypeOfPerson(TypeOfPerson typeOfPerson) { this.typeOfPerson = typeOfPerson; }

    public void setPerson(String string, String string1) {

    }
}

