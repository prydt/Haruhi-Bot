package net.pranoydll.bot.utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.JsonObject;

import org.json.*;

public class StrawPoller extends Command{

	private Document doc;

	public String getPollResults(int id)
	{
		try {
			doc = Jsoup.connect("https://strawpoll.me/api/v2/polls/" + id)
					.userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
					.ignoreContentType(true)
					.timeout(5000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String question = null;
		JSONArray choices = null;
		int largest = 0;

		JSONObject json;
		try {
			json = new JSONObject(doc.text());


			question = json.getString("title");

			JSONArray votes = json.getJSONArray("votes");
			choices = json.getJSONArray("options");

			largest = -1;
			for(int i = 0; i < votes.length(); i++)
			{
				largest = (votes.getInt(i) > largest) ? i : largest;
			}

			return "The winner of the poll \"" + question + "\" is ... " + choices.getString(largest);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return "Query failed!! :cry:";
	}

  public String run(String params)
  {
    return getPollResults(Integer.parseInt(params));
  }
}
