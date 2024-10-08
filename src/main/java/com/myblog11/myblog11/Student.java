package com.myblog11.myblog11;

public class Student {
    private long ID;
    private String Name;

    private String Address;
    private String email;


    public long getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return Address;
    }

    public Student(long ID, String Name, String Address, String email) {
        this.ID = ID;
        this.Address=Address;
        this.email=email;
        this.Name=Name;
    }
}
