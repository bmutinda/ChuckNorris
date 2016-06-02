package com.bmutinda.chucknorris.models;

public class Category {

    String name;

    public Category(){
    }

    public Category( String name ){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}