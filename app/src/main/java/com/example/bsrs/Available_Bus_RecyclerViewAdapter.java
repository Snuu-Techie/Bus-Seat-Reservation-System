package com.example.bsrs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Available_Bus_RecyclerViewAdapter extends RecyclerView.Adapter<Available_Bus_RecyclerViewAdapter.BusViewHolder> implements Filterable {
    List<Bus> buses;
    List<Bus> busesFull;
    private OnItemClickListener available_bus_ItemListener;
    private Context context;
    public interface OnItemClickListener{
        void  onItemClick(int postion);
    }
    public void setOnitemClickListener(OnItemClickListener listener){
        available_bus_ItemListener = listener;
    }


    public static  class BusViewHolder extends RecyclerView.ViewHolder{

        private TextView plate,model,number_of_seats;
        private CardView cardView;

        public BusViewHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            plate = itemView.findViewById(R.id.bus_plate_number);
            model = itemView.findViewById(R.id.bus_model);
            number_of_seats= itemView.findViewById(R.id.seat_all);
            cardView = itemView.findViewById(R.id.bus_profiles);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null ){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }

                    }
                }
            });


        }
    }
    public Available_Bus_RecyclerViewAdapter(ArrayList<Bus> driver){
        buses = driver;
        busesFull = new ArrayList<>(buses);
    }

    @NonNull
    @Override
    public BusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buses_activity,parent,false);
        BusViewHolder holder = new BusViewHolder(view,available_bus_ItemListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BusViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        Bus mybus = buses.get(position);
        holder.plate.setText(mybus.getBus_plate_number());
        holder.model.setText(mybus.getModel());
        holder.number_of_seats.setText(mybus.getNumber_of_seats());


    }

    @Override
    public int getItemCount() {
        return buses.size();
    }

    @Override
    public Filter getFilter() {
        return busesFilter;
    }
    private Filter busesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Bus> filterList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0 ){
                filterList.addAll(busesFull);

            }else{
                String filterpattern =  constraint.toString().toLowerCase().trim();
                for(Bus bus : busesFull){
                    if(bus.getBus_plate_number().toLowerCase().contains(filterpattern)){
                        filterList.add(bus);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            buses.clear();
            buses.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };
}


