package com.example.payback;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ResolveTransactionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resolve_transaction);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resolve_transaction, menu);
		return true;
	}

}
