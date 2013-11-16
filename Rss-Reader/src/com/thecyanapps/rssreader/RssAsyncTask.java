package com.thecyanapps.rssreader;

import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.os.AsyncTask;

public class RssAsyncTask extends AsyncTask<Void, Void, Void> {

	private String url;
	
	public RssAsyncTask(String url) {
		this.url = url;
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
	}
	
	private void downloadRSS() {
		try {
			InputStream dis = (InputStream) new URL(url).getContent();
			SAXParserFactory factory = SAXParserFactory.newInstance();
		
			SAXParser parser = factory.newSAXParser();
			RssHandler h = new RssHandler();
			parser.parse(dis,h);
		} catch (Exception e) {	}
	}	
}
