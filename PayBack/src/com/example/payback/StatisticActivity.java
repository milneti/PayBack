package com.example.payback;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

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
    	Intent lineIntent = line.getTestIntent(this);
        startActivity(lineIntent);
    }
    
    public void barGraphHandler (View view)
    {
    	BarGraph bar = new BarGraph();
    	Intent lineIntent = bar.getTestIntent(this);
        startActivity(lineIntent);
    }

}
