package com.example.cancellation_feature_seats_booking_app.presentaion.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancellation_feature_seats_booking_app.R;
import com.example.cancellation_feature_seats_booking_app.domain.BookingModel;

import java.util.List;

public class history_adapter extends RecyclerView.Adapter<history_adapter.HistoryViewHolder> {
    private List<BookingModel> bookingList;
    public history_adapter(List<BookingModel> bookingList){
        this.bookingList = bookingList;
    }
    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView from,to,startTime,endTime,total,tickets;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            startTime = itemView.findViewById(R.id.startTime);
            endTime = itemView.findViewById(R.id.endTime);
            total = itemView.findViewById(R.id.total);
            tickets =itemView.findViewById(R.id.tickets);
        }
    }
    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item,parent,false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {

        holder.from.setText(bookingList.get(position).getBoarding_station().toUpperCase());
        holder. to.setText(bookingList.get(position).getDestination_station().toUpperCase());
        holder. startTime.setText(bookingList.get(position).getTrip_start_date());
        holder. endTime.setText(bookingList.get(position).getTrip_end_date());
        holder. total.setText("Total: "+Integer.toString(bookingList.get(position).getTotal_price())+"EGP");
        holder.tickets.setText("Tickets: "+Integer.toString(bookingList.get(position).getTickets_count()));
    }

   public void setList(List<BookingModel> bookingList){
        this.bookingList=bookingList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }
}
