package com.example.payback;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

public class Transaction2Activity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
		        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
		        "Linux", "OS/2" };
	    // use your own layout
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.activity_transaction2, R.id.label , values);
	    setListAdapter(adapter);
		
		TextView costDisplay = (TextView) findViewById(R.id.textView2);
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
