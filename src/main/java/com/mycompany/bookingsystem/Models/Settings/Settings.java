/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingsystem.Models.Settings;

/**
 *
 * @author TragicNet
 */
public class Settings {
    int id;
    String name;
    String value;
    int status;

    public Settings() {}
    
    public Settings(int id, String name, String value, int status) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Settings{" + "id=" + id + ", name=" + name + ", value=" + value + ", status=" + status + '}';
    }
    
}
