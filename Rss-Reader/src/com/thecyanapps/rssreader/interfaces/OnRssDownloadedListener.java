package com.thecyanapps.rssreader.interfaces;

import java.util.List;

import com.thecyanapps.rssreader.RssObject;

public interface OnRssDownloadedListener {
	
	public void onRssDownloaded(List<RssObject> list);
	
}
