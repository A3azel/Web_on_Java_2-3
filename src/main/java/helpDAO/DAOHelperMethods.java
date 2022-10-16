package helpDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOHelperMethods {
    public static void closeCon(AutoCloseable closeable){
        if(closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void rollback(Connection con){
        try {
            con.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
