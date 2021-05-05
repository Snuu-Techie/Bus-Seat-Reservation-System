package com.example.bsrs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class AdminDriver_RecyclerViewAdapter extends RecyclerView.Adapter<AdminDriver_RecyclerViewAdapter.AdminDriverViewHolder> implements Filterable {
    List<Driver> drivers;
      List<Driver> driversFull;
    private OnItemClickListener admin_driver_ItemListener;
    private Context context;
    public interface OnItemClickListener{
        void  onItemClick(int postion);
    }
    public void setOnitemClickListener(OnItemClickListener listener){
        admin_driver_ItemListener = listener;
    }


    public static  class AdminDriverViewHolder extends RecyclerView.ViewHolder{

        private TextView fullname,id ;
        RatingBar driver_rating_rec;
        private CardView cardView;

        public AdminDriverViewHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            fullname = itemView.findViewById(R.id.fullname_admin_driver);
            id = itemView.findViewById(R.id.user_admin_driver);
            driver_rating_rec = itemView.findViewById(R.id.driver_rating_rec);
            cardView = itemView.findViewById(R.id.Driver_profiles);

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
    public AdminDriver_RecyclerViewAdapter(ArrayList<Driver> driver){
        drivers = driver;
        driversFull = new ArrayList<>(drivers);
    }

    @NonNull
    @Override
    public AdminDriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_driver_recyclerview,parent,false);
        AdminDriverViewHolder holder = new AdminDriverViewHolder(view,admin_driver_ItemListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminDriverViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        Driver mydriver = drivers.get(position);
        holder.fullname.setText(mydriver.getFullname());
        holder.id.setText(mydriver.getUsername());
        holder.driver_rating_rec.setRating(Float.parseFloat(mydriver.getRating()));


    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    @Override
    public Filter getFilter() {
        return driversFilter;
    }
    private Filter driversFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Driver> filterList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0 ){
                filterList.addAll(driversFull);

            }else{
               String filterpattern =  constraint.toString().toLowerCase().trim();
               for(Driver driver : driversFull){
                   if(driver.getFullname().toLowerCase().contains(filterpattern)){
                       filterList.add(driver);
                   }
               }
            }
            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            drivers.clear();
            drivers.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };
}
