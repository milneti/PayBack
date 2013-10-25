package com.example.payback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void Login(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		Toast.makeText(getApplicationContext(),
                "Welcome", Toast.LENGTH_SHORT)
                .show();
        startActivity(intent);
	}
	
	public void CreateAccount(View view) {
		Intent intent = new Intent(getApplicationContext(), CreateAccountActivity.class);
        startActivity(intent);
	}
}
