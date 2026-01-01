package com.example.hall_management_system;

public class HallBillMonth {

    private int id;
    private String month;
    private int amount;

    public HallBillMonth(String month, int amount) {
        this.month = month;
        this.amount = amount;
    }

    public HallBillMonth(int id, String month, int amount) {
        this.id = id;
        this.month = month;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getMonth() {
        return month;
    }

    public int getAmount() {
        return amount;
    }
}
