package com.thecyanapps.rssreader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import android.os.AsyncTask;

public class RssAsyncTask extends AsyncTask<Void, Void, Void> {

	private String url;
	private boolean isError = false;
	private String errorMsg;
	private ArrayList<RssObject> list;
	
	public RssAsyncTask(String url) {
		this.url = url;
		this.list = new ArrayList<RssObject>();
	}

	@Override
	protected void onPreExecute() {
	}

	@Override
	protected Void doInBackground(Void... voids) {
		downloadRSS();
		return null;
	}

	@Override
	protected void onPostExecute(Void aVoid) {
		if(list.size()>0) RssManager.getInstance().mOnRssDownloadedListener.onRssDownloaded(list);
		if(isError) RssManager.getInstance().mOnRssErrorListener.onRssError("Error while downloading RSS: "+errorMsg);
	}
	
	private void downloadRSS(){
			InputStream dis = null;
			try {
				dis = (InputStream) new URL(url).getContent();
			} catch (MalformedURLException e) {
				isError = true;
				errorMsg = e.getMessage();
				e.printStackTrace();
			} catch (IOException e) {
				isError = true;
				errorMsg = e.getMessage();
				e.printStackTrace();
			}
			SAXParserFactory factory = SAXParserFactory.newInstance();
		
			SAXParser parser = null;
			try {
				parser = factory.newSAXParser();
			} catch (ParserConfigurationException e) {
				isError = true;
				errorMsg = e.getMessage();
				e.printStackTrace();
			} catch (SAXException e) {
				isError = true;
				errorMsg = e.getMessage();
				e.printStackTrace();
			}
			RssHandler h = new RssHandler(list);
			try {
				parser.parse(dis,h);
			} catch (SAXException e) {
				isError = true;
				errorMsg = e.getMessage();
				e.printStackTrace();
			} catch (IOException e) {
				isError = true;
				errorMsg = e.getMessage();
				e.printStackTrace();
			}
	}	
}
