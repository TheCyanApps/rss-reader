package com.thecyanapps.rssreader;

public class RssException extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg;
	public RssException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		return msg;
	}

}
