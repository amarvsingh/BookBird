package com.example.bookbird;

public class WishlistConstructor {
    private String Bookname;
    private String Branch;
    private String Sem;
    private String Subject;
    private String Price;
    private String Imageid;
    private String Username;
    private String SellerUsername;

    public WishlistConstructor() {
    }

    public WishlistConstructor(String bookname, String branch, String sem, String subject, String price, String imageid, String username, String sellerUsername) {
        Bookname = bookname;
        Branch = branch;
        Sem = sem;
        Subject = subject;
        Price = price;
        Imageid = imageid;
        Username = username;
        SellerUsername = sellerUsername;
    }

    public String getBookname() {
        return Bookname;
    }

    public void setBookname(String bookname) {
        Bookname = bookname;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getSem() {
        return Sem;
    }

    public void setSem(String sem) {
        Sem = sem;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImageid() {
        return Imageid;
    }

    public void setImageid(String imageid) {
        Imageid = imageid;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getSellerUsername() {
        return SellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        SellerUsername = sellerUsername;
    }
}