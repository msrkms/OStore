package com.atlassoftwarepark.ostore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atlassoftwarepark.ostore.Adepter.DataHolder;
import com.atlassoftwarepark.ostore.Adepter.DueItems;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewStockItem;
import com.atlassoftwarepark.ostore.Adepter.RecylerViewDue;
import com.atlassoftwarepark.ostore.Adepter.StockItem;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;


public class DueFragment extends Fragment {

    private ArrayList<DueItems> dueItems;
    RecyclerView recyclerView;
    private MaterialTextView materialTextViewTitle;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DueFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DueFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DueFragment newInstance(String param1, String param2) {
        DueFragment fragment = new DueFragment();
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
        View viewDue= inflater.inflate(R.layout.fragment_due, container, false);
        recyclerView=viewDue.findViewById(R.id.recyclerStock);
        materialTextViewTitle=viewDue.findViewById(R.id.fragmentTitle);

        materialTextViewTitle.setText(DataHolder.sellbookfragment);

        dueItems=new ArrayList<DueItems>();
        DueItems dueItem= new DueItems();

        dueItem.setSlNo("#");
        dueItem.setCustomerName("গ্রাহকের নাম");
        dueItem.setPhoneNumber("ফোন নাম্বার");
        dueItem.setSellDate("বিক্রির তারিখ");
        dueItem.setAmmount("টাকার পরিমান");

        DueItems dueItem1= new DueItems();
        dueItem1.setSlNo("1");
        dueItem1.setCustomerName("Sajidur Rahman");
        dueItem1.setPhoneNumber("017000000");
        dueItem1.setSellDate("02/05/2020");
        dueItem1.setAmmount("500$");

        dueItems.add(dueItem);
        dueItems.add(dueItem1);
        RecylerViewDue recylerViewDue= new RecylerViewDue(getContext(),dueItems);
        recyclerView.setAdapter(recylerViewDue);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return viewDue;
    }
}