package com.example.payback;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NoBackingReceiver extends BroadcastReceiver
{
	static Activity activityInstance;

	@Override
	public void onReceive(Context context, Intent intent)
	{
		
		Activity a = PageKillReceiver.getActivityInstance();		//Gets the activity to kill
		
		//Toast.makeText(context, "Test Toast", Toast.LENGTH_LONG).show();
		//Acts like a print statement when testing
		
		Intent intentToStart = new Intent(a.getApplicationContext(), MainActivity.class);
		a.startActivity(intentToStart);
		
		a.finish();												//Kills the activity
	}
	
	public NoBackingReceiver()
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
