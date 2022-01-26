package com.mycompany.bookingsystem.Database;

import java.io.File;
import java.sql.*;

public abstract class DatabaseConnection {
    
    private static final String path = "database/", filename = "booking.db";
    private static final String url = "jdbc:sqlite:" + path + filename;

    private static Connection connection = null;
    
    static {
        init();
    }

    public static void init() {
        checkPath();
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    private static void checkPath() {
        File dir = new File(path);
        if(!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static void createTables() throws SQLException {
        Statement statement = connection.createStatement();

        String entityTableQuery = "create table if not exists entity (id integer primary key not null, name text not " +
                "null, type integer, amount integer, ac boolean default null, booking_slot integer default null, member_rate integer, " +
                "nonmember_rate, sale_rate integer, member_deposit integer, nonmember_deposit, sale_deposit integer, " +
                "comments text, unit_cost integer default null, unit_charge integer default null, status integer default '1');";
        statement.execute(entityTableQuery);
    }
    
}
