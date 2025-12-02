package com.example.vendiappfrontend;

public class Listing {
    private String title;
    private String description;
    private double price;
    private String photolink;
    private String phoneNumber;
    private String sellerName;
    private String location;
    public Listing(String title, String description, double price, String imageUrl, String phoneNumber, String sellerName, String location) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.photolink = imageUrl;
        this.phoneNumber=phoneNumber;
        this.sellerName=sellerName;
        this.location=location;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getImageUrl() { return photolink; }
    public String getPhoneNumber(){return phoneNumber; }
    public String getSellerName(){return sellerName;};
    public String getLocation(){return location;}
}
