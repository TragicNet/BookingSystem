/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingsystem.Models.BookingItem;

/**
 *
 * @author TragicNet
 */
public class BookingItem {
    
    int bookingId;
    int entityId;
    int quantity;

    public BookingItem() {
    }

    public BookingItem(int bookingId, int entityId, int quantity) {
        this.bookingId = bookingId;
        this.entityId = entityId;
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "BookingItem{" + "bookingId=" + bookingId + ", entityId=" + entityId + ", quantity=" + quantity + '}';
    }
    
}
