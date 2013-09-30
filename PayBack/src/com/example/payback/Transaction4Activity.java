package com.example.payback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Transaction4Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction4);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction4, menu);
		return true;
	}
	
	public void showTrans3(View view)
    {
    	Intent intent = new Intent(this, Transaction3Activity.class);
        startActivity(intent);
    }
	
	public void showTrans5(View view)
    {
    	Intent intent = new Intent(this, Transaction5Activity.class);
        startActivity(intent);
    }

}
