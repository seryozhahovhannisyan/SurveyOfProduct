package am.pm.util;

import am.pm.dataaccess.exception.DatabaseException;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Artur on 4/2/2016.
 */
public class ConnectionPool {

    public static Connection connect() throws DatabaseException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/pm", "root", "");
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public Connection connectSecond() throws DatabaseException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/pm");
            return ds.getConnection();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }

    }
}
