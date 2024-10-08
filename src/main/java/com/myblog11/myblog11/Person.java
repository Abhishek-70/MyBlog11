package com.myblog11.myblog11;

public class Person {
   private  String Name;
   private String Gender;

    public String getName() {
        return Name;
    }

    public String getGender() {
        return Gender;
    }

    public int getAge() {
        return Age;
    }

       private int Age;

    public Person(String name,String gender,int age) {
        Name = name;
        Gender= gender;
        Age=age;
    }
}
