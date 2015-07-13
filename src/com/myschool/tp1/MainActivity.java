package com.myschool.tp1;

import java.util.Locale;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	AlertDialog mDialog = null;
	Button mFRButton = null;
	Button mENButton = null;
	Locale mENLocale = new Locale("en");
	Locale mFRLocale = new Locale("fr");

	Configuration mConfig = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mFRButton = (Button) findViewById(R.id.fr_button);
		mFRButton.setOnClickListener(this);
		mENButton = (Button) findViewById(R.id.en_button);
		mENButton.setOnClickListener(this);

		mConfig = getResources().getConfiguration();
	}

	@Override
	protected void onStart() {
		Toast.makeText(this, getResources().getString(R.string.starting), Toast.LENGTH_LONG).show();
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_logout) {
			AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
			alertDialog.setTitle(getResources().getString(R.string.confirmation));
			alertDialog.setMessage(getResources().getString(R.string.confirmation_msg));
			alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getResources().getString(R.string.ok),
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							finish();
						}
					});
			alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getResources().getString(R.string.cancel),
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			alertDialog.show();

		}
		return super.onOptionsItemSelected(item);
	}

	private void restartActivity() {
	    Intent intent = getIntent();
	    finish();
	    startActivity(intent);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fr_button:
			mConfig.locale = mFRLocale;
			Locale.setDefault(mFRLocale);
			getResources().updateConfiguration(mConfig, getResources().getDisplayMetrics());
			restartActivity();
			break;
		case R.id.en_button:
			mConfig.locale = mENLocale;
			getResources().updateConfiguration(mConfig, getResources().getDisplayMetrics());
			Locale.setDefault(mENLocale);
			restartActivity();
			break;
		default:
			break;
		}
	}

}
