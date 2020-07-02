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

public class RecyclerViewQuickSellAdapter extends RecyclerView.Adapter<RecyclerViewQuickSellAdapter.ViewHolder> {

    private Context context;
    private ArrayList<QuickSellItem>quickSellItems;

    public RecyclerViewQuickSellAdapter(Context context, ArrayList<QuickSellItem> quickSellItems) {
        this.context = context;
        this.quickSellItems = quickSellItems;
    }

    @NonNull
    @Override
    public RecyclerViewQuickSellAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quick_sell_custom_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewQuickSellAdapter.ViewHolder holder, int position) {
        holder.txtInvoice.setText(quickSellItems.get(position).getQS_invoiceNo());
        holder.txtSellDate.setText(quickSellItems.get(position).getQS_sellDate());
        holder.txtAmount.setText(quickSellItems.get(position).getQS_amount());
        holder.txtAction.setText(quickSellItems.get(position).getQS_action());
    }

    @Override
    public int getItemCount() {
        return quickSellItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtInvoice,txtSellDate,txtAmount,txtAction;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtInvoice=(TextView)itemView.findViewById(R.id.invoiceQsell);
            txtSellDate=(TextView)itemView.findViewById(R.id.sellDateQsell);
            txtAmount=(TextView)itemView.findViewById(R.id.amountQsell);
            txtAction=(TextView)itemView.findViewById(R.id.actionQsell);
        }
    }
}
