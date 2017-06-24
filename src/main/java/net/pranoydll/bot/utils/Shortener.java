package net.pranoydll.bot.utils;

import net.swisstech.bitly.BitlyClient;
import net.swisstech.bitly.model.Response;
import net.swisstech.bitly.model.v3.ShortenResponse;

//
// Shortens long URLs down to a small bit.ly URL
//
public class Shortener extends Command{

  // internals
  private BitlyClient bitClient = new BitlyClient("e6737788dfe95a7923d055bcc5e31cc9af1e1c48");
  private Response<ShortenResponse> respShort;

  // run command
  public String run(String params)
  {
    respShort = bitClient.shorten().setLongUrl(params).call();
    return respShort.data.url;
  }
}
