package ua.kh.profit.baklanov.content.dao;

import org.springframework.jdbc.core.RowMapper;
import ua.kh.profit.baklanov.content.service.InsuredPerson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class InsuredMapper implements RowMapper{
    @Override
    public InsuredPerson mapRow(ResultSet resultSet, int i) throws SQLException {
        return new InsuredPerson(
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("patronymic"),
                LocalDate.parse(resultSet.getString("dateOfBirth")),
                resultSet.getInt("price"),
                resultSet.getInt("Contract_idContract"));
    }
}
