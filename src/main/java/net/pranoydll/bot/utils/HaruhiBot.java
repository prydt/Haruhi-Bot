package net.pranoydll.bot.utils;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.util.DiscordException;

//
// the HaruhiBot!
//
public class HaruhiBot {

  private ClientBuilder builder = new ClientBuilder();
  private IDiscordClient client;

  private EventDispatcher dispatcher;

  /*
   * Returns the HaruhiBot IDiscordClient
   */
  public IDiscordClient getClient()
  {
      return client;
  }

  private void makeClient(String token) throws DiscordException
  {
    builder.withToken(token);
    client = builder.login();

    dispatcher = client.getDispatcher();
    dispatcher.registerListener(new HaruhiEventListener(this));
  }

  public HaruhiBot(String token)
  {
    try {
      makeClient(token);
    } catch (DiscordException e) {
      System.err.println(e.getErrorMessage());
      e.printStackTrace();
    }
  }

  public HaruhiBot()
  {
    // null for now
  }

  /*
   * This will make the client if you didn't initially set the token
   */
  public void setToken(String token) throws DiscordException
  {
    makeClient(token);
  }
}
