package com.example.payback;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Transaction3Activity extends TitleActivity {

	private boolean button1Selected = false;
    private boolean button2Selected = true;

	static Activity activityInstance;	
	static PageKillReceiver pkr;		//these are the variables
	static IntentFilter filterPKR;		//used for PageKillReceiver.java
	static NoBackingReceiver nbr;		//these are the variables
	static IntentFilter filterNBR;		//used for NoBackingReceiver.java
    
	@SuppressLint("CutPasteId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		modifyTitle("Lender's Share",R.layout.activity_transaction3);

		activityInstance = this;

		pkr = new PageKillReceiver(); pkr.setActivityInstance(activityInstance);
		filterPKR = new IntentFilter();
		filterPKR.addAction("com.Payback.Logout_Intent");
		registerReceiver(pkr, filterPKR);
		
		nbr = new NoBackingReceiver(); nbr.setActivityInstance(activityInstance);
		filterNBR = new IntentFilter();
		filterNBR.addAction("com.Payback.MainActivity_Intent");
		registerReceiver(nbr, filterNBR);
		
		EditText transCost = (EditText) findViewById(R.id.lenderamount);
		EditText text = (EditText) findViewById(R.id.lenderamount);  
		
	    text.setRawInputType(Configuration.KEYBOARD_12KEY);
	    
	    text.addTextChangedListener(new TextWatcher(){
	        EditText text = (EditText)findViewById(R.id.lenderamount);
	        DecimalFormat dec = new DecimalFormat("0.00");
	        public void afterTextChanged(Editable arg0) {
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	        }
	        public void onTextChanged(CharSequence s, int start, int before, int count) {
	            if(!s.toString().matches("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$"))
	            {
	                String userInput= ""+ s.toString().replaceAll("[^\\d]", "");
	                if (userInput.length() > 0) {
	                    Float in=Float.parseFloat(userInput);
	                    float percen = in/100;
	                    text.setText("$"+dec.format(percen));
	                    text.setSelection(text.getText().length());
	                }
	            }
	        }
	    });
		
		
		transCost.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
        	    updateButtonState();
            }

			@Override
			public void afterTextChanged(Editable s) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {		
			}
			
		});
	}

	private boolean checkEditText(EditText edit) {
		String stringnumber = edit.getText().toString().substring(1);
		Float floatnumber = Float.parseFloat(stringnumber);
		int intnumber = (int) (floatnumber * 100F);
	    return intnumber < 0;
	}
	
	void updateButtonState() {
		EditText transCost= (EditText) findViewById(R.id.lenderamount);

        Button button=(Button) findViewById(R.id.tran3buttonnext);
		
	    if(checkEditText(transCost)) 
	    	button.setEnabled(false);
	    else 
	    	button.setEnabled(true);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction3, menu);
		return true;
	}
	
	public void onRadioButtonClicked(View view)
	{
		Bundle oldbundle = getIntent().getExtras();
		int transCostInt = oldbundle.getInt("Transaction1transCost");
		int numContacts = oldbundle.getParcelableArrayList("Transaction2selected").size();
		int lenderShare = transCostInt/(numContacts+1);
		String evenLenderShare = Integer.toString(lenderShare);
		
		evenLenderShare = "$" + evenLenderShare;
		int elsLength = evenLenderShare.length();
		String lenderText = evenLenderShare.substring(0, elsLength-2) + "." + evenLenderShare.substring(elsLength-2, elsLength);
				
		boolean checked = ((RadioButton) view).isChecked();
		
		switch(view.getId())
		{
			case R.id.radio1:
				if (checked)
				{
					EditText transCost = (EditText)findViewById(R.id.lenderamount);
					transCost.setText(lenderText);
					
			    	button1Selected = true;
				    button2Selected = false;
				}
				break;
			case R.id.radio2:
				if (checked)
				{
					//There's a bug where if you hit manual and then type it'll change the front rather then the back
					//For example. hit manual. edittext = $0.00. hit "1". expected is $0.01. actually is $10.00.
					EditText transCost = (EditText)findViewById(R.id.lenderamount);
					transCost.setText("$0.00");

			    	button1Selected = false;
			    	button2Selected = true;
				}
				break;
		}
	}
	public void checktomoveontotrans4(View view)
	{
	    Bundle oldbundle = getIntent().getExtras();
	    int transCostInt = oldbundle.getInt("Transaction1transCost");
	    
    	EditText translenderamount = (EditText)findViewById(R.id.lenderamount);
    	String stringnumber = translenderamount.getText().toString().substring(1);
		Float floatnumber = Float.parseFloat(stringnumber);
		int translenderamountInt = (int) (floatnumber * 100F);
		
		if(translenderamountInt <= transCostInt){
			showTrans4or5(view);
		}
		else{
			Toast.makeText(getApplicationContext(), "Lent amount is greater then the transaction", Toast.LENGTH_SHORT).show();
		}
		
	}

	public void showTrans2(View view)
    {
    	Intent intent = new Intent(this, Transaction2Activity.class);

		Bundle oldbundle = getIntent().getExtras();
		int transCostInt = oldbundle.getInt("Transaction1transCost");
		String transCommentString = oldbundle.getString("Transaction1transComment");
		
	    Bundle Bundle = new Bundle();
	    Bundle.putInt("Transaction1transCost", transCostInt);
	    Bundle.putString("Transaction1transComment", transCommentString);
     
	    intent.putExtras(Bundle);
	    startActivity(intent);
		
    }
	
	public void showTrans4or5(View view)
    {
	    Bundle oldbundle = getIntent().getExtras();
	    
	    int transCostInt = oldbundle.getInt("Transaction1transCost");
	    String transCommentString = oldbundle.getString("Transaction1transComment");
	    ArrayList<Friend> transselected = oldbundle.getParcelableArrayList("Transaction2selected");

	    ArrayList<Integer> lendsharelist = new ArrayList<Integer>();

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
			for(int i = 0; i < numContacts; i ++){
				lendsharelist.add(lenderShare);
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
	        
	        Toast.makeText(this.getApplicationContext(), "Test Toast A", Toast.LENGTH_LONG).show();
	        
	        //Place values in the bundle
	        Bundle.putInt("Transaction1transCost", transCostInt);				//total transaction cost
	        Bundle.putString("Transaction1transComment", transCommentString);
	        Bundle.putParcelableArrayList("Transaction2selected", transselected);
	        Bundle.putBoolean("Transaction3button1Selected", button1Selected);
	        Bundle.putBoolean("Transaction3button2Selected", button2Selected);
	        
	        EditText translenderamount = (EditText)findViewById(R.id.lenderamount);
	    	String stringnumber = translenderamount.getText().toString().substring(1);
			Float floatnumber = Float.parseFloat(stringnumber);
			int translenderamountInt = (int) (floatnumber * 100F);
			Bundle.putInt("Transaction3lenderamount", translenderamountInt);	//the lender's share
	        
			int numContacts = oldbundle.getParcelableArrayList("Transaction2selected").size();	//number of contacts
			int lenderShare = (transCostInt-translenderamountInt)/(numContacts);	//shouldn't this be borrowerShare
			for(int i = 0; i < numContacts; i++)
			{
				lendsharelist.add(lenderShare);		//add each borrower's share into the list
			}
			Bundle.putIntegerArrayList("Transaction3borroweramountlist", lendsharelist);

	        intent.putExtras(Bundle);
	        
	        Toast.makeText(this.getApplicationContext(), "Test Toast B", Toast.LENGTH_LONG).show();
	        
	        startActivity(intent);
	    }

	    	
    }
	
}
