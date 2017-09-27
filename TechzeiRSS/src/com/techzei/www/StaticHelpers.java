package com.techzei.www;

import android.content.Context;
import android.widget.Toast;

public class StaticHelpers {

	public static String getDataFromPTag(String tmp) {

		int pos = tmp.length();

		char[] chars = tmp.toCharArray();

		for (int j = 3; j < chars.length; j++) {

			if (chars[j] == '<' && chars[j + 1] == '/' && chars[j + 2] == 'p'
					&& chars[j + 3] == '>') {
				pos = j;
				break;
			}

		}

		return (tmp.substring(3, pos));

	}

	public void show(Context c, String t) {

		Toast.makeText(c, t, Toast.LENGTH_SHORT).show();
	}

	public static String getImageUrl(String imagedata) {

		String img = "";

		String data = imagedata;

		int p = data.indexOf("<img src=\"");
		if(p == -1){
			return "I";
		}

		char[] c = data.substring(p + 10).toCharArray();

		for (int i = 0; i < c.length; i++) {

			if (c[i] == '"') {

				img = (String.valueOf(c).substring(0, i));
				break;
			}
		}

		return img;
	}
}
