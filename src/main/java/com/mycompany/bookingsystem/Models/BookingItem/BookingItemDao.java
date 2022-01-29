/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingsystem.Models.BookingItem;

import com.mycompany.bookingsystem.Dao.Dao;
import com.mycompany.bookingsystem.Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TragicNet
 */
public class BookingItemDao implements Dao<BookingItem> {

    public static String createQuery = "create table if not exists booking_item " +
            "(booking_id integer references booking(id), entity_id integer references entity(id), " +
            "quantity integer default '1')";

    private static Connection connection = DatabaseConnection.getConnection();
    
    @Override
    public int add(BookingItem bookingItem) throws SQLException {
        String query = "insert into booking_item (booking_id, entity_id, quantity) values (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(bookingItem.getBookingId()));
        preparedStatement.setString(2, String.valueOf(bookingItem.getEntityId()));
        preparedStatement.setString(3, String.valueOf(bookingItem.getQuantity()));
        preparedStatement.executeUpdate();
        return -1;
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "delete from booking_item where booking_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(id));
        preparedStatement.executeUpdate();
    }

    @Override
    public BookingItem get(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<BookingItem> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<BookingItem> select(String query) throws SQLException {
        if(connection == null) {
            return null;
        }
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<BookingItem> bookingItemList = new ArrayList<>();

        while (resultSet.next()) {
            BookingItem bookingItem = new BookingItem();
            bookingItem.setBookingId(resultSet.getInt("booking_id"));
            bookingItem.setEntityId(resultSet.getInt("entity_id"));
            bookingItem.setQuantity(resultSet.getInt("quantity"));

            bookingItemList.add(bookingItem);
        }
        return bookingItemList;
    }

    @Override
    public void update(BookingItem t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
