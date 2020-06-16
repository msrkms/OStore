package com.atlassoftwarepark.ostore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.atlassoftwarepark.ostore.Adepter.ProfitLossItem;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewProfitLossAdapter;
import com.atlassoftwarepark.ostore.Adepter.StockItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfitLossFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfitLossFragment extends Fragment {
    private Spinner spinnerCategory;
    private ArrayList<ProfitLossItem> profitLossItems;
    RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfitLossFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfitLossFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfitLossFragment newInstance(String param1, String param2) {
        ProfitLossFragment fragment = new ProfitLossFragment();
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
        View profitLossView= inflater.inflate(R.layout.fragment_profit_loss, container, false);

        spinnerCategory=(Spinner)profitLossView.findViewById(R.id.spinnerCategory);
        recyclerView=(RecyclerView)profitLossView.findViewById(R.id.recyclerProfitLossReport);

        ArrayAdapter<String> arrayAdapterCategory=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getCategory()
        );
        spinnerCategory.setAdapter(arrayAdapterCategory);

        profitLossItems=new ArrayList<ProfitLossItem>();

        ProfitLossItem profitLossItem=new ProfitLossItem();
        profitLossItem.setProductName("প্রোডাক্ট নাম");
        profitLossItem.setTotalBuyRate("সর্বমোট ক্রয়মূল্য");
        profitLossItem.setTotalSellRate("সর্বমোট বিক্রয়মূল্য");
        profitLossItem.setStatus("স্ট্যাটাস");

        profitLossItems.add(profitLossItem);

        ProfitLossItem profitLossItem1=new ProfitLossItem();
        profitLossItem1.setProductName("Chips");
        profitLossItem1.setTotalBuyRate("100");
        profitLossItem1.setTotalSellRate("105");
        profitLossItem1.setStatus("Profit");

        profitLossItems.add(profitLossItem1);

        RecyclerViewProfitLossAdapter recyclerViewProfitLossAdapter=new RecyclerViewProfitLossAdapter(getContext(),profitLossItems);
        recyclerView.setAdapter(recyclerViewProfitLossAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return profitLossView;
    }

    private List<String> getCategory() {
        List<String> stringsCategory=new ArrayList<String>();
        stringsCategory.add("All Product");
        stringsCategory.add("Product 1");
        stringsCategory.add("Product 2");

        return stringsCategory;

    }

}