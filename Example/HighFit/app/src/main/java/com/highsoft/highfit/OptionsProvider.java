package com.highsoft.highfit;

import com.highsoft.highcharts.common.*;
import com.highsoft.highcharts.common.hichartsclasses.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class OptionsProvider {

    public static HIOptions provideOptionsForChartType(Map<String, String> options, ArrayList series, String type){
        String categories[] = new String[0];
        Double step = null;

        switch (type) {
            case "day":
                categories = new String[]{"12AM", "", "3AM", "", "6AM", "", "9AM", "", "12PM", "", "3PM", "", "6PM", "", "9PM", "", "12AM"};
                step = 1.0;
                break;
            case "week":
                categories = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
                break;
            case "month":
                categories = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
                break;
            case "year":
                categories = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                step = 1.0;
                break;
        }

        if(options.get("chartType").equals("area")){

            HIOptions hiOptions = new HIOptions();

            HIChart chart = new HIChart();

            HIGradient gradient = new HIGradient(0, 0, 0, 1);
            LinkedList<HIStop> stops = new LinkedList<>();
            stops.add(new HIStop(0, HIColor.initWithRGB(102, 153, 161)));
            chart.setBackgroundColor(HIColor.initWithLinearGradient(gradient, stops));
            chart.setBorderRadius(6.0);
            chart.setType(options.get("chartType"));
            hiOptions.setChart(chart);

            HIExporting exporting = new HIExporting();
            exporting.setEnabled(Boolean.valueOf(options.get("export")));
            hiOptions.setExporting(exporting);

            HINavigation navigation = new HINavigation();
            navigation.setButtonOptions(new HIButtonOptions());
            navigation.getButtonOptions().setSymbolStroke(HIColor.initWithRGBA(255,255,255,0.4));
            HITheme theme = new HITheme();
            theme.setFill(HIColor.initWithRGBA(0,0,0,0));
            navigation.getButtonOptions().setTheme(theme);
            hiOptions.setNavigation(navigation);

            HIPlotOptions plotOptions = new HIPlotOptions();
            plotOptions.setArea(new HIArea());
            HIGradient fillGradient = new HIGradient(0, 0, 0, 1);
            LinkedList<HIStop> fillStops = new LinkedList<>();
            fillStops.add(new HIStop(0, HIColor.initWithRGBA(255, 255, 255, 0.75)));
            fillStops.add(new HIStop(1, HIColor.initWithRGBA(255, 255, 255, 0.02)));
            plotOptions.getArea().setFillColor(HIColor.initWithLinearGradient(fillGradient, fillStops));
            hiOptions.setPlotOptions(plotOptions);

            HICredits credits = new HICredits();
            credits.setEnabled(true);
            credits.setText("www.highcharts.com");
            HICSSObject creditsStyle = new HICSSObject();
            creditsStyle.setColor("rgba(255, 255, 255, 0.6)");
            credits.setStyle(creditsStyle);
            hiOptions.setCredits(credits);

            HITitle title = new HITitle();
            title.setText(options.get("title"));
            title.setAlign("left");
            HICSSObject titleStyle = new HICSSObject();
            titleStyle.setFontFamily("Arial");
            titleStyle.setFontSize("14px");
            titleStyle.setColor("rgba(255, 255, 255, 0.6)");
            title.setStyle(titleStyle);
            title.setY(16);
            hiOptions.setTitle(title);

            HISubtitle subtitle = new HISubtitle();
            subtitle.setText(options.get("subtitle"));
            if(subtitle.getText().length() > 0) subtitle.setText(subtitle.getText() + " total");
            subtitle.setAlign( "left");
            HICSSObject subtitleStyle = new HICSSObject();
            subtitleStyle.setFontFamily("Arial");
            subtitleStyle.setFontSize("9px");
            subtitleStyle.setColor("rgba(255, 255, 255, 0.6)");
            subtitle.setStyle(subtitleStyle);
            subtitle.setY(28);
            hiOptions.setSubtitle(subtitle);

            final HIXAxis xaxis = new HIXAxis();
            xaxis.setTickColor(HIColor.initWithRGBA(255, 255, 255, 0.0));
            xaxis.setLineColor(HIColor.initWithRGBA(255, 255, 255, 0.3));
            xaxis.setLabels(new HILabels());
            HICSSObject xaxisStyle = new HICSSObject();
            xaxisStyle.setColor("rgb(255, 255, 255)");
            xaxisStyle.setFontSize("10px");
            xaxisStyle.setFontFamily("Arial");
            xaxis.getLabels().setStyle(xaxisStyle);
            xaxis.getLabels().setStep(step);
            xaxis.setCategories(new ArrayList<>(Arrays.asList(categories)));
            hiOptions.setXAxis(new ArrayList<HIXAxis>(){{add(xaxis);}});

            final HIYAxis yaxis = new HIYAxis();
            yaxis.setLineColor(HIColor.initWithRGBA(255, 255, 255, 0.3));
            yaxis.setLineWidth(1);
            yaxis.setGridLineWidth(0);
            yaxis.setLabels(new HILabels());
            HICSSObject yaxisStyle = new HICSSObject();
            yaxisStyle.setColor("rgb(255, 255, 255)");
            yaxisStyle.setFontFamily("Arial");
            yaxisStyle.setFontSize("10px");
            yaxis.getLabels().setStyle(yaxisStyle);
            yaxis.getLabels().setX(-5);
            yaxis.setTitle(new HITitle());
            yaxis.getTitle().setText("");
            hiOptions.setYAxis(new ArrayList<HIYAxis>(){{add(yaxis);}});

            final HIArea area = new HIArea();
            area.setTooltip(new HITooltip());
            area.getTooltip().setHeaderFormat("");
            area.getTooltip().setValueSuffix(" steps");
            area.setShowInLegend(false);
            area.setData(series);
            area.setColor(HIColor.initWithRGB(255, 255, 255));
            area.setName(options.get("title"));
            hiOptions.setSeries(new ArrayList<HISeries>(){{add(area);}});

            return hiOptions;
        }

        if(options.get("chartType").equals("column")){

            HIOptions hiOptions = new HIOptions();

            HIChart chart = new HIChart();

            HIGradient gradient = new HIGradient(0, 0, 0, 1);
            LinkedList<HIStop> stops = new LinkedList<>();
            stops.add(new HIStop(0, HIColor.initWithRGB(66, 218, 113)));
            stops.add(new HIStop(1, HIColor.initWithRGB(80, 140, 200)));
            chart.setBackgroundColor(HIColor.initWithLinearGradient(gradient, stops));
            chart.setBorderRadius(6);
            chart.setType(options.get("chartType"));
            hiOptions.setChart(chart);

            HIExporting exporting = new HIExporting();
            exporting.setEnabled(Boolean.valueOf(options.get("export")));
            hiOptions.setExporting(exporting);

            HINavigation navigation = new HINavigation();
            navigation.setButtonOptions(new HIButtonOptions());
            navigation.getButtonOptions().setSymbolStroke(HIColor.initWithRGBA(255, 255, 255, 0.4));
            HITheme theme = new HITheme();
            theme.setFill(HIColor.initWithRGBA(0,0,0,0));
            navigation.getButtonOptions().setTheme(theme);
            hiOptions.setNavigation(navigation);

            HIPlotOptions plotOptions = new HIPlotOptions();
            plotOptions.setColumn(new HIColumn());
            plotOptions.getColumn().setColor(HIColor.initWithRGBA(255, 255, 255, 0.6));
            plotOptions.getColumn().setBorderRadius(2);
            plotOptions.getColumn().setBorderWidth(0);
            hiOptions.setPlotOptions(plotOptions);

            HICredits credits = new HICredits();
            credits.setEnabled(true);
            credits.setText("www.highcharts.com");
            HICSSObject creditsStyle = new HICSSObject();
            creditsStyle.setColor("rgba(255, 255, 255, 0.6)");
            credits.setStyle(creditsStyle);
            hiOptions.setCredits(credits);

            HITitle title = new HITitle();
            title.setText(options.get("title"));
            title.setAlign("left");
            HICSSObject titleStyle = new HICSSObject();
            titleStyle.setFontFamily("Arial");
            titleStyle.setFontSize("14px");
            titleStyle.setColor("rgba(255, 255, 255, 0.6)");
            title.setStyle(titleStyle);
            title.setY(16);
            hiOptions.setTitle(title);

            HISubtitle subtitle = new HISubtitle();
            subtitle.setText(options.get("subtitle"));
            if (subtitle.getText().length() > 0) {
                subtitle.setText(subtitle.getText() + " total");
            }
            HICSSObject subtitleStyle = new HICSSObject();
            subtitleStyle.setFontFamily("Arial");
            subtitleStyle.setFontSize("9px");
            subtitleStyle.setColor("rgba(255, 255, 255, 0.6)");
            subtitle.setStyle(subtitleStyle);
            subtitle.setAlign("left");
            subtitle.setY(28);
            hiOptions.setSubtitle(subtitle);

            HITooltip tooltip = new HITooltip();
            tooltip.setHeaderFormat("");
            hiOptions.setTooltip(tooltip);

            final HIXAxis xaxis = new HIXAxis();
            xaxis.setTickColor(HIColor.initWithRGBA(255, 255, 255, 0.0));
            xaxis.setLineColor(HIColor.initWithRGBA(255, 255, 255, 0.3));
            xaxis.setLabels(new HILabels());
            HICSSObject xLabelsStyle = new HICSSObject();
            xLabelsStyle.setColor("rgb(255, 255, 255)");
            xLabelsStyle.setFontSize("10px");
            xLabelsStyle.setFontFamily("Arial");
            xaxis.getLabels().setStyle(xLabelsStyle);
            xaxis.getLabels().setStep(step);
            xaxis.setCategories(new ArrayList<>(Arrays.asList(categories)));
            hiOptions.setXAxis(new ArrayList<HIXAxis>(){{add(xaxis);}});

            final HIYAxis yaxis = new HIYAxis();
            yaxis.setLineWidth(1);
            yaxis.setGridLineWidth(0);
            yaxis.setLineColor(HIColor.initWithRGBA(255, 255, 255, 0.0));
            yaxis.setLabels(new HILabels());
            HICSSObject yLabelsStyle = new HICSSObject();
            yLabelsStyle.setColor("rgb(255, 255, 255)");
            yLabelsStyle.setFontSize("10px");
            yLabelsStyle.setFontFamily("Arial");
            yaxis.getLabels().setStyle(yLabelsStyle);
            yaxis.getLabels().setX(-5);
            yaxis.setTitle(new HITitle());
            yaxis.getTitle().setText("");
            hiOptions.setYAxis(new ArrayList<HIYAxis>(){{add(yaxis);}});

            final HIColumn column = new HIColumn();
            column.setTooltip(new HITooltip());
            column.getTooltip().setHeaderFormat("");
            column.getTooltip().setValueSuffix(" kcal");
            column.setShowInLegend(false);
            column.setData(series);
            column.setName(options.get("title"));
            hiOptions.setSeries(new ArrayList<HISeries>(){{add(column);}});

            return hiOptions;
        }

        if (options.get("chartType").equals("spline")){

            HIOptions hiOptions = new HIOptions();

            HIChart chart = new HIChart();

            HIGradient gradient = new HIGradient(0, 0, 0, 1);
            LinkedList<HIStop> stops = new LinkedList<>();
            stops.add(new HIStop(0, HIColor.initWithRGB(132, 103, 144)));
            stops.add(new HIStop(1, HIColor.initWithRGB(163, 95, 103)));
            chart.setBackgroundColor(HIColor.initWithLinearGradient(gradient, stops));
            chart.setBorderRadius(6);
            chart.setType(options.get("chartType"));
            hiOptions.setChart(chart);

            HIExporting exporting = new HIExporting();
            exporting.setEnabled(Boolean.valueOf(options.get("export")));
            hiOptions.setExporting(exporting);

            HINavigation navigation = new HINavigation();
            navigation.setButtonOptions(new HIButtonOptions());
            navigation.getButtonOptions().setSymbolStroke(HIColor.initWithRGBA(255, 255, 255, 0.4));
            HITheme theme = new HITheme();
            theme.setFill(HIColor.initWithRGBA(0,0,0,0));
            navigation.getButtonOptions().setTheme(theme);
            hiOptions.setNavigation(navigation);

            HIPlotOptions plotOptions = new HIPlotOptions();
            plotOptions.setSpline(new HISpline());
            plotOptions.getSpline().setColor(HIColor.initWithRGBA(255, 255, 255, 0.6));
            hiOptions.setPlotOptions(plotOptions);

            HICredits credits = new HICredits();
            credits.setEnabled(true);
            credits.setText("www.highcharts.com");
            HICSSObject creditsStyle = new HICSSObject();
            creditsStyle.setColor("rgba(255, 255, 255, 0.6)");
            credits.setStyle(creditsStyle);
            hiOptions.setCredits(credits);

            HITitle title = new HITitle();
            title.setText(options.get("title"));
            title.setAlign("left");
            HICSSObject titleStyle = new HICSSObject();
            titleStyle.setFontFamily("Arial");
            titleStyle.setFontSize("14px");
            titleStyle.setColor("rgba(255, 255, 255, 0.6)");
            title.setStyle(titleStyle);
            title.setY(16);
            hiOptions.setTitle(title);

            HISubtitle subtitle = new HISubtitle();
            subtitle.setText(options.get("subtitle"));
            if (subtitle.getText().length() > 0) {
                subtitle.setText(subtitle.getText() + " total");
            }
            subtitle.setAlign("left");
            HICSSObject subtitleStyle = new HICSSObject();
            subtitleStyle.setFontFamily("Arial");
            subtitleStyle.setFontSize("10px");
            subtitleStyle.setColor("rgba(255, 255, 255, 0.6)");
            subtitle.setStyle(subtitleStyle);
            subtitle.setY(28);
            hiOptions.setSubtitle(subtitle);

            HITooltip tooltip = new HITooltip();
            tooltip.setHeaderFormat("");
            hiOptions.setTooltip(tooltip);

            final HIXAxis xaxis = new HIXAxis();
            xaxis.setTickColor(HIColor.initWithRGBA(255, 255, 255, 0.0));
            xaxis.setLineColor(HIColor.initWithRGBA(255, 255, 255, 0.3));
            xaxis.setLabels(new HILabels());
            HICSSObject xLabelsStyle = new HICSSObject();
            xLabelsStyle.setColor("rgb(255, 255, 255)");
            xLabelsStyle.setFontFamily("Arial");
            xLabelsStyle.setFontSize("10px");
            xaxis.getLabels().setStyle(xLabelsStyle);
            xaxis.getLabels().setStep(step);
            xaxis.setCategories(new ArrayList<>(Arrays.asList(categories)));
            hiOptions.setXAxis(new ArrayList<HIXAxis>(){{add(xaxis);}});

            final HIYAxis yaxis = new HIYAxis();
            yaxis.setLineWidth(1);
            yaxis.setGridLineWidth(0);
            yaxis.setLineColor(HIColor.initWithRGBA(255, 255, 255, 0.3));
            yaxis.setLabels(new HILabels());
            HICSSObject yLabelsStyle = new HICSSObject();
            yLabelsStyle.setColor("rgb(255, 255, 255)");
            yLabelsStyle.setFontFamily("Arial");
            yLabelsStyle.setFontSize("10px");
            yaxis.getLabels().setStyle(yLabelsStyle);
            yaxis.getLabels().setX(-5);
            yaxis.setTitle(new HITitle());
            yaxis.getTitle().setText("");
            hiOptions.setYAxis(new ArrayList<HIYAxis>(){{add(yaxis);}});

            final HISpline spline = new HISpline();
            spline.setTooltip(new HITooltip());
            spline.getTooltip().setHeaderFormat("");
            spline.getTooltip().setValueSuffix(" kcal");
            spline.setShowInLegend(false);
            spline.setData(series);
            spline.setName(options.get("title"));
            hiOptions.setSeries(new ArrayList<HISeries>(){{add(spline);}});

            return hiOptions;
        }

        return null;
    }
}
