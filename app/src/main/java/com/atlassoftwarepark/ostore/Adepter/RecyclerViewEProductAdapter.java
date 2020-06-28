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

public class RecyclerViewEProductAdapter extends RecyclerView.Adapter<RecyclerViewEProductAdapter.ViewHolder> {

    private Context context;
    private ArrayList<EProductItem>eProductItems;

    public RecyclerViewEProductAdapter(Context context, ArrayList<EProductItem> eProductItems) {
        this.context = context;
        this.eProductItems = eProductItems;
    }

    @NonNull
    @Override
    public RecyclerViewEProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_info_custom_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewEProductAdapter.ViewHolder holder, int position) {
        holder.txtCategory.setText(eProductItems.get(position).getProductCategory());
        holder.txtName.setText(eProductItems.get(position).getProductName());
        holder.txtUnit.setText(eProductItems.get(position).getProductUnit());
        holder.txtUnitPrice.setText(eProductItems.get(position).getProductUnitPrice());
        holder.txtAction.setText(eProductItems.get(position).getProductAction());
    }

    @Override
    public int getItemCount() {
        return eProductItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategory,txtName,txtUnit,txtUnitPrice,txtAction;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCategory=(TextView)itemView.findViewById(R.id.productCategory);
            txtName=(TextView)itemView.findViewById(R.id.productName);
            txtUnit=(TextView)itemView.findViewById(R.id.productUnit);
            txtUnitPrice=(TextView)itemView.findViewById(R.id.productUnitPrice);
            txtAction=(TextView)itemView.findViewById(R.id.productAction);
        }
    }
}
