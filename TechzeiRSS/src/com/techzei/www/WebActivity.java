package com.techzei.www;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebActivity extends Activity {

	private WebView webview = null;
	boolean loadingFinished = true;
	boolean redirect = false;
	private String URL = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);

		webview = (WebView) findViewById(R.id.postWebView);
		Intent i = getIntent();
		String url = i.getStringExtra("url");
		URL = url.trim();
		webview.loadUrl(URL);

		webview.getSettings().setBuiltInZoomControls(true);
		webview.getSettings().setDisplayZoomControls(false);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

		webview.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view,
					String urlNewString) {
				if (!loadingFinished) {
					redirect = true;
				}

				loadingFinished = false;
				view.loadUrl(URL);
				return true;
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap facIcon) {
				loadingFinished = false;
				// SHOW LOADING IF IT ISNT ALREADY VISIBLE
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				if (!redirect) {
					loadingFinished = true;
				}

				if (loadingFinished && !redirect) {
					// HIDE LOADING IT HAS FINISHED
				} else {
					redirect = false;
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.web, menu);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.reload) {
			Toast.makeText(this, "Reloading..", Toast.LENGTH_SHORT).show();
			webview.reload();
		}
		if (id == android.R.id.home) {
			finish();
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
