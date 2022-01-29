package com.mycompany.bookingsystem.Database;

import com.mycompany.bookingsystem.Models.Booking.BookingDao;
import com.mycompany.bookingsystem.Models.BookingItem.BookingItemDao;
import com.mycompany.bookingsystem.Models.Entity.EntityDao;
import java.io.File;
import java.sql.*;

public abstract class DatabaseConnection {
    
    private static final String PATH = "database/", FILENAME = "booking.db";
    private static final String URL = "jdbc:sqlite:" + PATH + FILENAME;

    private static Connection connection = null;
    
    static {
        init();
    }

    public static void init() {
        checkPath();
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);
            createTables();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    private static void checkPath() {
        File dir = new File(PATH);
        if(!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static void createTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(EntityDao.createQuery);
        statement.execute(BookingDao.createQuery);
        statement.execute(BookingItemDao.createQuery);
    }
    
}
