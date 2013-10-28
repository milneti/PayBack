package com.example.payback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class TitleActivity extends Activity {

	public void modifyTitle(String name, int layout) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
		setContentView(layout);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title_bar); 
		TextView header = (TextView) getWindow().findViewById(R.id.titleName); 
        header.setText(name);
	}
	
	public void ShowShortcuts(View view)
	{
		Intent intent = new Intent(this, ShortcutsActivity.class);
        startActivity(intent);
        this.finish();
	}

}
