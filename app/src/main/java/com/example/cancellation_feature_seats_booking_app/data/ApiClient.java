package com.example.cancellation_feature_seats_booking_app.data;

import com.example.cancellation_feature_seats_booking_app.domain.BookingModel;
import com.example.cancellation_feature_seats_booking_app.domain.HistoryResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://seated-booking.onrender.com";
    private ApiInterface apiInterface;
    private static ApiClient instance;
    public static synchronized ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }
    private ApiClient() {
        apiInterface = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(ApiInterface.class);
    }

    public Call<HistoryResponseModel> getHistory(){
        //TODO replace with user's cached token
        return apiInterface.getHistory("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2NjQ2ODc5ZDcxZWY1YTk0ODk5OWFiMmUiLCJvcmdJZCI6IjY2MzdjYmJlOWJlMTBmOGQzODZiYWZiZiIsInVzZXJSb2xlIjoiY29uc3VtZXIiLCJpYXQiOjE3MTU4OTgzNDR9.pHpMvZNsdbhSKZEQjuVvOSMfZ8Sskfii__ovNt3qjdQ");
    }

    public Call<Void> cancelTicket(String ticketId){
        //TODO replace with user's cached token
        return apiInterface.cancelTicket("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2NjQ2ODc5ZDcxZWY1YTk0ODk5OWFiMmUiLCJvcmdJZCI6IjY2MzdjYmJlOWJlMTBmOGQzODZiYWZiZiIsInVzZXJSb2xlIjoiY29uc3VtZXIiLCJpYXQiOjE3MTU4OTgzNDR9.pHpMvZNsdbhSKZEQjuVvOSMfZ8Sskfii__ovNt3qjdQ",ticketId);
    }
}
