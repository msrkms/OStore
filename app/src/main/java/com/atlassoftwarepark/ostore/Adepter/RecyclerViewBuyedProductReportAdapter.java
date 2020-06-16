package com.atlassoftwarepark.ostore.Adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atlassoftwarepark.ostore.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerViewBuyedProductReportAdapter extends RecyclerView.Adapter<RecyclerViewBuyedProductReportAdapter.ViewHolder> {

    private Context context;
    private ArrayList<BuyedProductReportItem> buyedProductReportItems;

    public RecyclerViewBuyedProductReportAdapter(Context context, ArrayList<BuyedProductReportItem> buyedProductReportItems){
        this.context=context;
        this.buyedProductReportItems=buyedProductReportItems;
    }

    @NonNull
    @Override
    public RecyclerViewBuyedProductReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.buyed_product_report_custom_list,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBuyedProductReportAdapter.ViewHolder holder, int position) {
        holder.txtProductCategory.setText(buyedProductReportItems.get(position).getProductCategory());
        holder.txtProductName.setText(buyedProductReportItems.get(position).getProductName());
        holder.txtBuyDate.setText(buyedProductReportItems.get(position).getBuyDate());
        holder.txtQuantity.setText(buyedProductReportItems.get(position).getQuantity());
        holder.txtVendor.setText(buyedProductReportItems.get(position).getVendor());
        holder.txtUnitPrice.setText(buyedProductReportItems.get(position).getUnitPrice());
        holder.txtTotal.setText(buyedProductReportItems.get(position).getTotal());
        holder.txtAction.setText(buyedProductReportItems.get(position).getAction());
    }

    @Override
    public int getItemCount() {
        return buyedProductReportItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtProductCategory,txtProductName,txtBuyDate,txtQuantity,txtVendor,txtUnitPrice,txtTotal,txtAction;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductCategory=(TextView)itemView.findViewById(R.id.productCategory);
            txtProductName=(TextView)itemView.findViewById(R.id.productName);
            txtBuyDate=(TextView)itemView.findViewById(R.id.buyDate);
            txtQuantity=(TextView)itemView.findViewById(R.id.quantity);
            txtVendor=(TextView)itemView.findViewById(R.id.vendor);
            txtUnitPrice=(TextView)itemView.findViewById(R.id.unitPrice);
            txtTotal=(TextView)itemView.findViewById(R.id.total);
            txtAction=(TextView)itemView.findViewById(R.id.action);
        }
    }
}
