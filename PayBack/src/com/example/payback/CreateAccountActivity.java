package com.example.payback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccountActivity extends TitleActivity
{
	static Activity activityInstance;	//these are variables
	static PageKillReceiver pkr;		//used for PageKillReceiver.java
	static IntentFilter filterPKR;
	static MustLogoutReceiver mlr;		//used for PageKillReceiver.java
	static IntentFilter filterMLR;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
		
		activityInstance = this;
		pkr = new PageKillReceiver(); pkr.setActivityInstance(activityInstance);
		filterPKR = new IntentFilter();
		filterPKR.addAction("com.Payback.Logout_Intent");
		registerReceiver(pkr, filterPKR);
		
		mlr = new MustLogoutReceiver(); mlr.setActivityInstance(activityInstance);
		filterMLR = new IntentFilter();
		filterMLR.addAction("com.Payback.StayLoggedIn_Intent");
		registerReceiver(mlr, filterMLR);
	}

	public void Register(final View view) throws InterruptedException {
		//*
		Logger CONLOG = Logger.getLogger(CreateAccountActivity.class .getName());
		//is email check taken from: http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
		final String EMAIL_PATTERN = 
				"^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
		final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		final Matcher matcher;
		
		CONLOG.setLevel(Level.INFO);
		String fName = ((EditText)findViewById(R.id.fName)).getText().toString();
		String lName = ((EditText)findViewById(R.id.lName)).getText().toString();
		String email = ((EditText)findViewById(R.id.email)).getText().toString();
		String password = ((EditText)findViewById(R.id.password)).getText().toString();

		CONLOG.info("Register loaded data <"+fName+", "+lName+", "+email+", "+password+">");
		matcher = pattern.matcher(email);
		/*
		* <SRC of snippet: http://stackoverflow.com/questions/4205980/java-sending-http-parameters-via-post-method-easily >
		* Altered, modified, adulterated, outfitted, genetically modified by Hohyun@11/01/2013
		* Source of exception explained here: http://www.androiddesignpatterns.com/2012/06/app-force-close-honeycomb-ics.html
		* corrected to start under a new thread using tutorial:
		* http://developer.samsung.com/android/technical-docs/Basics-of-multi-threading-in-Android
		*/
		if(fName.isEmpty()){
			Toast.makeText(getApplicationContext(), "First name cannot be empty!", Toast.LENGTH_SHORT).show();
		}else if(lName.isEmpty()){
			Toast.makeText(getApplicationContext(), "Last name cannot be empty!", Toast.LENGTH_SHORT).show();
		}else if(email.isEmpty()){
			Toast.makeText(getApplicationContext(), "Email cannot be empty!", Toast.LENGTH_SHORT).show();
		}else if(password.isEmpty()){
			Toast.makeText(getApplicationContext(), "Password cannot be empty!", Toast.LENGTH_SHORT).show();
		}else if(password.length() < 8){
			Toast.makeText(getApplicationContext(), "Password must be at least 8 characters long!", Toast.LENGTH_SHORT).show();
		}else if(!matcher.matches()){
			Toast.makeText(getApplicationContext(), "Email: \""+email+"\" is not a valid email address!", Toast.LENGTH_SHORT).show();
		}else{
			
			//calling server
			if(AccessNet.AccountCreation(email, password, fName, lName)){
				CONLOG.info("Created new account in system: ");
				Toast.makeText(getApplicationContext(), "Account Created!", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(intent);
				this.finish();
			}else{
				CONLOG.warning("Failed to create account in system. Server response error.");
				Toast.makeText(getApplicationContext(), "Error account could not be created", Toast.LENGTH_SHORT).show();
			}
		}	
	}
	
	public void Login(View view) {
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        this.finish();
	}

}
