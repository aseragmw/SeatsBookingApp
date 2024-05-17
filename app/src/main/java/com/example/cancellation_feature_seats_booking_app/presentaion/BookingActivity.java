package com.example.cancellation_feature_seats_booking_app.presentaion;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancellation_feature_seats_booking_app.R;
import com.example.cancellation_feature_seats_booking_app.domain.BookingModel;
import com.example.cancellation_feature_seats_booking_app.domain.HistoryResponseModel;
import com.example.cancellation_feature_seats_booking_app.presentaion.adapters.BookingsAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {
    RecyclerView rv;
    BookingsAdapter adapter;
    BookingsViewModel bookingsViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RvSetUp();
        toolbarSetUp();
        bookingsViewModel = new ViewModelProvider(this).get(BookingsViewModel.class);
        bookingsViewModel.getHistory();
        bookingsViewModel.bookingsLiveData.observe(this, new Observer<HistoryResponseModel>() {
            @Override
            public void onChanged(HistoryResponseModel historyResponseModel) {
                Log.d("TAG", "onChanged: ");
                if(historyResponseModel!=null) {
                    if(historyResponseModel.getData().isEmpty()){
                    TextView noData = findViewById(R.id.tv_noBookings);
                    noData.setVisibility(View.VISIBLE);
                    }
                    if(!historyResponseModel.getData().isEmpty()){
                        ProgressBar progressBar = findViewById(R.id.progressBarBookings);
                        progressBar.setVisibility(View.GONE);
                    }
                    List<BookingModel>adapterList=new ArrayList<>();
                    for (int i = 0; i < historyResponseModel.getData().size(); i++) {
                        if(isDateValid(historyResponseModel.getData().get(i).getTrip_start_date())){
                            adapterList.add(historyResponseModel.getData().get(i));
                            Log.d("TAG", "onChanged: "+historyResponseModel.getData().get(i).getTrip_start_date());
                        }
                    }
                    adapter.setList(adapterList);
                }
                else
                {
                    TextView noData = findViewById(R.id.tv_noBookings);
                    noData.setVisibility(View.VISIBLE);
                }
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    void RvSetUp(){
        rv = findViewById(R.id.rv_myBookings);
        rv.setLayoutManager(new LinearLayoutManager(this));
        List<BookingModel> list = new ArrayList<>();
        adapter = new BookingsAdapter(list);
        rv.setAdapter( adapter);
    }
    void toolbarSetUp(){
        Toolbar toolbar = findViewById(R.id.toolbar_bookings);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView toolbarTitle = new TextView(this);
        toolbarTitle.setText("My Bookings");
        toolbarTitle.setTextSize(20);
        toolbarTitle.setTextColor(getResources().getColor(android.R.color.black));
        toolbarTitle.setTypeface(toolbarTitle.getTypeface(), Typeface.BOLD);
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(
                Toolbar.LayoutParams.WRAP_CONTENT,
                Toolbar.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER;
        toolbar.addView(toolbarTitle, layoutParams);
    }

    boolean isDateValid(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");

        try {
            Log.d("TAG", "1: ");
            // Parse the date string into a LocalDate object
            LocalDate dateToCompare = LocalDate.parse(dateString, formatter);
            Log.d("TAG", "2: ");

            // Get the current date
            LocalDate currentDate = LocalDate.now();
            Log.d("TAG", "3: ");
            Log.d("TAG", "11: "+dateToCompare.toString());
            Log.d("TAG", "12: "+currentDate.toString());


            // Compare the dates
            if (dateToCompare.isAfter(currentDate)) {
                Log.d("TAG", "4: ");

                // The date is upcoming
                return true;
                // Perform your action here
            } else {
                Log.d("TAG", "5: ");

                return false;
            }
        } catch (DateTimeParseException e) {
            Log.d("TAG", "6: ");

            return false;
        }
    }
}