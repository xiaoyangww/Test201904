package com.example.jsjx.myapplication;

public class Student {
    private String banji;
    private String name;
    private int age;
    public Student(String banji, String name, int age) {
        this.banji = banji;
        this.name = name;
        this.age = age;
    }

    public String getBanji() {
        return banji;
    }

    public void setBanji(String banji) {
        this.banji = banji;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
