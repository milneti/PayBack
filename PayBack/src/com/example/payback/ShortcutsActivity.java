package com.example.payback;

import java.util.ArrayList;

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

	public void Return(View view)
    {
		this.finish();
    }
	public void showMainMenu(View view)
    {
    	Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
	public void showCreateTransaction(View view)
    {
		int transCostInt = 0;
    	String transCommentString = "";
    	ArrayList<Friend> transselected = new ArrayList<Friend>();
 	    int translenderamountInt = 0;
 	    ArrayList<Integer> lendsharelist = new ArrayList<Integer>();
 	    boolean button1Selected = true;
 	    boolean button2Selected = false;
    	
    	Intent intent = new Intent(this, Transaction1Activity.class);
 	    Bundle Bundle = new Bundle();
        
        Bundle.putInt("Transaction1transCost", transCostInt);
        Bundle.putString("Transaction1transComment", transCommentString);
        Bundle.putParcelableArrayList("Transaction2selected", transselected);
        Bundle.putInt("Transaction3lenderamount", translenderamountInt);
        Bundle.putIntegerArrayList("Transaction3borroweramountlist", lendsharelist);
        Bundle.putBoolean("Transaction3button1Selected", button1Selected);
        Bundle.putBoolean("Transaction3button2Selected", button2Selected);
    
        intent.putExtras(Bundle);
        startActivity(intent);
        this.finish();
    }
	public void showContacts(View view)
    {
    	Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
        this.finish();
    }
	public void showLogout(View view)
    {
    	Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }
}
