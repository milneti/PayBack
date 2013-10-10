package com.example.payback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Transaction2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction2);
		
		TextView costDisplay = (TextView) findViewById(R.id.textView3);
		costDisplay.setText(getIntent().getStringExtra(Transaction1Activity.transactionCost));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction2, menu);
		return true;
	}

	public void showTrans1(View view)
    {
    	Intent intent = new Intent(this, Transaction1Activity.class);
        startActivity(intent);
    }
	
	public void showTrans3(View view)
    {
    	Intent intent = new Intent(this, Transaction3Activity.class);
        startActivity(intent);
    }
}
