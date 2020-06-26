package com.atlassoftwarepark.ostore;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.atlassoftwarepark.ostore.Adepter.ExtraItem;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewExtraAdapter;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExtraIncomeCostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExtraIncomeCostFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ExtraItem> extraItems;
    LinearLayout linearTextView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExtraIncomeCostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExtraIncomeCostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExtraIncomeCostFragment newInstance(String param1, String param2) {
        ExtraIncomeCostFragment fragment = new ExtraIncomeCostFragment();
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
        View extra= inflater.inflate(R.layout.fragment_extra_income_cost, container, false);

        recyclerView=(RecyclerView)extra.findViewById(R.id.recyclerExtraIncome);
        linearTextView=(LinearLayout)extra.findViewById(R.id.linearExtraIncomeCost);

        linearTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        extraItems=new ArrayList<ExtraItem>();
        ExtraItem extraItem= new ExtraItem();
        extraItem.setFrom("উৎস");
        extraItem.setDate("তারিখ");
        extraItem.setAmount("টাকার পরিমাণ");
        extraItem.setStatus("স্ট্যাটাস");
        extraItem.setAction("অ্যাকশন");

        extraItems.add(extraItem);

        RecyclerViewExtraAdapter recyclerViewExtraAdapter= new RecyclerViewExtraAdapter(getContext(),extraItems);
        recyclerView.setAdapter(recyclerViewExtraAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return extra;
    }

    private void showDialog(){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        ImageButton closeDialog;
        ImageView dateExtra;
        Spinner extraCategory;
        final TextInputEditText dateEditTextExtra;
        TextInputEditText amountExtra,fromExtra;
        View addExtraIncomeCost=inflater.inflate(R.layout.extra_incom_cost_layout,null);
        closeDialog=(ImageButton)addExtraIncomeCost.findViewById(R.id.closeDialogExtra);
        dateExtra=(ImageView) addExtraIncomeCost.findViewById(R.id.extraDateSelect);
        dateEditTextExtra=(TextInputEditText)addExtraIncomeCost.findViewById(R.id.textInputEditTextExtraDate);
        extraCategory=(Spinner)addExtraIncomeCost.findViewById(R.id.spinnerExtraCategory);
        fromExtra=(TextInputEditText)addExtraIncomeCost.findViewById(R.id.textInputEditTextExtraFrom);
        amountExtra=(TextInputEditText)addExtraIncomeCost.findViewById(R.id.textInputEditTextExtraAmount);

        dateExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialDatePicker.Builder builder=MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Select Date");
                final MaterialDatePicker materialDatePicker=builder.build();
                materialDatePicker.show(getFragmentManager(),"DatePicker");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        dateEditTextExtra.setText(materialDatePicker.getHeaderText());
                    }
                });

            }
        });


        ArrayAdapter<String> arrayAdapterOperationCategory = new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getExtraCategory()
        );
        extraCategory.setAdapter(arrayAdapterOperationCategory);


        final AlertDialog alertDialog=new AlertDialog.Builder(getContext()).setView(addExtraIncomeCost).create();
        alertDialog.show();
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    private List<String> getExtraCategory(){
        List<String> stringsCategory=new ArrayList<String>();
        stringsCategory.add("Select One");
        stringsCategory.add("ইনকাম");
        stringsCategory.add("খরচ");

        return stringsCategory;
    }


}