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

public class RecyclerViewSellReportAdapter extends RecyclerView.Adapter<RecyclerViewSellReportAdapter.ViewHolder> {

    private Context context;
    private ArrayList<SellReportItem> sellReportItems;

    public RecyclerViewSellReportAdapter(Context contex,ArrayList<SellReportItem> sellReportItems){
        this.context=contex;
        this.sellReportItems=sellReportItems;
    }

    @NonNull
    @Override
    public RecyclerViewSellReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sell_report_custom_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewSellReportAdapter.ViewHolder holder, int position) {
        holder.invoice.setText(sellReportItems.get(position).getInvoiceno());
        holder.productname.setText(sellReportItems.get(position).getProductname());
        holder.selldate.setText(sellReportItems.get(position).getSelldate());
        holder.quantity.setText(sellReportItems.get(position).getQuantity());
        holder.unitprice.setText(sellReportItems.get(position).getUnitprice());
        holder.total.setText(sellReportItems.get(position).getTotal());
    }

    @Override
    public int getItemCount() {
        return sellReportItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView invoice,productname,selldate,quantity,unitprice,total;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            invoice=(TextView)itemView.findViewById(R.id.invoiceNo);
            productname=(TextView)itemView.findViewById(R.id.productName);
            selldate=(TextView)itemView.findViewById(R.id.sellDate);
            quantity=(TextView)itemView.findViewById(R.id.quantity);
            unitprice=(TextView)itemView.findViewById(R.id.unitPrice);
            total=(TextView)itemView.findViewById(R.id.total);
        }
    }
}
