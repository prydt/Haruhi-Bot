package net.pranoydll.bot.utils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.swisstech.bitly.BitlyClient;
import net.swisstech.bitly.model.Response;
import net.swisstech.bitly.model.v3.ShortenResponse;



// learned this from https://www.mkyong.com/java/jsoup-send-search-query-to-google/

public class Googler{
	
	private BitlyClient bitClient = new BitlyClient("e6737788dfe95a7923d055bcc5e31cc9af1e1c48");
	
	private Set<String> getDataFromGoogle(String query) {
		
		Set<String> result = new HashSet<String>();
		String request = "https://www.google.com/search?q=" + query + "&num=20";

		try {

			// need http protocol, set this as a Google bot agent :)
			Document doc = Jsoup
				.connect(request)
				//.userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
				.userAgent("Mozilla/17.0")
				.timeout(5000).get();

			// get all links
			int i = 0;
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				
				if(i > 5) break;
				
				String temp = link.attr("href");
				if(temp.startsWith("/url?q=") && !temp.contains("webcache.googleusercontent.com")){
	                                //use regex to get domain name
					result.add("google.com" + temp);
					i++;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public String google(String query)
	{
		String out = "";
		Set<String> result = getDataFromGoogle(query);
		out += "I'm showing you the results of searching for \"" + query + "\"\n";
		for(String temp : result){
			Response<ShortenResponse> respShort = bitClient.shorten().setLongUrl("https://" + temp).call();
			out += respShort.data.url + "\n";
		}
		out += result.size()+" Results...";
		
		return out;
	}
}