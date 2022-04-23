package com.example.smashing2;

public class BoardInfo {
    public String sports;
    public String level;
    public String x;
    public String y;
    public String matchingYear;
    public String matchingMonth;
    public String matchingDate;
    public String startHour;
    public String startMin;
    public String endHour;
    public String endMin;
    public String date;
    public String name;


    public BoardInfo(String name ,String sports, String level, String date, String x, String y, String matchingYear,String matchingMonth, String matchingDate,
                      String startHour, String startMin, String endHour, String endMin) {
        this.name = name;
        this.sports = sports;
        this.level = level;
        this.date = date;
        this.x = x;
        this.y = y;
        this.matchingYear = matchingYear;
        this.startHour = startHour;
        this.startMin = startMin;
        this.endHour = endHour;
        this.endMin = endMin;
        this.matchingYear = matchingYear;
        this.matchingMonth = matchingMonth;
        this.matchingDate = matchingDate;
    }
}
