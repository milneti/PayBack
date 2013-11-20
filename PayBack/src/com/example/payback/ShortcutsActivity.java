package com.example.payback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;

public class ShortcutsActivity extends Activity
{
	static Activity activityInstance;	//these are variables
	static PageKillReceiver pkr;		//used for PageKillReceiver.java
	static IntentFilter filter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shortcuts);
		
		activityInstance = this;
		pkr = new PageKillReceiver(); pkr.setActivityInstance(activityInstance);
		filter = new IntentFilter();
		filter.addAction("com.Payback.Logout_Intent");
		registerReceiver(pkr, filter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shortcuts, menu);
		return true;
	}
	public void Return(View view)
    {
    }
	public void showMainMenu(View view)
    {
    	Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
	public void showCreateTransaction(View view)
    {
    	Intent intent = new Intent(this, Transaction1Activity.class);
        startActivity(intent);
        this.finish();
    }
	public void showContacts(View view)
    {
    	Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
        this.finish();
    }public void showLogout(View view)
    {
    	Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }
}
