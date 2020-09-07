package com.example.diokhlpass.entities;

import com.orm.SugarRecord;

public class Book_ticket extends SugarRecord {
    private Long id;
    private String departure_point;
    private String destination;
    private String number_of_tickets;
    private String date;
    private String time_travel;
    private String number_of_seat;
    private String price;

    public Book_ticket(Long id, String departure_point,
                       String destination, String number_of_tickets, String date, String time_travel
            , String number_of_seat, String price) {
        this.id = id;
        this.departure_point = departure_point;
        this.destination = destination;
        this.number_of_tickets = number_of_tickets;
        this.date = date;
        this.time_travel = time_travel;
        this.number_of_seat = number_of_seat;
        this.price = price;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDeparture_point() {
        return departure_point;
    }

    public void setDeparture_point(String departure_point) {
        this.departure_point = departure_point;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getNumber_of_tickets() {
        return number_of_tickets;
    }

    public void setNumber_of_tickets(String number_of_tickets) {
        this.number_of_tickets = number_of_tickets;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime_travel() {
        return time_travel;
    }

    public void setTime_travel(String time_travel) {
        this.time_travel = time_travel;
    }

    public String getNumber_of_seat() {
        return number_of_seat;
    }

    public void setNumber_of_seat(String number_of_seat) {
        this.number_of_seat = number_of_seat;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
