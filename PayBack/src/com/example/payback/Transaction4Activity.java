package com.example.payback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Transaction4Activity extends TitleActivity
{
	 ListView listView;		//shows items in a vertically scrolling list
	 EditText editing;		
	 TextView text;

	 ArrayList<Map<String, String>> data;	

		static Activity activityInstance;	//this is for the broadcasts
		static PageKillReceiver pkr;		//these are the variables
		static IntentFilter filterPKR;		//used for PageKillReceiver.java
		static NoBackingReceiver nbr;		//these are the variables
		static IntentFilter filterNBR;		//used for NoBackingReceiver.java
	 
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		modifyTitle("Transaction allotment",R.layout.activity_transaction4);	//change title

		activityInstance = this;

		pkr = new PageKillReceiver(); pkr.setActivityInstance(activityInstance);		//for the broadcast which 
		filterPKR = new IntentFilter();													//kills all pages upon logout
		filterPKR.addAction("com.Payback.Logout_Intent");
		registerReceiver(pkr, filterPKR);
		
		nbr = new NoBackingReceiver(); nbr.setActivityInstance(activityInstance);		//for the broadcast which
		filterNBR = new IntentFilter();													//prevents backing once transaction
		filterNBR.addAction("com.Payback.MainActivity_Intent");							//has been created
		registerReceiver(nbr, filterNBR);
		
		listView = (ListView) findViewById(R.id.Listofselected);	//list of selected contacts
		text = (TextView) findViewById(R.id.borrowername);			//Name of the borrower
		editing = (EditText) findViewById(R.id.borroweramount);		//Amount each borrower will owe back
		
		data = buildData();		//data is the ArrayList which maps each borrower to his/her share											
		String[] from = {"friendname", "data"};
		int[] to = {R.id.borrowername, R.id.borroweramount};
		
		Bundle oldBndl = getIntent().getExtras();		//get extras
		
		//retrieve total amount for transaction - lender share
		int maxForBorrower = oldBndl.getInt("Transaction1transCost") - oldBndl.getInt("Transaction3lenderamount");
		
		Transaction4_listview_adapter adapter = new Transaction4_listview_adapter(this, data,
		        R.layout.activity_transaction4_listviewitem, from, to, maxForBorrower);
		    
		listView.setAdapter(adapter);
	}	    

	//Makes an array list of each borrower and the default amount they owe	  
	private ArrayList<Map<String, String>> buildData()
	{
	    Bundle oldbundle = getIntent().getExtras();		//get extras from old bundle
	    Friend mainuser = new Friend(user.fName, user.lName, user.email, user.displayName);
	    ArrayList<Friend> transselected = oldbundle.getParcelableArrayList("Transaction2selected");			//List of borrowers
	    int translenderamountInt = oldbundle.getInt("Transaction3lenderamount");
	    ArrayList<Integer> lendsharelist = oldbundle.getIntegerArrayList("Transaction3borroweramountlist");	//Default borrower share
		
	    ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();		//New ArrayList
	    
	    list.add(putData(mainuser.toString(), String.valueOf(translenderamountInt)));
	    for(int i = 0; i < transselected.size(); i++)		//for each borrower
	    {
	    	list.add(putData(transselected.get(i).toString(), String.valueOf(lendsharelist.get(i))));		//add borrower's share into ArrayList
	    }
	    
	    return list;	//return the list which maps each borrower to the amount he/she owes
	}

	private HashMap<String, String> putData(String friendname, String data)
	{
		HashMap<String, String> item = new HashMap<String, String>();
		item.put("friendname", friendname);
		item.put("data", data);
		return item;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction4, menu);
		return true;
	}
	
	//SeekBar sb = (SeekBar) findViewById(R.id.seekBar1);
	
	public void showTrans3(View view)
    {
	    Bundle oldbundle = getIntent().getExtras();
	    
	    int transCostInt = oldbundle.getInt("Transaction1transCost");
	    String transCommentString = oldbundle.getString("Transaction1transComment");
	    ArrayList<Friend> transselected = oldbundle.getParcelableArrayList("Transaction2selected");
	    int translenderamountInt = oldbundle.getInt("Transaction3lenderamount");
	    ArrayList<Integer> lendsharelist = oldbundle.getIntegerArrayList("Transaction3borroweramountlist");
	    boolean button1Selected = oldbundle.getBoolean("Transaction3button1Selected");
	    boolean button2Selected = oldbundle.getBoolean("Transaction3button2Selected");

    	Intent intent = new Intent(this, Transaction3Activity.class);
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
	
	public void checktomoveontotrans5(View view)
	{
	    Bundle oldbundle = getIntent().getExtras();		//get extras from old bundle
	    int transCostInt = oldbundle.getInt("Transaction1transCost");	//get total cost

        int counting = 0;
        for(int i = 0; i < data.size(); i++)	//go through the array list
        {
        	counting = counting + Integer.parseInt(data.get(i).get("data"));
        }
        
        if(counting != transCostInt)
        {
			Toast.makeText(getApplicationContext(), "("+String.valueOf((transCostInt)-counting) 
					+ ") Placeholder Money ERROR", Toast.LENGTH_SHORT).show();
        }
        else
        {
        	showTrans5(view);
        }
	}
	
	public void showTrans5(View view)
    {
	    Bundle oldbundle = getIntent().getExtras();
	    
	    int transCostInt = oldbundle.getInt("Transaction1transCost");
	    String transCommentString = oldbundle.getString("Transaction1transComment");
	    ArrayList<Friend> transselected = oldbundle.getParcelableArrayList("Transaction2selected");
	    boolean button1Selected = oldbundle.getBoolean("Transaction3button1Selected");
	    boolean button2Selected = oldbundle.getBoolean("Transaction3button2Selected");

    	Intent intent = new Intent(this, Transaction5Activity.class);
        Bundle Bundle = new Bundle();
        
        Bundle.putInt("Transaction1transCost", transCostInt);
        Bundle.putString("Transaction1transComment", transCommentString);
        Bundle.putParcelableArrayList("Transaction2selected", transselected);

        ArrayList<Integer> lendsharelist = new ArrayList<Integer>();
        for(int i = 0; i < data.size(); i++){
        	lendsharelist.add(Integer.parseInt(data.get(i).get("data")));
        }
        int translenderamountInt = lendsharelist.remove(0);        
        
        Bundle.putInt("Transaction3lenderamount", translenderamountInt);
        Bundle.putIntegerArrayList("Transaction3borroweramountlist", lendsharelist);
        
        Bundle.putBoolean("Transaction3button1Selected", button1Selected);
        Bundle.putBoolean("Transaction3button2Selected", button2Selected);
        
        intent.putExtras(Bundle);
        startActivity(intent);
    }

}
