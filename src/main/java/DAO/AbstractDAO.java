package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDAO implements CustomDBManager {
    private static final String URL = "jdbc:mysql://localhost:3306/Railway_Ticket_Office_JSP";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public Connection getConnection(){
        try {
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("BAD CONNECTION");
        }
    }
}
