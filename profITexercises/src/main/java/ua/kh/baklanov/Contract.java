package ua.kh.baklanov;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;


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
            this.listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName", "SimpleHumanSurname", 100));
        }
        else {
            this.listOfInsuredPersons.add(new InsuredPerson("NameOfHumanWhoWorkInCompany", "SurnameOfHumanWhoWorkInCompany", 150));
        }
    }

    private int sum() {
        int sum=0;
        for (int i=0;i<listOfInsuredPersons.size();i++) {
         sum+=listOfInsuredPersons.get(i).getPrice();
        }

        return sum;
    }


    public static void main(String[] args)
    {
        Contract humanContract= new Contract(1,setDate(2019,2,3),setDate(2019,5,6),getCurrentDate(), new Client(true, "Alex Baklanov", "Dom"));
        Contract companyContract= new Contract(2,setDate(2019,1,28),setDate(2019,12,23),getCurrentDate(), new Client(false, "My Company", "NeDom"));
       char addOrNo='X';
       while (addOrNo !='N') {
           Scanner in = new Scanner(System.in);
           System.out.print("Add insured person?(Y/N)");
           addOrNo = in.next().charAt(0);
           if (addOrNo == 'Y' || addOrNo=='y') {
               humanContract.add();
               companyContract.add();
           } else if (addOrNo == 'N'||addOrNo=='n') {
            humanContract.output(humanContract);
            humanContract.output(companyContract);
           }
       }

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

    public void output(Contract contract)
    {
        System.out.println
                ("\n"+"ID: "+String.valueOf(contract.id)+","+"\n"+"Conclusion date: "+String.valueOf(contract.conclusionDate)+","+"\n"+
                        "Date of start: "+String.valueOf(contract.dateOfStart) +","+"\n"+"Date of finish: "+
                        String.valueOf(contract.dateOfFinish) +","+"\n"+"Sum of contract: "+String.valueOf(contract.sum())+"\n");
    }

}
