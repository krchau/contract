package ua.kh.profit.baklanov.content.dao;
import ua.kh.profit.baklanov.content.dict.TypeOfPerson;
import ua.kh.profit.baklanov.content.domain.entities.Company;
import ua.kh.profit.baklanov.content.domain.entities.Human;
import ua.kh.profit.baklanov.content.service.Contract;
import ua.kh.profit.baklanov.content.service.InsuredPerson;
import java.sql.*;

public class CRUD implements IDao {
    public static final String INSERT_QUERY = "INSERT INTO myschema.contract (dateOfStart,dateOfFinish,conclusionDate,type, name, address) VALUES (?,?,?,?,?,?);";
    public static final String SELECT_QUERY = "SELECT idContract,dateOfStart,dateOfFinish,conclusionDate,type, name, address FROM myschema.contract WHERE idContract = ?;";
    public static final String UPDATE_QUERY = "UPDATE myschema.contract SET dateOfStart = ?, dateOfFinish = ?, conclusionDate = ?,type = ? ,name = ?,address = ?  WHERE idContract = ?;";
    private static final String DELETE_QUERY = "DELETE FROM myschema.contract WHERE idContract = ?";
    public static final String LAST_INSERT_ID="SELECT LAST_INSERT_ID();";
    public static final String INSERT_QUERY_INSURED = "INSERT INTO myschema.insuredperson (firstname,lastname,patronymic,dateOfBirth, price, contract_idContract) VALUES (?,?,?,?,?,?);";
    public static final String SELECT_QUERY_INSURED="Select firstName, lastName, patronymic, dateOfBirth, price, Contract_idContract from myschema.insuredperson Where Contract_idContract=?;";
    public static final String DELETE_QUERY_INSURED="DELETE FROM insuredperson where Contract_idContract=?;";
    public static final String UPDATE_QUERY_INSURED="UPDATE myschema.insuredperson SET firstName = ?, lastName = ?, patronymic = ?,dateOfBirth = ? ,price = ? WHERE idInsured=? AND Contract_idContract = ?;";
    private ConnectionFactory connectionFactory;

    public CRUD(ConnectionFactory connectionFactory) { this.connectionFactory=connectionFactory; }

    @Override
    public Contract create(Object entity) {
            try (Connection connection = connectionFactory.createConnection();PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
                Contract contract= (Contract) entity;

                statement.setDate(1, Date.valueOf(contract.getDateOfStart()));
                statement.setDate(2, Date.valueOf(contract.getDateOfFinish()));
                statement.setDate(3, Date.valueOf(contract.getConclusionDate()));
                statement.setString(4, contract.getTypeOfPerson().toString());
                humanOrCompanyStatement(statement,contract);
                statement.executeUpdate();
                PreparedStatement lastId=connection.prepareStatement(LAST_INSERT_ID);
                ResultSet resultSet = lastId.executeQuery();
                long lastInsertedId=0;
                while (resultSet.next()) {
                    lastInsertedId=resultSet.getLong(1);
                }

                for (InsuredPerson insuredPerson : contract.getListOfInsuredPersons()) {
                    try (PreparedStatement statement2 = connection.prepareStatement(INSERT_QUERY_INSURED))
                    {
                        statement2.setString(1,insuredPerson.getFirstName());
                        statement2.setString(2,insuredPerson.getLastName());
                        statement2.setString(3,insuredPerson.getPatronymic());
                        statement2.setDate(4, Date.valueOf(insuredPerson.getDateofBirth()));
                        statement2.setLong(5,insuredPerson.getPrice());
                        statement2.setLong(6, lastInsertedId);
                        statement2.execute();
                    }
                }
            return (Contract) entity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object read(long id) {
        Contract contract=new Contract();
        try (Connection connection = connectionFactory.createConnection();PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                contract.setId((int) resultSet.getLong("idContract"));
                contract.setDateOfStart(resultSet.getDate("dateOfStart").toLocalDate());
                contract.setDateOfFinish(resultSet.getDate("dateOfFinish").toLocalDate());
                contract.setConclusionDate(resultSet.getDate("conclusionDate").toLocalDate());
                contract.setTypeOfPerson(TypeOfPerson.valueOf(resultSet.getString("type")));
                contract.setPerson(TypeOfPerson.valueOf(resultSet.getString("type")).getContract(resultSet.getString("name"),resultSet.getString("address")));
            }
            resultSet.close();
            try(PreparedStatement preparedStatement=connection.prepareStatement(SELECT_QUERY_INSURED)) {
                preparedStatement.setLong(1,id);
                ResultSet resultSet2 = preparedStatement.executeQuery();
                while (resultSet2.next()) {
                InsuredPerson insuredPerson=new InsuredPerson(
                        resultSet2.getString("firstName"),
                        resultSet2.getString("lastName"),
                        resultSet2.getString("patronymic"),
                        resultSet2.getDate("dateOfBirth").toLocalDate(),
                        (int)resultSet2.getLong("price"),
                        (int)resultSet2.getLong("Contract_idContract"));
                contract.add(insuredPerson);
                }
                resultSet2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contract;
    }

    @Override
    public void update(Object entity) {
        try (Connection connection = connectionFactory.createConnection();PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            Contract contract = (Contract) entity;
            statement.setDate(1, Date.valueOf(contract.getDateOfStart()));
            statement.setDate(2, Date.valueOf(contract.getDateOfFinish()));
            statement.setDate(3, Date.valueOf(contract.getConclusionDate()));
            statement.setString(4, contract.getTypeOfPerson().toString());
            humanOrCompanyStatement(statement,contract);
            statement.setLong(7,contract.getId());
            statement.executeUpdate();
            for (InsuredPerson insuredPerson : contract.getListOfInsuredPersons()) {
                try (PreparedStatement statement2 = connection.prepareStatement(UPDATE_QUERY_INSURED))
                {
                    statement2.setString(1,insuredPerson.getFirstName());
                    statement2.setString(2,insuredPerson.getLastName());
                    statement2.setString(3,insuredPerson.getPatronymic());
                    statement2.setDate(4, Date.valueOf(insuredPerson.getDateofBirth()));
                    statement2.setLong(5,insuredPerson.getPrice());
                    statement2.setLong(6, insuredPerson.getId());
                    statement2.setLong(7, contract.getId());
                    statement2.execute();
                }
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = connectionFactory.createConnection();PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            PreparedStatement preparedStatement=connection.prepareStatement(DELETE_QUERY_INSURED);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
            statement.setLong(1, id);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean detectHuman(Contract contract)
    {
        if(contract.getTypeOfPerson().equals(TypeOfPerson.PHYSICAL)) { return true;}
        return false;
    }

    public PreparedStatement humanOrCompanyStatement(PreparedStatement statement, Contract contract) {
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
