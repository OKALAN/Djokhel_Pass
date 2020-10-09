package com.example.diokhlpass.byt.entities;

public class Bus {


   private String Dept_town;
   private String Dest_town;
    private String date;
    private String  leaving_time;
    private int place_booked;

    public Bus(String dept_town, String dest_town, String date, String leaving_time, int place_booked) {
        Dept_town = dept_town;
        Dest_town = dest_town;
        this.date = date;
        this.leaving_time = leaving_time;
        this.place_booked = place_booked;
    }

    public String getDept_town() {
        return Dept_town;
    }

    public void setDept_town(String dept_town) {
        Dept_town = dept_town;
    }

    public String getDest_town() {
        return Dest_town;
    }

    public void setDest_town(String dest_town) {
        Dest_town = dest_town;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLeaving_time() {
        return leaving_time;
    }

    public void setLeaving_time(String leaving_time) {
        this.leaving_time = leaving_time;
    }

    public int getplace_booked() {
        return place_booked;
    }

    public void setplace_booked(int place_booked) {
        this.place_booked = place_booked;
    }
}
