package com.example.smashing2;

public class Board {
    private int ImgId;
    private String userName;
    private String type;
    private String date;
    private String time;
    private String x;
    private String y;
    private String level;

    public Board(int imgId, String userName, String type, String date, String time, String x,String y ,String level) {
        this.ImgId = imgId;
        this.userName = userName;
        this.type = type;
        this.date = date;
        this.time = time;
        this.x = x;
        this.y = y;
        this.level = level;
    }

    public int getImgId() {
        return ImgId;
    }

    public String getY() {return y;}

    public void setY(String y) {this.y = y;}

    public String getUserName() {
        return userName;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getX() {
        return x;
    }

    public String getLevel() {return  level;}

    public void setImgId(int imgId) {
        ImgId = imgId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setLevel(String level) {this.level = level;}
}
