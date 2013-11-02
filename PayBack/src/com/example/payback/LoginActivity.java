package com.example.payback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends TitleActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setTitle("PayBack");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void Login(View view) {
//		EditText text = (EditText) findViewById(R.id.email);
//		String url = "jdbc:mysql://70.171.40.125:3306/";
//		String dbName = "mydb";
//		String driver = "com.mysql.jbdc.Driver";
//		String userName = "Admin";
//		String password = "AdminPassword1";
//		try {
//			Class.forName(driver).newInstance();
//			Connection conn = DriverManager.getConnection(url+dbName, userName, password);
//			Statement st = conn.createStatement();
//			ResultSet res = st.executeQuery("Select * FROM account where accountid < 10");
//			while(res.next()) {
//				int id = res.getInt("id");
//				String msg = res.getString("msg");
//				text.setText(id + "\t" +msg);
//			}
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		Intent intent = new Intent(this, MainActivity.class);
		Toast.makeText(getApplicationContext(),"Welcome", Toast.LENGTH_SHORT).show();
        startActivity(intent);
	}
	
	public void CreateAccount(View view) {
		Intent intent = new Intent(getApplicationContext(), CreateAccountActivity.class);
        startActivity(intent);
	}
}
