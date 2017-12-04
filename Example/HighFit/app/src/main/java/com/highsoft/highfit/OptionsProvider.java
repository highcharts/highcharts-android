package com.highsoft.highfit;

import com.highsoft.highcharts.Common.*;
import com.highsoft.highcharts.Common.HIChartsClasses.*;

import java.util.*;

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

            /*Gradient stuff for background color*/
            Map<String, Number> gradientPoints = new HashMap<>();
            gradientPoints.put("x1", 0);
            gradientPoints.put("y1", 0);
            gradientPoints.put("x2", 0);
            gradientPoints.put("y2", 1);
            List<List> gradientColors = new ArrayList<>();
            List<Object> l1 = new LinkedList<>();
            l1.add(0);
            l1.add("rgb(102, 153, 161)");
            List<Object> l2 = new LinkedList<>();
            l2.add(1);
            l2.add("rgb(128, 135, 232)");
            gradientColors.add(l1);
            gradientColors.add(l2);
            chart.backgroundColor = HIColor.initWithLinearGradient(gradientPoints, gradientColors);
            chart.borderRadius = 6.0;
            chart.type = options.get("chartType");
            hiOptions.chart = chart;

            HIExporting exporting = new HIExporting();
            exporting.enabled = Boolean.valueOf(options.get("export"));
            hiOptions.exporting = exporting;

            HINavigation navigation = new HINavigation();
            navigation.buttonOptions = new HIButtonOptions();
            navigation.buttonOptions.symbolStroke = HIColor.initWithRGBA(255,255,255,0.4);
            HITheme theme = new HITheme();
            theme.fill = "rgba(0,0,0,0.0)";
            navigation.buttonOptions.theme =  theme;
            hiOptions.navigation = navigation;

            HIPlotOptions plotOptions = new HIPlotOptions();
            plotOptions.area = new HIArea();
            /*Gradient stuff for background color*/
            Map<String, Number> fillGradientPoints = new HashMap<>();
            fillGradientPoints.put("x1", 0);
            fillGradientPoints.put("y1", 0);
            fillGradientPoints.put("x2", 0);
            fillGradientPoints.put("y2", 300);
            List<List> fillGradientColors = new ArrayList<>();
            List<Object> g1 = new LinkedList<>();
            g1.add(0);
            g1.add("rgba(255, 255, 255, 0.75)");
            List<Object> g2 = new LinkedList<>();
            g2.add(1);
            g2.add("rgba(255, 255, 255, 0.02)");
            fillGradientColors.add(g1);
            fillGradientColors.add(g2);
            plotOptions.area.fillColor = HIColor.initWithLinearGradient(fillGradientPoints, fillGradientColors);
            hiOptions.plotOptions = plotOptions;

            HICredits credits = new HICredits();
            credits.enabled = true;
            credits.text = "www.highcharts.com";
            HIStyle creditsStyle = new HIStyle();
            creditsStyle.color = "rgba(255, 255, 255, 0.6)";
            credits.style = creditsStyle;
            hiOptions.credits = credits;

            HITitle title = new HITitle();
            title.text = options.get("title");
            title.align = "left";
            HIStyle titleStyle = new HIStyle();
            titleStyle.fontFamily = "Arial";
            titleStyle.fontSize = "14px";
            titleStyle.color = "rgba(255, 255, 255, 0.6)";
            title.style = titleStyle;
            title.y = 16;
            hiOptions.title = title;

            HISubtitle subtitle = new HISubtitle();
            subtitle.text = options.get("subtitle");
            if(subtitle.text.length() > 0) subtitle.text = subtitle.text + " total";
            subtitle.align = "left";
            HashMap<String, String> subtitleStyle = new HashMap<>();
            subtitleStyle.put("fontFamily", "Arial");
            subtitleStyle.put("fontSize", "9px");
            subtitleStyle.put("color", "rgba(255, 255, 255, 0.6)");
            subtitle.style = subtitleStyle;
            subtitle.y = 28;
            hiOptions.subtitle = subtitle;

            final HIXAxis xaxis = new HIXAxis();
            xaxis.tickColor = HIColor.initWithRGBA(255, 255, 255, 0.0);
            xaxis.lineColor = HIColor.initWithRGBA(255, 255, 255, 0.3);
            xaxis.labels = new HILabels();
            HIStyle xaxisStyle = new HIStyle();
            xaxisStyle.color = "rgb(255, 255, 255)";
            xaxisStyle.fontSize = "10px";
            xaxisStyle.fontFamily = "Arial";
            xaxis.labels.style = xaxisStyle;
            xaxis.labels.step = step;
            xaxis.categories = new ArrayList<>(Arrays.asList(categories));
            hiOptions.xAxis = new ArrayList<HIXAxis>(){{add(xaxis);}};

            final HIYAxis yaxis = new HIYAxis();
            yaxis.lineColor = HIColor.initWithRGBA(255, 255, 255, 0.3);
            yaxis.lineWidth = 1;
            yaxis.gridLineWidth = 0;
            yaxis.labels = new HILabels();
            HIStyle yaxisStyle = new HIStyle();
            yaxisStyle.color = "rgb(255, 255, 255)";
            yaxisStyle.fontFamily = "Arial";
            yaxisStyle.fontSize = "10px";
            yaxis.labels.style = yaxisStyle;
            yaxis.labels.x = -5;
            yaxis.title = new HITitle();
            yaxis.title.text = "";
            hiOptions.yAxis = new ArrayList<HIYAxis>(){{add(yaxis);}};

            final HIArea area = new HIArea();
            area.tooltip = new HITooltip();
            area.tooltip.headerFormat = "";
            area.tooltip.valueSuffix = " steps";
            area.showInLegend = false;
            area.data = series;
            area.color = HIColor.initWithRGB(255, 255, 255);
            area.name = options.get("title");
            hiOptions.series = new ArrayList<HISeries>(){{add(area);}};

            return hiOptions;
        }

        if(options.get("chartType").equals("column")){

            HIOptions hioptions = new HIOptions();

            HIChart chart = new HIChart();

            /*Gradient stuff for background color*/
            Map<String, Number> gradientPoints = new HashMap<>();
            gradientPoints.put("x1", 0);
            gradientPoints.put("y1", 0);
            gradientPoints.put("x2", 0);
            gradientPoints.put("y2", 1);
            List<List> gradientColors = new ArrayList<>();
            List<Object> l1 = new LinkedList<>();
            l1.add(0);
            l1.add("rgb(66, 218, 113)");
            List<Object> l2 = new LinkedList<>();
            l2.add(1);
            l2.add("rgb(80, 140, 200)");
            gradientColors.add(l1);
            gradientColors.add(l2);
            chart.backgroundColor = HIColor.initWithLinearGradient(gradientPoints, gradientColors);
            chart.borderRadius = 6;
            chart.type = options.get("chartType");
            hioptions.chart = chart;

            HIExporting exporting = new HIExporting();
            exporting.enabled = Boolean.valueOf(options.get("export"));
            hioptions.exporting = exporting;

            HINavigation navigation = new HINavigation();
            navigation.buttonOptions = new HIButtonOptions();
            navigation.buttonOptions.symbolStroke = HIColor.initWithRGBA(255, 255, 255, 0.4);
            HITheme theme = new HITheme();
            theme.fill = "rgba(0,0,0,0.0)";
            navigation.buttonOptions.theme = theme;
            hioptions.navigation = navigation;

            HIPlotOptions plotOptions = new HIPlotOptions();
            plotOptions.column = new HIColumn();
            plotOptions.column.color = HIColor.initWithRGBA(255, 255, 255, 0.6);
            plotOptions.column.borderRadius = 2;
            plotOptions.column.borderWidth = 0;
            hioptions.plotOptions = plotOptions;

            HICredits credits = new HICredits();
            credits.text = "www.highcharts.com";
            HIStyle creditsStyle = new HIStyle();
            creditsStyle.color = "rgba(255, 255, 255, 0.6)";
            credits.style = creditsStyle;
            credits.enabled = true;
            hioptions.credits = credits;

            HITitle title = new HITitle();
            title.text = options.get("title");
            title.align = "left";
            HIStyle titleStyle = new HIStyle();
            titleStyle.fontFamily = "Arial";
            titleStyle.fontSize = "14px";
            titleStyle.color = "rgba(255, 255, 255, 0.6)";
            title.style = titleStyle;
            title.y = 16;
            hioptions.title = title;

            HISubtitle subtitle = new HISubtitle();
            subtitle.text = options.get("subtitle");
            HashMap<String, String> subtitleStyle = new HashMap<>();
            subtitleStyle.put("fontFamily", "Arial");
            subtitleStyle.put("fontSize", "9px");
            subtitleStyle.put("color", "rgba(255, 255, 255, 0.6)");
            subtitle.style = subtitleStyle;
            subtitle.align = "left";
            subtitle.y = 28;
            hioptions.subtitle = subtitle;

            HITooltip tooltip = new HITooltip();
            tooltip.headerFormat = "";
            hioptions.tooltip = tooltip;

            final HIXAxis xaxis = new HIXAxis();
            xaxis.tickColor = HIColor.initWithRGBA(255, 255, 255, 0.0);
            xaxis.lineColor = HIColor.initWithRGBA(255, 255, 255, 0.3);
            xaxis.labels = new HILabels();
            HIStyle xLabelsStyle = new HIStyle();
            xLabelsStyle.color = "rgb(255, 255, 255)";
            xLabelsStyle.fontSize = "10px";
            xLabelsStyle.fontFamily = "Arial";
            xaxis.labels.style = xLabelsStyle;
            xaxis.labels.step = step;
            xaxis.categories = new ArrayList<>(Arrays.asList(categories));
            hioptions.xAxis = new ArrayList<HIXAxis>(){{add(xaxis);}};

            final HIYAxis yaxis = new HIYAxis();
            yaxis.lineWidth = 1;
            yaxis.gridLineWidth = 0;
            yaxis.lineColor = HIColor.initWithRGBA(255, 255, 255, 0.0);
            yaxis.labels = new HILabels();
            HIStyle yLabelsStyle = new HIStyle();
            yLabelsStyle.color = "rgb(255, 255, 255)";
            yLabelsStyle.fontSize = "10px";
            yLabelsStyle.fontFamily = "Arial";
            yaxis.labels.style = yLabelsStyle;
            yaxis.labels.x = -5;
            yaxis.title = new HITitle();
            yaxis.title.text = "";
            hioptions.yAxis = new ArrayList<HIYAxis>(){{add(yaxis);}};

            final HIColumn column = new HIColumn();
            column.tooltip = new HITooltip();
            column.tooltip.headerFormat = "";
            column.tooltip.valueSuffix = " kcal";
            column.showInLegend = false;
            column.data = series;
            column.name = options.get("title");
            hioptions.series = new ArrayList<HISeries>(){{add(column);}};

            return hioptions;
        }

        if (options.get("chartType").equals("spline")){

            HIOptions hioptions = new HIOptions();

            HIChart chart = new HIChart();

            /*Gradient stuff for background color*/
            Map<String, Number> gradientPoints = new HashMap<>();
            gradientPoints.put("x1", 0);
            gradientPoints.put("y1", 0);
            gradientPoints.put("x2", 0);
            gradientPoints.put("y2", 1);
            List<List> gradientColors = new ArrayList<>();
            List<Object> l1 = new LinkedList<>();
            l1.add(0);
            l1.add("rgb(132, 103, 144)");
            List<Object> l2 = new LinkedList<>();
            l2.add(1);
            l2.add("rgb(163, 95, 103)");
            gradientColors.add(l1);
            gradientColors.add(l2);
            chart.backgroundColor = HIColor.initWithLinearGradient(gradientPoints, gradientColors);
            chart.borderRadius = 6;
            chart.type = options.get("chartType");
            hioptions.chart = chart;

            HIExporting exporting = new HIExporting();
            exporting.enabled = Boolean.valueOf(options.get("export"));
            hioptions.exporting = exporting;

            HINavigation navigation = new HINavigation();
            navigation.buttonOptions = new HIButtonOptions();
            navigation.buttonOptions.symbolStroke = HIColor.initWithRGBA(255, 255, 255, 0.4);
            HITheme theme = new HITheme();
            theme.fill = "rgba(0,0,0,0.0)";
            navigation.buttonOptions.theme = theme;
            hioptions.navigation = navigation;

            HIPlotOptions plotOptions = new HIPlotOptions();
            plotOptions.spline = new HISpline();
            plotOptions.spline.color = HIColor.initWithRGBA(255, 255, 255, 0.6);
            hioptions.plotOptions = plotOptions;

            HICredits credits = new HICredits();
            credits.text = "www.highcharts.com";
            HIStyle creditsStyle = new HIStyle();
            creditsStyle.color = "rgba(255, 255, 255, 0.6)";
            credits.style = creditsStyle;
            credits.enabled = true;
            hioptions.credits = credits;

            HITitle title = new HITitle();
            title.text = options.get("title");
            title.align = "left";
            HIStyle titleStyle = new HIStyle();
            titleStyle.fontFamily = "Arial";
            titleStyle.fontSize = "14px";
            titleStyle.color = "rgba(255, 255, 255, 0.6)";
            title.style = titleStyle;
            title.y = 16;
            hioptions.title = title;

            HISubtitle subtitle = new HISubtitle();
            subtitle.text = options.get("subtitle");
            if (subtitle.text.length() > 0) {
                subtitle.text = subtitle.text + " total";
            }
            subtitle.align = "left";
            HashMap<String, String> subtitleStyle = new HashMap<>();
            subtitleStyle.put("fontFamily", "Arial");
            subtitleStyle.put("fontSize", "10px");
            subtitleStyle.put("color", "rgba(255, 255, 255, 0.6)");
            subtitle.style = subtitleStyle;
            subtitle.y = 28;
            hioptions.subtitle = subtitle;

            HITooltip tooltip = new HITooltip();
            tooltip.headerFormat = "";
            hioptions.tooltip = tooltip;

            final HIXAxis xaxis = new HIXAxis();
            xaxis.tickColor = HIColor.initWithRGBA(255, 255, 255, 0.0);
            xaxis.lineColor = HIColor.initWithRGBA(255, 255, 255, 0.3);
            xaxis.labels = new HILabels();
            HIStyle xLabelsStyle = new HIStyle();
            xLabelsStyle.color = "rgb(255, 255, 255)";
            xLabelsStyle.fontFamily = "Arial";
            xLabelsStyle.fontSize = "10px";
            xaxis.labels.style = xLabelsStyle;
            xaxis.labels.step = step;
            xaxis.categories = new ArrayList<>(Arrays.asList(categories));
            hioptions.xAxis = new ArrayList<HIXAxis>(){{add(xaxis);}};

            final HIYAxis yaxis = new HIYAxis();
            yaxis.lineWidth = 1;
            yaxis.gridLineWidth = 0;
            yaxis.lineColor = HIColor.initWithRGBA(255, 255, 255, 0.3);
            yaxis.labels = new HILabels();
            HIStyle yLabelsStyle = new HIStyle();
            yLabelsStyle.color = "rgb(255, 255, 255)";
            yLabelsStyle.fontFamily = "Arial";
            yLabelsStyle.fontSize = "10px";
            yaxis.labels.style = yLabelsStyle;
            yaxis.labels.x = -5;
            yaxis.title = new HITitle();
            yaxis.title.text = "";
            hioptions.yAxis = new ArrayList<HIYAxis>(){{add(yaxis);}};

            final HISpline spline = new HISpline();
            spline.tooltip = new HITooltip();
            spline.tooltip.headerFormat = "";
            spline.tooltip.valueSuffix = " kcal";
            spline.showInLegend = false;
            spline.data = series;
            spline.name = options.get("title");
            hioptions.series = new ArrayList<HISeries>(){{add(spline);}};

            return hioptions;
        }

        return null;
    }
}
