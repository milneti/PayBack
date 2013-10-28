package com.example.payback;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ManageGroupActivity extends TitleActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		modifyTitle("Manage Groups",R.layout.activity_manage_group);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manage_group, menu);
		return true;
	}

}
