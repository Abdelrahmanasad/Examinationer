package org.example.labb1;

public class Member {
    private String id;
    private String name;
    private int activeLoans;

    public Member(String id, String name, int activeLoans) {
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
}
