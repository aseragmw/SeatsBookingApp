package com.example.cancellation_feature_seats_booking_app.domain;

import java.util.List;

public class HistoryResponseModel {

     private String message;
    private List<BookingModel> data;

    public HistoryResponseModel(String message, List<BookingModel> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BookingModel> getData() {
        return data;
    }

    public void setData(List<BookingModel> data) {
        this.data = data;
    }
}
