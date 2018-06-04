package myproject.database;

import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class DBConnector {

    private static final String CONN_URL = "jdbc:ucanaccess://D:\\Library\\sandbox\\workshop\\001-SeparateLayersInSpaghetti\\MyDatabase.accdb";


    private Connection getConn() throws SQLException {
        return DriverManager.getConnection(CONN_URL);
    }
    //Connection conn = DriverManager.getConnection(CONN_URL);

    public void executeQuery(String query) {
        internalExecuteQuery(query, true);
    }

    public ResultSet executeSearchQuery(String query) {
        return internalExecuteQuery(query, false);
    }

    private ResultSet internalExecuteQuery(String query, boolean isDML) {

        Connection conn = null;

        try {
            conn = this.getConn();
            Statement statement = conn.createStatement();
            if (isDML) {
                statement.execute(query);// modification of data DML!!!!!!!
                return null;
            } else {
                return statement.executeQuery(query); // simple select
            }
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
