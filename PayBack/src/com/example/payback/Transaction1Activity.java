package com.example.payback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Transaction1Activity extends Activity {
	public final static String transactionCost = "com.example.PayBack.TransCost";
	public final static String transactionComment = "com.example.PayBack.TransComment";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction1, menu);
		return true;
	}
	
	public void showMainMenu(View view)
    {
    	Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
	
	public void showTrans2(View view)
    {	
    	Intent intent = new Intent(this, Transaction2Activity.class);
    	EditText transCost = (EditText)findViewById(R.id.editText1);
    	EditText transComment = (EditText)findViewById(R.id.editText2);
    	intent.putExtra(transactionCost, transCost.getText().toString());
    	intent.putExtra(transactionComment, transComment.getText().toString());
        startActivity(intent);
    }

}
