package com.example.payback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Transaction3Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction3);
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
        startActivity(intent);
    }
	
	public void showTrans4(View view)
    {
    	Intent intent = new Intent(this, Transaction4Activity.class);
        startActivity(intent);
    }
	
}
