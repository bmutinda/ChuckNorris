package com.bmutinda.chucknorris.models;

import com.orm.SugarRecord;

public class Category extends SugarRecord {

    String name;

    public Category(){
    }

    public Category( String name ){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }

}