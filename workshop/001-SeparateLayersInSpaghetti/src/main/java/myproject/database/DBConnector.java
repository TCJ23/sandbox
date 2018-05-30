package myproject.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {

    private static final String CONN_URL = "jdbc:ucanaccess://MyDatabase.accdb";


    private Connection getConn() throws SQLException {
        return DriverManager.getConnection(CONN_URL);
    }
    //Connection conn = DriverManager.getConnection(CONN_URL);

    public ResultSet executeQuery(String query) throws SQLException {
        return getConn().createStatement().executeQuery(query);
    }
    //    Statement s = conn.createStatement();
}
