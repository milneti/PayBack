package com.example.payback;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class ContactActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}
	
	public void showCreateContact(View view)
    {
		setContentView(R.layout.activity_contact);
		LayoutInflater inflater = this.getLayoutInflater();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		setContentView(R.layout.dialog_create_contact);
 	    final EditText email = (EditText)findViewById(R.id.username);		
 	    setContentView(R.layout.activity_contact);
 	    
 	    builder.setTitle("Add new Contact")
		
		       .setView(inflater.inflate(R.layout.dialog_create_contact, null))
		       
		       .setPositiveButton(R.string.Add, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   
		        	   //InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
		        	   //inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);
		        	   confirmContact(email.getText().toString());
		        	   dialog.dismiss();
		           }
		       })
		       .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               dialog.dismiss();
		           }
		       });
		Dialog dialog = builder.create();
		dialog.show();
		
    }
	
	public void confirmContact(final String email){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle("Confirm Add Contact?")
		       
		       .setPositiveButton(R.string.Confirm, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   sendContact(email);
		        	   dialog.dismiss();
		           }
		       })
		       .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               dialog.dismiss();
		           }
		       });
		Dialog dialog = builder.create();
		dialog.show();
	}
	
	public void sendContact(String email){
		setContentView(R.layout.activity_contact);
		TextView textView2 = (TextView)findViewById(R.id.textView2);
		textView2.setText(email);
	}
}
