<p align="center" >
<img src="https://github.com/highcharts/highcharts-android/blob/master/Images/logo.png" alt="Highcharts" title="Highcharts">
</p>

[ ![Download](https://api.bintray.com/packages/highsoft/Highcharts/Highcharts/images/download.svg) ](https://bintray.com/highsoft/Highcharts/Highcharts/_latestVersion)

[Highcharts Android](http://www.highcharts.com/blog/mobile/) is a delightful wrapper of HighchartsJS for Android.

The most popular, robust and battle-tested JavaScript Charting library is now available for Android with our new Java wrapper. Get gorgeous, multi-touch charts with minimal effort.

# Documentation

Access the full [API documentation](https://api.highcharts.com/android/highcharts/) here.

# HOWTO

Here we present how to create basic chart and place it in your project.


## What we need to do

  - Prepare your project for Highcharts
  - Create chart view and place it in your view
  - Create chart options and add them to your chart view
  - Run your app and enjoy!


## Preparing your project

- First of all download the Highcharts framework.

**A)** You can add the library to the gradle dependecies from Bintray:

Add the Highcharts repository to your **build.gradle** file:
```gradle
repositories {
    maven {
        url "https://highsoft.bintray.com/Highcharts"
    }
}
```
and following to the dependencies in your app **build.gradle** file:
```gradle
dependencies {
    compile 'com.highsoft.highcharts:highcharts:7.0.3'
}
```
**B)** You can download the _aar_ from [here](https://github.com/highcharts/highcharts-android/releases) and add it manually if you want. Put the _aar_ in the _libs_ folder in your project structure:

![Project structure screenshot](https://github.com/highcharts/highcharts-android/blob/master/Images/1.png "Project structure")

Then, add the following lines to your **build.gradle** file in `repositories`:
```gradle
repositories {
    flatDir {
        dirs 'libs'
    }
}
```
and following to the dependencies in your app **build.gradle** file:
```gradle
dependencies {
    compile (name: 'highcharts-release', ext:'aar')
    compile 'com.google.code.gson:gson:2.8.1'
}
```

You are now set to use Highcharts!


## Using Highcharts (demo app)

### Prepare the layout

At first, you need to create a **view** for your chart. Go to your `activity_main.xml` and add this to your layout:
```xml
<com.highsoft.highcharts.Core.HIChartView
   android:id="@+id/hc"
   android:layout_width="match_parent"
   android:layout_height="match_parent" />

```

### Add HIChartView to your Activity

In your **MainActivity.java** import Highcharts at the top

```java
import com.highsoft.highcharts.Core.*;
import com.highsoft.highcharts.Common.HIChartsClasses.*;
```

Next, in **onCreate** add the **HIChartView** following line:
```java
HIChartView chartView = (HIChartView) findViewById(R.id.hc);
```

This will create our chartView with the size defined in **layout**.

Done! Now let's create a chart!

### Create the chart

Let's start with creating simple chart!

For the purpose of this tutorial, we will create a simple column chart using random data.

The heart of a chart is **HIOptions** class which contains all the information needed to present it. Some of the options there are optional, some are not (see demo app [HighFit](https://github.com/highcharts/highcharts-android/tree/master/Example/HighFit) provided by Highcharts).

Create instance of **HIOptions** class
```java
HIOptions options = new HIOptions();
```
Now we need to add the options that our chart requires to be presented. Let's start with **chart type**. To do so, create HIChart class object and set its type to "column"
```java
HIChart chart = new HIChart();
chart.setType("column");
```
Add this object to your **options**
```java
options.setChart(chart);
```
Then let's give our chart a name (title) and also add it to **options**
```java
HITitle title = new HITitle();
title.setText("Demo chart");

options.setTitle(title);
```
Now we need to add some data (in this tutorial it will be some random set of numbers). Since we are creating a **column** chart, we need to use **HIColumn** data series
```java
HIColumn series = new HIColumn();
```
To add data, just create an ArrayList with our data objects
```java
series.setData(new ArrayList<>(Arrays.asList(49.9, 71.5, 106.4, 129.2, 144, 176, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4)));
```
Since options can store multiple series, we need to add our series as one-element ArrayList
```java
options.setSeries(new ArrayList<HISeries>(Collections.singletonList(series)));
```
And at last add our **options** to the chartView
```java
chartView.setOptions(options);
```

That's it! We are now set to run our application!
Your **MainActivity.java** file should look like this
```java
package com.highsoft.highchartsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.highsoft.highcharts.Core.*;
import com.highsoft.highcharts.Common.HIChartsClasses.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HIChartView chartView = (HIChartView) findViewById(R.id.hc);

        HIOptions options = new HIOptions();

        HIChart chart = new HIChart();
        chart.setType("column");
        options.setChart(chart);

        HITitle title = new HITitle();
        title.setText("Demo chart");

        options.setTitle(title);

        HIColumn series = new HIColumn();
        series.setData(new ArrayList<>(Arrays.asList(49.9, 71.5, 106.4, 129.2, 144, 176, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4)));
        options.setSeries(new ArrayList<HISeries>(Collections.singletonList(series)));

        chartView.setOptions(options);
    }
}
```
## ***Press "Run" in Android Studio.***
#### For more complex solutions see demo app [HighFit](https://github.com/highcharts/highcharts-android/tree/master/Example/HighFit) provided by Highcharts or read the following [documentation](https://api.highcharts.com/android/highcharts/)!

# Additional info
#### Export module requirements
Please note that if you plan to use export module, you need to put specific _provider_ in your app manifest:
```xml
<provider android:authorities="com.your.package.name.FileProvider"
   android:name="android.support.v4.content.FileProvider"
   android:exported="false"
   android:grantUriPermissions="true">
   <meta-data
       android:name="android.support.FILE_PROVIDER_PATHS"
       android:resource="@xml/provider_paths"/>
</provider>
```
and the needed _provider_paths_ file
```xml
<?xml version="1.0" encoding="utf-8"?>
<paths>
   <files-path name="export" path="." />
</paths>
```
into _xml_ folder here:

![XML folder location](https://github.com/highcharts/highcharts-android/blob/master/Images/2.png "XML folder location")


#### HIColor example
Highcharts Android wrapper provides its own colors implementation. As you can notice, some options are of `HIColor` type. You can instantiate the desired color in few ways which are described in the [API documentation](https://api.highcharts.com/android/highcharts/). In here, we will show the most complex case which is gradient usage.
First, you need to create the `HIGradient` (you can use base constructor with default values too):
```java
HIGradient gradient = new HIGradient(0, 0.5f, 1, 1);
```
Next, you should define `HIStop`'s:
```java
LinkedList<HIStop> stops = new LinkedList<>();
stops.add(new HIStop(0.4f, HIColor.initWithRGB(160, 160, 160)));
stops.add(new HIStop(1, HIColor.initWithRGB(60, 60, 60)));
```
Now you can instantiate a color thanks to those objects for, let's say, chart background:
```java
HIChart chart = new HIChart();
chart.setBackgroundColor(HIColor.initWithLinearGradient(gradient, stops));
```
#### HIFunction example
Thanks to Highcharts Android wrapper you can now assign native Android callbacks to on click events for specific chart elements. We will show you a small taste of such usage. For these purpose we will let appear a simple Toast with point coordinates when it's clicked but keep in mind that you can achieve much more with `HIFunction` mechanism!

First of all, you need to create a series:
```java
HISpline spline = new HISpline();
spline.setData(new ArrayList<>(Arrays.asList(0.3,5.3,8.0,9.1,3.2,5.4,4.0,4.2,2.1,10.0)));
```
Now, you can refer to the point event and add on click callback like this:
```java
spline.setPoint(new HIPoint());
spline.getPoint().setEvents(new HIEvents());
spline.getPoint().getEvents().setClick(new HIFunction(
        f -> {
            Toast t = Toast.makeText(
                this,
                "Clicked point [ " + f.getProperty("x") + ", " + f.getProperty("y") + " ]",
                Toast.LENGTH_SHORT);
                t.show();
            },
            new String[] {"x", "y"}
));
```
As you can see in the above code snippet first argument of the `HIFunction` is the actual callback defined in the lambda expression. Second argument is simple array of chart elements. We need to put them here to let wrapper pull them for us during `HIFunction` instantiation. Thanks to this, we can refer to these elements corresponding values by `getProperty()` method. You can pull any data from chart like this. Depending on the current needs you can just run some code, withdraw data from chart, return a String to the chart (e.g. in HITooltip formatter) and even put pure Javascript function in the constructor in the String format. For more information feel free to check the [API documentation](https://api.highcharts.com/android/highcharts/).
#### Custom fonts
Highcharts Android wrapper allows you to add custom fonts. If you have your own font and want to use that in your chart follow next steps:
Add a font file to your project. Select File -> Add Files to “Your Project Name” from the menu bar or drag and drop the file into your Xcode project, check Copy items if needed option and add the font to your app target.

- Add a font file to your project.

![Fonts location](https://github.com/highcharts/highcharts-android/blob/master/Images/3.png "Fonts in project structure")
- Add your font to `HIChartView`. To do this, pass your font resource to your `HIChartView`:
```Java
HIChartView chartView = findViewById(R.id.hc);
chartView.addFont(R.font.griphite);
```
So, now you can use a custom font in your chart. For example, let's change the chart title font. You only need to create a style object for the title and set its font family to the font file name:
```Java
HICSSObject style = new HICSSObject();
style.setFontFamily("griphite");
HITitle title = new HITitle();
title.setStyle(style);
```
