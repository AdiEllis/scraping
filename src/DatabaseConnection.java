import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection dbIsntance = null;
    private static Connection con;

    private DatabaseConnection() {
    }

    public static DatabaseConnection getInstance() {
        if (dbIsntance == null) {
            dbIsntance = new DatabaseConnection();
        }
        return dbIsntance;
    }

    public Connection getConnection() throws Exception {
        if (con == null || con.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/crypto1?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=IST";
            String driver = "com.mysql.cj.jdbc.Driver";
            String userName = "root";
            String password = "1234";

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, userName, password);
        }
        return con;
    }

    public void closeConnection(Connection conn) {
        try {
            if (con != null || !con.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
        }
    }
}