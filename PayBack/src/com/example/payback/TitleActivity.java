package com.example.payback;


import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class TitleActivity extends Activity {
	protected static User user;
	private static final int MY_REQUEST_CODE = 0;
    public final boolean success = false;
    
	public void modifyTitle(String name, int layout) 
	{
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
		setContentView(layout);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title_bar); 
		TextView header = (TextView) getWindow().findViewById(R.id.titleNameView); 
        header.setText(name);
	}
	public void ShowShortcuts(View view)
	{
		Intent intent = new Intent(this, ShortcutsActivity.class);
		startActivity(intent);
	}
	public void hideSoftKeyboard(View view) 
	{
		((InputMethodManager)this.getSystemService(Service.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
}
