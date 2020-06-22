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

public class RecyclerViewBankAdapter extends RecyclerView.Adapter<RecyclerViewBankAdapter.ViewHolder> {

    private Context context;
    private ArrayList<BankItem>bankItems;

    public RecyclerViewBankAdapter(Context context,ArrayList<BankItem> bankItems){
        this.context=context;
        this.bankItems=bankItems;
    }

    @NonNull
    @Override
    public RecyclerViewBankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.see_bank_balance_custom_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBankAdapter.ViewHolder holder, int position) {
        holder.bName.setText(bankItems.get(position).getBankName());
        holder.accType.setText(bankItems.get(position).getAccountType());
        holder.accNumber.setText(bankItems.get(position).getAccountNumber());
        holder.oBalance.setText(bankItems.get(position).getOpeningBalance());
    }

    @Override
    public int getItemCount() {
        return bankItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bName,accType,accNumber,oBalance;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bName=(TextView)itemView.findViewById(R.id.bankName);
            accType=(TextView)itemView.findViewById(R.id.accType);
            accNumber=(TextView)itemView.findViewById(R.id.accNumber);
            oBalance=(TextView)itemView.findViewById(R.id.openingBalance);
        }
    }
}
