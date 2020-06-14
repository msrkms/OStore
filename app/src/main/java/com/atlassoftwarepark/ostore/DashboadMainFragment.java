package com.atlassoftwarepark.ostore;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class DashboadMainFragment extends Fragment {

    LineChart lineCharts;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public DashboadMainFragment() {

    }


    public static DashboadMainFragment newInstance(String param1, String param2) {
        DashboadMainFragment fragment = new DashboadMainFragment();
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

        View dashboard= inflater.inflate(R.layout.fragment_dashboad_main, container, false);
        lineCharts=(LineChart)dashboard.findViewById(R.id.lineChart);

        ArrayList<String> xAxis=new ArrayList<>();
        ArrayList<Entry> yAxis1=new ArrayList<>();
        ArrayList<Entry> yAxis2=new ArrayList<>();

        yAxis1.add(new Entry(5f,0));
        yAxis1.add(new Entry(15f,1));
        yAxis1.add(new Entry(14f,2));
        yAxis1.add(new Entry(36f,3));
        yAxis1.add(new Entry(32f,4));
        yAxis1.add(new Entry(32f,5));


        yAxis2.add(new Entry(7f,0));
        yAxis2.add(new Entry(11f,1));
        yAxis2.add(new Entry(30f,2));
        yAxis2.add(new Entry(18f,3));
        yAxis2.add(new Entry(25f,4));
        yAxis2.add(new Entry(13f,5));

        xAxis.add("January");
        xAxis.add("February");
        xAxis.add("March");
        xAxis.add("April");
        xAxis.add("May");
        xAxis.add("June");


        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        LineDataSet lineDataSet1 = new LineDataSet(yAxis1,"High");
        lineDataSet1.setDrawCircles(true);
        lineDataSet1.setCircleColor(Color.parseColor("#525fb8"));
        lineDataSet1.setColor(Color.parseColor("#6778ef"));

        LineDataSet lineDataSet2= new LineDataSet(yAxis2,"Low");
        lineDataSet2.setDrawCircles(true);
        lineDataSet2.setCircleColor(Color.DKGRAY);
        lineDataSet2.setColor(Color.GRAY);

        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);

        lineCharts.setData(new LineData(xAxis,lineDataSets));






        return dashboard;
    }
}