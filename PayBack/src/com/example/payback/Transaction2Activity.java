package com.example.payback;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

public class Transaction2Activity extends ListActivity {

	SparseArray<Transaction2Activity_expandablecontactlist_group> groups = new SparseArray<Transaction2Activity_expandablecontactlist_group>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		 super.onCreate(savedInstanceState);
		    setContentView(R.layout.activity_transaction2_expandablecontactlist_main);
		    createData();
		    ExpandableListView listView = (ExpandableListView) findViewById(R.id.listview);	
		    Transaction2Activity_expandablecontactlist_adapter adapter = new Transaction2Activity_expandablecontactlist_adapter(this, groups);
		    listView.setAdapter(adapter);
	    
	    
//		//setContentView(R.layout.activity_transaction2);
//	    View header = getLayoutInflater().inflate(R.layout.activity_transaction2_header, null);
//	    View footer = getLayoutInflater().inflate(R.layout.activity_transaction2_footer, null);
//	    
//	    // create an array of Strings, that will be put to our ListActivity
//	    ArrayAdapter<Friend> adapter = new Transaction2Activity_contactlist_adapter(this, getModel());
//	    
//	    ListView listView = getListView();
//	    listView.addHeaderView(header);
//	    listView.addFooterView(footer);
//	    
//	    setListAdapter(adapter);
//		
	}
	
	  public void createData() {
		    for (int j = 0; j < 5; j++) {
		    	Transaction2Activity_expandablecontactlist_group group = 
		    			new Transaction2Activity_expandablecontactlist_group("Test " + j);
		      for (int i = 0; i < 5; i++) {
		        group.children.add("Sub Item" + i);
		      }
		      groups.append(j, group);
		    }
		  }
	
	private List<Friend> getModel() {
	    List<Friend> list = new ArrayList<Friend>();
	    list.add(get("Linux"));
	    list.add(get("Windows7"));
	    list.add(get("Suse"));
	    list.add(get("Eclipse"));
	    list.add(get("Ubuntu"));
	    list.add(get("Solaris"));
	    list.add(get("Android"));
	    list.add(get("iPhone"));
	    // Initially select one of the items
	    //list.get(1).setSelected(true);
	    return list;
	  }

	private Friend get(String s) {
	    return new Friend(s);
	  }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction2, menu);
		return true;
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
