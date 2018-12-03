package ua.kh.profit.baklanov.content.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbcp.ConnectionFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;


 public class DaoFactory {
    private final Properties properties;
    private final static DaoFactory INSTANCE = new DaoFactory();
    private static BasicDataSource dataSource;
    public static DaoFactory getInstance()
    {
        return INSTANCE;
    }

    private DaoFactory() {
        properties = new Properties();
        try {
            InputStream in = new FileInputStream(DaoFactory.class.getResource("/dbcp.properties").getPath());
            properties.load(in);
            dataSource = (BasicDataSource) BasicDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ConnectionFactory getConnectionFactory() {
        try {
            return (ConnectionFactory) ConnectionFactoryImplementation.createDataSource(properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
