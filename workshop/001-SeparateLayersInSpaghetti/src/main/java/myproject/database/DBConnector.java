package myproject.database;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class DBConnector {

    private static final String CONN_URL = "jdbc:ucanaccess://MyDatabase.accdb";


    private Connection getConn() throws SQLException {
        return DriverManager.getConnection(CONN_URL);
    }
    //Connection conn = DriverManager.getConnection(CONN_URL);

    public ResultSet executeQuery(String query) {

        Connection conn = null;
        try {
            conn = this.getConn();
            return getConn().createStatement().executeQuery(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //    Statement s = conn.createStatement();


}
