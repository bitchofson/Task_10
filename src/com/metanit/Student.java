package com.metanit;

public class Student {

    String name;
    String surname;
    String patronymic;

    int mark;
    int markRusLang;
    int markMath;
    int markPhysics;


    public Student(String name, String surname, String patronymic, int markRusLang, int markMath, int markPhysics) {

        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;


        this.markRusLang = markRusLang;
        this.markMath = markMath;
        this.markPhysics = markPhysics;

    }

}
