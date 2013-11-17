package com.example.payback;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class LineGraph {

	public Intent getTestIntent(Context context) {
		// Our first data
		int[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // x values!
		int[] y =  { 30, 34, 45, 57, 77, 89, 100, 111 ,123 ,145 }; // y values!
		TimeSeries series = new TimeSeries("Line1"); 
		for( int i = 0; i < x.length; i++)
		{
			series.add(x[i], y[i]);
		}
		
		// Our second data
		int[] x2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // x values!
		int[] y2 =  { 145, 123, 111, 100, 89, 77, 57, 45, 34, 30}; // y values!
		TimeSeries series2 = new TimeSeries("Line2"); 
		for( int i = 0; i < x2.length; i++)
		{
			series2.add(x2[i], y2[i]);
		}
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		dataset.addSeries(series2);
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); // Holds a collection of XYSeriesRenderer and customizes the graph
		XYSeriesRenderer renderer = new XYSeriesRenderer(); // This will be used to customize line 1
		XYSeriesRenderer renderer2 = new XYSeriesRenderer(); // This will be used to customize line 2
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.addSeriesRenderer(renderer2);
		
		// Customization time for line 1!
		renderer.setColor(Color.WHITE);
		renderer.setPointStyle(PointStyle.SQUARE);
		renderer.setFillPoints(true);
		// Customization time for line 2!
		renderer2.setColor(Color.YELLOW);
		renderer2.setPointStyle(PointStyle.DIAMOND);
		renderer2.setFillPoints(true);
		
		Intent intent = ChartFactory.getLineChartIntent(context, dataset, mRenderer, "Line Graph Title");
		return intent;
		
	}

	public Intent getOneLineIntent(Context context, int[] x, int[] y) {
		TimeSeries series = new TimeSeries("Line1"); 
		for( int i = 0; i < x.length; i++)
		{
			series.add(x[i], y[i]);
		}
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		
		Intent intent = createIntent(context, dataset);
		return intent;
		
	}

	public Intent createIntent(Context context, XYMultipleSeriesDataset dataset) {
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); // Holds a collection of XYSeriesRenderer and customizes the graph
		XYSeriesRenderer renderer = new XYSeriesRenderer(); // This will be used to customize line 1
		mRenderer.addSeriesRenderer(renderer);
		
		// Customization time for line 1!
		renderer.setColor(Color.WHITE);
		renderer.setPointStyle(PointStyle.SQUARE);
		renderer.setFillPoints(true);
		
		Intent intent = ChartFactory.getLineChartIntent(context, dataset, mRenderer, "Line Graph Title");
		return intent;
		
	}
	
	public GraphicalView getTestView(Context context) {
		// Our first data
		int[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // x values!
		int[] y =  { 30, 34, 45, 57, 77, 89, 100, 111 ,123 ,145 }; // y values!
		TimeSeries series = new TimeSeries("Line1"); 
		for( int i = 0; i < x.length; i++)
		{
			series.add(x[i], y[i]);
		}
		
		// Our second data
		int[] x2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // x values!
		int[] y2 =  { 145, 123, 111, 100, 89, 77, 57, 45, 34, 30}; // y values!
		TimeSeries series2 = new TimeSeries("Line2"); 
		for( int i = 0; i < x2.length; i++)
		{
			series2.add(x2[i], y2[i]);
		}
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		dataset.addSeries(series2);
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); // Holds a collection of XYSeriesRenderer and customizes the graph
		XYSeriesRenderer renderer = new XYSeriesRenderer(); // This will be used to customize line 1
		XYSeriesRenderer renderer2 = new XYSeriesRenderer(); // This will be used to customize line 2
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.addSeriesRenderer(renderer2);
		
		mRenderer.setBackgroundColor(Color.BLACK);
		mRenderer.setGridColor(Color.BLACK);
		mRenderer.setMarginsColor(Color.BLACK);
		mRenderer.setPanEnabled(false);
		
		// Customization time for line 1!
		renderer.setColor(Color.WHITE);
		renderer.setPointStyle(PointStyle.SQUARE);
		renderer.setFillPoints(true);
		// Customization time for line 2!
		renderer2.setColor(Color.YELLOW);
		renderer2.setPointStyle(PointStyle.DIAMOND);
		renderer2.setFillPoints(true);
		
		return ChartFactory.getLineChartView(context, dataset, mRenderer);
		
	}
	
}
