package com.techzei.www;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class HandleXML {

	private ArrayList<String> title;
	private ArrayList<String> link;
	private ArrayList<String> desc;
	private ArrayList<String> date;
	private ArrayList<String> fdesc;
	private ArrayList<String> author;
	private ArrayList<String> tmp;
	private ArrayList<String> categories;

	static int gettingSameCategories = 1;
	static int mainDescriptionOver = 0;

	private String urlString = null;
	private String combined = "| ";
	private XmlPullParserFactory xmlFactoryObject;
	public volatile boolean parsingComplete = true;

	public HandleXML(String url) {
		this.urlString = url;
		title = new ArrayList<String>();
		link = new ArrayList<String>();
		desc = new ArrayList<String>();
		fdesc = new ArrayList<String>();
		date = new ArrayList<String>();
		author = new ArrayList<String>();
		tmp = new ArrayList<String>();
		categories = new ArrayList<String>();

	}

	public ArrayList<String> getTitle() {
		return title;
	}

	public ArrayList<String> getAuthor() {
		return author;
	}

	public ArrayList<String> getLink() {
		return link;
	}

	public ArrayList<String> getDesc() {
		return desc;
	}

	public ArrayList<String> getDate() {
		return date;
	}

	public ArrayList<String> getFullDesc() {
		return fdesc;
	}

	public ArrayList<String> getCategories() {
		return categories;
	}

	public void parseXMLAndStoreIt(XmlPullParser myParser) {
		int event;
		int eventI;

		String text = null;
		String authorN = null;
		try {
			event = myParser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {
				String name = myParser.getName();
				switch (event) {
				case XmlPullParser.START_TAG: {

					break;
				}
				case XmlPullParser.TEXT:
					text = myParser.getText();
					break;
				case XmlPullParser.END_TAG:

					if (name.equals("title")) {

						title.add(text);
						gettingSameCategories = 1;
					} else if (name.equals("link")) {
						link.add(text);
					} else if (name.equals("description")) {
						desc.add(text);
					} else if (name.equals("pubDate")) {
						date.add(text);
					} else if (name.equals("category")) {

						combined += text + " | ";

					} else if (name.equals("content:encoded")) {
						fdesc.add(text);
						categories.add(combined);
						combined = "| ";
						

					} else if (name.equals("dc:creator")) {
						author.add(text);
					} else {

					}
					break;
				}
				event = myParser.next();
			}
			parsingComplete = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fetchXML() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					URL url = new URL(urlString);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setReadTimeout(10000 /* milliseconds */);
					conn.setConnectTimeout(15000 /* milliseconds */);
					conn.setRequestMethod("GET");
					conn.setDoInput(true);
					// Starts the query
					conn.connect();
					InputStream stream = conn.getInputStream();
					xmlFactoryObject = XmlPullParserFactory.newInstance();
					XmlPullParser myparser = xmlFactoryObject.newPullParser();
					myparser.setFeature(
							XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
					myparser.setInput(stream, null);
					parseXMLAndStoreIt(myparser);
					stream.close();
				} catch (Exception e) {

				}
			}
		});
		thread.start();
	}
}
