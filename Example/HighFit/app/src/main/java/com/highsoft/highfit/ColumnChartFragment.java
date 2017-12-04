package com.highsoft.highfit;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.highsoft.highcharts.Core.HIGChartView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColumnChartFragment extends Fragment {

    private Map<String, Boolean> dashboardCharts;
    private String dashboardFileName = "dashboardCharts.txt";

    public ColumnChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart_column, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        View view = getView();
        if(view !=null){
            final HIGChartView chartView = (HIGChartView) view.findViewById(R.id.column);
            float height = getResources().getDisplayMetrics().heightPixels;
            chartView.getLayoutParams().height = Math.round(height*2/5);
            final Context c = view.getContext();
            final ArrayList<String> samples = ChartsManager.returnSamples("DataCaloriesOut.json", c);
            final ArrayList<String> dates = ChartsManager.returnDates("DataCaloriesOut.json", c);
            final ChartsManager manager = new ChartsManager();
            manager.setChart(chartView, c, "column", "DataCaloriesOut.json", true);
            ImageButton splineInfoBtn = (ImageButton) chartView.findViewById(R.id.infoBtn);
            splineInfoBtn.setVisibility(View.GONE);
            Button dayButton = (Button) view.findViewById(R.id.dayButColumn);
            Button weekButton = (Button) view.findViewById(R.id.weekButColumn);
            Button monthButton = (Button) view.findViewById(R.id.monthButColumn);
            Button yearButton = (Button) view.findViewById(R.id.yearButColumn);
            try {
                FileInputStream fileInputStream = new FileInputStream(getActivity().getFilesDir().getAbsolutePath()+"/" + dashboardFileName);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                dashboardCharts = (Map) objectInputStream.readObject();
                fileInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            Switch showSwitch = (Switch) view.findViewById(R.id.showSwitchColumn);
            showSwitch.setChecked(dashboardCharts.get("column"));
            showSwitch.jumpDrawablesToCurrentState();

            showSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    dashboardCharts.put("column", isChecked);
                    try {
                        FileOutputStream fos = getActivity().openFileOutput(dashboardFileName, Context.MODE_PRIVATE);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
                        objectOutputStream.writeObject(dashboardCharts);
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            TextView showAllData = (TextView) view.findViewById(R.id.showAllDataColumn);

            dayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manager.updateChart(chartView, c, "column", "DataCaloriesOut.json", "day");
                    chartView.reload();
                }
            });
            weekButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manager.updateChart(chartView, c, "column", "DataCaloriesOut.json", "week");
                    chartView.reload();
                }
            });
            monthButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manager.updateChart(chartView, c, "column", "DataCaloriesOut.json", "month");
                    chartView.reload();
                }
            });
            yearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manager.updateChart(chartView, c, "column", "DataCaloriesOut.json", "year");
                    chartView.reload();
                }
            });

            showAllData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    DetailedDataFragment detailedDataFragment = new DetailedDataFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("unit", "Calories");
                    bundle.putStringArrayList("DatesArrayList", dates);
                    bundle.putStringArrayList("SamplesArrayList", samples);
                    detailedDataFragment.setArguments(bundle);

                    fragmentTransaction.replace(R.id.container, detailedDataFragment, "detailedDataFragment");
                    fragmentTransaction.commit();
                }
            });
        }
    }


}
