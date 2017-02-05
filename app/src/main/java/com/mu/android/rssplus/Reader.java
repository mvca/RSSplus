package com.mu.android.rssplus;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Reader extends Activity implements OnItemClickListener
{
	DatabaseManager db;
	public String passedFeed = null;
	
	private static final int PLAY = 0;
	private static final int SETTINGS = 1;
	
	public final String tag = "RSSReader";
	private Feed feed = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.main);
                
        Bundle passing = getIntent().getExtras();
        passedFeed = passing.getString("feed");
        
        Log.i(tag, "problem feed in reader: " + passedFeed);

        db = new DatabaseManager(this); 
        
        if(passedFeed==null)
        {
        	feed = null;
        }
        if(passedFeed!=null)
        {
        	feed = getFeed(passedFeed);
        }
        UpdateView();
    }


        
    private Feed getFeed(String feedURL)
    {
    	try
    	{
    	   URL url = new URL(feedURL);
           SAXParserFactory factory = SAXParserFactory.newInstance();
           SAXParser parser = factory.newSAXParser();
           XMLReader reader = parser.getXMLReader();
           Handler theHandler = new Handler();
           reader.setContentHandler(theHandler);
           InputSource is = new InputSource(url.openStream());          
           reader.parse(is);
           return theHandler.getFeed();           
    	}
    	catch (Exception ee)
    	{
    		return null;
    	}
    	
    }
    public boolean onCreateOptionsMenu(Menu menu) 
    {
    	super.onCreateOptionsMenu(menu);
     menu.add(0, Reader.PLAY, 0, "Playback Audio").setIcon(android.R.drawable.ic_menu_info_details);
    	
     menu.add(0, Reader.SETTINGS, 0, "Settings").setIcon(android.R.drawable.ic_menu_manage);
    	return true; 	
    }
    
    @Override
    public boolean onMenuItemSelected(final int featureId, final MenuItem item) {
        switch (item.getItemId()) {
        case Reader.PLAY:
        	Toast.makeText(Reader.this, "Sorry, Feed List TTS currently unavailable.", Toast.LENGTH_LONG).show();
            return true;
        case Reader.SETTINGS:
        	Toast.makeText(Reader.this, "Sorry, settings are currently unavailable.", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
    
    
    private void UpdateView()
    {    	
        TextView feedtitle = (TextView) findViewById(R.id.feedtitle);
        TextView date = (TextView) findViewById(R.id.date);
        ListView itemlist = (ListView) findViewById(R.id.itemlist);
        
        if (feed == null)
        {
        	feedtitle.setText("No response. Please check your data connection.");
        	return;
        }

        if(feed.getTitle()==null)
        {
        	feedtitle.setText("Unable to parse feed title.");
        }else
        feedtitle.setText(feed.getTitle());
        
        if(feed.getlastBuildDate()==null)
        {
        	date.setText("");
        }else
        	date.setText("Last Updated: " + feed.getlastBuildDate());

        ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this,R.layout.style,feed.getAllItems());
        itemlist.setAdapter(adapter);
        itemlist.setOnItemClickListener(this);
        itemlist.setSelection(0);
        
        int count = feed.getItemCount();
    	for(int i=0;i<count;i++)
    	{
    		try
    		{        	
				db.addRow(					
						feed.getItem(i).getTitle(),
						feed.getItem(i).getDescription()
						);
			}
			catch (Exception e)
			{
				Log.e("Add Error", e.toString());
				e.printStackTrace();
			}
		}
    }
  
    public void onItemClick(AdapterView parent, View v, int position, long id)
    {
    	 Log.i(tag,"item clicked! [" + feed.getItem(position).getTitle() + "]");

    	 Intent itemintent = new Intent(this,Description.class);
         
    	 Bundle b = new Bundle();
    	 b.putString("title", feed.getItem(position).getTitle());
    	 b.putString("description", feed.getItem(position).getDescription());
    	 b.putString("link", feed.getItem(position).getLink());
    	 b.putString("pubdate", feed.getItem(position).getPubDate());
    	 
    	 itemintent.putExtra("android.intent.extra.INTENT", b);
         
         startActivity(itemintent);
    }   
}