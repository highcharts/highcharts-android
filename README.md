<p align="center" >
<img src="https://github.com/highcharts/highcharts-android/blob/master/Images/logo.png" alt="Highcharts" title="Highcharts">
</p>

[Highcharts Android](http://www.highcharts.com/blog/mobile/) is a delightful wrapper of HighchartsJS for Android.

The most popular, robust and battle-tested JavaScript Charting library is now available for Android with our new Java wrapper. Get gorgeous, multi-touch charts with minimal effort.

## Documentation

Access the full [API documentation](https://api.highcharts.com/android/highcharts/) here.

# HOWTO

Here we present how to create basic chart and place it in your project.


## What we need to do

  - Prepare your project for Highcharts
  - Create chart view and place it in your view
  - Create chart options and add them to your chart view
  - Run your app and enjoy!


## Preparing your project

- First of all download Highcharts framework. 

**A)** You can download the _aar_ from here: [Highcharts](https://github.com/highcharts/highcharts-android/blob/master/highcharts-release.aar) and add it manually. Put the _aar_ in the _libs_ folder in your project structure:

![alt text](https://github.com/highcharts/highcharts-android/blob/master/Images/1.png "Files1")

Then, add the following lines to your project **build.gradle** file:

    repositories {
        flatDir {
            dirs ‘libs’
	    }
    }

and following to the dependencies in your app **build.gradle** file:

    dependencies {
        compile (name: 'highcharts-release', ext:'aar')
	compile 'com.google.code.gson:gson:2.8.0'
    }

**B)** **Not available yet**

 You can add the library to the gradle dependecies from JCenter like this:

    dependencies {
        compile 'com.highsoft.highcharts:6.0.2'
	compile 'com.google.code.gson:gson:2.8.0'
    }  

- Please note that if you plan to use export module, you need to put specific _provider_ in your app manifest:

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

![alt text](https://github.com/highcharts/highcharts-android/blob/master/Images/2.png "Files2")

You are now set to use Highcharts!

## Using Highcharts (demo app)

### Prepare the layout

At first, you need to create a **view** for your chart. Go to your `activity_main.xml` and add this to your layout:
```
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
HIGChartView chartView = (HIGChartView) findViewById(R.id.hc);
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
chart.type = "column";
```
Add this object to your **options**
```java
options.chart = chart;
```
Then let's give our chart a name (title) and also add it to **options**
```java
HITitle title = new HITitle();
title.text = "Demo chart";

options.title = title;
```
Now we need to add some data (in this tutorial it will be some random set of numbers). Since we are creating a **column** chart, we need to use **HIColumn** data series
```java
HIColumn series = new HIColumn();
```
To add data, just create array an ArrayList with our data objects
```java
series.data = new ArrayList<>(Arrays.asList(49.9, 71.5, 106.4, 129.2, 144, 176, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4));
```
Since options can store multiple series, we need to add our series as one-element ArrayList
```java
options.series = new ArrayList<HISeries>(Collections.singletonList(series));
```
And at last add our **options** to the chartView
```java
chartView.options = options;
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
        chart.type = "column";
        options.chart = chart;

        HITitle title = new HITitle();
        title.text = "Demo chart";

        options.title = title;

        HIColumn series = new HIColumn();
        series.data = new ArrayList<>(Arrays.asList(49.9, 71.5, 106.4, 129.2, 144, 176, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4));
        options.series = new ArrayList<HISeries>(Collections.singletonList(series));

        chartView.options = options;
    }
}

```

## ***Press "Run" in your IDE.***
#### For more complex solutions see demo app [HighFit](https://github.com/highcharts/highcharts-android/tree/master/Example/HighFit) provided by Highcharts or read the following [documentation](https://api.highcharts.com/android/highcharts/)!

