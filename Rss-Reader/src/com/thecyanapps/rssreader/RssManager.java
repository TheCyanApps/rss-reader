package com.thecyanapps.rssreader;

import android.app.Dialog;

import com.thecyanapps.rssreader.interfaces.OnRssDownloadStartedListener;
import com.thecyanapps.rssreader.interfaces.OnRssDownloadedListener;
import com.thecyanapps.rssreader.interfaces.OnRssErrorListener;

public class RssManager{
	
	private static RssManager manager;
	protected Dialog dialog;
	protected boolean isLoaded;
	protected OnRssDownloadStartedListener mOnRssDownloadStartedListener;
	protected OnRssDownloadedListener mOnRssDownloadedListener;
	protected OnRssErrorListener mOnRssErrorListener;
	
	public static RssManager getInstance(){
		if(manager==null){
			manager = new RssManager();
			manager.isLoaded = false;
		}
		return manager;
	}
	
	/**  
	 * @return Whether the rss feed is loaded.
	 */
	public boolean isLoaded(){
		return isLoaded;
	}
	
	/**
	 * Downloads the rss feed from the url given and if you have defined a dialog with
	 * setDownloadingDialog(Dialog) it will show it until the process finishes.
	 * @param url The rss feed URL.
	 * @see #setDownloadingDialog(Dialog)
	 */
	public void downloadRss(String url){
		RssAsyncTask rat = new RssAsyncTask(url);
		if(dialog!=null) dialog.show();
		rat.execute();
	}
	
	/**
	 * Sets the dialog to display when the rss is beign downloaded.
	 * @param dialog The dialog that will show.
	 */
	public void setDownloadingDialog(Dialog dialog){
		this.dialog = dialog;
	}
	
	/**
	 * Register a callback to be invoked when the RSS starts to downlaod.
	 * @param eventListener The callback that will run.
	 */
	public void setOnRssDownloadStarted(OnRssDownloadStartedListener eventListener){
		mOnRssDownloadStartedListener = eventListener;
	}
	
	/**
	 * Register a callback to be invoked when the RSS is downloaded.
	 * @param eventListener The callback that will run.
	 */
	public void setOnRssDownloaded(OnRssDownloadedListener eventListener){
		mOnRssDownloadedListener = eventListener;
	}
	
	/**
	 * Register a callback to be invoked when the RSS gets an error in the download process.
	 * @param eventListener The callback that will run.
	 */
	public void setOnRssError(OnRssErrorListener eventListener){
		mOnRssErrorListener = eventListener;
	}
}
