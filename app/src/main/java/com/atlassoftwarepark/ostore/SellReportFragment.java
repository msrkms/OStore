package com.atlassoftwarepark.ostore;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atlassoftwarepark.ostore.Adepter.RecyclerViewSellReportAdapter;
import com.atlassoftwarepark.ostore.Adepter.SellReportItem;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Random;



import static android.graphics.fonts.FontStyle.FONT_SLANT_ITALIC;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SellReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellReportFragment extends Fragment {

    TextInputEditText startDate,endDate;
    ImageView imageViewStart,imageViewEnd;

    ArrayList<SellReportItem> sellReportItems;
    RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SellReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SellReportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SellReportFragment newInstance(String param1, String param2) {
        SellReportFragment fragment = new SellReportFragment();
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

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View sellReport= inflater.inflate(R.layout.fragment_sell_report, container, false);

        startDate=(TextInputEditText)sellReport.findViewById(R.id.textInputEditTextStartDate);
        endDate=(TextInputEditText)sellReport.findViewById(R.id.textInputEditTextEndDate);

        imageViewStart=(ImageView)sellReport.findViewById(R.id.startDateSelect);
        imageViewEnd=(ImageView)sellReport.findViewById(R.id.endDateSelect);
        recyclerView=(RecyclerView)sellReport.findViewById(R.id.recyclerSellReport);
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

        sellReportItems= new ArrayList<SellReportItem>();
        SellReportItem sellReportItem0= new SellReportItem();
        sellReportItem0.setInvoiceno("ইনভয়েস নং");
        sellReportItem0.setProductname("প্রোডাক্ট নাম");
        sellReportItem0.setSelldate("বিক্রির তারিখ");
        sellReportItem0.setQuantity("পরিমাণ");
        sellReportItem0.setUnitprice("ইউনিট মূল্য");
        sellReportItem0.setTotal("সর্বমোট");
        sellReportItems.add(sellReportItem0);

        SellReportItem sellReportItem=new SellReportItem();
        sellReportItem.setInvoiceno("1234");
        sellReportItem.setProductname("Potato");
        sellReportItem.setSelldate("15 June, 2020");
        sellReportItem.setQuantity("3");
        sellReportItem.setUnitprice("5");
        sellReportItem.setTotal("15");
        sellReportItems.add(sellReportItem);

        SellReportItem sellReportItem1=new SellReportItem();
        sellReportItem1.setInvoiceno("7854");
        sellReportItem1.setProductname("Tomato");
        sellReportItem1.setSelldate("14 June, 2020");
        sellReportItem1.setQuantity("10");
        sellReportItem1.setUnitprice("8");
        sellReportItem1.setTotal("80");
        sellReportItems.add(sellReportItem1);

        RecyclerViewSellReportAdapter recyclerViewSellReportAdapter= new RecyclerViewSellReportAdapter(getContext(),sellReportItems);
        recyclerView.setAdapter(recyclerViewSellReportAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return  sellReport;
    }
}