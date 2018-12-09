package ua.kh.profit.baklanov.content.service;

import ua.kh.profit.baklanov.content.dict.ContractVars;
import ua.kh.profit.baklanov.content.dict.ContractVarsInsured;
import ua.kh.profit.baklanov.content.dict.TypeOfPerson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


//reading file into Contract object
public class ReadFromFile implements IReadFromFile {

    private List<Contract> contracts = new ArrayList<>();

    public List<Contract> read() {
        List<Contract> listOfContracts = new ArrayList<>();
        try (BufferedReader inFile = new BufferedReader(new FileReader
                ("..\\profITexercise\\src\\main\\resources\\contracts.csv"))) {
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
                ("..\\profITexercise\\src\\main\\resources\\insured.csv"))) {
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
    public void updateEntityThoughtArrayOfStringsWithData(Contract entity, String[] contractVars) {
        InsuredPerson insuredPerson = new InsuredPerson(contractVars[ContractVarsInsured.FIRSTNAME.ordinal()], //firstName
                contractVars[ContractVarsInsured.LASTNAME.ordinal()], //lastName
                contractVars[ContractVarsInsured.PATRONYMIC.ordinal()], //patronymic
                LocalDate.parse(contractVars[ContractVarsInsured.DATEOFBIRTH.ordinal()]), //dateofBirth
                Integer.parseInt(contractVars[ContractVarsInsured.PRICE.ordinal()]), //price
                Integer.parseInt(contractVars[ContractVarsInsured.IDINSURED.ordinal()])); //id
        entity.add(insuredPerson);
    }


    @Override
    public Object createEntityThoughtArrayOfStringsWithData(Object entity, String[] contractVars) {
        entity = new Contract(Integer.parseInt(contractVars[ContractVars.ID.ordinal()]),
                LocalDate.parse(contractVars[ContractVars.DOS.ordinal()]),
                LocalDate.parse(contractVars[ContractVars.DOF.ordinal()]),
                LocalDate.parse(contractVars[ContractVars.CD.ordinal()]));
        if (contractVars[ContractVars.TYPE.ordinal()].equals(TypeOfPerson.PHYSICAL.name())) {
            ((Contract) entity).setTypeOfPerson(TypeOfPerson.PHYSICAL);
            ((Contract) entity).setPerson(TypeOfPerson.PHYSICAL.getContract(contractVars[ContractVars.NAME.ordinal()], contractVars[ContractVars.ADDRESS.ordinal()]));
        } else {
            ((Contract) entity).setTypeOfPerson(TypeOfPerson.JURIDICAL);
            ((Contract) entity).setPerson(TypeOfPerson.JURIDICAL.getContract(contractVars[ContractVars.NAME.ordinal()], contractVars[ContractVars.ADDRESS.ordinal()]));
        }
        return entity;
    }

}