package com.example.payback;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Transaction5Activity extends TitleActivity 
{
	ListView listView;
	List<String> data = new ArrayList<String>();
	
	static Activity activityInstance;	
	static PageKillReceiver pkr;		//these are the variables
	static IntentFilter filterPKR;		//used for PageKillReceiver.java
	static NoBackingReceiver nbr;		//these are the variables
	static IntentFilter filterNBR;		//used for NoBackingReceiver.java
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		modifyTitle("Transaction Summary",R.layout.activity_transaction5);
		
		activityInstance = this;

		//----- broadcasts -----
		pkr = new PageKillReceiver(); pkr.setActivityInstance(activityInstance);
		filterPKR = new IntentFilter();
		filterPKR.addAction("com.Payback.Logout_Intent");
		registerReceiver(pkr, filterPKR);
		
		nbr = new NoBackingReceiver(); nbr.setActivityInstance(activityInstance);
		filterNBR = new IntentFilter();
		filterNBR.addAction("com.Payback.MainActivity_Intent");
		registerReceiver(nbr, filterNBR);						
		//----------------------
		
		//----- bundle handling for strings ------
	    Bundle oldbundle = getIntent().getExtras();
	    
	    int transCostInt = oldbundle.getInt("Transaction1transCost");
	    String cost = "" + transCostInt; //for server call
	    DecimalFormat dec = new DecimalFormat("0.00");
        float percen = transCostInt/100F;
        String transCoststring = "$" + dec.format(percen);
	    
	    String transCommentString = oldbundle.getString("Transaction1transComment");
	    ArrayList<Friend> transselected = oldbundle.getParcelableArrayList("Transaction2selected");
	    int translenderamountInt = oldbundle.getInt("Transaction3lenderamount");
	    ArrayList<Integer> lendsharelist = oldbundle.getIntegerArrayList("Transaction3borroweramountlist");
	    
	    DecimalFormat dec2 = new DecimalFormat("0.00");
        float percen2 = translenderamountInt/100F;
        String translenderamountstring = "$" + dec2.format(percen2);
        
	    boolean button1Selected = oldbundle.getBoolean("Transaction3button1Selected");
	    boolean button2Selected = oldbundle.getBoolean("Transaction3button2Selected");

	    data.add("Amount: " + transCoststring);
	    data.add("Comment: " + transCommentString);
	    data.add("LendAmt: " + translenderamountstring);
	    
	    for(int i = 0; i < transselected.size(); i++){
	    	data.add("Selected: "+transselected.get(i).toString());
	    	
		    DecimalFormat dec3 = new DecimalFormat("0.00");
	        float percen3 = lendsharelist.get(i)/100F;
	        String lendshareliststring = "$" + dec3.format(percen3);
	        
	    	data.add("AmtforSel: "+ lendshareliststring);
	    }
	    
	    data.add("Auto: " +String.valueOf(button1Selected));
	    data.add("Manual: " +String.valueOf(button2Selected));

	    listView = (ListView) findViewById(R.id.listviewforplaceholderdata);
	    listView.setAdapter(new ArrayAdapter<String>(this, R.layout.activity_contact_iteminlist, data));

	}
	
	public void showTrans4(View view)
    {		
	    Bundle oldbundle = getIntent().getExtras();
	    
	    int transCostInt = oldbundle.getInt("Transaction1transCost");
	    String transCommentString = oldbundle.getString("Transaction1transComment");
	    ArrayList<Friend> transselected = oldbundle.getParcelableArrayList("Transaction2selected");
	    int translenderamountInt = oldbundle.getInt("Transaction3lenderamount");
	    ArrayList<Integer> lendsharelist = oldbundle.getIntegerArrayList("Transaction3borroweramountlist");
	    boolean button1Selected = oldbundle.getBoolean("Transaction3button1Selected");
	    boolean button2Selected = oldbundle.getBoolean("Transaction3button2Selected");

	    InputMethodManager im = (InputMethodManager) this.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        
	    
	    if(button1Selected){
	    	Intent intent = new Intent(this, Transaction2Activity.class);
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
	    }
	    else if(button2Selected){
	    	Intent intent = new Intent(this, Transaction4Activity.class);
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
	    }
	}
	
	public void showMainMenu(View view)
    {
		Bundle bundle = getIntent().getExtras();
		String from = user.getEmail();
		String fromPass = user.getPassword();
	    String comment = bundle.getString("Transaction1transComment");
	    ArrayList<Friend> borrowers = bundle.getParcelableArrayList("Transaction2selected");
	    ArrayList<Integer> borrowAmount = bundle.getIntegerArrayList("Transaction3borroweramountlist");

	    for(int i = 0; i < borrowers.size(); i++){
	    	try {
	    		AccessNet.AddTrans(from, fromPass, borrowAmount.get(i), comment, from, borrowers.get(i).getEmail());
	    	}catch (JSONException e) {
				e.printStackTrace();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }

		Intent broadcastIntent = new Intent();
    	broadcastIntent.setAction("com.Payback.MainActivity_Intent");
    	sendBroadcast(broadcastIntent);
		
		Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
