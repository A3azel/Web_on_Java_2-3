package DAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface CustomDBManager {
    Connection getConnection() throws SQLException;
}
