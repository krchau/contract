package ua.kh.profit.baklanov.content.dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryImplementation implements ConnectionFactory{
    Properties properties;

    @Override
    public Connection createConnection() throws Exception {
        properties=new Properties();
        try
        {
            InputStream in = new FileInputStream(DaoFactory.class.getResource("/dbcp.properties").getPath());
            properties.load(in);
            Class.forName(properties.getProperty("driverClassName")).newInstance();
        }
        catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        try {
            return DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("username"),properties.getProperty("password"));
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
}
