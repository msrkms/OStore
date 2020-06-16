package com.atlassoftwarepark.ostore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atlassoftwarepark.ostore.Adepter.BuyedProductReportItem;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewBuyedProductReportAdapter;
import com.atlassoftwarepark.ostore.Adepter.SellReportItem;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyedProductReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyedProductReportFragment extends Fragment {
    TextInputEditText startDate,endDate;
    ImageView imageViewStart,imageViewEnd;

    ArrayList<BuyedProductReportItem> buyedProductReportItems;
    RecyclerView recyclerView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BuyedProductReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuyedProductReportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuyedProductReportFragment newInstance(String param1, String param2) {
        BuyedProductReportFragment fragment = new BuyedProductReportFragment();
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
        View buyedProductView= inflater.inflate(R.layout.fragment_buyed_product_report, container, false);

        startDate=(TextInputEditText)buyedProductView.findViewById(R.id.textInputEditTextStartDate);
        endDate=(TextInputEditText)buyedProductView.findViewById(R.id.textInputEditTextEndDate);

        imageViewStart=(ImageView)buyedProductView.findViewById(R.id.startDateSelect);
        imageViewEnd=(ImageView)buyedProductView.findViewById(R.id.endDateSelect);
        recyclerView=(RecyclerView)buyedProductView.findViewById(R.id.recyclerBuyedProductReport);

        MaterialDatePicker.Builder builder= MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select a Date");
        final MaterialDatePicker materialDatePickerstart=builder.build();
        final MaterialDatePicker materialDatePickerend=builder.build();


        imageViewStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePickerstart.show(getFragmentManager(),"Date Picker");

                materialDatePickerstart.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        startDate.setText(materialDatePickerstart.getHeaderText());
                    }
                });
            }
        });



        imageViewEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePickerend.show(getFragmentManager(),"Date Picker");

                materialDatePickerend.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        endDate.setText(materialDatePickerend.getHeaderText());
                    }
                });

            }
        });

        buyedProductReportItems=new ArrayList<BuyedProductReportItem>();
        BuyedProductReportItem buyedProductReportItem= new BuyedProductReportItem();
        buyedProductReportItem.setProductCategory("পণ্য ক্যাটাগরি");
        buyedProductReportItem.setProductName("প্রোডাক্ট নাম");
        buyedProductReportItem.setBuyDate("ক্রয়ের তারিখ");
        buyedProductReportItem.setQuantity("পরিমাণ");
        buyedProductReportItem.setVendor("ভেন্ডর");
        buyedProductReportItem.setUnitPrice("ইউনিট মূল্য");
        buyedProductReportItem.setTotal("সর্বমোট");
        buyedProductReportItem.setAction("অ্যাকশন");

        buyedProductReportItems.add(buyedProductReportItem);

        BuyedProductReportItem buyedProductReportItem1=new BuyedProductReportItem();
        buyedProductReportItem1.setProductCategory("Chips");
        buyedProductReportItem1.setProductName("Potato");
        buyedProductReportItem1.setBuyDate("13 June, 2020");
        buyedProductReportItem1.setQuantity("10");
        buyedProductReportItem1.setVendor("Pran");
        buyedProductReportItem1.setUnitPrice("10");
        buyedProductReportItem1.setTotal("100");
        buyedProductReportItem1.setAction("Paid");

        buyedProductReportItems.add(buyedProductReportItem1);


        RecyclerViewBuyedProductReportAdapter recyclerViewBuyedProductReportAdapter=new RecyclerViewBuyedProductReportAdapter(getContext(),buyedProductReportItems);
        recyclerView.setAdapter(recyclerViewBuyedProductReportAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return buyedProductView;
    }
}