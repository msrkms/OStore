package com.atlassoftwarepark.ostore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atlassoftwarepark.ostore.Adepter.DataHolder;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class SellBookFragment extends Fragment{

    private ArrayList<String> titles=new ArrayList<String>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SellBookFragment() {
    }

    public static SellBookFragment newInstance(String param1, String param2) {
        SellBookFragment fragment = new SellBookFragment();
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
        titles.add("নগদ বা পরিশোধিত বিক্রিত লিস্ট");
        titles.add("বাকিতে বিক্রিত লিস্ট");
        titles.add("কিস্তিতে বিক্রিত লিস্ট");
        DataHolder.sellbookfragment=titles.get(0);
        View sellbooksView= inflater.inflate(R.layout.fragment_sell_book, container, false);
        getChildFragmentManager().beginTransaction().replace(R.id.fragmentInnerContainer,new DueFragment()).commit();


       TabLayout tabLayout=(TabLayout) sellbooksView.findViewById(R.id.tablayout);
       tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               if(tab.getPosition()==0){
                   DataHolder.sellbookfragment=titles.get(0);
                   getChildFragmentManager().beginTransaction().replace(R.id.fragmentInnerContainer,new DueFragment()).commit();
               }else if(tab.getPosition()==1){
                   DataHolder.sellbookfragment=titles.get(1);
                   getChildFragmentManager().beginTransaction().replace(R.id.fragmentInnerContainer,new DueFragment()).commit();
               }else  if(tab.getPosition()==2){
                   DataHolder.sellbookfragment=titles.get(2);
                   getChildFragmentManager().beginTransaction().replace(R.id.fragmentInnerContainer,new DueFragment()).commit();
               }

           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });
        return sellbooksView;
    }

    public void itemOne(View view){

    }



}