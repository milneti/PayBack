package com.example.payback;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class StatisticActivity extends TitleActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		modifyTitle("Select Contacts",R.layout.activity_statistic);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.statistic, menu);
		return true;
	}

}
