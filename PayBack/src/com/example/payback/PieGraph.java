package com.example.payback;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class PieGraph {

	public Intent getTestIntent(Context context) {

		int[] values = { 1, 2, 3, 4, 5 };
		CategorySeries series = new CategorySeries("Pie Graph");
		int k = 0;
		for (int value : values) {
			series.add("Section " + ++k, value);
		}

		int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN };

		DefaultRenderer renderer = new DefaultRenderer();
		for (int color : colors) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);
		}
		renderer.setChartTitle("Pie Chart Demo");
		renderer.setChartTitleTextSize(7);
		renderer.setZoomButtonsVisible(true);

		Intent intent = ChartFactory.getPieChartIntent(context, series, renderer, "Pie");
		return intent;
	}
	
	public GraphicalView getTestView(Context context) {

		int[] values = { 1, 2, 3, 4, 5 };
		CategorySeries series = new CategorySeries("Pie Graph");
		int k = 0;
		for (int value : values) {
			series.add("Section " + ++k, value);
		}

		int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN };

		DefaultRenderer renderer = new DefaultRenderer();
		for (int color : colors) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);
		}
		renderer.setChartTitle("Pie Chart Demo");
		renderer.setChartTitleTextSize(7);
		renderer.setZoomButtonsVisible(true);

		return ChartFactory.getPieChartView(context, series, renderer);
	}
	
	public GraphicalView getTwoSectionView(Context context, double standingPayables, double standingReceivables) {

		CategorySeries series = new CategorySeries("Payables and Receivables");
		series.add("Payables", standingPayables);
		series.add("Receivables", standingReceivables);

		int[] colors = new int[] { Color.BLUE, Color.GREEN };

		DefaultRenderer renderer = new DefaultRenderer();
		for (int color : colors) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);
		}
		renderer.setChartTitle("Payables versus Receivables");
		renderer.setChartTitleTextSize(7);
		renderer.setZoomButtonsVisible(true);

		return ChartFactory.getPieChartView(context, series, renderer);
	}
}
