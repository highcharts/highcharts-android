package com.highsoft.highfit;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.highsoft.highcharts.core.HIChartView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartsManager {

    private String title;

    HIChartView setChart(
            HIChartView chartView,
            Context c,
            String chartType,
            String dataFilename,
            Boolean enableExport
    ){
        if(chartType.equals("area")) title = "Steps";
        else title = "Calories";

        Double total = 0.0;
        String subtitle;

        Map allSeries = jsonFileToMap(dataFilename, c);
        List<Double> daySeries = (List)allSeries.get("day"); // we pass scale of the chart (day, week, etc...)
        StringBuilder series = new StringBuilder();
        for (Double d : daySeries) {
            series.append(String.valueOf(d.intValue()));
            series.append(",");
            total = total + d;
        }
        series.deleteCharAt(series.length()-1);
        subtitle = (String.valueOf(total));
        subtitle = subtitle.substring(0, subtitle.length()-2);

        Map<String, String> options = new HashMap<>();
        options.put("chartType", chartType);
        options.put("title", title);
        options.put("subtitle", subtitle);
        if(enableExport) options.put("export", "true");
        else options.put("export", "false");

        chartView.setOptions(OptionsProvider.provideOptionsForChartType(options, (ArrayList) daySeries, "day"));

        return chartView;
    }



    void updateChart(
            HIChartView chartView,
            Context c,
            String chartType,
            String dataFilename,
            String scale
    ){
        if(chartType.equals("area")) title = "Steps";
        else title = "Calories";

        Double total = 0.0;
        String subtitle;

        Map allSeries = jsonFileToMap(dataFilename, c);
        List<Double> scaleSeries = (List)allSeries.get(scale); // we pass scale of the chart (day, week, etc...)
        StringBuilder series = new StringBuilder();
        for (Double d : scaleSeries) {
            series.append(String.valueOf(d.intValue()));
            series.append(",");
            total = total + d;
        }
        series.deleteCharAt(series.length()-1);
        subtitle = (String.valueOf(total));
        subtitle = subtitle.substring(0, subtitle.length()-2);

        Map<String, String> options = new HashMap<>();
        options.put("chartType", chartType);
        options.put("title", title);
        options.put("subtitle", subtitle);
        options.put("export", "true");

        chartView.setOptions(OptionsProvider.provideOptionsForChartType(options, (ArrayList) scaleSeries, scale));
    }

    private static String loadJSONFromAsset(String file, Context c) {
        String json;
        try {
            InputStream is = c.getAssets().open(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private static Map<String, Object> jsonFileToMap(String fileName, Context c) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> map = gson.fromJson(loadJSONFromAsset(fileName, c), type);
        return map;
    }

    static ArrayList<String> returnDates(String dataFilename, Context c){
        ArrayList<String> dates = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Map series = ChartsManager.jsonFileToMap(dataFilename, c);
        for(Object o: (List)series.get("all")){
            if(o instanceof Map){
                Double d = ((Double) ((Map)o).get("date"));
                Timestamp timestamp = new Timestamp(d.longValue() * 1000L);
                String strDate = dateFormat.format(new Date(timestamp.getTime()));
                dates.add(strDate);
            }
        }
        return dates;
    }

    static ArrayList<String> returnSamples(String dataFilename, Context c) {
        ArrayList<String> samples = new ArrayList<>();
        String sample;
        int i;
        Map series = ChartsManager.jsonFileToMap(dataFilename, c);
        for (Object o : (List) series.get("all")) {
            if (o instanceof Map) {
                i = ((Double) ((Map) o).get("sample")).intValue();
                sample = String.valueOf(i);
                samples.add(sample);
            }
        }
        return samples;
    }
}
