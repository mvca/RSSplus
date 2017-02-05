package com.mu.android.rssplus;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import android.util.Log;

public class Handler extends DefaultHandler 
{			
  
	Feed _feed;
	Item _item;
	String _lastElementName = "";
	boolean feedAvailable = false;
	final int rsstitle = 1;
	final int rsslink = 2;
	final int rssdescription = 3;
	final int rsscategory = 4;
	final int rsspubdate = 5;
	final int rssbuilddate = 6;
	
	int depth = 0;
	int state = 0;

	Handler(){}
	
	Feed getFeed()
	{
		
		return _feed;
	}
	
	public void startDocument() throws SAXException
	{
		_feed = new Feed();
		_item = new Item();

	}
	public void endDocument() throws SAXException{}
	
	public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException
	{
		depth++;
		if (localName.equals("channel"))
		{
			state = 0;
			return;
		}
		if (localName.equals("image"))
		{
			_feed.setTitle(_item.getTitle());
			_feed.setPubDate(_item.getPubDate());
			_feed.setlastBuildDate(_feed.getlastBuildDate());
		}
		if (localName.equals("item"))
		{
			_item = new Item();
			return;
		}
		if (localName.equals("title"))
		{
			state = rsstitle;
			return;
		}
		if (localName.equals("description"))
		{
			state = rssdescription;
			return;
		}
		if (localName.equals("link"))
		{
			state = rsslink;
			return;
		}
		if (localName.equals("category"))
		{
			state = rsscategory;
			return;
		}
		if (localName.equals("pubDate"))
		{
			state = rsspubdate;
			return;
		}
		if (localName.equals("lastBuildDate"))
		{
			state = rssbuilddate;
			return;
		}
		state = 0;
	}
	
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException
	{
		
		depth--;
		if (localName.equals("item"))
		{
			_feed.addItem(_item);
			return;
		}
	}
	 
	public void characters(char ch[], int start, int length)
	{
		String theString = new String(ch,start,length);		
		switch (state)
		{
			case rsstitle:
				_item.setTitle(theString);
				state = 0;
				break;
			case rsslink:
				_item.setLink(theString);
				state = 0;
				break;
			case rssdescription:
				_item.setDescription(theString.replaceAll("\\<.*?\\>", ""));
				state = 0;
				break;
			case rsscategory:
				_item.setCategory(theString);
				state = 0;
				break;
			case rsspubdate:
				_item.setPubDate(theString);
				state = 0;
				break;
			case rssbuilddate:
				_feed.setlastBuildDate(theString);
				state = 0;
				break;
			default:
				return;
		}	
	}
}