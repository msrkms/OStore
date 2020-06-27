package com.atlassoftwarepark.ostore.Adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atlassoftwarepark.ostore.R;

import java.util.ArrayList;

public class RecyclerViewCustomerAdapter extends RecyclerView.Adapter<RecyclerViewCustomerAdapter.ViewHolder>{

    private Context context;
    private ArrayList<CustomerItem>customerItems;

    public RecyclerViewCustomerAdapter(Context context,ArrayList<CustomerItem>customerItems){
        this.context=context;
        this.customerItems=customerItems;
    }

    @NonNull
    @Override
    public RecyclerViewCustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_customer_custom_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewCustomerAdapter.ViewHolder holder, int position) {
        holder.txtCustomerName.setText(customerItems.get(position).getCustomerName());
        holder.txtCustomerPhone.setText(customerItems.get(position).getCustomerPhone());
        holder.txtCustomerAddress.setText(customerItems.get(position).getCustomerAddress());
        holder.txtCustomerAction.setText(customerItems.get(position).getCustomerAction());
    }

    @Override
    public int getItemCount() {
        return customerItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCustomerName,txtCustomerPhone,txtCustomerAddress,txtCustomerAction;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCustomerName=(TextView)itemView.findViewById(R.id.customerName);
            txtCustomerPhone=(TextView)itemView.findViewById(R.id.customerPhone);
            txtCustomerAddress=(TextView)itemView.findViewById(R.id.customerAddress);
            txtCustomerAction=(TextView)itemView.findViewById(R.id.customerAction);
        }
    }
}
