package com.example.cancellation_feature_seats_booking_app.presentaion;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancellation_feature_seats_booking_app.R;
import com.example.cancellation_feature_seats_booking_app.domain.BookingModel;
import com.example.cancellation_feature_seats_booking_app.domain.HistoryResponseModel;
import com.example.cancellation_feature_seats_booking_app.presentaion.adapters.history_adapter;

import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.ViewModelProvider;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView rv;
    history_adapter adapter;
    BookingsViewModel bookingsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
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
                if(historyResponseModel!=null) {
                    if(historyResponseModel.getData().isEmpty()){
                        TextView noData = findViewById(R.id.tv_noBookings);
                        noData.setVisibility(View.VISIBLE);
                    }
                    if(!historyResponseModel.getData().isEmpty()){
                        ProgressBar progressBar = findViewById(R.id.progressBarHistory);
                        progressBar.setVisibility(View.GONE);
                    }
                    adapter.setList(historyResponseModel.getData());
                }
                else {
                    TextView noData = findViewById(R.id.tv_noHistory);
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
        rv = findViewById(R.id.rv_myHistory);
        rv.setLayoutManager(new LinearLayoutManager(this));
        List<BookingModel> list = new ArrayList<>();
        adapter = new history_adapter(list);
        rv.setAdapter( adapter);
    }

    void toolbarSetUp(){
        Toolbar toolbar = findViewById(R.id.toolbar_history);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView toolbarTitle = new TextView(this);
        toolbarTitle.setText("History");
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
}