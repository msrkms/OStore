package com.atlassoftwarepark.ostore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SellFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellFragment extends Fragment {

    private Spinner spinnerCategory,spinnerTimeType,spinnerCustomer,spinnersellType;
    private HorizontalScrollView horizontalScrollViewInstallment;
    private MaterialButton materialButtonSell;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SellFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SellFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SellFragment newInstance(String param1, String param2) {
        SellFragment fragment = new SellFragment();
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
        View viewSell= inflater.inflate(R.layout.fragment_sell, container, false);
        spinnerCategory=(Spinner) viewSell.findViewById(R.id.spinnerAllProduct);
        ArrayAdapter<String> arrayAdapterCategory=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getCategory()
        );
        spinnerCategory.setAdapter(arrayAdapterCategory);

        spinnerTimeType=(Spinner) viewSell.findViewById(R.id.spinnerinterestTimeType);
        ArrayAdapter<String> arrayAdapterTimeType=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getTimeType()
        );
        spinnerTimeType.setAdapter(arrayAdapterTimeType);

        spinnerCustomer=(Spinner) viewSell.findViewById(R.id.spinnerCustomers);
        ArrayAdapter<String> arrayAdapterCustomer=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getCustomer()
        );
        spinnerCustomer.setAdapter(arrayAdapterCustomer);

        spinnersellType=(Spinner) viewSell.findViewById(R.id.spinnerSellType);
        ArrayAdapter<String> arrayAdapterSellType=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getsellType()
        );
        spinnersellType.setAdapter(arrayAdapterSellType);

        horizontalScrollViewInstallment=(HorizontalScrollView) viewSell.findViewById(R.id.installmentLayout);
        materialButtonSell=(MaterialButton) viewSell.findViewById(R.id.materialButtonSell) ;

        spinnersellType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    horizontalScrollViewInstallment.setVisibility(View.GONE);
                    materialButtonSell.setText("নগদ বিক্রি");
                } else if(i==1){
                    horizontalScrollViewInstallment.setVisibility(View.VISIBLE);
                    materialButtonSell.setText("কিস্তিতে বিক্রি");
                }
                else if(i==2){
                    horizontalScrollViewInstallment.setVisibility(View.GONE);
                    materialButtonSell.setText("বাকিতে বিক্রি");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return viewSell;
    }

    private List<String> getCategory() {
        List<String> stringsCategory=new ArrayList<String>();
        stringsCategory.add("All Product");
        stringsCategory.add("Product 1");
        stringsCategory.add("Product 2");

        return stringsCategory;

    }
    private List<String> getTimeType() {
        List<String> stringsTimeType=new ArrayList<String>();
        stringsTimeType.add("মেয়াদ কাল ধরণ");
        stringsTimeType.add(" দিন");
        stringsTimeType.add("মাস");

        return stringsTimeType;

    }

    private List<String> getsellType() {
        List<String> stringsSellType=new ArrayList<String>();
        stringsSellType.add("নগদ");
        stringsSellType.add("কিস্তি");
        stringsSellType.add("বাকি");

        return stringsSellType;

    }
    private List<String> getCustomer() {
        List<String> cutomer=new ArrayList<String>();
        cutomer.add("Select One");
        cutomer.add("Customer One");

        return cutomer;

    }


}