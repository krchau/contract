package ua.kh.profit.baklanov.content.dao;

import java.sql.Connection;

public interface ConnectionFactory {
    Connection createConnection() throws Exception;
}
