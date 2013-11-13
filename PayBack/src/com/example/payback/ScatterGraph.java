package com.example.payback;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class ScatterGraph{
	
	public Intent getTestIntent(Context context) {
		// Data 1
		int[] x = {1, 2, 3, 4, 5, 6 ,7, 8 ,9, 10};
		double[] values = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6 ,7.7, 8.8 ,9.9, 10.1};
	    XYSeries series = new XYSeries("Series 1"); 
	    for (int k = 0; k < x.length; k++) {
	    	series.add(x[k], values[k]);
	    }
		// Data 1
		int[] x2 = {1, 2, 3, 4, 5, 6 ,7, 8 ,9, 10};
		double[] values2 = {2.4, 3.5, 6.7, 3.5, 4.57, 6.7 ,9.7, 10.8 ,11.9, 14.1};
	    XYSeries series2 = new XYSeries("Series 2"); 
	    for (int k = 0; k < x2.length; k++) {
	    	series2.add(x2[k], values2[k]);
	    }
	    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	 	dataset.addSeries(series);
	 	dataset.addSeries(series2);
	 	
	 	
	    // Customization  for data 1
	    XYSeriesRenderer renderer = new XYSeriesRenderer();
	    renderer.setColor(Color.WHITE);
	    renderer.setPointStyle(PointStyle.DIAMOND);
	    renderer.setLineWidth(6);
	    // Customization for data 2
	    XYSeriesRenderer renderer2 = new XYSeriesRenderer();
	    renderer2.setColor(Color.YELLOW);
	    renderer2.setPointStyle(PointStyle.SQUARE);
	    renderer2.setLineWidth(6);
	    // Customization
	    XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
	    mRenderer.addSeriesRenderer(renderer);
	    mRenderer.addSeriesRenderer(renderer2);
	    
	    return ChartFactory.getScatterChartIntent(context, dataset, mRenderer);
	}
	
}
