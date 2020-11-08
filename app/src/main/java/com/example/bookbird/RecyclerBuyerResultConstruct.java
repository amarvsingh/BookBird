package com.example.bookbird;

public class RecyclerBuyerResultConstruct {
    private String Username;
    private String Bookname;
    private String Condition;
    private String Mrp;
    private String Price;
    private String Imageid1;
    private String Imageid2;
    private String Imageid3;
    private String Imageid4;

    public RecyclerBuyerResultConstruct() {
    }

    public RecyclerBuyerResultConstruct(String username, String bookname, String condition, String mrp, String price, String imageid1, String imageid2, String imageid3, String imageid4) {
        Username = username;
        Bookname = bookname;
        Condition = condition;
        Mrp = mrp;
        Price = price;
        Imageid1 = imageid1;
        Imageid2 = imageid2;
        Imageid3 = imageid3;
        Imageid4 = imageid4;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getBookname() {
        return Bookname;
    }

    public void setBookname(String bookname) {
        Bookname = bookname;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public String getMrp() {
        return Mrp;
    }

    public void setMrp(String mrp) {
        Mrp = mrp;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImageid1() {
        return Imageid1;
    }

    public void setImageid1(String imageid1) {
        Imageid1 = imageid1;
    }

    public String getImageid2() {
        return Imageid2;
    }

    public void setImageid2(String imageid2) {
        Imageid2 = imageid2;
    }

    public String getImageid3() {
        return Imageid3;
    }

    public void setImageid3(String imageid3) {
        Imageid3 = imageid3;
    }

    public String getImageid4() {
        return Imageid4;
    }

    public void setImageid4(String imageid4) {
        Imageid4 = imageid4;
    }
}
