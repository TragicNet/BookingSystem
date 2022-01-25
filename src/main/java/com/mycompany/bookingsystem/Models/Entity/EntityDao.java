package com.mycompany.bookingsystem.Models.Entity;

import com.mycompany.bookingsystem.Dao.Dao;
import com.mycompany.bookingsystem.Database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntityDao implements Dao<Entity> {

    private static final String path = "database/", filename = "booking.db";
    private static final String url = "jdbc:sqlite:" + path + filename;

    private static Connection connection = DatabaseConnection.getConnection();

    @Override
    public int add(Entity entity) throws SQLException {
        String query = "insert into entity(name, type, amount, ac, booking_slot, member_rate, nonmember_rate, " +
                "sale_rate, member_deposit, nonmember_deposit, sale_deposit, comments, unit_cost, unit_charge, status" +
                ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, String.valueOf(entity.getType()));
        preparedStatement.setString(3, String.valueOf(entity.getAmount()));
        preparedStatement.setString(4, String.valueOf(entity.getAc()));
        preparedStatement.setString(5, String.valueOf(entity.getBookingSlot()));
        preparedStatement.setString(6, String.valueOf(entity.getMemberRate()));
        preparedStatement.setString(7, String.valueOf(entity.getNonMemberRate()));
        preparedStatement.setString(8, String.valueOf(entity.getSaleRate()));
        preparedStatement.setString(9, String.valueOf(entity.getMemberDeposit()));
        preparedStatement.setString(10, String.valueOf(entity.getNonMemberDeposit()));
        preparedStatement.setString(11, String.valueOf(entity.getSaleDeposit()));
        preparedStatement.setString(12, entity.getComments());
        preparedStatement.setString(13, String.valueOf(entity.getUnitCost()));
        preparedStatement.setString(14, String.valueOf(entity.getUnitCharge()));
        preparedStatement.setString(15, String.valueOf(entity.getStatus()));
        int i = preparedStatement.executeUpdate();
        if(i != 0) {
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        return i;
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "update entity set status = '-1' where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(id));
        preparedStatement.executeUpdate();
    }

    @Override
    public Entity get(int id) throws SQLException {
        String query = "select * from entity where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(id));
        ResultSet resultSet = preparedStatement.executeQuery();

        Entity entity = new Entity();
        boolean found = false;

        while(resultSet.next()) {
            found = true;
            entity.setId(resultSet.getInt("id"));
            entity.setType(resultSet.getInt("type"));
            entity.setName(resultSet.getString("name"));
            entity.setBookingSlot(Boolean.valueOf(resultSet.getString("booking_slot")));
            entity.setAc(Boolean.valueOf(resultSet.getString("ac")));
            entity.setUnitCost(resultSet.getInt("unit_cost"));
            entity.setUnitCharge(resultSet.getInt("unit_charge"));
            entity.setMemberRate(resultSet.getInt("member_rate"));
            entity.setAmount(resultSet.getInt("amount"));
            entity.setNonMemberRate(resultSet.getInt("nonMember_rate"));
            entity.setSaleRate(resultSet.getInt("sale_rate"));
            entity.setMemberDeposit(resultSet.getInt("member_deposit"));
            entity.setNonMemberDeposit(resultSet.getInt("nonmember_deposit"));
            entity.setSaleDeposit(resultSet.getInt("sale_deposit"));
            entity.setStatus(resultSet.getInt("status"));
            entity.setComments(resultSet.getString("comments"));
            
        }

        if (found) {
            return entity;
        } else
            return null;
    }

    @Override
    public List<Entity> getAll() throws SQLException {
        String query = "select * from entity where status <> -1";
        return select(query);
    }

    @Override
    public List<Entity> select(String query) throws SQLException {
        if(connection == null) {
            return null;
        }
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Entity> entityList = new ArrayList<>();

        while (resultSet.next()) {
            Entity entity = new Entity();
            entity.setId(resultSet.getInt("id"));
            entity.setType(resultSet.getInt("type"));
            entity.setName(resultSet.getString("name"));
            entity.setBookingSlot(Boolean.valueOf(resultSet.getString("booking_slot")));
            entity.setAc(Boolean.valueOf(resultSet.getString("ac")));
            entity.setUnitCost(resultSet.getInt("unit_cost"));
            entity.setUnitCharge(resultSet.getInt("unit_charge"));
            entity.setMemberRate(resultSet.getInt("member_rate"));
            entity.setAmount(resultSet.getInt("amount"));
            entity.setNonMemberRate(resultSet.getInt("nonMember_rate"));
            entity.setSaleRate(resultSet.getInt("sale_rate"));
            entity.setMemberDeposit(resultSet.getInt("member_deposit"));
            entity.setNonMemberDeposit(resultSet.getInt("nonmember_deposit"));
            entity.setSaleDeposit(resultSet.getInt("sale_deposit"));
            entity.setStatus(resultSet.getInt("status"));
            entity.setComments(resultSet.getString("comments"));

            entityList.add(entity);

//            System.out.println(entity);
        }
        return entityList;
    }

    @Override
    public void update(Entity entity) throws SQLException {
        String query = "update entity set name = ?, type = ?, amount = ?, ac = ?, booking_slot = ?, member_rate = " +
                "?, nonmember_rate = ?, sale_rate = ?, member_deposit = ?, nonmember_deposit = ?, sale_deposit = ?, comments " +
                "= ?, unit_cost = ?, unit_charge = ?, status = ? where id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, String.valueOf(entity.getType()));
        preparedStatement.setString(3, String.valueOf(entity.getAmount()));
        preparedStatement.setString(4, String.valueOf(entity.getAc()));
        preparedStatement.setString(5, String.valueOf(entity.getBookingSlot()));
        preparedStatement.setString(6, String.valueOf(entity.getMemberRate()));
        preparedStatement.setString(7, String.valueOf(entity.getNonMemberRate()));
        preparedStatement.setString(8, String.valueOf(entity.getSaleRate()));
        preparedStatement.setString(9, String.valueOf(entity.getMemberDeposit()));
        preparedStatement.setString(10, String.valueOf(entity.getNonMemberDeposit()));
        preparedStatement.setString(11, String.valueOf(entity.getSaleDeposit()));
        preparedStatement.setString(12, entity.getComments());
        preparedStatement.setString(13, String.valueOf(entity.getUnitCost()));
        preparedStatement.setString(14, String.valueOf(entity.getUnitCharge()));
        preparedStatement.setString(15, String.valueOf(entity.getStatus()));
        preparedStatement.setString(16, String.valueOf(entity.getId()));
        preparedStatement.executeUpdate();
    }
}
