package com.example.payback;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ContactActivity extends Activity {

	  private ListView contactlistview;
	  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);

		contactlistview = (ListView) findViewById(R.id.listofcontacts);
		ArrayList<String> friendList = buildFriendList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_contact_iteminlist,
				friendList);
		contactlistview.setAdapter(adapter);
		    
	}


	private ArrayList<String> buildFriendList() {
	    ArrayList<String> list = new ArrayList<String>();
	    
	    //dummy friends
	    Friend test1 = new Friend("Price", "Gutierrez");
	    Friend test2 = new Friend("Vanna", "Mccullough");
	    Friend test3 = new Friend("Wyatt", "Paul");
	    Friend test4 = new Friend("Thaddeus", "Robbins");
	    Friend test5 = new Friend("Rooney", "Dejesus");
	    Friend test6 = new Friend("Xavier", "Wolfe");
	    Friend test7 = new Friend("Byron", "Raymond");
	    Friend test8 = new Friend("Quinn", "Whitfield");
	    Friend test9 = new Friend("Farrah", "Moon");
	    Friend test10 = new Friend("Ainsley", "Whitehead");
	    Friend test11 = new Friend("Josephine", "Patton");
	    Friend test12 = new Friend("Mariko", "Patton");
	    Friend test13 = new Friend("Raphael", "Fitzgerald");
	    Friend test14 = new Friend("Deacon", "Daniels");
	    Friend test15 = new Friend("Delilah", "Fletcher");
	    Friend test16 = new Friend("Robin", "Andrews");
	    Friend test17 = new Friend("Melvin", "Price");
	    
	    for(int x = 0; x < 2;x++){
		    list.add(test1.toString());
		    list.add(test2.toString());
		    list.add(test3.toString());
		    list.add(test4.toString());
		    list.add(test5.toString());
		    list.add(test6.toString());
		    list.add(test7.toString());
		    list.add(test8.toString());
		    list.add(test9.toString());
		    list.add(test10.toString());
		    list.add(test11.toString());
		    list.add(test12.toString());
		    list.add(test13.toString());
		    list.add(test14.toString());
		    list.add(test15.toString());
		    list.add(test16.toString());
		    list.add(test17.toString());
	    }

	    return list;
	  }

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}
	
	public void showCreateContact(View view)
    {
		LayoutInflater inflater = this.getLayoutInflater();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
 	    final EditText email = (EditText)findViewById(R.id.newemail);		
 	    
 	    builder.setTitle("Add new Contact")
		
		       .setView(inflater.inflate(R.layout.dialog_create_contact, null))
		       
		       .setPositiveButton(R.string.Add, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   
		        	   //InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
		        	   //inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);
		        	   dialog.dismiss();
		        	   confirmContact(email.getText().toString());
		        	   
		           }
		       })
		       .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		              // dialog.dismiss();
		        	   dialog.cancel();
		           }
		       });
		Dialog dialog = builder.create();
		dialog.show();
		
    }
	
	public void confirmContact(final String email){
		
		AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
		
		builder2.setTitle("Confirm Add Contact?")
		       
		       .setPositiveButton(R.string.Confirm, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   dialog.dismiss();
		        	   sendContact(email);
		           }
		       })
		       .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               dialog.cancel();
		           }
		       });
		Dialog dialog = builder2.create();
		dialog.show();
	}
	
	public void sendContact(String email){
		setContentView(R.layout.activity_contact);
		TextView textView2 = (TextView)findViewById(R.id.textView2);
		textView2.setText(email);
	}
	
	public void showMainMenu(View view)
    {
    	Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
