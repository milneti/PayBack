package com.example.payback;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
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

	    Friend test1 = new Friend("Android", "Mobile");
	    Friend test2 = new Friend("Windows7", "PC");
	    Friend test3 = new Friend("iPhone", "IOS");
	    for(int x = 0; x < 20;x++){
		    list.add(test1.getfName() + " " + test1.getlName());
		    list.add(test2.getfName() + " " + test2.getlName());
		    list.add(test3.getfName() + " " + test3.getlName());
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
		//setContentView(R.layout.activity_contact);
		LayoutInflater inflater = this.getLayoutInflater();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		//setContentView(R.layout.dialog_create_contact);
 	    final EditText email = (EditText)findViewById(R.id.newemail);		
 	   // setContentView(R.layout.activity_contact);
 	    
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
