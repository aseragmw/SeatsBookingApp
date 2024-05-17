package com.example.cancellation_feature_seats_booking_app.data;

import com.example.cancellation_feature_seats_booking_app.domain.BookingModel;
import com.example.cancellation_feature_seats_booking_app.domain.HistoryResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("/ticket/history")
    Call<HistoryResponseModel> getHistory(@Header("Authorization") String authHeader);
    @DELETE( "/ticket/{id}")
    Call<Void>cancelTicket(@Header("Authorization") String authHeader, @Path("id") String id);
}
