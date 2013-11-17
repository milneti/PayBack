package com.example.payback;

import org.achartengine.GraphicalView;
import org.achartengine.model.TimeSeries;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StatisticActivity extends TitleActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		modifyTitle("Transaction Statistics",R.layout.activity_statistic);
		displayTestValue();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.statistic, menu);
		return true;
	}
    
    public void lineGraphHandler (View view)
    {
    	LineGraph line = new LineGraph();
    	//Open a new activity to display the graph
    	Intent lineIntent = line.getTestIntent(this);
      startActivity(lineIntent);
    }
    
    public void barGraphHandler (View view)
    {
    	BarGraph bar = new BarGraph();
    	Intent lineIntent = bar.getTestIntent(this);
        startActivity(lineIntent);
    }
    
    public void pieGraphHandler (View view)
    {
    	PieGraph pie = new PieGraph();
    	Intent lineIntent = pie.getTestIntent(this);
        startActivity(lineIntent);
    }
    
    public void scatterGraphHandler (View view)
    {
    	ScatterGraph scatter = new ScatterGraph();
    	Intent lineIntent = scatter.getTestIntent(this);
        startActivity(lineIntent);
    }
    
    public void displayTestValue ()
    {    	
    	double totalPayables = getTotalPayables();
    	double totalReceivables = getTotalReceivables();
    	TextView payableTextView = (TextView) findViewById(R.id.totalPayables);
    	payableTextView.append(String.format("$%.2f", totalPayables));
    	TextView receivableTextView = (TextView) findViewById(R.id.totalReceivables);
    	receivableTextView.append(String.format("$%.2f", totalReceivables));
    	
//    	LineGraph line = new LineGraph();
//    	//Display graph in view
//    	GraphicalView gView = line.getTestView(this);
//    	LinearLayout chartView = (LinearLayout) findViewById(R.id.chart);
//    	chartView.addView(gView); 
    	
    	PieGraph pie = new PieGraph();
    	GraphicalView gView = pie.getTestView(this);
    	LinearLayout chartView = (LinearLayout) findViewById(R.id.chart);
    	chartView.addView(gView);
    }
    
    public double getTotalPayables ()
    {
    	return 5.00;
    }
    
    public double getTotalReceivables ()
    {
    	return 10.00;
    }
    
    public double getPayablesOverTime ()
    {
		int[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // x values!
		int[] y =  { 30, 34, 45, 57, 77, 89, 100, 111 ,123 ,145 }; // y values!
		TimeSeries series = new TimeSeries("Line1"); 
		for( int i = 0; i < x.length; i++)
		{
			series.add(x[i], y[i]);
		}
		
    	return 5.00;
    }
    
    public double getReceivablesOverTime ()
    {
		int[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // x values!
		int[] y =  { 30, 34, 45, 57, 77, 89, 100, 111 ,123 ,145 }; // y values!
		TimeSeries series = new TimeSeries("Line1"); 
		for( int i = 0; i < x.length; i++)
		{
			series.add(x[i], y[i]);
		}
		
    	return 5.00;
    }

}
