package com.example.diokhlpass.entities;

import com.orm.SugarRecord;

public class Cancel_ticket extends SugarRecord {
    private Long id;
    private String ca_point;
    private String cad_estination;
    private String ca_tickets;
    private String ca_date;
    private String ca_timetravel;
    private String ca_numberofseat;
    private String ca_price;

    public Cancel_ticket(Long id, String ca_point, String cad_estination,
                         String ca_tickets, String ca_date, String ca_timetravel, String ca_numberofseat, String ca_price) {
        this.id = id;
        this.ca_point = ca_point;
        this.cad_estination = cad_estination;
        this.ca_tickets = ca_tickets;
        this.ca_date = ca_date;
        this.ca_timetravel = ca_timetravel;
        this.ca_numberofseat = ca_numberofseat;
        this.ca_price = ca_price;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCa_point() {
        return ca_point;
    }

    public void setCa_point(String ca_point) {
        this.ca_point = ca_point;
    }

    public String getCad_estination() {
        return cad_estination;
    }

    public void setCad_estination(String cad_estination) {
        this.cad_estination = cad_estination;
    }

    public String getCa_tickets() {
        return ca_tickets;
    }

    public void setCa_tickets(String ca_tickets) {
        this.ca_tickets = ca_tickets;
    }

    public String getCa_date() {
        return ca_date;
    }

    public void setCa_date(String ca_date) {
        this.ca_date = ca_date;
    }

    public String getCa_timetravel() {
        return ca_timetravel;
    }

    public void setCa_timetravel(String ca_timetravel) {
        this.ca_timetravel = ca_timetravel;
    }

    public String getCa_numberofseat() {
        return ca_numberofseat;
    }

    public void setCa_numberofseat(String ca_numberofseat) {
        this.ca_numberofseat = ca_numberofseat;
    }

    public String getCa_price() {
        return ca_price;
    }

    public void setCa_price(String ca_price) {
        this.ca_price = ca_price;
    }
}
