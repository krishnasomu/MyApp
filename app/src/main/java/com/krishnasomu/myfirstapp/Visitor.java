package com.krishnasomu.myfirstapp;

/**
 * Created by ADMIN on 3/27/2018.
 */

public class Visitor {
    public String name;

    public Visitor() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Visitor(String name) {
        this.name = name;
    }
}
