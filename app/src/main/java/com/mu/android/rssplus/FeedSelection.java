package com.mu.android.rssplus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;

public class FeedSelection extends Activity implements OnItemClickListener{

	public final String tag = "RSSReader";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection);
        
        Button msn = (Button) findViewById(R.id.feedselect_buttonmsn);
        msn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Reader.class);
                Bundle passing = new Bundle();
                passing.putString("feed", "http://pheedo.msnbc.msn.com/id/3032091/device/rss");
                Log.i(tag, "passingString: " + passing);
                myIntent.putExtras(passing);   
                FeedSelection.this.startActivity(myIntent);
            }
        });
        
        Button cbs = (Button) findViewById(R.id.feedselect_buttoncbs);
        cbs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Reader.class);
                Bundle passing = new Bundle();
                passing.putString("feed", "http://feeds.cbsnews.com/CBSNewsMain");
                Log.i(tag, "passingString: " + passing);
                myIntent.putExtras(passing);   
                FeedSelection.this.startActivity(myIntent);
            }
        });
        
        Button bbc = (Button) findViewById(R.id.feedselect_buttonbbc);
        bbc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Reader.class);
                Bundle passing = new Bundle();
                passing.putString("feed", "http://feeds.bbci.co.uk/news/rss.xml");
                Log.i(tag, "passingString: " + passing);
                myIntent.putExtras(passing);   
                FeedSelection.this.startActivity(myIntent);
            }
        });
        
        Button reuters = (Button) findViewById(R.id.feedselect_buttonreuters);
        reuters.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Reader.class);
                Bundle passing = new Bundle();
                passing.putString("feed", "http://feeds.reuters.com/Reuters/worldNews?format=xml");
                Log.i(tag, "passingString: " + passing);
                myIntent.putExtras(passing);   
                FeedSelection.this.startActivity(myIntent);
            }
        });
        
        Button npr = (Button) findViewById(R.id.feedselect_buttonnpr);
        npr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Reader.class);
                Bundle passing = new Bundle();
                passing.putString("feed", "http://www.npr.org/rss/rss.php?id=1004");
                Log.i(tag, "passingString: " + passing);
                myIntent.putExtras(passing);   
                FeedSelection.this.startActivity(myIntent);
            }
        });
        
        Button abc = (Button) findViewById(R.id.feedselect_buttonabc);
        abc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Reader.class);
                Bundle passing = new Bundle();
                passing.putString("feed", "http://feeds.abcnews.com/abcnews/usheadlines");
                Log.i(tag, "passingString: " + passing);
                myIntent.putExtras(passing);  
                FeedSelection.this.startActivity(myIntent);
                
            }
        });
        
        Button usatop = (Button) findViewById(R.id.feedselect_buttonusatop);
        usatop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Reader.class);
                Bundle passing = new Bundle();
                passing.putString("feed", "http://rssfeeds.usatoday.com/usatoday-NewsTopStories");
                Log.i(tag, "passingString: " + passing);
                myIntent.putExtras(passing);  
                FeedSelection.this.startActivity(myIntent);
                
            }
        });
        
        Button wsj = (Button) findViewById(R.id.feedselect_buttonwsj);
        wsj.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Reader.class);
                Bundle passing = new Bundle();
                passing.putString("feed", "http://online.wsj.com/xml/rss/3_7011.xml");
                Log.i(tag, "passingString: " + passing);
                myIntent.putExtras(passing);  
                FeedSelection.this.startActivity(myIntent);
                
            }
        });
        
        Button yahoof = (Button) findViewById(R.id.feedselect_buttonyahoof);
        yahoof.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Reader.class);
                Bundle passing = new Bundle();
                passing.putString("feed", "http://finance.yahoo.com/rss/topfinstories");
                Log.i(tag, "passingString: " + passing);
                myIntent.putExtras(passing);  
                FeedSelection.this.startActivity(myIntent);
                
            }
        });
        
        Button marketwatch = (Button) findViewById(R.id.feedselect_buttonmarketwatch);
        marketwatch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Reader.class);
                Bundle passing = new Bundle();
                passing.putString("feed", "http://feeds.marketwatch.com/marketwatch/topstories?format=xml");
                Log.i(tag, "passingString: " + passing);
                myIntent.putExtras(passing);  
                FeedSelection.this.startActivity(myIntent);
                
            }
        });
        
        Button usa = (Button) findViewById(R.id.feedselect_buttonusasports);
        usa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Reader.class);
                Bundle passing = new Bundle();
                passing.putString("feed", "http://rssfeeds.usatoday.com/UsatodaycomSports-TopStories");
                Log.i(tag, "passingString: " + passing);
                myIntent.putExtras(passing);  
                FeedSelection.this.startActivity(myIntent);
                
            }
        });
        
        Button foxsports = (Button) findViewById(R.id.feedselect_buttonfoxsports);
        foxsports.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Reader.class);
                Bundle passing = new Bundle();
                passing.putString("feed", "http://feeds.pheedo.com/feedout/syndicatedContent_categoryId_0");
                Log.i(tag, "passingString: " + passing);
                myIntent.putExtras(passing);  
                FeedSelection.this.startActivity(myIntent);
                
            }
        });
        
        Button espn = (Button) findViewById(R.id.feedselect_buttonespn);
        espn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Reader.class);
                Bundle passing = new Bundle();
                passing.putString("feed", "http://sports.espn.go.com/espn/rss/news");
                Log.i(tag, "passingString: " + passing);
                myIntent.putExtras(passing);  
                FeedSelection.this.startActivity(myIntent);
                
            }
        });          
        
        UpdateDisplay();
    }
    
    private void UpdateDisplay()
    {
    	/*ListView feedlist = (ListView) findViewById(R.id.feedlist);
    	String[] feeds = new String[20]; 
    	feeds[0] = "http://www.google.com"
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.style,strings);

        feedlist.setAdapter(adapter);
        
        feedlist.setOnItemClickListener(this);
        
        feedlist.setSelection(0);*/
    }
    
    public void onItemClick(AdapterView parent, View v, int position, long id){}

}