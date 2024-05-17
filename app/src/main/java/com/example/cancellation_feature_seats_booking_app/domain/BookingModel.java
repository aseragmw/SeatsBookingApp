package com.example.cancellation_feature_seats_booking_app.domain;

import java.util.List;

public class BookingModel {
    private String ticket_id,trip_id,boarding_station,destination_station,trip_start_date,trip_end_date;
    private int tickets_count,total_price;
    private List<String> tickets_ids;


    public List<String> getTickets_ids() {
        return tickets_ids;
    }

    public void setTickets_ids(List<String> tickets_ids) {
        this.tickets_ids = tickets_ids;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getBoarding_station() {
        return boarding_station;
    }

    public void setBoarding_station(String boarding_station) {
        this.boarding_station = boarding_station;
    }

    public String getDestination_station() {
        return destination_station;
    }

    public void setDestination_station(String destination_station) {
        this.destination_station = destination_station;
    }

    public String getTrip_start_date() {
        return trip_start_date;
    }

    public void setTrip_start_date(String trip_start_date) {
        this.trip_start_date = trip_start_date;
    }

    public String getTrip_end_date() {
        return trip_end_date;
    }

    public void setTrip_end_date(String trip_end_date) {
        this.trip_end_date = trip_end_date;
    }

    public int getTickets_count() {
        return tickets_count;
    }

    public void setTickets_count(int tickets_count) {
        this.tickets_count = tickets_count;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public BookingModel(String ticket_id, String trip_id, String boarding_station, String destination_station, String trip_start_date, String trip_end_date, int tickets_count, int total_price, List<String> tickets_ids) {
        this.ticket_id = ticket_id;
        this.trip_id = trip_id;
        this.boarding_station = boarding_station;
        this.destination_station = destination_station;
        this.trip_start_date = trip_start_date;
        this.trip_end_date = trip_end_date;
        this.tickets_count = tickets_count;
        this.total_price = total_price;
        this.tickets_ids = tickets_ids;
    }
}
