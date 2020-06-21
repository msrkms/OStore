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

public class RecyclerViewLoanAdapter extends RecyclerView.Adapter<RecyclerViewLoanAdapter.ViewHolder> {

    private Context context;
    private ArrayList<LoanItem> loanItems;

    public RecyclerViewLoanAdapter(Context context,ArrayList<LoanItem> loanItems){
        this.context=context;
        this.loanItems=loanItems;
    }

    @NonNull
    @Override
    public RecyclerViewLoanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.loan_custom_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewLoanAdapter.ViewHolder holder, int position) {
        holder.txtDate.setText(loanItems.get(position).getDate());
        holder.txtAmount.setText(loanItems.get(position).getAmount());
        holder.txtStatus.setText(loanItems.get(position).getStatus());
        holder.txtMarfot.setText(loanItems.get(position).getMarfot());
        holder.txtAction.setText(loanItems.get(position).getAction());
    }

    @Override
    public int getItemCount() {
        return loanItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate,txtAmount,txtStatus,txtMarfot,txtAction;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate=(TextView)itemView.findViewById(R.id.loanDate);
            txtAmount=(TextView)itemView.findViewById(R.id.loanAmount);
            txtStatus=(TextView)itemView.findViewById(R.id.loanStatus);
            txtMarfot=(TextView)itemView.findViewById(R.id.loanMarfot);
            txtAction=(TextView)itemView.findViewById(R.id.loanAction);
        }
    }
}
