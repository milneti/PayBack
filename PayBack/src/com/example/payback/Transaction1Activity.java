package com.example.payback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Transaction1Activity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction1);
		
		EditText transCost = (EditText) findViewById(R.id.editText1);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction1, menu);
		return true;
	}
	
	private boolean checkEditText(EditText edit) {
	    return edit.getText().length() == 0;
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
    	EditText transCost = (EditText)findViewById(R.id.editText1);
    	EditText transComment = (EditText)findViewById(R.id.editText2);
    	
    	int transCostInt = Integer.parseInt(transCost.getText().toString());
    	String transCommentString = transComment.getText().toString();
    	
        Intent intent = new Intent(getApplicationContext(), Transaction2Activity.class);
        Bundle Bundle = new Bundle();
        
        Bundle.putInt("Transaction1transCost", transCostInt);
        Bundle.putString("Transaction1transComment", transCommentString);
        
        intent.putExtras(Bundle);
        startActivity(intent);

    }

}
