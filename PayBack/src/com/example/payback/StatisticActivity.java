package com.example.payback;

import org.achartengine.GraphicalView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

public class StatisticActivity extends TitleActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		modifyTitle("Transaction Statistics",R.layout.activity_statistic);
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
//    	Intent lineIntent = line.getTestIntent(this);
//      startActivity(lineIntent);
    	
    	//Display the graph in the current view
    	GraphicalView gView = line.getTestView(this);
    	
    	LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
    	layout.addView(gView);
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

}
