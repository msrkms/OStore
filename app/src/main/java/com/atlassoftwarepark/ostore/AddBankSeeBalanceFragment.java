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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.atlassoftwarepark.ostore.Adepter.BankItem;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewBankAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddBankSeeBalanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddBankSeeBalanceFragment extends Fragment {

    LinearLayout addBank;
    RecyclerView recyclerView;
    ArrayList<BankItem> bankItems;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddBankSeeBalanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddBankSeeBalanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddBankSeeBalanceFragment newInstance(String param1, String param2) {
        AddBankSeeBalanceFragment fragment = new AddBankSeeBalanceFragment();
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
        View bankBalance = inflater.inflate(R.layout.fragment_add_bank_see_balance, container, false);

        addBank = (LinearLayout) bankBalance.findViewById(R.id.layoutAddBankBalance);
        recyclerView = (RecyclerView) bankBalance.findViewById(R.id.recyclerBankBalance);

        bankItems = new ArrayList<BankItem>();

        BankItem bankItem = new BankItem();
        bankItem.setBankName("ব্যাংকের নাম");
        bankItem.setAccountType("অ্যাকাউন্ট ধরণ");
        bankItem.setAccountNumber("অ্যাকাউন্ট নাম্বার");
        bankItem.setOpeningBalance("ওপেনিং ব্যালেন্স");

        bankItems.add(bankItem);

        RecyclerViewBankAdapter recyclerViewBankAdapter=new RecyclerViewBankAdapter(getContext(),bankItems);
        recyclerView.setAdapter(recyclerViewBankAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        addBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return bankBalance;
    }

    private void showDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        Spinner categoryAccount;
        final TextInputEditText bankNameEditText, accNumberEditText, openingBalanceEditText;
        MaterialButton bankAddButton;
        ImageButton closeBankPopUp;
        View pop = inflater.inflate(R.layout.add_bank_balance, null);
        categoryAccount = (Spinner) pop.findViewById(R.id.spinnerAccountCategory);
        bankNameEditText = (TextInputEditText) pop.findViewById(R.id.textInputEditTextBankName);
        accNumberEditText = (TextInputEditText) pop.findViewById(R.id.textInputEditTextAccountNumber);
        openingBalanceEditText = (TextInputEditText) pop.findViewById(R.id.textInputEditTextOpeningBalance);
        bankAddButton = (MaterialButton) pop.findViewById(R.id.bankAddBtn);

        closeBankPopUp=(ImageButton)pop.findViewById(R.id.closeDialogBank);

        ArrayAdapter<String> arrayAdapterCategory = new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getCategory()
        );
        categoryAccount.setAdapter(arrayAdapterCategory);

        final AlertDialog builder = new AlertDialog.Builder(getContext()).setView(pop).create();
        builder.show();

        bankAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), bankNameEditText.getText().toString(), Toast.LENGTH_SHORT).show();
                builder.dismiss();
            }
        });


        closeBankPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });

    }

    private List<String> getCategory() {
        List<String> stringsCategory = new ArrayList<String>();
        stringsCategory.add("Select One");
        stringsCategory.add("Savings Account");
        stringsCategory.add("Checking Account");
        stringsCategory.add("Money Market Account");
        stringsCategory.add("Certificates of Deposit");
        stringsCategory.add("Brokerage Account");

        return stringsCategory;

    }
}