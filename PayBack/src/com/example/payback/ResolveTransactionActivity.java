//CREDIT TO Will Macfarlane for the template used in Notification.
package com.example.payback;

import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ResolveTransactionActivity extends TitleActivity
{
	static final boolean DEMO = true;
	
	static Activity activityInstance;	//these are variables
	static PageKillReceiver pkr;		//used for PageKillReceiver.java
	static IntentFilter filter;
	
	@Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				modifyTitle("Resolve Transactions",R.layout.activity_resolve_transaction1);
		
				activityInstance = this;
				pkr = new PageKillReceiver(); pkr.setActivityInstance(activityInstance);
				filter = new IntentFilter();
				filter.addAction("com.Payback.Logout_Intent");
				registerReceiver(pkr, filter);
				
				final ListView listview = (ListView) findViewById(R.id.listview);
				ArrayList<BaseTransaction> list = new ArrayList<BaseTransaction>();
				
				for(int i = 0; i<user.getTransBorrow().size(); i++)
				{
					list.add(user.getTransBorrow().get(i));
				}
				for(int i = 0; i<user.getTransLend().size(); i++)
				{
					list.add(user.getTransLend().get(i));
				}
				
				/*
				if(DEMO)
				{
					trans.add(new ResolveTransaction("$4.50", "Paid for the milk this week."));
					trans.add(new ResolveTransaction("$20.50", "Paid for Brian's pizza."));
					trans.add(new ResolveTransaction("$5.00", "Spotted Kristin a 5"));
					trans.add(new ResolveTransaction("$2.50", "Paid for the eggs this week"));
					trans.add(new ResolveTransaction("$18.50", "Paid for Brian's pizza AGAIN"));
					trans.add(new ResolveTransaction("$17.00", "Filled the fridge with beer"));

				}
				*/
					//final StableArrayAdapter adapter = new StableArrayAdapter(this,android.R.layout.simple_list_item_1, al);
				TransAdapter ta = new TransAdapter(this, android.R.layout.simple_list_item_1, list);
				listview.setAdapter(ta);
				listview.setOnItemClickListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> adapter, View arg1,
							int position, long arg3) {
						BaseTransaction t = (BaseTransaction) adapter.getItemAtPosition(position);
						if(t.getResolved()){
							t.setResolved(false);
							try {
								AccessNet.transFlagger(user.getEmail(), user.getPassword(), t.getID(), "resolved");
								refreshTrans();
							} catch (InterruptedException e) {
								e.printStackTrace();
							} catch (JSONException e) {
								e.printStackTrace();
							}	
							refresh();
						}
						else{
							t.setResolved(true);
						}
						
						
					}	
				});
				setContentView(listview);	
				
			}
		}

		class TransAdapter extends ArrayAdapter<BaseTransaction>
		{
			private final Context context;
			private final ArrayList<BaseTransaction> al;
			public TransAdapter (Context context, int textViewResourceId, ArrayList<BaseTransaction> al)
			{
				super(context, R.layout.activity_resolve_transaction1, al);
				this.context = context;
				this.al = al;
			}
			@Override
			public View getView(int position, View convertView, ViewGroup parent)
			{
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View rowView = inflater.inflate(R.layout.activity_noti_row_layout, parent, false);
			    TextView from = (TextView) rowView.findViewById(R.id.from);
			    TextView date = (TextView) rowView.findViewById(R.id.date);
			    TextView message = (TextView) rowView.findViewById(R.id.secondLine);
			    
			    if(al.get(position).getLenderEmail().toString().equalsIgnoreCase(TitleActivity.user.getEmail())){
			    	from.setText("Amount: $" + al.get(position).getAmount() + " With: " + al.get(position).getBorrowerEmail());
			    	from.setTextColor(Color.GREEN);
				    date.setTextColor(Color.GREEN);
				    message.setTextColor(Color.GREEN);
			    }
			    else{
			    	from.setText("Amount: $" + al.get(position).getAmount() + " With: " + al.get(position).getLenderEmail());
			    	from.setTextColor(Color.RED);
				    date.setTextColor(Color.RED);
				    message.setTextColor(Color.RED);
			    }
			    date.setText(al.get(position).getTransDate());
			    if(al.get(position).getComment().toString().equalsIgnoreCase("")){
				    message.setText(al.get(position).getComment());	
			    }
			    else
			    	message.setText("No message for transaction!");
			    message.setText(al.get(position).getComment());
			    if(al.get(position).getResolved()){
				    from.setPaintFlags(from.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
				    date.setPaintFlags(date.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
				    message.setPaintFlags(message.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			    }
			    
			    return rowView;
			}
		}