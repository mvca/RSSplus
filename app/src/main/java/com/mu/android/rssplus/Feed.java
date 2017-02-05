package com.mu.android.rssplus;

import java.util.List;
import java.util.Vector;

import com.mu.android.rssplus.Item;

public class Feed 
{
	private String _title = null;
	private String _pubdate = null;
	private String _builddate = null;
	private int _itemcount = 0;
	private List<Item> _itemlist;
		
	Feed()
	{
		_itemlist = new Vector<Item>(0); 
	}
	int addItem(Item item)
	{
		_itemlist.add(item);
		_itemcount++;
		return _itemcount;
	}
	Item getItem(int location)
	{
		return _itemlist.get(location);
	}
	List<Item> getAllItems()
	{
		return _itemlist;
	}
	int getItemCount()
	{
		return _itemcount;
	}
	void setTitle(String title)
	{
		_title = title;
	}
	void setPubDate(String pubdate)
	{
		_pubdate = pubdate;
	}
	void setlastBuildDate(String builddate)
	{
		_builddate = builddate;
	}
	String getTitle()
	{
		return _title;
	}
	String getPubDate()
	{
		return _pubdate;
	}
	String getlastBuildDate()
	{
		return _builddate;
	}	
}
