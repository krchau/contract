package ua.kh.profit.baklanov.content.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.kh.profit.baklanov.content.dict.TypeOfPerson;
import ua.kh.profit.baklanov.content.domain.entities.Company;
import ua.kh.profit.baklanov.content.domain.entities.Human;
import ua.kh.profit.baklanov.content.service.Contract;
import ua.kh.profit.baklanov.content.service.InsuredPerson;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class CRUD implements IDao {
    private static final String INSERT_QUERY = "INSERT INTO myschema.contract (dateOfStart,dateOfFinish,conclusionDate,type, name, address) VALUES (?,?,?,?,?,?);";
    private static final String SELECT_QUERY = "SELECT idContract,dateOfStart,dateOfFinish,conclusionDate,type, name, address FROM myschema.contract WHERE idContract = ?;";
    private static final String UPDATE_QUERY = "UPDATE myschema.contract SET dateOfStart = ?, dateOfFinish = ?, conclusionDate = ?,type = ? ,name = ?,address = ?  WHERE idContract = ?;";
    private static final String DELETE_QUERY = "DELETE FROM myschema.contract WHERE idContract = ?";
    private static final String INSERT_QUERY_INSURED = "INSERT INTO myschema.insuredperson (firstname,lastname,patronymic,dateOfBirth, price, contract_idContract) VALUES (?,?,?,?,?,?);";
    private static final String SELECT_QUERY_INSURED = "Select firstName, lastName, patronymic, dateOfBirth, price, Contract_idContract from myschema.insuredperson Where Contract_idContract=?;";
    private static final String DELETE_QUERY_INSURED = "DELETE FROM insuredperson where Contract_idContract=?;";
    private static final String UPDATE_QUERY_INSURED = "UPDATE myschema.insuredperson SET firstName = ?, lastName = ?, patronymic = ?,dateOfBirth = ? ,price = ? WHERE idInsured=? AND Contract_idContract = ?;";
    private JdbcTemplate jdbcTemplate;


    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Contract create(Object entity) {
        KeyHolder key = new GeneratedKeyHolder();
        Contract contract=(Contract) entity;
        jdbcTemplate.update(con -> {
            PreparedStatement statement =
                    con.prepareStatement(INSERT_QUERY,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setDate(1, Date.valueOf(contract.getDateOfStart()));
            statement.setDate(2, Date.valueOf(contract.getDateOfFinish()));
            statement.setDate(3, Date.valueOf(contract.getConclusionDate()));
            statement.setString(4, contract.getTypeOfPerson().toString());
            humanOrCompanyStatement(statement,contract);
            return statement;
        }, key);
        long lastInsertedId = key.getKey().longValue();

        createInsured(contract, lastInsertedId);
        return (Contract) entity;
    }

    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.SERIALIZABLE)
    public void createInsured(Contract contract, long lastInsertedId) {
        for (InsuredPerson insuredPerson : contract.getListOfInsuredPersons()) {
            jdbcTemplate.update(con -> {
                PreparedStatement statement2 =
                        con.prepareStatement(INSERT_QUERY_INSURED,PreparedStatement.RETURN_GENERATED_KEYS);
                statement2.setString(1, insuredPerson.getFirstName());
                statement2.setString(2, insuredPerson.getLastName());
                statement2.setString(3, insuredPerson.getPatronymic());
                statement2.setDate(4, Date.valueOf(insuredPerson.getDateofBirth()));
                statement2.setLong(5, insuredPerson.getPrice());
                statement2.setLong(6, lastInsertedId);
                return statement2;
            });
        }
    }

    @Override
    @Transactional (propagation = Propagation.REQUIRED)
    public Object read(long id) {
        Contract contract;
        try {
            contract = (Contract) jdbcTemplate.queryForObject(SELECT_QUERY, new Object[]{id}, new ContractMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        try {
            List<InsuredPerson> retList = jdbcTemplate.query(SELECT_QUERY_INSURED, new Object[]{id}, new InsuredMapper());
            contract.setListOfInsuredPersons(retList);
        }
        catch (EmptyResultDataAccessException e1) {
                return null;
            }

        return contract;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Object entity) {
        Contract contract=(Contract) entity;
        Object[] params = { contract.getDateOfStart(),
                contract.getDateOfFinish(),
                contract.getConclusionDate(),
                String.valueOf(contract.getTypeOfPerson()),
                humanOrCompany(contract)[0] ,
                humanOrCompany(contract)[1],
                contract.getId()};
        int[] types = {Types.DATE, Types.DATE, Types.DATE, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
        jdbcTemplate.update(UPDATE_QUERY, params, types);
        for (InsuredPerson insuredPerson : contract.getListOfInsuredPersons()) {
            jdbcTemplate.update(con -> {
                PreparedStatement statement2 =
                        con.prepareStatement(UPDATE_QUERY_INSURED);
                statement2.setString(1, insuredPerson.getFirstName());
                statement2.setString(2, insuredPerson.getLastName());
                statement2.setString(3, insuredPerson.getPatronymic());
                statement2.setDate(4, Date.valueOf(insuredPerson.getDateofBirth()));
                statement2.setLong(5, insuredPerson.getPrice());
                statement2.setLong(6, insuredPerson.getId());
                statement2.setLong(7, contract.getId());
                return statement2;
            });
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(long id) {
        Object[] params = { id };
        int[] types = {Types.INTEGER};
        jdbcTemplate.update(DELETE_QUERY_INSURED, params, types);
        jdbcTemplate.update(DELETE_QUERY, params, types);
    }


    private String[] humanOrCompany(Contract contract) {
        String[] result=new String[2];
        if (contract.getTypeOfPerson().equals(TypeOfPerson.PHYSICAL)) {
            Human human = new Human(contract.getPerson());
            result[0]=human.getFullName();
            result[1]=human.getAddress();
        } else {
            Company company = new Company(contract.getPerson());
            result[0]=company.getNameOfOrg();
            result[1]=company.getAddress();
        }
        return result;
    }

    private boolean detectHuman(Contract contract)
    {
        if(contract.getTypeOfPerson().equals(TypeOfPerson.PHYSICAL)) { return true;}
        return false;
    }

    private PreparedStatement humanOrCompanyStatement(PreparedStatement statement, Contract contract) {
        if (detectHuman(contract)) {
            Human human = new Human(contract.getPerson());
            try { statement.setString(5, human.getFullName());
                statement.setString(6, human.getAddress());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            Company company = new Company(contract.getPerson());
            try {
                statement.setString(5, company.getNameOfOrg());
                statement.setString(6, company.getAddress());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return statement;
    }
}
