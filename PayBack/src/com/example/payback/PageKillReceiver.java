package com.example.payback;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PageKillReceiver extends BroadcastReceiver
{
	static Activity activityInstance;

	@Override
	public void onReceive(Context context, Intent intent)
	{
		
		Activity a = PageKillReceiver.getActivityInstance();		//Gets the activity to kill
		
//		Toast.makeText(context, "Test Toast", Toast.LENGTH_LONG).show();
		//Acts like a print statement when testing
		
		Intent intentToStart = new Intent(a.getApplicationContext(), LoginActivity.class);
		a.startActivity(intentToStart);
		
		a.finish();												//Kills the activity
	}
	
	public PageKillReceiver()
	{
	}
	
	public void setActivityInstance(Activity ai)
	{
		activityInstance = ai;
	}
	
	public static Activity getActivityInstance()
	{
		return activityInstance;
	}
	
}
