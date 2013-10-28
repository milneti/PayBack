package com.example.payback;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Transaction3Activity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction3);

	}

	private boolean checkEditText(EditText edit) {
	    return edit.getText().length() == 0;
	}
	
	void updateButtonState() {
		EditText transCost= (EditText) findViewById(R.id.editText1);

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
					EditText transCost = (EditText)findViewById(R.id.editText1);
					transCost.setText(lenderText);
				}
				break;
			case R.id.radio0:
				if (checked)
				{
					EditText transCost = (EditText)findViewById(R.id.editText1);
					transCost.setText("");
				}
				break;
		}
	}
	
	public void showTrans4(View view)
	{	
		/*
		Bundle oldbundle = getIntent().getExtras();
		
	    int transCostInt = oldbundle.getInt("Transaction1transCost");
	    String transCommentString = oldbundle.getString("Transaction1transComment");
	    ArrayList<Friend> selectedContacts = oldbundle.getParcelableArrayList("Transaction2selected");
	    
	    Bundle bundle = new Bundle();
	    
	    bundle.putInt("Transaction1transCost", transCostInt);
	    bundle.putString("Transaction1transComment", transCommentString);
	    bundle.putParcelableArrayList("Transaction2selected", selectedContacts);
	    
	    EditText transCost = (EditText)findViewById(R.id.editText1);
		String lenderText = transCost.getText().toString();
		int lenderShare = Integer.parseInt(lenderText);
	    bndl.putInt("Transaction3lenderShare", lenderShare);
	    */

    	Intent intent = new Intent(this, Transaction4Activity.class);
            
        //intent.putExtras(bundle);
        startActivity(intent);
    }
	
}
