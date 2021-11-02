package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static DataBaseConnection dataBaseConnection;
    private Connection connection;

    private DataBaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/HotelManagementSystem",
                "root","1234"
        );
    }

    public static DataBaseConnection getInstance() throws SQLException, ClassNotFoundException {
        return dataBaseConnection=
                (dataBaseConnection==null)?(new DataBaseConnection()):(dataBaseConnection);
    }

    public Connection getConnection(){
        return connection;
    }

}
