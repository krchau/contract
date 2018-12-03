package ua.kh.profit.baklanov.content.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.kh.profit.baklanov.content.dict.TypeOfPerson;
import ua.kh.profit.baklanov.content.service.Contract;
import ua.kh.profit.baklanov.content.service.InsuredPerson;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.*;

public class CRUDTest {
    CRUD crud;
    private ConnectionFactory connectionFactory;
    private static final int ETALON_READID=1;
    private static final int ETALON_UPDATEID=2;
    private static final int ETALON_DELETEID=3;
    private static final int ETALON_INSUREDID=10;
    private static final LocalDate ETALON_DOS = LocalDate.of(1001,01,01);
    private static final LocalDate ETALON_DOF = LocalDate.of(1002,02,02);
    private static final LocalDate ETALON_CD = LocalDate.of(1003,03,03);
    private static final LocalDate ETALON_DATEOFBIRTH = LocalDate.of(1000,1,1);
    private static final TypeOfPerson ETALON_TYPE = TypeOfPerson.JURIDICAL;
    private static final String ETALON_NAME = "TEST_NAME";
    private static final String ETALON_ADDRESS = "TEST_ADRESS";
    private static final String ETALON_FIRSTNAME = "test";
    private static final String ETALON_LASTNAME = "ins";
    private static final String ETALON_PATRONYMIC = "per";

    private static final int ETALON_PRICE = 400;


    @Before
    public void setUp() throws Exception {
        connectionFactory=new ConnectionFactoryImplementation();
        crud = new CRUD(connectionFactory);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testcreate() {
        try {
            Contract humanContract = new Contract(
                    ETALON_UPDATEID, ETALON_DOS,ETALON_DOF,ETALON_CD,ETALON_TYPE,ETALON_TYPE.getContract(ETALON_NAME,ETALON_ADDRESS));
            humanContract.add();
            assertNotNull(humanContract); //null or not null?
            humanContract = crud.create(humanContract);
            assertNotNull(humanContract.getId());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }
            /* reading contract with id=1 */
    @Test
    public void read() {
        try {
            Contract contract=(Contract)crud.read(ETALON_READID);
            assertNotNull(contract);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


            /* updating contract №2 and update insured person №10 in this contract */
    @Test
    public void update() {
        Contract contract=(Contract) crud.read(ETALON_UPDATEID);
        Contract updateContract = new Contract(ETALON_UPDATEID, ETALON_DOS,ETALON_DOF,ETALON_CD,ETALON_TYPE,ETALON_TYPE.getContract(ETALON_NAME,ETALON_ADDRESS));
        InsuredPerson insuredPerson= new InsuredPerson(ETALON_FIRSTNAME,ETALON_LASTNAME, ETALON_PATRONYMIC,ETALON_DATEOFBIRTH,ETALON_PRICE,ETALON_INSUREDID);
        updateContract.add(insuredPerson);
        crud.update(updateContract);
        updateContract=(Contract) crud.read(ETALON_UPDATEID);
        assertNotEquals(contract,updateContract);
    }

            /* deleting contract with id №3 */
    @Test
    public void delete() {
        final long EXPECTED = 0;
        Contract updateContract=(Contract) crud.read(ETALON_DELETEID);
        assertNotNull(updateContract);
        crud.delete(ETALON_DELETEID);
        updateContract=(Contract) crud.read(ETALON_DELETEID);
        assertEquals(EXPECTED,updateContract.getId());
    }
}