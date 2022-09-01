package com.example.bookapp.entity;

import java.io.Serializable;

public class Book implements Serializable {

    private int imageId;
    private String name;
    private String type;
    private String readRecord;
    private String size;
    private String author;

    public Book() {}

    public Book(int imageId, String name, String type, String readRecord, String size, String author) {
        this.imageId = imageId;
        this.name = name;
        this.type = type;
        this.readRecord = readRecord;
        this.size = size;
        this.author = author;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setReadRecord(String readRecord) {
        this.readRecord = readRecord;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getReadRecord() {
        return readRecord;
    }

    public String getSize() {
        return size;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "imageId=" + imageId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", readRecord='" + readRecord + '\'' +
                ", size='" + size + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
