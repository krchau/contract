package ua.kh.profit.baklanov;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.kh.profit.baklanov.content.dao.ReadFromFile;
import ua.kh.profit.baklanov.content.service.Contract;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class ReadFromFileTest {
    ReadFromFile readFromFile;
    ArrayList <Contract> list=new ArrayList<>();
    @Before
    public void setUp() {
        readFromFile= new ReadFromFile();
    }

    @After
    public void tearDown()  {
    }

    @Test
    public void testNumberOfObjects() {

        int count=0;
        try {
            BufferedReader inFile = new BufferedReader(new FileReader
                    ("..\\profITexercise\\src\\main\\java\\ua\\kh\\profit\\baklanov\\content\\data\\contracts.csv"));
            while (null != (inFile.readLine()))
            {
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(count,readFromFile.read().size());
    }

    //test that vars from file = or > than fields in class (> because insured persons is a list in class, but peace of line in file)
    /*@Test
    public void testSplit()
    {
        Contract contract=new Contract();
        String[] contractVars=null;
        try {
            BufferedReader inFile = new BufferedReader(new FileReader
                    ("data.csv"));
            String inputLine=inFile.readLine();
            contractVars = readFromFile.split(inputLine);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue( contract.getClass().getDeclaredFields().length <= contractVars.length );
    }*/
}
