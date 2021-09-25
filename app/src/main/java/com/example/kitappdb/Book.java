package com.example.kitappdb;

public class Book {

    private String bookID;
    private String book_name;
    private String author;
    private String publisher;
    private Float price;
    private int quantity;
    private String description;
    public String imageName;
    public String imageURL;


    public Book() {
    }


    public Book(String book_name, String author, String publisher, Float price, int quantity, String description, String imageName, String imageURL) {

        this.book_name = book_name;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.imageName = imageName;
        this.imageURL = imageURL;
    }





    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookID() {
        return bookID;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getAuthor() {
        return author;
    }



    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
