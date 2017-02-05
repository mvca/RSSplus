package com.mu.android.rssplus;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Description extends Activity implements OnInitListener
{
	private int MY_DATA_CHECK_CODE = 0;
	
	private TextToSpeech tts;
	
	private Button speakButton;
    
    public void onCreate(Bundle icicle) 
    {
        super.onCreate(icicle);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.showdescription);
        
        String title = null;
        String date = null;
        String story = null;
        String link = null;
        
        
        Intent receiveIntent = getIntent();
        
        if (receiveIntent != null)
        {
        	Bundle b = receiveIntent.getBundleExtra("android.intent.extra.INTENT");
        	if (b == null)
        	{
        		title = "Bundle error.";
        		date = "Bundle error.";
        		story = "Bundle error.";
        		link = "Bundle error.";
        	}
        	else
    		{
        		title = b.getString("title");
        		date = b.getString("pubdate");
        		story = b.getString("description");
        		link = b.getString("link");
    		}
        }
        else
        {
        	story = "Information Not Found.";
        }
        
        
        //Set up views
        TextView titleb= (TextView) findViewById(R.id.title);
        titleb.setText(title);
        
        TextView dateb= (TextView) findViewById(R.id.date);
        dateb.setText(date);
        
        TextView storyb= (TextView) findViewById(R.id.description);
        storyb.setText(story);

        
        //Back button
        Button backbutton = (Button) findViewById(R.id.back);
        backbutton.setOnClickListener(new Button.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	tts.stop();
            	finish();
            }
        }); 
        
        //Open link button
        final String linkb = link;
        Button openbutton = (Button) findViewById(R.id.open);
        openbutton.setOnClickListener(new Button.OnClickListener() 
        {
        	
            public void onClick(View v) 
            {
            	Intent myIntent = new Intent(v.getContext(), Browser.class);
                Bundle address = new Bundle();
                address.putString("url", linkb);
                myIntent.putExtras(address);   
                Description.this.startActivity(myIntent);
            }
        }); 
        
        //Playback button
        final String textforspeech = story;
        Button speakButton = (Button) findViewById(R.id.playback);
        speakButton.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				if (textforspeech!=null && textforspeech.length()>0) {
					Toast.makeText(Description.this, "Saying: " + textforspeech, Toast.LENGTH_LONG).show();
					tts.speak(textforspeech, TextToSpeech.QUEUE_ADD, null);
				}
			}
		});
        
        Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, MY_DATA_CHECK_CODE); 
		
		//Share button
		Button shareButton = (Button) findViewById(R.id.share);
        shareButton.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				String message = "Check out this story: " + linkb;
				Intent share = new Intent(Intent.ACTION_SEND);
				share.setType("text/plain");
				share.putExtra(Intent.EXTRA_TEXT, message);
				startActivity(Intent.createChooser(share, "Share via"));
			}
		});

    }
		
    
    public void openWebURL( String inURL ) {
        Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( inURL ) );
        startActivity(browse);
    }
    
    //back button override to stop tts playback
    public void onBackPressed() {
    	tts.stop();
    	finish();
    }
    
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// success, create the TTS instance
				tts = new TextToSpeech(this, this);
			} 
			else {
				// missing data, install it
				Intent installIntent = new Intent();
				installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
			}
		}

	}

	public void onInit(int status) {		
		if (status == TextToSpeech.SUCCESS) {
			Toast.makeText(Description.this, 
					"Text-To-Speech engine is initialized", Toast.LENGTH_LONG).show();
		}
		else if (status == TextToSpeech.ERROR) {
			Toast.makeText(Description.this, 
					"Error occurred while initializing Text-To-Speech engine", Toast.LENGTH_LONG).show();
		}
	}
}
