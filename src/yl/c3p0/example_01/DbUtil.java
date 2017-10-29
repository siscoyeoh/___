package yl.c3p0.example_01;

import java.sql.Connection;
import java.sql.SQLException;

public class DbUtil {
    public static Connection getConnection() throws SQLException {
        DBPool pool = DBPool.getInstance();
        return pool.getConnection();
    }
}