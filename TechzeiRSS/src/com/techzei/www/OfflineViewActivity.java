package com.techzei.www;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class OfflineViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offline_view);

		Intent i = getIntent();
		String title = i.getStringExtra("title");
		String date = i.getStringExtra("date");
		String desc = i.getStringExtra("desc");

		TextView titleT = (TextView) findViewById(R.id.titleO);
		TextView dateT = (TextView) findViewById(R.id.dateO);
		TextView descT = (TextView) findViewById(R.id.descO);
		View v = findViewById(R.id.title_below_offline);

		Log.e("Sinduja", title + " " + date);
		titleT.setText(title);
		titleT.setBackground(getResources().getDrawable(
				R.drawable.title_shape_offline));
		dateT.setText(date);
		descT.setText(Html.fromHtml(desc));
		v.setBackgroundColor(Color.parseColor("#396e21"));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getActionBar().setDisplayHomeAsUpEnabled(true);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();

		if (id == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
