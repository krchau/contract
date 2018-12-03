package ua.kh.profit.baklanov.content.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DaoFactoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getInstance() {
        DaoFactory daoFactory= DaoFactory.getInstance();
        assertNotNull("Dao factory is null", daoFactory);
    }

}