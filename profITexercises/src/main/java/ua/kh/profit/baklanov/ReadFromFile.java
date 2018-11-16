package ua.kh.profit.baklanov;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;


//reading file into Contract object and adding it to ArrayList
public class ReadFromFile
{
    public String[] split(String line)
    {
        String[] contractVars = line.split(";");
        return contractVars;
    }

    public ArrayList<Contract> read()
    {
        ArrayList<Contract> listOfContracts = new ArrayList<>();
        try {

            BufferedReader inFile = new BufferedReader (new FileReader
                    ("data.csv"));
            String inputLine;
            while (null != (inputLine = inFile.readLine())) {
                Client client=new Client();
                Contract contract = new Contract(client);

                String[] contractVars = split(inputLine);
                contract.setId(Integer.parseInt(contractVars[0]));
                contract.setDateOfStart(LocalDate.parse(contractVars[1]));
                contract.setDateOfFinish(LocalDate.parse(contractVars[2]));
                contract.setConclusionDate(LocalDate.parse(contractVars[3]));
                client.setHuman(Boolean.parseBoolean(contractVars[4]));
                if(client.isHuman()==true)
                {
                    client.setFullName(contractVars[5]);
                }
                else
                {
                    client.setNameOfOrg(contractVars[5]);
                }
                client.setAddress(contractVars[6]);
                ArrayList<InsuredPerson> listOfInsuredPerson=new ArrayList<>();
                for(int i=0;i<(contractVars.length-6)/6;i++)
                {
                    InsuredPerson ip=new InsuredPerson(contractVars[6*(i+1)+1],contractVars[6*(i+1)+2],contractVars[6*(i+1)+3],LocalDate.parse(contractVars[6*(i+1)+4]),Integer.parseInt(contractVars[6*(i+1)+5]),Integer.parseInt(contractVars[6*(i+1)+6]));
                    listOfInsuredPerson.add(ip);
                }
                contract.setListOfInsuredPersons(listOfInsuredPerson);
                listOfContracts.add(contract);
            }
            inFile.close();
            for (Contract item : listOfContracts
            ) {
                item.outputInfoAboutContract(item);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfContracts;
    }
    public static void main(String[] args)
    {
       ReadFromFile readFromFile= new ReadFromFile();
       readFromFile.read();

    }
}