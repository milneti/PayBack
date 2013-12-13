package com.example.payback;

import java.util.ArrayList;
import java.util.Iterator;

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
    
    public void displayValue ()
    {   
	    ArrayList<BaseTransaction> transBorrow = user.getTransBorrow();
	    ArrayList<BaseTransaction> transLend = user.getTransLend();
	    
    	double currentPayable = getcurrentPayable(transBorrow);
    	double currentReceivable = getcurrentReceivable(transLend);
//    	
    	double totalPayable = getTotalPayable(transBorrow);
    	double totalReceivable = getTotalReceivable(transLend);
    	
    	int numTransaction = transBorrow.size();
    	
    	
    	//Append value to textview
    	TextView numTransactionTextView = (TextView) findViewById(R.id.numTransaction);
    	numTransactionTextView.append(Integer.toString(numTransaction));
    	TextView currentPayableTextView = (TextView) findViewById(R.id.currentPayable);
    	currentPayableTextView.append(String.format("$%.2f", currentPayable));
    	TextView currentReceivableTextView = (TextView) findViewById(R.id.currentReceivable);
    	currentReceivableTextView.append(String.format("$%.2f", currentReceivable));
    	TextView TotalPayableTextView = (TextView) findViewById(R.id.totalPayable);
    	TotalPayableTextView.append(String.format("$%.2f", totalPayable));
    	TextView TotalReceivableTextView = (TextView) findViewById(R.id.totalReceivable);
    	TotalReceivableTextView.append(String.format("$%.2f", totalReceivable));
    	
    	//Display Pie Graph
    	PieGraph pie = new PieGraph();
    	GraphicalView gView = pie.getTwoSectionView(this, totalPayable, totalReceivable);
    	LinearLayout chartView = (LinearLayout) findViewById(R.id.chart);
    	chartView.addView(gView);
    }
    
    public double getcurrentPayable (ArrayList<BaseTransaction> transBorrow)
    {
    	double currentPayable = 0;
    	for(int i = 0; i < transBorrow.size(); i++)
    	{
    		if(!transBorrow.get(i).getResolved())
    			currentPayable += transBorrow.get(i).getAmount();
    	}
    	
    	return currentPayable;
    }
    
    public double getcurrentReceivable (ArrayList<BaseTransaction> transLend)
    {
    	double currentReceivable = 0;
    	for(int i = 0; i < transLend.size(); i++)
    	{
    		if(!transLend.get(i).getResolved())
    			currentReceivable += transLend.get(i).getAmount();
    	}
    	
    	return currentReceivable;
    }
    
    public double getTotalPayable (ArrayList<BaseTransaction> transBorrow)
    {
    	double totalPayable = 0;
    	for(int i = 0; i < transBorrow.size(); i++)
    	{
    		totalPayable += transBorrow.get(i).getAmount();
    	}
    	
    	return totalPayable;
    }
    
    public double getTotalReceivable (ArrayList<BaseTransaction> transLend)
    {
    	double totalReceivable = 0;
    	Iterator<BaseTransaction> i1 = transLend.iterator();
    	while(i1.hasNext())
    	{
    		totalReceivable += i1.next().getAmount();
    	}
    	
    	return totalReceivable;
    }


    public void displayTransactionsHistory (View view)
    {
    	LineGraph line = new LineGraph();
    	TimeSeries payableSeries = getPayableSeries("Payable");
    	TimeSeries receivableSeries = getReceivableSeries("Receivable");
    	
    	//Open a new activity to display the graph
    	Intent lineIntent = line.getTwoLineIntent(this, payableSeries, receivableSeries, "Transactions History");
    	startActivity(lineIntent);
    }    
    
    public TimeSeries getPayableSeries (String seriesName)
    {
	    ArrayList<BaseTransaction> transBorrow = user.getTransBorrow();

//    	int[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // x values!
//		double[] y =  { 30, 34, 45, 57, 77, 89, 100, 111 ,123 ,145 }; // y values!
	    
    	int[] x = getPayableX(transBorrow); // x values!
		double[] y = getPayableY(transBorrow); // y values!
		
		TimeSeries series = new TimeSeries(seriesName); 
		for( int i = 0; i < x.length; i++)
		{
			series.add(x[i], y[i]);
		}
		
		return series;
    }
    
    public int[] getPayableX(ArrayList<BaseTransaction> transBorrow)
    {
    	int numTransaction = transBorrow.size();
    	
    	/*creating the x axis*/
    	int[] x = new int[numTransaction];
    	for(int i = 0; i < numTransaction; i++)
    	{
    		x[i] = i+1;
    	}
    	
    	return x;
    }
    
	public double[] getPayableY(ArrayList<BaseTransaction> transBorrow)
	{
    	int numTransaction = transBorrow.size();
    	
    	/*creating the y axis*/
    	double[] y = new double[numTransaction];
    	for(int i = 0; i < numTransaction; i++)
    	{
    		if(i == 0)
    		{
    			y[i] = transBorrow.get(i).getAmount();
    		}
    		y[i] = transBorrow.get(i).getAmount() + y[i-1];
    	}
    	
    	return y;
	}
    
	public TimeSeries getReceivableSeries (String seriesName)
	{
	    ArrayList<BaseTransaction> transBorrow = user.getTransBorrow();
	    ArrayList<BaseTransaction> transLend = user.getTransLend();
	    
//		int[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // x values!
//		int[] y =  { 145, 123, 111, 100, 89, 77, 57, 45, 34, 30}; // y values!
	    
    	int[] x = getReceivableX(transLend); // x values!
		double[] y = getReceivableY(transLend); // y values!
		
		TimeSeries series = new TimeSeries(seriesName); 
		for( int i = 0; i < x.length; i++)
		{
			series.add(x[i], y[i]);
		}
		
		return series;
	}
    
    public int[] getReceivableX(ArrayList<BaseTransaction> transLend)
    {
    	int numTransaction = transLend.size();
    	
    	/*creating the x axis*/
    	int[] x = new int[numTransaction];
    	for(int i = 0; i < numTransaction; i++)
    	{
    		x[i] = i+1;
    	}
    	
    	return x;
    }
    
	public double[] getReceivableY(ArrayList<BaseTransaction> transLend)
	{
    	int numTransaction = transLend.size();
    	
    	/*creating the y axis*/
    	double[] y = new double[numTransaction];
    	for(int i = 0; i < numTransaction; i++)
    	{
    		if(i == 0)
    		{
    			y[i] = transLend.get(i).getAmount();
    		}
    		y[i] = transLend.get(i).getAmount() + y[i-1];
    	}
    	
    	return y;
	}
	
    public void showMainMenu(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		this.finish();
	}

}
