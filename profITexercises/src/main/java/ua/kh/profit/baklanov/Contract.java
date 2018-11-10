package ua.kh.profit.baklanov;

import java.util.*;
import java.time.LocalDate;

public class Contract {
    private int id;
    private LocalDate conclusionDate;
    private LocalDate dateOfStart;
    private LocalDate dateOfFinish;
    private Client client;
    private ArrayList<InsuredPerson> listOfInsuredPersons = new ArrayList<>();




    public Contract(int id, LocalDate dateOfStart,LocalDate dateOfFinish, LocalDate conclusionDate, Client client ) {

        if(dateOfStart.compareTo(dateOfFinish)>0) {
            throw new IllegalArgumentException("Finish date can't be earlier than start!");
        }
        else if (dateOfFinish.compareTo(getCurrentDate())<0 || dateOfStart.compareTo(getCurrentDate())<0) {
            throw new IllegalArgumentException("Can't create contract with date in future!");
        }
        else {
            this.dateOfStart=dateOfStart;
            this.dateOfFinish=dateOfFinish;
            this.conclusionDate=conclusionDate;
        }

        this.client= client;
        this.id=id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getConclusionDate() {
        return conclusionDate;
    }

    public void setConclusionDate(LocalDate conclusionDate) {
        this.conclusionDate = conclusionDate;
    }

    public LocalDate getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(LocalDate dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public LocalDate getDateOfFinish() {
        return dateOfFinish;
    }

    public void setDateOfFinish(LocalDate dateOfFinish) {
        this.dateOfFinish = dateOfFinish;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public ArrayList<InsuredPerson> getListOfInsuredPersons() {
        return listOfInsuredPersons;
    }

    public void setListOfInsuredPersons(ArrayList<InsuredPerson> listOfInsuredPersons) {
        this.listOfInsuredPersons = listOfInsuredPersons;
    }

    //adding new
    private void add() {
        if (this.client.isHuman()) {
            this.listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName", "SimpleHumanSurname","SimpleHumanPatronymic",setDate(1999,2,3),100, 1));
            this.listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName", "ItisSimpleHumanSurname","SimpleHumanPatronymic",setDate(1995,2,3), 100,2));
            this.listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName", "IsItSimpleHumanSurname","SimpleHumanPatronymic",setDate(1997,7,3), 100,3 ));
            this.listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName", "B","SimpleHumanPatronymic",setDate(1993,2,3), 100,10));
            this.listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName", "AB","SimpleHumanPatronymic",setDate(1997,8,3), 100,11));
            this.listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName", "AB","SimpleHumanPatronymic",setDate(1995,2,1), 100,24));
        }
        else {
            this.listOfInsuredPersons.add(new InsuredPerson("NameOfHumanWhoWorkInCompany", "SurnameOfHumanWhoWorkInCompany", "PatronymicOfHumanWhoWorkInCompany",setDate(1995,2,1), 150, 1));
        }
    }

    private int sumFor() {
        int sum=0;
        for (int i=0;i<listOfInsuredPersons.size();i++) {
            sum+=listOfInsuredPersons.get(i).getPrice();
        }

        return sum;
    }

    private int sumIterator() {
        int sum=0;
        for (Iterator<InsuredPerson> i = listOfInsuredPersons.iterator(); i.hasNext();) {

           sum+=i.next().getPrice();
        }
        return sum;
    }

    private int sumForEach() {
        int sum=0;
        for (InsuredPerson temp : listOfInsuredPersons) {
            sum+=temp.getPrice();
        }
        return sum;
    }

    private int sumLambda() {
        int sum = listOfInsuredPersons.stream().mapToInt(value -> value.getPrice()).sum();
        return sum;
    }

    private ArrayList<InsuredPerson> sortFullName(ArrayList listOfInsuredPersons)
    {
        Collections.sort(listOfInsuredPersons, new Comparator<InsuredPerson>() {
            public int compare(InsuredPerson o1, InsuredPerson o2) {
                return o1.getLastName().toString().compareTo(o2.getLastName().toString());
            }
        });
        return listOfInsuredPersons;
    }

    private ArrayList<InsuredPerson> sortFullNameLambda(ArrayList listOfInsuredPersons)
    {
        Collections.sort(listOfInsuredPersons, (Comparator<InsuredPerson>) (o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
        return listOfInsuredPersons;
    }

    private ArrayList<InsuredPerson> sortDateOfBirthLambda(ArrayList listOfInsuredPersons)
    {
        Collections.sort(listOfInsuredPersons, (Comparator<InsuredPerson>) (o1, o2) -> o1.getDateofBirth().compareTo(o2.getDateofBirth()));
        return listOfInsuredPersons;
    }

    private void search( int id)
    {
        for (InsuredPerson temp : listOfInsuredPersons) {
            if (temp.getId() == id) {
                System.out.println("Result of search: " + temp.initials());
            }
        }
    }


    public static void main(String[] args)
    {
        Contract humanContract= new Contract(1,setDate(2019,2,3),setDate(2019,5,6),getCurrentDate(), new Client(true, "Alex Baklanov", "Dom"));
        Contract companyContract= new Contract(2,setDate(2019,1,28),setDate(2019,12,23),getCurrentDate(), new Client(false, "My Company", "NeDom"));
        char addOrNo='X';
        while (addOrNo =='Y' ||addOrNo =='y' || addOrNo =='X') {
            Scanner in = new Scanner(System.in);
            System.out.print("Add insured person?(Y/N)");
            addOrNo = in.next().charAt(0);
            if (addOrNo == 'Y' || addOrNo=='y') {
                humanContract.add();
                companyContract.add();
            } else if (addOrNo == 'N'||addOrNo=='n') {
                humanContract.outputInfoAboutContract(humanContract);
                humanContract.outputInfoAboutContract(companyContract);
            }
        }
        humanContract.sortFullName(humanContract.listOfInsuredPersons);
        humanContract.outputListOfInsuredPersonsNames(humanContract);
        humanContract.sortFullNameLambda(humanContract.listOfInsuredPersons);
        humanContract.outputListOfInsuredPersonsNames(humanContract);
        humanContract.sortDateOfBirthLambda(humanContract.listOfInsuredPersons);
        humanContract.outputListOfInsuredPersonsDateOfBirth(humanContract);
        humanContract.search(10);

    }

    private static LocalDate getCurrentDate() {
        LocalDate date = null;
        try {
            date=LocalDate.now();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    private static LocalDate setDate(int year, int month, int day) {
        LocalDate date = null;
        try {
            date = LocalDate.of(year, month, day);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Date must be valid!");
        }
        return date;
    }

    public void outputInfoAboutContract(Contract contract)
    {
        System.out.println
                ("\n"+"ID: "+String.valueOf(contract.id)+","+"\n"+"Conclusion date: "+String.valueOf(contract.conclusionDate)+","+"\n"+
                        "Date of start: "+String.valueOf(contract.dateOfStart) +","+"\n"+"Date of finish: "+
                        String.valueOf(contract.dateOfFinish) +","+"\n"+"Sum of contract: "+String.valueOf(contract.sumLambda())+"\n");
    }

    public void outputListOfInsuredPersonsNames(Contract contract)
    {
        for (int i=0;i<contract.listOfInsuredPersons.size();i++)
        {
            System.out.println(contract.listOfInsuredPersons.get(i).initials());
        }
    }
    public void  outputListOfInsuredPersonsDateOfBirth(Contract contract)
    {
        for (int i=0;i<contract.listOfInsuredPersons.size();i++)
        {
            System.out.println(contract.listOfInsuredPersons.get(i).getDateofBirth());
        }
    }

}
