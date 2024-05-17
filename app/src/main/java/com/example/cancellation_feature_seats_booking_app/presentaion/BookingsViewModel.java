package com.example.cancellation_feature_seats_booking_app.presentaion;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cancellation_feature_seats_booking_app.data.ApiClient;
import com.example.cancellation_feature_seats_booking_app.domain.BookingModel;
import com.example.cancellation_feature_seats_booking_app.domain.HistoryResponseModel;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingsViewModel  extends ViewModel {
    public MutableLiveData<HistoryResponseModel> bookingsLiveData = new MutableLiveData<>();
    ApiClient client = ApiClient.getInstance();
    public void getHistory() {
        client.getHistory().enqueue(new Callback<HistoryResponseModel>() {
            @Override
            public void onResponse(Call<HistoryResponseModel> call, Response<HistoryResponseModel> response) {
                bookingsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<HistoryResponseModel> call, Throwable throwable) {
                bookingsLiveData.setValue(new HistoryResponseModel("Failed", Collections.emptyList()));
                Log.d("BookingsViewModel", "failed");
            }
        });
    }
    public void cancelTrip(BookingModel bookingModel){
        final int count = bookingModel.getTickets_count();
        for(int i=0;i<count;i++){
            int finalI = i;
            client.cancelTicket(bookingModel.getTickets_ids().get(i)).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("BookingsViewModel", "cancelled "+Integer.toString(finalI)+bookingModel.getTicket_id());
                }

                @Override
                public void onFailure(Call<Void> call, Throwable throwable) {
                    Log.d("BookingsViewModel", "failed");
                }
            });
        }
    }
}
