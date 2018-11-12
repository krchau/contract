package ua.kh.profit.baklanov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ContractTest {

    Contract contract;
    private LocalDate conclusionDate= LocalDate.now();
    private LocalDate dateOfStart = LocalDate.of(2019, 2,3);
    private LocalDate dateOfFinish=LocalDate.of(2019,5,6);
    private LocalDate dateOfBirthday=LocalDate.of(1999,2,3);
    private ArrayList<InsuredPerson> listOfInsuredPersons = new ArrayList<>();
    private int ETALON_SUM_FOR_HUMAN;
    private String ETALON_INITIALS="SimpleHumanSurname S. S.";

    @Before
    public void setUp() throws Exception {

        contract=new Contract(1, dateOfStart, dateOfFinish, conclusionDate, new Client(true, "Alex Baklanov", "Dom"));
        contract.add();
        listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName", "SimpleHumanSurname","SimpleHumanPatronymic",dateOfBirthday,100, 1));
        listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName", "ItisSimpleHumanSurname","SimpleHumanPatronymic",dateOfBirthday, 100,2));
        listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName", "IsItSimpleHumanSurname","SimpleHumanPatronymic",dateOfBirthday, 100,3 ));
        listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName", "B","SimpleHumanPatronymic",dateOfBirthday, 100,10));
        listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName", "AB","SimpleHumanPatronymic",dateOfBirthday, 100,11));
        listOfInsuredPersons.add(new InsuredPerson("SimpleHumanName", "AB","SimpleHumanPatronymic",dateOfBirthday, 100,24));
        ETALON_SUM_FOR_HUMAN=listOfInsuredPersons.size()*100;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSumFor() {
        assertEquals(ETALON_SUM_FOR_HUMAN, contract.sumFor());
    }

    @Test
    public void testSumIterator() {
        assertEquals(ETALON_SUM_FOR_HUMAN, contract.sumIterator());
    }

    @Test
    public void testSumForEach()
    {
        assertEquals(ETALON_SUM_FOR_HUMAN, contract.sumForEach());
    }
    @Test
    public void testSumLambda()
    {
        assertEquals(ETALON_SUM_FOR_HUMAN, contract.sumLambda());
    }
    @Test
    public void testSearch()
    {
        assertEquals(listOfInsuredPersons.get(5).getLastName(),contract.search(11));
    }
    @Test
    public void testInitials()
    {
        assertEquals(ETALON_INITIALS, contract.getListOfInsuredPersons().get(0).initials());
    }

}
