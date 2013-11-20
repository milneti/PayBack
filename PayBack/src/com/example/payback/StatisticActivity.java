package com.example.payback;

import org.achartengine.GraphicalView;
import org.achartengine.model.TimeSeries;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StatisticActivity extends TitleActivity
{
	static Activity activityInstance;	//these are variables
	static PageKillReceiver pkr;		//used for PageKillReceiver.java
	static IntentFilter filter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		modifyTitle("Transaction Statistics",R.layout.activity_statistic);
		
		activityInstance = this;
		pkr = new PageKillReceiver(); pkr.setActivityInstance(activityInstance);
		filter = new IntentFilter();
		filter.addAction("com.Payback.Logout_Intent");
		registerReceiver(pkr, filter);
		
		displayValue();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.statistic, menu);
		return true;
	}
    
    public void displayTransactionsHistory (View view)
    {
    	LineGraph line = new LineGraph();
    	TimeSeries payableSeries = getPayableSeries("Payable");
    	TimeSeries receivableSeries = getReceivableSeries("Receivable");
    	
    	//Open a new activity to display the graph
    	Intent lineIntent = line.getTwoLineIntent(this, payableSeries, receivableSeries, "Transaction History");
    	startActivity(lineIntent);
    }    
    
    public void displayValue ()
    {    	
    	double standingPayables = getStandingPayables();
    	double standingReceivables = getStandingReceivables();
    	
    	double totalPayables = getTotalPayables();
    	double totalReceivables = getTotalReceivables();
    	
    	//Append value to textview
    	TextView standingPayableTextView = (TextView) findViewById(R.id.standingPayables);
    	standingPayableTextView.append(String.format("$%.2f", standingPayables));
    	TextView StandingReceivableTextView = (TextView) findViewById(R.id.standingReceivables);
    	StandingReceivableTextView.append(String.format("$%.2f", standingReceivables));
    	TextView TotalPayableTextView = (TextView) findViewById(R.id.totalPayables);
    	TotalPayableTextView.append(String.format("$%.2f", totalPayables));
    	TextView TotalReceivableTextView = (TextView) findViewById(R.id.totalReceivables);
    	TotalReceivableTextView.append(String.format("$%.2f", totalReceivables));
    	
//    	LineGraph line = new LineGraph();
//    	//Display graph in view
//    	GraphicalView gView = line.getTestView(this);
//    	LinearLayout chartView = (LinearLayout) findViewById(R.id.chart);
//    	chartView.addView(gView); 
    	
    	//Display Pie Graph
    	PieGraph pie = new PieGraph();
    	GraphicalView gView = pie.getTwoSectionView(this, totalPayables, totalReceivables);
    	LinearLayout chartView = (LinearLayout) findViewById(R.id.chart);
    	chartView.addView(gView);
    }
    
    public double getStandingPayables ()
    {
    	return 5;
    }
    
    public double getTotalPayables ()
    {
    	return 50;
    }
    
    public double getStandingReceivables ()
    {
    	return 10;
    }
    
    public double getTotalReceivables ()
    {
    	return 100;
    }
        
    public TimeSeries getPayableSeries (String seriesName)
    {
    	int[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // x values!
		int[] y =  { 30, 34, 45, 57, 77, 89, 100, 111 ,123 ,145 }; // y values!
		TimeSeries series = new TimeSeries(seriesName); 
		for( int i = 0; i < x.length; i++)
		{
			series.add(x[i], y[i]);
		}
		
		return series;
    }
    
	public TimeSeries getReceivableSeries (String seriesName)
	{
		int[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // x values!
		int[] y =  { 145, 123, 111, 100, 89, 77, 57, 45, 34, 30}; // y values!
		TimeSeries series = new TimeSeries(seriesName); 
		for( int i = 0; i < x.length; i++)
		{
			series.add(x[i], y[i]);
		}
		
		return series;
		
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
    public void showMainMenu(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		this.finish();
	}

}
