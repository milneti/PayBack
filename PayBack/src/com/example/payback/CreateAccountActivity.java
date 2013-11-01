package com.example.payback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

//import org.json.JSONObject;
//import org.json.JSONArray;
//import org.json.JSONException;
import org.json.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
	
	public void Register(View view) {
		/*
		String fName = ((EditText)findViewById(R.id.fName)).getText().toString();
		String lName = ((EditText)findViewById(R.id.lName)).getText().toString();
		String email = ((EditText)findViewById(R.id.email)).getText().toString();
		String password = ((EditText)findViewById(R.id.password)).getText().toString();
		
		String Url = "http://chase.mamatey.com/PayBack/AccountCreation.php";
		
		HttpClient httpclient = new DefaultHttpClient();
   	    HttpPost httppost = new HttpPost(Url);

   	    try {
	   		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);	   		
	   		nameValuePairs.add(new BasicNameValuePair("fname",fName));
	   		nameValuePairs.add(new BasicNameValuePair("lname",lName));
	   		nameValuePairs.add(new BasicNameValuePair("email",email));
	   		nameValuePairs.add(new BasicNameValuePair("password",password));
	   		
	   		JSONStringer jParser = new JSONStringer();
	   		JSONObject json = jParser.makeHttpRequest(Url, "POST", nameValuePairs);
	   		
	   		//httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	   		//HttpResponse response = httpclient.execute(httppost);
	   		//response.getEntity().toString();
   			Toast.makeText(getApplicationContext(), response.getEntity().toString(), Toast.LENGTH_SHORT).show();
//
	   		if(Boolean.parseBoolean(httpclient.execute(httppost).toString())){
	   			Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
	   		}else{
	   			Toast.makeText(getApplicationContext(),"Failure", Toast.LENGTH_SHORT).show();
	   		}
//
	    } catch (ClientProtocolException e) {
			Toast.makeText(getApplicationContext(), "clientProtocolException", Toast.LENGTH_SHORT).show();
	    } catch (IOException e) {
	    	 System.out.println("General I/O exception: " + e.getMessage());
			Toast.makeText(getApplicationContext(), "IOException", Toast.LENGTH_SHORT).show();
	    }
	  	*/
	}
	
	public void Login(View view) {
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
	}

}
