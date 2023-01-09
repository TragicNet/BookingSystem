/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingsystem.Models.Booking;

import com.mycompany.bookingsystem.Dao.Dao;
import com.mycompany.bookingsystem.Database.DatabaseConnection;
import static com.mycompany.bookingsystem.Helper.Helper.dateFormat;
import static com.mycompany.bookingsystem.Helper.Helper.sqlDateFormat;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TragicNet
 */
public class BookingDao implements Dao<Booking> {
    
    public static String createQuery = "create table if not exists booking (id integer primary key not null, " + 
            "bill_number text not null, bill_date Date, type integer, name text not null, address text, " +
            "mobile1 text, mobile2 text, email text, eventDetails text, " + 
            "total_rate integer, total_deposit integer, status integer default '0')";

    private static Connection connection = DatabaseConnection.getConnection();
    
    @Override
    public int add(Booking booking) throws SQLException {
        try {
            booking.setBillDate(new Date(dateFormat.parse(dateFormat.format(booking.getBillDate())).getTime()));
        } catch (ParseException ex) {
            Logger.getLogger(BookingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "insert into booking (bill_number, bill_date, type, name, address, mobile1, mobile2, email, " + 
                "eventDetails, total_rate, total_deposit, status) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(booking.getBillNumber()));
        preparedStatement.setString(2, String.valueOf(booking.getBillDate()));
        preparedStatement.setString(3, String.valueOf(booking.getType()));
        preparedStatement.setString(4, String.valueOf(booking.getName()));
        preparedStatement.setString(5, String.valueOf(booking.getAddress()));
        preparedStatement.setString(6, String.valueOf(booking.getMobile1()));
        preparedStatement.setString(7, String.valueOf(booking.getMobile2()));
        preparedStatement.setString(8, String.valueOf(booking.getEmail()));
        preparedStatement.setString(9, String.valueOf(booking.getEventDetails()));
        preparedStatement.setString(10, String.valueOf(booking.getTotalRate()));
        preparedStatement.setString(11, String.valueOf(booking.getTotalDeposit()));
        preparedStatement.setString(12, String.valueOf(booking.getStatus()));
        int i = preparedStatement.executeUpdate();
        if(i != 0) {
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    booking.setId((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creation failed, no ID obtained.");
                }
            }
        }
        
        return i;
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "update booking set status = '-1' where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(id));
        preparedStatement.executeUpdate();
    }

    @Override
    public Booking get(int id) throws SQLException {
        String query = "select * from booking where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(id));
        ResultSet resultSet = preparedStatement.executeQuery();

        Booking booking = new Booking();
        boolean found = false;

            while(resultSet.next()) {
            found = true;
            booking.setId(resultSet.getInt("id"));
            booking.setBillNumber(resultSet.getString("bill_number"));
            try {
                booking.setBillDate(sqlDateFormat.parse(resultSet.getString("bill_date")));
            } catch (ParseException ex) {
                Logger.getLogger(BookingDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            booking.setType(resultSet.getInt("type"));
            booking.setName(resultSet.getString("name"));
            booking.setAddress(resultSet.getString("address"));
            booking.setMobile1(resultSet.getString("mobile1"));
            booking.setMobile2(resultSet.getString("mobile2"));
            booking.setEmail(resultSet.getString("email"));
            booking.setEventDetails(resultSet.getString("eventDetails"));
            booking.setTotalRate(resultSet.getInt("total_rate"));
            booking.setTotalDeposit(resultSet.getInt("total_deposit"));
            booking.setStatus(resultSet.getInt("status"));
        }

        if (found) {
            return booking;
        } else
            return null;
    }

    @Override
    public List<Booking> getAll() throws SQLException {
        String query = "select * from booking where status <> -1";
        return select(query);
    }

    @Override
    public List<Booking> select(String query) throws SQLException {
        if(connection == null) {
            return null;
        }
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Booking> bookingList = new ArrayList<>();

        while (resultSet.next()) {
            Booking booking = new Booking();
            booking.setId(resultSet.getInt("id"));
            booking.setBillNumber(resultSet.getString("bill_number"));
            try {
                booking.setBillDate(sqlDateFormat.parse(resultSet.getString("bill_date")));
            } catch (ParseException ex) {
                Logger.getLogger(BookingDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            booking.setType(resultSet.getInt("type"));
            booking.setName(resultSet.getString("name"));
            booking.setAddress(resultSet.getString("address"));
            booking.setMobile1(resultSet.getString("mobile1"));
            booking.setMobile2(resultSet.getString("mobile2"));
            booking.setEmail(resultSet.getString("email"));
            booking.setEventDetails(resultSet.getString("eventDetails"));
            booking.setTotalRate(resultSet.getInt("total_rate"));
            booking.setTotalDeposit(resultSet.getInt("total_deposit"));
            booking.setStatus(resultSet.getInt("status"));

            bookingList.add(booking);
        }
        return bookingList;
    }

    @Override
    public void update(Booking booking) throws SQLException {
        try {
            booking.setBillDate(new Date(dateFormat.parse(dateFormat.format(booking.getBillDate())).getTime()));
        } catch (ParseException ex) {
            Logger.getLogger(BookingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "update booking set bill_number = ?, bill_date = ?, type = ?, name = ?, address = ?, " +
                "mobile1 = ?, mobile2 = ?, email = ?, eventDetails = ?, " +
                "total_rate = ?, total_deposit = ?, status = ? where id = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(booking.getBillNumber()));
        preparedStatement.setString(2, String.valueOf(booking.getBillDate()));
        preparedStatement.setString(3, String.valueOf(booking.getType()));
        preparedStatement.setString(4, String.valueOf(booking.getName()));
        preparedStatement.setString(5, String.valueOf(booking.getAddress()));
        preparedStatement.setString(6, String.valueOf(booking.getMobile1()));
        preparedStatement.setString(7, String.valueOf(booking.getMobile2()));
        preparedStatement.setString(8, String.valueOf(booking.getEmail()));
        preparedStatement.setString(9, String.valueOf(booking.getEventDetails()));
        preparedStatement.setString(10, String.valueOf(booking.getTotalRate()));
        preparedStatement.setString(11, String.valueOf(booking.getTotalDeposit()));
        preparedStatement.setString(12, String.valueOf(booking.getStatus()));
        preparedStatement.setString(13, String.valueOf(booking.getId()));
        preparedStatement.executeUpdate();
    }
    
    
    
}
