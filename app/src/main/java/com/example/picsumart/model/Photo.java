package com.example.picsumart.model;

public class Photo {
    private int id;
    private String author;
    private String linkurl;

    //constructors
    public Photo(int id, String author) {
        this.id = id;
        this.author = author;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getLinkurl() {
        return linkurl;
    }

    //setters
    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

}
