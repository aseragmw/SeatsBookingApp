package com.example.cancellation_feature_seats_booking_app.presentaion.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancellation_feature_seats_booking_app.R;
import com.example.cancellation_feature_seats_booking_app.domain.BookingModel;
import com.example.cancellation_feature_seats_booking_app.presentaion.BookingsViewModel;

import java.util.List;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.BookingViewHolder> {
    private List<BookingModel> bookingList;
    public BookingsAdapter(List<BookingModel> bookingList){
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item_with_cancel,parent,false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        holder.from.setText(bookingList.get(position).getBoarding_station().toUpperCase());
        holder. to.setText(bookingList.get(position).getDestination_station().toUpperCase());
        holder. startTime.setText(bookingList.get(position).getTrip_start_date());
        holder. endTime.setText(bookingList.get(position).getTrip_end_date());
        holder. total.setText("Total: "+Integer.toString(bookingList.get(position).getTotal_price())+"EGP");
        holder.tickets.setText("Tickets: "+Integer.toString(bookingList.get(position).getTickets_count()));
        holder.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BookingsViewModel().cancelTrip(bookingList.get(position));
                bookingList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(holder.itemView.getContext(), "Trip Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return bookingList.size();
    }
    public void setList(List<BookingModel> bookingList){
        this.bookingList=bookingList;
        notifyDataSetChanged();
    }

    public class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView from,to,startTime,endTime,total,tickets,noBookings;
        Button cancelButton;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            startTime = itemView.findViewById(R.id.startTime);
            endTime = itemView.findViewById(R.id.endTime);
            total = itemView.findViewById(R.id.total);
            tickets =itemView.findViewById(R.id.tickets);
            cancelButton = itemView.findViewById(R.id.btn_cancelTrip);
            noBookings= itemView.findViewById(R.id.tv_noBookings);
        }
    }
}
