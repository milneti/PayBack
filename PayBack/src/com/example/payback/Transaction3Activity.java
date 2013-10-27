package com.example.payback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
	
	public void showTrans4(View view)
    {
		//intent not finished
    	Intent intent = new Intent(this, Transaction4Activity.class);
        startActivity(intent);
    }
	
}
