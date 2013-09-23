package com.example.payback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    
    public void CreateTrsn(View view)
    {
    	Intent intent = new Intent(this, CreateTransactionActivity.class);
        startActivity(intent);
    }
    
    public void ResolveTrsn(View view)
    {
    	Intent intent = new Intent(this, ResolveTransactionActivity.class);
        startActivity(intent);
    }
    
    public void Contact(View view)
    {
    	Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }
    
    public void ManageGroup(View view)
    {
    	Intent intent = new Intent(this, ManageGroupActivity.class);
        startActivity(intent);
    }
    
    public void Notification(View view)
    {
    	Intent intent = new Intent(this, NotificationActivity.class);
        startActivity(intent);
    }
    
    public void Statistic(View view)
    {
    	Intent intent = new Intent(this, StatisticActivity.class);
        startActivity(intent);
    }
    
    public void Setting(View view)
    {
    	/*Intent intent = new Intent(this, SettingsActivty.class);
        EditText editText = (EditText) findViewById(R.id.editText1);
        String message = editText.getText().toString();
        startActivity(intent);*/
    }

}
