package com.example.payback;

import android.app.Activity;
import android.content.Intent;

public class TitleBar extends Activity{

	public void ShowShortcuts()
	{
		Intent intent = new Intent(this, ShortcutsActivity.class);
        startActivity(intent);
	}

}