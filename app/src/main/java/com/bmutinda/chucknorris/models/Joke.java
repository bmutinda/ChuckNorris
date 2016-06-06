package com.bmutinda.chucknorris.models;

import com.orm.SugarRecord;

import org.json.JSONObject;

public class Joke extends SugarRecord{

    int remoteId;
    String joke;
    String category;

    public Joke(){
    }

    public int getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(int remoteId) {
        this.remoteId = remoteId;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString(){
        return "remoteId ="+this.remoteId+", joke="+this.joke+", category="+this.category;
    }

    public static Joke saveFromJson( JSONObject jsonObject ){
        Joke joke = createFromJson(jsonObject);
        if ( joke != null  ){
            joke.save();
        }
        return joke;
    }

    public static Joke createFromJson(JSONObject jsonObject){
        if ( jsonObject == null ){
            return null;
        }
        Joke joke = new Joke();
        joke.setRemoteId(jsonObject.optInt("id"));
        joke.setCategory(jsonObject.optString("categories"));
        joke.setJoke(jsonObject.optString("joke"));
        return joke;
    }
}