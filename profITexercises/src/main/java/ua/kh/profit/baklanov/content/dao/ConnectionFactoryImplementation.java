package ua.kh.profit.baklanov.content.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class ConnectionFactoryImplementation implements ConnectionFactory{
    /**
     * Returns new Pooled {@link DataSource} implementation
     *
     * @param poolProperties pool properties
     * @return new Pooled {@link DataSource} implementation
     * @throws SQLException
     */
    public static DataSource createDataSource(Properties poolProperties) throws SQLException {
        try {
            return BasicDataSourceFactory.createDataSource(poolProperties);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Connection createConnection() throws Exception {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/myschema","root","root");
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
}
