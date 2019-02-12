package com.highsoft.highfit;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.highsoft.highcharts.common.hichartsclasses.HIChart;
import com.highsoft.highcharts.core.HIChartView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    private Map<String, Boolean> dashboardCharts;

    public DashboardFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_charts_dashboard, container, false);


        HIChartView areaView = (HIChartView) view.findViewById(R.id.area);
        HIChartView splineView = (HIChartView) view.findViewById(R.id.spline);
        HIChartView columnView = (HIChartView) view.findViewById(R.id.column);
        areaView.setWillNotDraw(true);
        splineView.setWillNotDraw(true);
        columnView.setWillNotDraw(true);

        //Internal Storage Map implementation for charts in dashboard
        {
            String dashboardFileName = "dashboardCharts.txt";
            String filePath = getActivity().getFilesDir().getAbsolutePath() + "/" + dashboardFileName;
            File file = new File(filePath);
            if(file.exists()){
                try {
                    FileInputStream fileInputStream = new FileInputStream(getActivity().getFilesDir().getAbsolutePath()+"/" + dashboardFileName);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    dashboardCharts = (Map)objectInputStream.readObject();
                    fileInputStream.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                dashboardCharts = new HashMap<>();
                dashboardCharts.put("area", true);
                dashboardCharts.put("spline", true);
                dashboardCharts.put("column", true);
                try {
                    FileOutputStream fos = getActivity().openFileOutput(dashboardFileName, Context.MODE_PRIVATE);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
                    objectOutputStream.writeObject(dashboardCharts);
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(view !=null){
            float height = getResources().getDisplayMetrics().heightPixels;
            Context c = view.getContext();
            ChartsManager manager = new ChartsManager();
            FragmentManager fragmentManager = getFragmentManager();
            final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null);


            if(dashboardCharts.get("area")){
                areaView.setWillNotDraw(false);
                areaView.getLayoutParams().height = Math.round(height*2/5);
                manager.setChart(areaView, c, "area", "DataSteps.json", false);
                ImageButton areaInfoBtn = (ImageButton) areaView.findViewById(R.id.infoBtn);
                areaInfoBtn.bringToFront();
                areaInfoBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fragmentTransaction
                                .replace(R.id.container, new AreaChartFragment(), "CHARTS")
                                .commit();
                    }
                });
            }
            if(dashboardCharts.get("spline")){
                splineView.setWillNotDraw(false);
                splineView.getLayoutParams().height = Math.round(height*2/5);
                manager.setChart(splineView, c, "spline", "DataCaloriesIn.json", false);
                ImageButton splineInfoBtn = (ImageButton) splineView.findViewById(R.id.infoBtn);
                splineInfoBtn.bringToFront();
                splineInfoBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fragmentTransaction
                                .replace(R.id.container, new SplineChartFragment(), "CHARTS")
                                .commit();
                    }
                });
            }
            if(dashboardCharts.get("column")){
                columnView.setWillNotDraw(false);
                columnView.getLayoutParams().height = Math.round(height*2/5);
                manager.setChart(columnView, c, "column", "DataCaloriesOut.json", false);
                ImageButton columnInfoBtn = (ImageButton) columnView.findViewById(R.id.infoBtn);
                columnInfoBtn.bringToFront();
                columnInfoBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fragmentTransaction
                                .replace(R.id.container, new ColumnChartFragment(), "CHARTS")
                                .commit();
                    }
                });
            }
        }
        return view;
    }




}