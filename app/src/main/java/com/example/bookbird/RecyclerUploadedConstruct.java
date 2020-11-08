package com.example.bookbird;

public class RecyclerUploadedConstruct {
    private String Name;
    private String Condition;
    private String Mrp;
    private String Price;
    private String Imageid;
    private String Branch;
    private String Sem;
    private String Subject;
    private String Username;


    public RecyclerUploadedConstruct() {
    }

    public RecyclerUploadedConstruct(String name, String condition, String mrp, String price, String imageid, String branch, String sem, String subject, String username) {
        Name = name;
        Condition = condition;
        Mrp = mrp;
        Price = price;
        Imageid = imageid;
        Branch = branch;
        Sem = sem;
        Subject = subject;
        Username = username;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public String getImageid() {
        return Imageid;
    }

    public void setImageid(String imageid) {
        Imageid = imageid;
    }
}
