package com.example.payback;

import java.util.ArrayList;
import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;

public class Transaction3Activity extends TitleActivity {
	boolean button1Selected;
	boolean button2Selected;

	static Activity activityInstance;	
	static PageKillReceiver pkr;		//these are the variables
	static IntentFilter filterPKR;		//used for PageKillReceiver.java
	static NoBackingReceiver nbr;		//these are the variables
	static IntentFilter filterNBR;		//used for NoBackingReceiver.java
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		modifyTitle("Lender's Share",R.layout.activity_transaction3);
		
		RadioButton radio1 = (RadioButton) findViewById(R.id.radio1);
		RadioButton radio2 = (RadioButton) findViewById(R.id.radio2);

		Bundle oldbundle = getIntent().getExtras();
	    boolean button1Selected = oldbundle.getBoolean("Transaction3button1Selected");
	    boolean button2Selected = oldbundle.getBoolean("Transaction3button2Selected");

		radio1.setChecked(button1Selected);
		radio2.setChecked(button2Selected);
		
		activityInstance = this;

		pkr = new PageKillReceiver(); pkr.setActivityInstance(activityInstance);
		filterPKR = new IntentFilter();
		filterPKR.addAction("com.Payback.Logout_Intent");
		registerReceiver(pkr, filterPKR);
		
		nbr = new NoBackingReceiver(); nbr.setActivityInstance(activityInstance);
		filterNBR = new IntentFilter();
		filterNBR.addAction("com.Payback.MainActivity_Intent");
		registerReceiver(nbr, filterNBR);
	}

	public void onRadioButtonClicked(View view)
	{
//		Bundle oldbundle = getIntent().getExtras();
//		int transCostInt = oldbundle.getInt("Transaction1transCost");
//		int numContacts = oldbundle.getParcelableArrayList("Transaction2selected").size();
//		int lenderShare = transCostInt/(numContacts+1);
//		String evenLenderShare = Integer.toString(lenderShare);
//		
//		evenLenderShare = "$" + evenLenderShare;
//		int elsLength = evenLenderShare.length();
//		String lenderText = evenLenderShare.substring(0, elsLength-2) + "." + evenLenderShare.substring(elsLength-2, elsLength);
				
		boolean checked = ((RadioButton) view).isChecked();
		
		switch(view.getId())
		{
			case R.id.radio1:
				if (checked)
				{
//					EditText transCost = (EditText)findViewById(R.id.lenderamount);
//					transCost.setText(lenderText);
					
			    	button1Selected = true;
				    button2Selected = false;
				}
				break;
			case R.id.radio2:
				if (checked)
				{
					//There's a bug where if you hit manual and then type it'll change the front rather then the back
					//For example. hit manual. edittext = $0.00. hit "1". expected is $0.01. actually is $10.00.
//					EditText transCost = (EditText)findViewById(R.id.lenderamount);
//					transCost.setText("$0.00");

			    	button1Selected = false;
			    	button2Selected = true;
				}
				break;
		}
	}


	public void showTrans2(View view)
    {
    	Intent intent = new Intent(this, Transaction2Activity.class);
    	
    	

		Bundle oldbundle = getIntent().getExtras();
		int transCostInt = oldbundle.getInt("Transaction1transCost");
		String transCommentString = oldbundle.getString("Transaction1transComment");
	    ArrayList<Friend> transselected = oldbundle.getParcelableArrayList("Transaction2selected");
	    int translenderamountInt = oldbundle.getInt("Transaction3lenderamount");
	    ArrayList<Integer> lendsharelist = oldbundle.getIntegerArrayList("Transaction3borroweramountlist");
		
	    if(!(button1Selected ^ button2Selected)){
		    button1Selected = oldbundle.getBoolean("Transaction3button1Selected");
		    button2Selected = oldbundle.getBoolean("Transaction3button2Selected");
	    }
	    
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
		finish();
    }
	
	public void showTrans4or5(View view)
    {
	    Bundle oldbundle = getIntent().getExtras();
	    
	    int transCostInt = oldbundle.getInt("Transaction1transCost");
	    String transCommentString = oldbundle.getString("Transaction1transComment");
	    ArrayList<Friend> transselected = oldbundle.getParcelableArrayList("Transaction2selected");

	    ArrayList<Integer> lendsharelist = new ArrayList<Integer>();

	    if(!(button1Selected ^ button2Selected)){
		    button1Selected = oldbundle.getBoolean("Transaction3button1Selected");
		    button2Selected = oldbundle.getBoolean("Transaction3button2Selected");
	    }
	    
	    if(button1Selected){
	    	Intent intent = new Intent(this, Transaction5Activity.class);
	        Bundle Bundle = new Bundle();
	        
	        Bundle.putInt("Transaction1transCost", transCostInt);
	        Bundle.putString("Transaction1transComment", transCommentString);
	        Bundle.putParcelableArrayList("Transaction2selected", transselected);
	        Bundle.putBoolean("Transaction3button1Selected", button1Selected);
	        Bundle.putBoolean("Transaction3button2Selected", button2Selected);
	        
	        
			int numContacts = oldbundle.getParcelableArrayList("Transaction2selected").size();
			int lenderShare = transCostInt/(numContacts+1);
			int totaltrans = lenderShare;
			for(int i = 0; i < numContacts; i ++){
				lendsharelist.add(lenderShare);
				totaltrans = totaltrans + lenderShare;
			}
			if(totaltrans != transCostInt){
				lenderShare = lenderShare + (transCostInt - totaltrans);
			}
			
			Bundle.putIntegerArrayList("Transaction3borroweramountlist", lendsharelist);
			Bundle.putInt("Transaction3lenderamount", lenderShare);

	        intent.putExtras(Bundle);
	        startActivity(intent);
	    }
	    else if(button2Selected)
	    {
	    	Intent intent = new Intent(this, Transaction4Activity.class);
	        Bundle Bundle = new Bundle();
	        
	        Bundle.putInt("Transaction1transCost", transCostInt);
	        Bundle.putString("Transaction1transComment", transCommentString);
	        Bundle.putParcelableArrayList("Transaction2selected", transselected);
	        Bundle.putBoolean("Transaction3button1Selected", button1Selected);
	        Bundle.putBoolean("Transaction3button2Selected", button2Selected);
	        
			int numContacts = oldbundle.getParcelableArrayList("Transaction2selected").size();
			int lenderShare = transCostInt/(numContacts+1);
			int totaltrans = lenderShare;
			for(int i = 0; i < numContacts; i ++){
				lendsharelist.add(lenderShare);
				totaltrans = totaltrans + lenderShare;
			}
			if(totaltrans != transCostInt){
				lenderShare = lenderShare + (transCostInt - totaltrans);
			}
			Bundle.putIntegerArrayList("Transaction3borroweramountlist", lendsharelist);
			Bundle.putInt("Transaction3lenderamount", lenderShare);

	        intent.putExtras(Bundle);
	        startActivity(intent);
	    }
	    finish();
	    	
    }
	
}
