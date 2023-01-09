package com.mycompany.bookingsystem.Models.Entity;

public class Entity {
    int id;
    public String name;
    int type;
    int amount;
    Boolean ac;
    Boolean bookingSlot;
    int memberRate;
    int nonMemberRate;
    int saleRate;
    int memberDeposit;
    int nonMemberDeposit;
    int saleDeposit;
    String comments;
    int unitCost;
    int unitCharge;
    int status;
    

    public Entity() {}

    public Entity(int id, String name, int type, int amount, Boolean ac, Boolean bookingSlot, int normalRate, int otherRate, int saleRate, int normalDeposit, int otherDeposit, int saleDeposit, String comments, int unitCost, int unitCharge, int status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.ac = ac;
        this.bookingSlot = bookingSlot;
        this.memberRate = normalRate;
        this.nonMemberRate = otherRate;
        this.saleRate = saleRate;
        this.memberDeposit = normalDeposit;
        this.nonMemberDeposit = otherDeposit;
        this.saleDeposit = saleDeposit;
        this.comments = comments;
        this.unitCost = unitCost;
        this.unitCharge = unitCharge;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Boolean getAc() {
        return ac;
    }

    public void setAc(Boolean ac) {
        this.ac = ac;
    }

    public Boolean getBookingSlot() {
        return bookingSlot;
    }

    public void setBookingSlot(Boolean bookingSlot) {
        this.bookingSlot = bookingSlot;
    }

    public int getMemberRate() {
        return memberRate;
    }

    public void setMemberRate(int memberRate) {
        this.memberRate = memberRate;
    }

    public int getNonMemberRate() {
        return nonMemberRate;
    }

    public void setNonMemberRate(int nonMemberRate) {
        this.nonMemberRate = nonMemberRate;
    }

    public int getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(int saleRate) {
        this.saleRate = saleRate;
    }

    public int getMemberDeposit() {
        return memberDeposit;
    }

    public void setMemberDeposit(int memberDeposit) {
        this.memberDeposit = memberDeposit;
    }

    public int getNonMemberDeposit() {
        return nonMemberDeposit;
    }

    public void setNonMemberDeposit(int nonMemberDeposit) {
        this.nonMemberDeposit = nonMemberDeposit;
    }

    public int getSaleDeposit() {
        return saleDeposit;
    }

    public void setSaleDeposit(int saleDeposit) {
        this.saleDeposit = saleDeposit;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(int unitCost) {
        this.unitCost = unitCost;
    }

    public int getUnitCharge() {
        return unitCharge;
    }

    public void setUnitCharge(int unitCharge) {
        this.unitCharge = unitCharge;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Entity{" + "id=" + id + ", name=" + name + ", type=" + type + ", amount=" + amount + ", ac=" + ac + ", bookingSlot=" + bookingSlot + ", memberRate=" + memberRate + ", nonMemberRate=" + nonMemberRate + ", saleRate=" + saleRate + ", memberDeposit=" + memberDeposit + ", nonMemberDeposit=" + nonMemberDeposit + ", saleDeposit=" + saleDeposit + ", comments=" + comments + ", unitCost=" + unitCost + ", unitCharge=" + unitCharge + ", status=" + status + '}';
    }
    
}
