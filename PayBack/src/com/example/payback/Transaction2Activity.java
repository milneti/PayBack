package com.example.payback;

//import java.util.ArrayList;
//import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
//import android.app.ListActivity;
import android.content.Intent;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListAdapter;
//import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
//import android.widget.TextView;
//import android.widget.ListView;
//import android.widget.Toast;

public class Transaction2Activity extends Activity  {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listHeader;
    HashMap<String, List<String>> listChild;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		 super.onCreate(savedInstanceState);
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.activity_transaction2_expandablecontactlist_main);
		    ExpandableListView expListView = (ExpandableListView) findViewById(R.id.expandlistView);

		    createData();

		    Transaction2Activity_expandablecontactlist_adapter listAdapter = new Transaction2Activity_expandablecontactlist_adapter(this, listHeader, listChild);
		    expListView.setAdapter(listAdapter);
	      
	}
	
	public void createData() {
		
        listHeader = new ArrayList<String>();
        listChild = new HashMap<String, List<String>>();

        for (int i=0; i<=10; i++){
            listHeader.add("Header"+ i);

            List<String> child = new ArrayList<String>();
            child.add("Child"+i);
            child.add("Child"+i);

            listChild.put(listHeader.get(i), child); // Header, Child data

        }


//		
//	    Friend test1 = new Friend("Price", "Gutierrez");
//	    Friend test2 = new Friend("Vanna", "Mccullough");
//	    Friend test3 = new Friend("Wyatt", "Paul");
//	    Friend test4 = new Friend("Thaddeus", "Robbins");
//	    Friend test5 = new Friend("Rooney", "Dejesus");
//	    Friend test6 = new Friend("Xavier", "Wolfe");
//	    Friend test7 = new Friend("Byron", "Raymond");
//	    Friend test8 = new Friend("Quinn", "Whitfield");
//	    Friend test9 = new Friend("Farrah", "Moon");
//	    Friend test10 = new Friend("Ainsley", "Whitehead");
//	    Friend test11 = new Friend("Josephine", "Patton");
//	    Friend test12 = new Friend("Mariko", "Patton");
//	    Friend test13 = new Friend("Raphael", "Fitzgerald");
//	    Friend test14 = new Friend("Deacon", "Daniels");
//	    Friend test15 = new Friend("Delilah", "Fletcher");
//	    Friend test16 = new Friend("Robin", "Andrews");
//	    Friend test17 = new Friend("Melvin", "Price");
//	    
//	    
//	    for (int j = 0; j < 2; j++) {
//	    	if(j==0){
//		    	Transaction2Activity_expandablecontactlist_group group = new Transaction2Activity_expandablecontactlist_group("Groups");
////		        for (int i = 0; i < 3; i++) {
////			          group.children.add(test1.getfName() + " " + test1.getlName());
////		        }
//		    	
//		    	group.children.add(test1);
//
//		    	
////		    	group.children.add(test1.getfName() + " " + test1.getlName());
////		    	group.children.add(test2.getfName() + " " + test2.getlName());
////		    	group.children.add(test3.getfName() + " " + test3.getlName());
////		    	group.children.add(test4.getfName() + " " + test4.getlName());
////		    	group.children.add(test5.getfName() + " " + test5.getlName());
//		    	
//		        groups.append(j, group);
//	    	}
//	    	if(j==1){
//		    	Transaction2Activity_expandablecontactlist_group group = new Transaction2Activity_expandablecontactlist_group("Contacts");
////		        for (int i = 0; i < 5; i++) {
////			          group.children.add(test1.getfName() + " " + test1.getlName());
////		        }
//		    	
////		    	group.children.add(test6.getfName() + " " + test6.getlName());
////		    	group.children.add(test7.getfName() + " " + test7.getlName());
////		    	group.children.add(test8.getfName() + " " + test8.getlName());
////		    	group.children.add(test9.getfName() + " " + test9.getlName());
////		    	group.children.add(test10.getfName() + " " + test10.getlName());
////		    	group.children.add(test11.getfName() + " " + test11.getlName());
////		    	group.children.add(test12.getfName() + " " + test12.getlName());
////		    	group.children.add(test13.getfName() + " " + test13.getlName());
////		    	group.children.add(test14.getfName() + " " + test14.getlName());
////		    	group.children.add(test15.getfName() + " " + test15.getlName());
////		    	group.children.add(test16.getfName() + " " + test16.getlName());
////		    	group.children.add(test17.getfName() + " " + test17.getlName());
//		    	
//		        groups.append(j, group);
//	    	}
// 
//
//	      }
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
    	Intent intent = new Intent(this, Transaction3Activity.class);
        startActivity(intent);
    }
	
}
