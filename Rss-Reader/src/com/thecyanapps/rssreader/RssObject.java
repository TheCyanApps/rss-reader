package com.thecyanapps.rssreader;

import java.io.Serializable;

public class RssObject implements Serializable {

	private static final long serialVersionUID = 124578L;
	private String title;
	private String content;
	private String url;
	private String description;
	private String creator;
	
	public RssObject(){}
	
	public RssObject(String title, String content, String url, String description, String creator){
		this.title = title;
		this.content= content;
		this.url = url;
		this.description = description;
		this.creator = creator;
	}

	public String getTitle() {		
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
}
