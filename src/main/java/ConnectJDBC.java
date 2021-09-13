import java.sql.*;

public class ConnectJDBC {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "Password123";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/sakila";
    Connection conn = null;

    public Connection connectDB() throws SQLException {
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    public void disconnectDB() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}