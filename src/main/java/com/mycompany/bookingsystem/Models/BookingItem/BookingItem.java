/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingsystem.Models.BookingItem;

import java.util.Date;

/**
 *
 * @author TragicNet
 */
public class BookingItem {
    
    int bookingId;
    int entityId;
    int quantity;
    Date startDate;
    Date endDate;
    int bookingSlot;

    public BookingItem() {
    }

    public BookingItem(int bookingId, int entityId, int quantity, Date startDate, Date endDate, int bookingSlot) {
        this.bookingId = bookingId;
        this.entityId = entityId;
        this.quantity = quantity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookingSlot = bookingSlot;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getBookingSlot() {
        return bookingSlot;
    }

    public void setBookingSlot(int bookingSlot) {
        this.bookingSlot = bookingSlot;
    }
    
}
