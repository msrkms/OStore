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

public class RecylerViewDue extends RecyclerView.Adapter<RecylerViewDue.ViewHolder> {
    private Context context;
    private ArrayList<DueItems> dueItems;

    public RecylerViewDue(Context context, ArrayList<DueItems> dueItems) {
        this.context = context;
        this.dueItems = dueItems;
    }


    @NonNull
    @Override
    public RecylerViewDue.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.due_custom_list,parent,false);
        RecylerViewDue.ViewHolder viewHolder=new RecylerViewDue.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerViewDue.ViewHolder holder, int position) {
        holder.SL.setText(dueItems.get(position).getSlNo());
        holder.customerName.setText(dueItems.get(position).getCustomerName());
        holder.PhoneNumber.setText(dueItems.get(position).getPhoneNumber());
        holder.SellDate.setText(dueItems.get(position).getSellDate());
        holder.ammount.setText(dueItems.get(position).getAmmount());
    }

    @Override
    public int getItemCount() {
        return dueItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView SL,customerName,PhoneNumber,SellDate,ammount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            SL=(TextView)itemView.findViewById(R.id.SlNo);
            customerName=(TextView)itemView.findViewById(R.id.customername);
            PhoneNumber=(TextView)itemView.findViewById(R.id.phoneNumber);
            SellDate=(TextView)itemView.findViewById(R.id.sellDate);
            ammount=(TextView)itemView.findViewById(R.id.ammount);
        }
    }
}
