package com.atlassoftwarepark.ostore;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductBuyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductBuyFragment extends Fragment {

    private Spinner spinnerCategory;

    MaterialButton materialButtonProduct0,materialButtonProduct1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductBuyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductBuyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductBuyFragment newInstance(String param1, String param2) {
        ProductBuyFragment fragment = new ProductBuyFragment();
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
        View viewProductBuy= inflater.inflate(R.layout.fragment_product_buy, container, false);

        materialButtonProduct0=(MaterialButton) viewProductBuy.findViewById(R.id.materialButtonProduct0);
        materialButtonProduct1=(MaterialButton) viewProductBuy.findViewById(R.id.materialButtonProduct1);

        materialButtonProduct0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        materialButtonProduct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });


        spinnerCategory=viewProductBuy.findViewById(R.id.spinnerCategory);
        ArrayAdapter<String> arrayAdapterCategory=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getCategory()
        );

        spinnerCategory.setAdapter(arrayAdapterCategory);

        return viewProductBuy;
    }

    private List<String> getCategory() {
        List<String> stringsCategory=new ArrayList<String>();
        stringsCategory.add("All Product");
        stringsCategory.add("Product 1");
        stringsCategory.add("Product 2");

        return stringsCategory;

    }

    private void showDialog(){
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View addTransaction=inflater.inflate(R.layout.add_buy_product,null);
        final TextInputEditText textInputEditTextDate,textInputEditTextAmount,textInputEditTextunitPrice;
        ImageView dateSelect;
        Spinner spinnerVendorList;
        MaterialButton addTransactionBtn;
        ImageButton closeBtn;
        dateSelect=(ImageView)addTransaction.findViewById(R.id.imageViewDateSelect);
        textInputEditTextDate=(TextInputEditText)addTransaction.findViewById(R.id.textInputEditTextDate);
        textInputEditTextAmount=(TextInputEditText)addTransaction.findViewById(R.id.textInputEditTextAmount);
        textInputEditTextunitPrice=(TextInputEditText)addTransaction.findViewById(R.id.textInputEditTextUnitPrice);

        closeBtn=(ImageButton)addTransaction.findViewById(R.id.closeDialogProductBuy);

        spinnerVendorList=(Spinner)addTransaction.findViewById(R.id.spinnerVendorList);

        addTransactionBtn=(MaterialButton)addTransaction.findViewById(R.id.TransactionAddBtn);

        ArrayAdapter<String> arrayAdapterVendorList=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getVendorList()
        );
        spinnerVendorList.setAdapter(arrayAdapterVendorList);


        dateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder builder= MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Select a Date");
                final MaterialDatePicker materialDatePickerDate=builder.build();
                materialDatePickerDate.show(getFragmentManager(),"Date Picker");
                materialDatePickerDate.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        textInputEditTextDate.setText(materialDatePickerDate.getHeaderText());
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

    private List<String> getVendorList() {
        List<String> stringsCategory=new ArrayList<String>();
        stringsCategory.add("Select One");
        stringsCategory.add("Vendor1");
        stringsCategory.add("Vendor2");

        return stringsCategory;

    }
}