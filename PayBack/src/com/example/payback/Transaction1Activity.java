package com.example.payback;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Transaction1Activity extends TitleActivity {
	
	@SuppressLint("CutPasteId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		modifyTitle("Create Transaction",R.layout.activity_transaction1);		

		Bundle oldbundle = getIntent().getExtras();
		    
		int transCostInt = oldbundle.getInt("Transaction1transCost");
		String transCoststring;
		EditText text = (EditText)findViewById(R.id.editText1);  

		if(transCostInt == 0){
			transCoststring = "";
		}
		else{
	        DecimalFormat dec = new DecimalFormat("0.00");
			transCoststring = Integer.toString(transCostInt);
            Float in=Float.parseFloat(transCoststring);
            float percen = in/100;

            text.setText("$"+dec.format(percen));
            text.setSelection(text.getText().length());
            
	        Button button=(Button) findViewById(R.id.tran1buttonnext);
	    	button.setEnabled(true);
		}
		
		String transCommentString = oldbundle.getString("Transaction1transComment");
		
		EditText text2 = (EditText)findViewById(R.id.editText2); 
		text2.setText(transCommentString);
		
	    text.requestFocus();
	    text.setRawInputType(Configuration.KEYBOARD_12KEY);    
	    
	    text.addTextChangedListener(new TextWatcher(){
	        EditText text = (EditText)findViewById(R.id.editText1);
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
		
	    text.addTextChangedListener(new TextWatcher() {
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction1, menu);
		return true;
	}
	
	private boolean checkEditText(EditText edit) {
		String stringnumber = edit.getText().toString().substring(1);
		Float floatnumber = Float.parseFloat(stringnumber);
		int intnumber = (int) (floatnumber * 100F);
	    return intnumber == 0;
	}
	
	void updateButtonState() {
		EditText transCost= (EditText) findViewById(R.id.editText1);

        Button button=(Button) findViewById(R.id.tran1buttonnext);
		
	    if(checkEditText(transCost)) 
	    	button.setEnabled(false);
	    else 
	    	button.setEnabled(true);
	}
	
	public void showMainMenu(View view)
    {
    	Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
	
	public void showTrans2(View view)
    {	
    	Bundle oldbundle = getIntent().getExtras();
		
    	EditText transCost = (EditText)findViewById(R.id.editText1);
    	EditText transComment = (EditText)findViewById(R.id.editText2);
    	
		String stringnumber = transCost.getText().toString().substring(1);
		Float floatnumber = Float.parseFloat(stringnumber);
		int transCostInt = (int) (floatnumber * 100F);
		
    	String transCommentString = transComment.getText().toString();
	    
	    ArrayList<Friend> transselected = oldbundle.getParcelableArrayList("Transaction2selected");
	    int translenderamountInt = oldbundle.getInt("Transaction3lenderamount");
	    ArrayList<Integer> lendsharelist = oldbundle.getIntegerArrayList("Transaction3borroweramountlist");
	    boolean button1Selected = oldbundle.getBoolean("Transaction3button1Selected");
	    boolean button2Selected = oldbundle.getBoolean("Transaction3button2Selected");
    	
	    
        Intent intent = new Intent(getApplicationContext(), Transaction2Activity.class);
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
