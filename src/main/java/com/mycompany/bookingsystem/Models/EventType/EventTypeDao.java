/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingsystem.Models.EventType;

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
public class EventTypeDao implements Dao<EventType> {
    
    public static String createQuery = "create table if not exists event_type (id integer primary key not null, " + 
            "name text not null )";

    private static Connection connection = DatabaseConnection.getConnection();

    @Override
    public int add(EventType eventType) throws SQLException {
        String query = "insert into event_type (name) " +
                "values (?)";
                
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(eventType.getName()));
        int i = preparedStatement.executeUpdate();
        if(i != 0) {
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    eventType.setId((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creation failed, no ID obtained.");
                }
            }
        }
        
        return i;
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "delete from event_type where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(id));
        preparedStatement.executeUpdate();
    }

    @Override
    public EventType get(int id) throws SQLException {
        String query = "select * from event_type where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(id));
        ResultSet resultSet = preparedStatement.executeQuery();

        EventType eventType = new EventType();
        boolean found = false;

            while(resultSet.next()) {
            found = true;
            eventType.setId(resultSet.getInt("id"));
            eventType.setName(resultSet.getString("name"));
        }

        if (found) {
            return eventType;
        } else
            return null;
    }

    @Override
    public List<EventType> getAll() throws SQLException {
        String query = "select * from event_type";
        return select(query);
    }

    @Override
    public List<EventType> select(String query) throws SQLException {
        if(connection == null) {
            return null;
        }
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<EventType> eventTypeList = new ArrayList<>();

        while (resultSet.next()) {
            EventType eventType = new EventType();
            eventType.setId(resultSet.getInt("id"));
            eventType.setName(resultSet.getString("name"));

            eventTypeList.add(eventType);
        }
        return eventTypeList;
    }

    @Override
    public void update(EventType eventType) throws SQLException {
        String query = "update event_type set name = ? where id = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(eventType.getName()));
        preparedStatement.setString(2, String.valueOf(eventType.getId()));
        preparedStatement.executeUpdate();
    }
        
}
