package com.example.payback;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.logging.Logger;

public class CreateAccountActivity extends TitleActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_account, menu);
		return true;
	}
	
	public void Register(final View view) {
		String fName = ((EditText)findViewById(R.id.fName)).getText().toString();
		String lName = ((EditText)findViewById(R.id.lName)).getText().toString();
		String email = ((EditText)findViewById(R.id.email)).getText().toString();
		String password = ((EditText)findViewById(R.id.password)).getText().toString();

		if(fName.isEmpty()){ //fname.checkAlpha()
			Toast.makeText(getApplicationContext(), "First name cannot be empty!", Toast.LENGTH_SHORT).show();
		}else if(lName.isEmpty()){ //lname.checkAlpha()
			Toast.makeText(getApplicationContext(), "Last name cannot be empty!", Toast.LENGTH_SHORT).show();
		}else if(email.isEmpty()){ //email.checkEmail()
			Toast.makeText(getApplicationContext(), "Email cannot be empty!", Toast.LENGTH_SHORT).show();
		}else if(password.isEmpty()){ //password.checkPassword()
			Toast.makeText(getApplicationContext(), "Password cannot be empty!", Toast.LENGTH_SHORT).show();
		}else{
			final String name = CreateAccountActivity.class.getName();
			final String url = "http://chase.mamatey.com/PayBack/AccountCreation.php";
			final String parameters = "fname="+fName+"&lname="+lName+"&email="+email+"&password="+password;

			super.sendServer(name, parameters, url);
				
			//this is a dummy, Does not check response from server and may not have actually created one.
			Toast.makeText(getApplicationContext(), "Account Created!", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
			startActivity(intent);
		}
	}
	
	public void Login(View view) {
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
	}

}
