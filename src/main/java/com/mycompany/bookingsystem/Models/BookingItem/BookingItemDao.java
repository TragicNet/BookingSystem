/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingsystem.Models.BookingItem;

import com.mycompany.bookingsystem.Dao.Dao;
import com.mycompany.bookingsystem.Database.DatabaseConnection;
import com.mycompany.bookingsystem.Helper.Helper;
import static com.mycompany.bookingsystem.Helper.Helper.dateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TragicNet
 */
public class BookingItemDao implements Dao<BookingItem> {

    int bookingId;
    
    public static String createQuery = "create table if not exists booking_item " +
            "(booking_id integer references booking(id), entity_id integer references entity(id), " +
            "quantity integer default '1', start_date Date, end_date Date, bookingSlot integer)";

    private static Connection connection = DatabaseConnection.getConnection();

    public BookingItemDao(int bookingId) {
        this.bookingId = bookingId;
    }
    
    @Override
    public int add(BookingItem bookingItem) throws SQLException {
        
        try {
            bookingItem.setStartDate(new java.sql.Date(dateFormat.parse(dateFormat.format(bookingItem.getStartDate())).getTime()));
            bookingItem.setEndDate(new java.sql.Date(dateFormat.parse(dateFormat.format(bookingItem.getEndDate())).getTime()));
        } catch (ParseException ex) {
            Logger.getLogger(BookingItemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "insert into booking_item (booking_id, entity_id, quantity, start_date, end_date, bookingSlot) values (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(bookingItem.getBookingId()));
        preparedStatement.setString(2, String.valueOf(bookingItem.getEntityId()));
        preparedStatement.setString(3, String.valueOf(bookingItem.getQuantity()));
        preparedStatement.setString(4, String.valueOf(bookingItem.getStartDate()));
        preparedStatement.setString(5, String.valueOf(bookingItem.getEndDate()));
        preparedStatement.setString(6, String.valueOf(bookingItem.getBookingSlot()));
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
        String query = "select * from booking_item where booking_id = " + bookingId;
        
        return select(query);
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
            try {
                bookingItem.setStartDate(new Date());
                bookingItem.setEndDate(new Date());
                bookingItem.setStartDate(Helper.sqlDateFormat.parse(resultSet.getString("start_date")));
                bookingItem.setEndDate(Helper.sqlDateFormat.parse(resultSet.getString("end_date")));
            } catch (ParseException ex) {
                Logger.getLogger(BookingItemDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            bookingItem.setBookingSlot(resultSet.getInt("bookingSlot"));

            bookingItemList.add(bookingItem);
        }
        return bookingItemList;
    }

    @Override
    public void update(BookingItem bookingItem) throws SQLException {

        try {
            bookingItem.setStartDate(new java.sql.Date(dateFormat.parse(dateFormat.format(bookingItem.getStartDate())).getTime()));
            bookingItem.setEndDate(new java.sql.Date(dateFormat.parse(dateFormat.format(bookingItem.getEndDate())).getTime()));
        } catch (ParseException ex) {
            Logger.getLogger(BookingItemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "update booking_item set booking_id = ?, entity_id = ?, quantity = ?, start_date = ?, end_date = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(bookingItem.getBookingId()));
        preparedStatement.setString(2, String.valueOf(bookingItem.getEntityId()));
        preparedStatement.setString(3, String.valueOf(bookingItem.getQuantity()));
        preparedStatement.setString(4, String.valueOf(bookingItem.getStartDate()));
        preparedStatement.setString(5, String.valueOf(bookingItem.getEndDate()));
        preparedStatement.setString(6, String.valueOf(bookingItem.getBookingSlot()));
        preparedStatement.executeUpdate();
    }
    
}
