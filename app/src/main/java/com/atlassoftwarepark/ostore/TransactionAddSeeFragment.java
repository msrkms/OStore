package com.atlassoftwarepark.ostore;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.atlassoftwarepark.ostore.Adepter.RecyclerViewTransactionAdapter;
import com.atlassoftwarepark.ostore.Adepter.TransactionItem;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransactionAddSeeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionAddSeeFragment extends Fragment {
    LinearLayout linearLayoutTransaction;
    RecyclerView recyclerView;
    ArrayList<TransactionItem> transactionItems;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TransactionAddSeeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransactionAddSeeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransactionAddSeeFragment newInstance(String param1, String param2) {
        TransactionAddSeeFragment fragment = new TransactionAddSeeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View transactionView= inflater.inflate(R.layout.fragment_transaction_add_see, container, false);

        linearLayoutTransaction=(LinearLayout)transactionView.findViewById(R.id.layoutTransaction);
        recyclerView=(RecyclerView)transactionView.findViewById(R.id.recyclerTransaction);

        transactionItems=new ArrayList<TransactionItem>();

        TransactionItem transactionItem= new TransactionItem();
        transactionItem.setBankName("ব্যাংকের নাম");
        transactionItem.setDate("তারিখ");
        transactionItem.setAccountNumber("অ্যাকাউন্ট নাম্বার");
        transactionItem.setAmount("টাকার পরিমাণ");
        transactionItem.setOperation("অপারেশন");
        transactionItem.setMarfot("মারফৎ");
        transactionItem.setAction("অ্যাকশান");

        transactionItems.add(transactionItem);

        RecyclerViewTransactionAdapter recyclerViewTransactionAdapter=new RecyclerViewTransactionAdapter(getContext(),transactionItems);
        recyclerView.setAdapter(recyclerViewTransactionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        linearLayoutTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return transactionView;
    }

    private void showDialog(){
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View addTransaction=inflater.inflate(R.layout.add_transaction_layout,null);
        final TextInputEditText textInputEditTextDate,textInputEditTextAccountNumber,textInputEditTextAmount,textInputEditTextMarfot;
        ImageView dateSelect;
        Spinner bankSpinner,operationSpinner;
        MaterialButton addTransactionBtn;
        ImageButton closeBtn;
        dateSelect=(ImageView)addTransaction.findViewById(R.id.transactionDateSelect);
        textInputEditTextDate=(TextInputEditText)addTransaction.findViewById(R.id.textInputEditTextTransactionDate);
        textInputEditTextAccountNumber=(TextInputEditText)addTransaction.findViewById(R.id.textInputEditTextTransactionAccountNumber);
        textInputEditTextAmount=(TextInputEditText)addTransaction.findViewById(R.id.textInputEditTextTransactionAmount);
        textInputEditTextMarfot=(TextInputEditText)addTransaction.findViewById(R.id.textInputEditTextTransactionMarfot);

        closeBtn=(ImageButton)addTransaction.findViewById(R.id.closeDialogTransaction);

        bankSpinner=(Spinner)addTransaction.findViewById(R.id.spinnerBankCategory);
        operationSpinner=(Spinner)addTransaction.findViewById(R.id.spinnerOperationCategory);
        addTransactionBtn=(MaterialButton)addTransaction.findViewById(R.id.TransactionAddBtn);

        ArrayAdapter<String> arrayAdapterBankCategory=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getBankCategory()
        );
        bankSpinner.setAdapter(arrayAdapterBankCategory);

        ArrayAdapter<String> arrayAdapterOperationCategory = new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getOperationCategory()
        );
        operationSpinner.setAdapter(arrayAdapterOperationCategory);

        dateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder builder= MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Select a Date");
                final MaterialDatePicker materialDatePickerTransactionDate=builder.build();
                materialDatePickerTransactionDate.show(getFragmentManager(),"Date Picker");
                materialDatePickerTransactionDate.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        textInputEditTextDate.setText(materialDatePickerTransactionDate.getHeaderText());
                    }
                });

            }
        });



        final AlertDialog alertDialog=new AlertDialog.Builder(getContext()).setView(addTransaction).create();
        alertDialog.show();

        addTransactionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

    private List<String> getBankCategory() {
        List<String> stringsCategory=new ArrayList<String>();
        stringsCategory.add("Select One");
        stringsCategory.add("NA");
        stringsCategory.add("NA");

        return stringsCategory;

    }

    private List<String> getOperationCategory() {
        List<String> stringsCategory=new ArrayList<String>();
        stringsCategory.add("Select One");
        stringsCategory.add("ডিপোজিট");
        stringsCategory.add("উইথড্র");

        return stringsCategory;

    }

}