package com.thecyanapps.rssreader;

import java.util.ArrayList;

import org.xml.sax.AttributeList;
import org.xml.sax.HandlerBase;
import org.xml.sax.SAXException;

@SuppressWarnings("deprecation")
public class RssHandler extends HandlerBase{
	
	private StringBuffer strBuff = new StringBuffer(1024);
	private ArrayList<RssObject> list;
	
	boolean isItem = false;
	String title, url, desc, cont,creator;
	
	public RssHandler(){
		this.list = new ArrayList<RssObject>();
	}
	
	@Override
	public void startDocument() throws SAXException {
		RssManager.getInstance().isLoaded = false;
		RssManager.getInstance().mOnRssDownloadStartedListener.onDownloadStarted();
		super.startDocument();
	}
	
	@Override
	public void endDocument() throws SAXException {
		RssManager.getInstance().isLoaded = true;
		if(RssManager.getInstance().dialog!=null) RssManager.getInstance().dialog.dismiss();
		RssManager.getInstance().mOnRssDownloadedListener.onRssDownloaded(list);
		super.endDocument();
	}
	
	@Override
	public void characters (char ch[], int start, int length) throws SAXException
	{
		strBuff.append(ch, start, length);
	}
	
	@Override
	public void startElement(String name, AttributeList attributes)
			throws SAXException {
		super.startElement(name, attributes);
		
		if(name.equals("item")){
			isItem = true;
		}
		
		else if(name.equals("title") || name.equals("link") || name.equals("description") || name.equals("content:encoded") || name.equals("dc:creator")){
			strBuff = new StringBuffer();
		}
	} 
	
	@Override
	public void endElement(String name) throws SAXException {
		super.endElement(name);
		
		if(name.equals("item")){
			if(isItem){
				list.add(new RssObject(title, cont, url, desc,creator));
			}
			isItem = false;
		}
		
		if(isItem){
			if(name.equals("title")){
				title = strBuff.toString();
			}
			else if(name.equals("link")){
				url = strBuff.toString();
			}
			else if(name.equals("description")){
				desc = strBuff.toString();
			}
			else if(name.equals("content:encoded")){
				cont = strBuff.toString();
			}
			else if(name.equals("dc:creator")){
				creator = strBuff.toString();
			}
		}
	}
	
	
	
	/*private void downloadComments(String url, ArrayList<RSSComment> comments) {
		try {
			InputStream dis = (InputStream) new URL(url).getContent();
			SAXParserFactory factory = SAXParserFactory.newInstance();
		
			SAXParser parser = factory.newSAXParser();
			RSSCommentsHandler h = new RSSCommentsHandler(comments);
			parser.parse(dis,h);
		} catch (Exception e) {	}
	}*/
}
