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

public class AdminCustomer_RecyclerView_Adapter extends RecyclerView.Adapter<AdminCustomer_RecyclerView_Adapter.AdminCustomerViewHolder> implements Filterable {
    List<Customer> customers;
    List<Customer> customersFull;
    private OnItemClickListener admin_driver_ItemListener;
    private Context context;
    public interface OnItemClickListener{
        void  onItemClick(int postion);
    }
    public void setOnitemClickListener(OnItemClickListener listener){
        admin_driver_ItemListener = listener;
    }


    public static  class AdminCustomerViewHolder extends RecyclerView.ViewHolder{

        private TextView fullname,id ;
        RatingBar driver_rating_rec;
        private CardView cardView;

        public AdminCustomerViewHolder(@NonNull View itemView,final OnItemClickListener listener) {
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
    public AdminCustomer_RecyclerView_Adapter(ArrayList<Customer> customer){
        customers = customer;
        customersFull = new ArrayList<>(customers);
    }

    @NonNull
    @Override
    public AdminCustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_driver_recyclerview,parent,false);
        AdminCustomerViewHolder holder = new AdminCustomerViewHolder(view,admin_driver_ItemListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminCustomerViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        Customer customer = customers.get(position);
        holder.fullname.setText(customer.getFullname());
        holder.id.setText(customer.getUsername());
        //holder.driver_rating_rec.setRating(Float.parseFloat(customer.getRating()));
        holder.driver_rating_rec.setVisibility(View.GONE);


    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    @Override
    public Filter getFilter() {
        return driversFilter;
    }
    private Filter driversFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Customer> filterList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0 ){
                filterList.addAll(customersFull);

            }else{
                String filterpattern =  constraint.toString().toLowerCase().trim();
                for(Customer customer : customersFull){
                    if(customer.getFullname().toLowerCase().contains(filterpattern)){
                        filterList.add(customer);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            customers.clear();
            customers.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };
}
