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
import android.widget.Spinner;

import com.atlassoftwarepark.ostore.Adepter.QuickSellItem;
import com.atlassoftwarepark.ostore.Adepter.RecyclerViewQuickSellAdapter;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuickSellFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuickSellFragment extends Fragment {

    Spinner spinnerProductCategorySelect;
    MaterialButton qSellListBtn;
    ArrayList<QuickSellItem>quickSellItems;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuickSellFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuickSellFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuickSellFragment newInstance(String param1, String param2) {
        QuickSellFragment fragment = new QuickSellFragment();
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
        View qSell = inflater.inflate(R.layout.fragment_quick_sell, container, false);

        spinnerProductCategorySelect=(Spinner)qSell.findViewById(R.id.selectQuickSellSpinner);
        qSellListBtn=(MaterialButton)qSell.findViewById(R.id.quickSellListBtn);

        ArrayAdapter<String> arrayAdapterBankCategory=new ArrayAdapter<String>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                getProductCategory()
        );
        spinnerProductCategorySelect.setAdapter(arrayAdapterBankCategory);

        quickSellItems = new ArrayList<QuickSellItem>();

        QuickSellItem quickSellItem = new QuickSellItem();
        quickSellItem.setQS_invoiceNo("ইনভয়েস");
        quickSellItem.setQS_sellDate("বিক্রির তারিখ");
        quickSellItem.setQS_amount("টাকার পরিমাণ");
        quickSellItem.setQS_action("অ্যাকশন");

        quickSellItems.add(quickSellItem);

        qSellListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuickSellList();
            }
        });


        return qSell;
    }

    private List<String> getProductCategory() {
        List<String> stringsCategory=new ArrayList<String>();
        stringsCategory.add("Select One");
        stringsCategory.add("NA");
        return stringsCategory;

    }

    private void showQuickSellList(){

        View view = LayoutInflater.from(getContext()).inflate(R.layout.quick_sell_list_layout,null);

        ImageButton closeDialog;
        RecyclerView recyclerView;
        closeDialog=(ImageButton)view.findViewById(R.id.closeDialogQuickSell);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerViewQuickSellList);


        RecyclerViewQuickSellAdapter recyclerViewQuickSellAdapter=new RecyclerViewQuickSellAdapter(getContext(),quickSellItems);
        recyclerView.setAdapter(recyclerViewQuickSellAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setView(view).create();
        alertDialog.show();

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

}