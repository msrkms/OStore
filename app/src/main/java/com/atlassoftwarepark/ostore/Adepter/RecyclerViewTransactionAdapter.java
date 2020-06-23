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

public class RecyclerViewTransactionAdapter extends RecyclerView.Adapter<RecyclerViewTransactionAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TransactionItem>transactionItems;

    public RecyclerViewTransactionAdapter(Context context,ArrayList<TransactionItem>transactionItems){
        this.context=context;
        this.transactionItems=transactionItems;
    }

    @NonNull
    @Override
    public RecyclerViewTransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_add_see_custom_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewTransactionAdapter.ViewHolder holder, int position) {
        holder.txtBankName.setText(transactionItems.get(position).getBankName());
        holder.txtDate.setText(transactionItems.get(position).getDate());
        holder.txtAccountNumber.setText(transactionItems.get(position).getAccountNumber());
        holder.txtAmount.setText(transactionItems.get(position).getAmount());
        holder.txtOperation.setText(transactionItems.get(position).getOperation());
        holder.txtMarfot.setText(transactionItems.get(position).getMarfot());
        holder.txtAction.setText(transactionItems.get(position).getAction());
    }

    @Override
    public int getItemCount() {
        return transactionItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtBankName,txtDate,txtAccountNumber,txtAmount,txtOperation,txtMarfot,txtAction;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBankName=(TextView)itemView.findViewById(R.id.bankNameTransaction);
            txtDate=(TextView)itemView.findViewById(R.id.dateTransaction);
            txtAccountNumber=(TextView)itemView.findViewById(R.id.accountNumberTransaction);
            txtAmount=(TextView)itemView.findViewById(R.id.amountTransaction);
            txtOperation=(TextView)itemView.findViewById(R.id.operationTransaction);
            txtMarfot=(TextView)itemView.findViewById(R.id.marfotTransaction);
            txtAction=(TextView)itemView.findViewById(R.id.actionTransaction);
        }
    }
}
