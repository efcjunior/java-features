package model;

import java.time.LocalDate;

public class Member {

    private long id;
    private String name;
    private int age;

    public Member(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
}
