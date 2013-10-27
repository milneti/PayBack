package com.example.payback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class Transaction2Activity extends Activity  {

    private List<String> listHeader;
    private HashMap<String, List<Friend>> listChild;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		 super.onCreate(savedInstanceState);
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.activity_transaction2_expandablecontactlist_main);
		    ExpandableListView expListView = (ExpandableListView) findViewById(R.id.expandlistView);

		    createData();

		    final Transaction2Activity_expandablecontactlist_adapter listAdapter = new Transaction2Activity_expandablecontactlist_adapter(this, listHeader, listChild);
		    expListView.setAdapter(listAdapter);
	}
	

	
	public void createData() {
		
        listHeader = new ArrayList<String>();
        listChild = new HashMap<String, List<Friend>>();

        //test friends
    	Friend tests1 = new Friend("Place", "Ment");
    	Friend tests2 = new Friend("For", "Later");
    	Friend tests3 = new Friend("Sprints", "Lname");
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

        
        listHeader.add("Groups");
        List<Friend> child0 = new ArrayList<Friend>();
        
        child0.add(tests1);
        child0.add(tests2);
        child0.add(tests3);
        
        listChild.put(listHeader.get(0), child0);
        
        listHeader.add("Individual");
        List<Friend> child1 = new ArrayList<Friend>();
        
        child1.add(test1);
        child1.add(test2);
        child1.add(test3);
        child1.add(test4);
        child1.add(test5);
        child1.add(test6);
        child1.add(test7);
        child1.add(test8);
        child1.add(test9);
        child1.add(test10);
        child1.add(test11);
        child1.add(test12);
        child1.add(test13);
        child1.add(test14);
        child1.add(test15);
        child1.add(test16);
        child1.add(test17);
        
        listChild.put(listHeader.get(1), child1);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction2, menu);
		return true;
	}
	
	public void showCreateContact(View view)
    {
		
    }
	
	public void showTrans1(View view)
    {
    	Intent intent = new Intent(this, Transaction1Activity.class);
        startActivity(intent);
    }
	
	public void showTrans3(View view)
    {
	    Bundle oldbundle = getIntent().getExtras();
	    int transCostInt = oldbundle.getInt("Transaction1transCost");
	    String transCommentString = oldbundle.getString("Transaction1transComment");

    	Intent intent = new Intent(this, Transaction3Activity.class);
        Bundle Bundle = new Bundle();
        
        Bundle.putInt("Transaction1transCost", transCostInt);
        Bundle.putString("Transaction1transComment", transCommentString);
        

	    
    	
    	
    	
    	
    	
        startActivity(intent);
    }
	
}
