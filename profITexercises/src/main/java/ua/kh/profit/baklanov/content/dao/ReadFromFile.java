package ua.kh.profit.baklanov.content.dao;

import ua.kh.profit.baklanov.content.dict.TypeOfPerson;
import ua.kh.profit.baklanov.content.service.Contract;
import ua.kh.profit.baklanov.content.service.InsuredPerson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


//reading file into Contract object
public class ReadFromFile implements IDao {

    private List<Contract> contracts = new ArrayList<>();

    public List<Contract> read() {
        List<Contract> listOfContracts = new ArrayList<>();
        try (BufferedReader inFile = new BufferedReader(new FileReader
                ("..\\profITexercise\\src\\main\\java\\ua\\kh\\profit\\baklanov\\content\\data\\contracts.csv"))) {
            String inputLine;
            while (null != (inputLine = inFile.readLine())) {
                Contract contract;
                String[] contractVars = inputLine.split(";");
                contract = (Contract) createEntityThoughtArrayOfStringsWithData(new Contract(), contractVars);
                listOfContracts.add(contract);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfContracts;
    }

    public List<InsuredPerson> readInsured(List<Contract> contracts) {
        List<InsuredPerson> listOfInsuredPersons = new ArrayList<>();
        try (BufferedReader inFile = new BufferedReader(new FileReader
                ("..\\profITexercise\\src\\main\\java\\ua\\kh\\profit\\baklanov\\content\\data\\insured.csv"))) {
            String inputLine;
            while (null != (inputLine = inFile.readLine())) {
                String[] contractVars = inputLine.split(";");
                for (Contract temp : contracts) {
                    if (Integer.parseInt(contractVars[0]) == temp.getId()) {
                        updateEntityThoughtArrayOfStringsWithData(temp, contractVars);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfInsuredPersons;
    }


    public static void main(String[] args) {
        ReadFromFile readFromFile = new ReadFromFile();
        readFromFile.contracts = readFromFile.read();
        readFromFile.readInsured(readFromFile.contracts);
    }

    @Override
    public void create(Object entity) {
    }

    @Override
    public Object read(long id) {
        return null;
    }

    @Override
    public void update(Object entity) {
    }

    @Override
    public void delete(long id) {
    }

    @Override
    public void updateEntityThoughtArrayOfStringsWithData(Contract entity, String[] contractVars) {
        InsuredPerson insuredPerson = new InsuredPerson(contractVars[1], //firstName
                contractVars[2], //lastName
                contractVars[3], //patronymic
                LocalDate.parse(contractVars[4]), //dateofBirth
                Integer.parseInt(contractVars[5]), //price
                Integer.parseInt(contractVars[6])); //id
        entity.add(insuredPerson);
    }


    @Override
    public Object createEntityThoughtArrayOfStringsWithData(Object entity, String[] contractVars) {
        entity = new Contract(Integer.parseInt(contractVars[0]),
                LocalDate.parse(contractVars[1]),
                LocalDate.parse(contractVars[2]),
                LocalDate.parse(contractVars[3]));
        if (contractVars[4].equals(TypeOfPerson.PHYSICAL.name())) {
            ((Contract) entity).setPerson(TypeOfPerson.PHYSICAL.getContract(contractVars[5], contractVars[6]));
        } else
            ((Contract) entity).setPerson(TypeOfPerson.JURIDICAL.getContract(contractVars[5], contractVars[6]));
        return entity;
    }

}