package com.example.payback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;

public class TitleBar extends Activity{

	public void ShowShortcuts()
	{
		Intent intent = new Intent(this, ShortcutsActivity.class);
        startActivity(intent);
        this.finish();
	}

}