package ua.kh.profit.baklanov.content.dao;

import org.springframework.jdbc.core.RowMapper;
import ua.kh.profit.baklanov.content.dict.TypeOfPerson;
import ua.kh.profit.baklanov.content.service.Contract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ContractMapper implements RowMapper {
    @Override
    public Contract mapRow(ResultSet rs, int rowNum) throws SQLException {
            Contract contract = new Contract();
            contract.setId(rs.getInt("idContract"));
            contract.setDateOfStart(LocalDate.parse(rs.getString("dateOfStart")));
            contract.setDateOfFinish(LocalDate.parse(rs.getString("dateOfFinish")));
            contract.setConclusionDate(LocalDate.parse(rs.getString("conclusionDate")));
            contract.setTypeOfPerson(TypeOfPerson.valueOf(rs.getString("type")));
            contract.setPerson(TypeOfPerson.valueOf(rs.getString("type")).getContract(rs.getString("name"), rs.getString("address")));
            return contract;
        }
}
