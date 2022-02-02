/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingsystem.Models.Booking;

import java.util.Date;

/**
 *
 * @author TragicNet
 */
public class Booking {
    int id;
    String billNumber;
    Date billDate;
    int type;
    String name;
    String address;
    String mobile1;
    String mobile2;
    String email;
    String eventDetails;
    int totalRate;
    int totalDeposit;
    int status;

    public Booking() {}

    public Booking(int id, String billNumber, Date billDate, int type, String name, String address, String mobile1, String mobile2, String email, String eventDetails, int totalRate, int totalDeposit, int status) {
        this.id = id;
        this.billNumber = billNumber;
        this.billDate = billDate;
        this.type = type;
        this.name = name;
        this.address = address;
        this.mobile1 = mobile1;
        this.mobile2 = mobile2;
        this.email = email;
        this.eventDetails = eventDetails;
        this.totalRate = totalRate;
        this.totalDeposit = totalDeposit;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public int getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(int totalRate) {
        this.totalRate = totalRate;
    }

    public int getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(int totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
}
