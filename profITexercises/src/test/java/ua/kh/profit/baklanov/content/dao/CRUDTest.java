package ua.kh.profit.baklanov.content.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.kh.profit.baklanov.content.dict.TypeOfPerson;
import ua.kh.profit.baklanov.content.service.Contract;
import ua.kh.profit.baklanov.content.service.InsuredPerson;

import java.time.LocalDate;
import static org.junit.Assert.*;

public class CRUDTest {
    IDao crud;
    private static final int ETALON_READID=1;
    private static final int ETALON_UPDATEID=1;
    private static final int ETALON_DELETEID=2;
    private static final int ETALON_UPDATEDIDINSURED=5;
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
        ApplicationContext context =
                new ClassPathXmlApplicationContext("config.xml");
        crud = (IDao) context.getBean("ContractJDBCTemplate");
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
            assertNotNull(humanContract);
            humanContract = crud.create(humanContract);
            assertNotNull(humanContract.getId());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }

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

    @Test
    public void update() {
        Contract contract=(Contract) crud.read(ETALON_UPDATEID);
        Contract updateContract = new Contract(ETALON_UPDATEID, ETALON_DOS,ETALON_DOF,ETALON_CD,ETALON_TYPE,ETALON_TYPE.getContract(ETALON_NAME,ETALON_ADDRESS));
        InsuredPerson insuredPerson= new InsuredPerson(ETALON_FIRSTNAME,ETALON_LASTNAME, ETALON_PATRONYMIC,ETALON_DATEOFBIRTH,ETALON_PRICE,ETALON_UPDATEDIDINSURED);
        updateContract.add(insuredPerson);
        crud.update(updateContract);
        updateContract=(Contract) crud.read(ETALON_UPDATEID);
        assertNotEquals(contract,updateContract);
    }


    @Test
    public void delete() {
        crud.delete(ETALON_DELETEID);
        assertNull(crud.read(ETALON_DELETEID));
    }
}