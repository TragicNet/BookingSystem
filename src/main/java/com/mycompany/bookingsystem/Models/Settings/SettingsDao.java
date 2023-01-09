/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingsystem.Models.Settings;

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
public class SettingsDao implements Dao<Settings> {
    
    public static String createQuery = "create table if not exists settings (id integer primary key not null, " + 
            "name text not null, value text not null, status integer default '0')";

    private static Connection connection = DatabaseConnection.getConnection();

    @Override
    public int add(Settings settings) throws SQLException {
        String query = "insert into settings (name, value, status) " +
                "values (?, ?, ?)";
                
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(settings.getName()));
        preparedStatement.setString(2, String.valueOf(settings.getValue()));
        preparedStatement.setString(3, String.valueOf(settings.getStatus()));
        int i = preparedStatement.executeUpdate();
        if(i != 0) {
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    settings.setId((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creation failed, no ID obtained.");
                }
            }
        }
        
        return i;
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "update settings set status = '-1' where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(id));
        preparedStatement.executeUpdate();
    }

    @Override
    public Settings get(int id) throws SQLException {
        String query = "select * from settings where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(id));
        ResultSet resultSet = preparedStatement.executeQuery();

        Settings settings = new Settings();
        boolean found = false;

            while(resultSet.next()) {
            found = true;
            settings.setId(resultSet.getInt("id"));
            settings.setName(resultSet.getString("name"));
            settings.setValue(resultSet.getString("value"));
            settings.setStatus(resultSet.getInt("status"));
        }

        if (found) {
            return settings;
        } else
            return null;
    }

    @Override
    public List<Settings> getAll() throws SQLException {
        String query = "select * from settings where status <> -1";
        return select(query);
    }

    @Override
    public List<Settings> select(String query) throws SQLException {
        if(connection == null) {
            return null;
        }
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Settings> settingsList = new ArrayList<>();

        while (resultSet.next()) {
            Settings settings = new Settings();
            settings.setId(resultSet.getInt("id"));
            settings.setName(resultSet.getString("name"));
            settings.setValue(resultSet.getString("value"));
            settings.setStatus(resultSet.getInt("status"));

            settingsList.add(settings);
        }
        return settingsList;
    }

    @Override
    public void update(Settings settings) throws SQLException {
        String query = "update settings set name = ?, value = ?, status = ? where id = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(settings.getName()));
        preparedStatement.setString(2, String.valueOf(settings.getValue()));
        preparedStatement.setString(3, String.valueOf(settings.getStatus()));
        preparedStatement.setString(4, String.valueOf(settings.getId()));
        preparedStatement.executeUpdate();
    }
        
}
