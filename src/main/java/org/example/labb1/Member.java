package org.example.labb1;

public class Member {
    private String id;
    private String name;
    private int activeLoans;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.activeLoans = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getActiveLoans() {
        return activeLoans;
    }

    public void borrowBook() {
        activeLoans++;
    }

    public void returnBook() {
        if (activeLoans > 0) {
            activeLoans--;
        }
    }


    public boolean canBorrow() {
        return activeLoans < 3;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Namn: " + name + " | Aktiva lån: " + activeLoans;
    }
}
