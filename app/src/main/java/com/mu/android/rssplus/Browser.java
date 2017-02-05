package com.mu.android.rssplus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class Browser extends Activity {
	
	WebView mWebView;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.browser);
	    
	    Bundle address = getIntent().getExtras();
        final String url = address.getString("url");
        
	    mWebView = (WebView) findViewById(R.id.webview);
	    mWebView.getSettings().setJavaScriptEnabled(true);
	    mWebView.loadUrl(url);
	    mWebView.setWebViewClient(new ViewClient());
	    
	    
	    //Back button
	    Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	finish();
            }
        });
        
        //TTS button
        Button tts = (Button) findViewById(R.id.playback);
        tts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	Toast.makeText(Browser.this, "TTS currently unavailable for WebView.", Toast.LENGTH_LONG).show();
            }
        });
        
        //Refresh button
        Button refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	Browser.this.mWebView.loadUrl(url);
            }
        });
        
        //Share button
		Button shareButton = (Button) findViewById(R.id.share);
        
        shareButton.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				String message = "Check out this story: " + url;
				Intent share = new Intent(Intent.ACTION_SEND);
				share.setType("text/plain");
				share.putExtra(Intent.EXTRA_TEXT, message);
				startActivity(Intent.createChooser(share, "Share via"));
			}
		});
	}
	
	private class ViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        view.loadUrl(url);
	        return true;
	    }
	}
}
