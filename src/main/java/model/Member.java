package model;

import java.time.LocalDate;

public class Member {

    private int id;
    private String name;
    private int age;
    private LocalDate birthDate;

    public Member(int id, String name, int age, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }
}
