package com.atlassoftwarepark.ostore.Adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atlassoftwarepark.ostore.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerViewVendorAdapter extends RecyclerView.Adapter<RecyclerViewVendorAdapter.ViewHolder> implements Filterable {

    private List<VendorItem> vendorItems;
    private List<VendorItem>vendorItemListFull;

    public RecyclerViewVendorAdapter(List<VendorItem> vendorItems) {
        this.vendorItems = vendorItems;
        vendorItemListFull=new ArrayList<>(vendorItems);
    }

    @NonNull
    @Override
    public RecyclerViewVendorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_vendor_custom_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewVendorAdapter.ViewHolder holder, int position) {
        holder.txtVendorID.setText(vendorItems.get(position).getVendorId());
        holder.txtvendorName.setText(vendorItems.get(position).getVendorName());
        holder.txtvendorPhone.setText(vendorItems.get(position).getVendorPhone());
        holder.txtvendorInstitute.setText(vendorItems.get(position).getVendorInstitute());
        holder.txtvendorAddress.setText(vendorItems.get(position).getVendorAddress());
        if(position==0){
            holder.deleteVendor.setVisibility(View.GONE);
            holder.txtvendorAction.setText(vendorItems.get(position).getVendorAction());
        }
        else{
            holder.txtvendorAction.setVisibility(View.GONE);
            holder.deleteVendor.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return vendorItems.size();
    }

    @Override
    public Filter getFilter() {
        return vendorFilter;
    }

    private Filter vendorFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<VendorItem> filteredList = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filteredList.addAll(vendorItemListFull);
            }
            else {
                for(VendorItem vendor: vendorItemListFull){
                    if(vendor.getVendorName().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filteredList.add(vendor);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values=filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            vendorItems.clear();
            vendorItems.addAll((Collection<? extends VendorItem>) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtVendorID,txtvendorName, txtvendorPhone, txtvendorInstitute, txtvendorAddress, txtvendorAction;
        ImageView deleteVendor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtVendorID=(TextView)itemView.findViewById(R.id.vendorId);
            txtvendorName = (TextView) itemView.findViewById(R.id.vendorName);
            txtvendorPhone = (TextView) itemView.findViewById(R.id.vendorPhone);
            txtvendorInstitute = (TextView) itemView.findViewById(R.id.vendorInstitution);
            txtvendorAddress = (TextView) itemView.findViewById(R.id.vendorAddress);
            txtvendorAction = (TextView) itemView.findViewById(R.id.vendroAction);
            deleteVendor=(ImageView)itemView.findViewById(R.id.deleteItemImageView);
        }
    }
}
