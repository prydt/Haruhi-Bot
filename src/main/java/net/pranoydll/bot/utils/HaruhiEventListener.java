package net.pranoydll.bot.utils;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

/*
 * A class that will handle all of the events
 */

public class HaruhiEventListener
{
  // an instance of Haruhi
  private HaruhiBot haruhi;

  // the character that goes in front of all the commands
  private static char botPrefix = Helper.getBotPrefix();

  // all the command descriptions
  private static String[][] descriptions = Helper.getDescriptions();

  private Command[] commands = new Command[] {
    new StrawPoller(),
    new Helper(),
    new Shortener(),
    new Googler()
  };

  public HaruhiEventListener(HaruhiBot haruhi)
  {
    this.haruhi = haruhi;
  }

  public void sendMessage(IChannel channel, String content)
  {
    try
    {
      new MessageBuilder(haruhi.getClient()).withChannel(channel).withContent(content).build();
    }
    catch(RateLimitException e)
    {
      System.err.println("Bot is sending messages too fast!!");
      e.printStackTrace();
    }
    catch(DiscordException e)
    {
      System.err.println(e.getErrorMessage());
      e.printStackTrace();
    }
    catch(MissingPermissionsException e)
    {
      System.err.println("Give Haruhi some power will ya...\nHaruhi need more permissions :yum:");
      e.printStackTrace();
    }
  }

  @EventSubscriber
  public void onReadyEvent(ReadyEvent event)
  {
    // I'm Haruhi Suzumiya, from East Junior High. First off, I'm not interested in ordinary people.
    //But, if any of you are aliens, time-travelers, or espers, please come see me. That is all!

    System.out.println("Successfully started on " + event.getClient().getGuilds().size() + " guilds...");
  }

  @EventSubscriber
  public void onMessageReceivedEvent(MessageReceivedEvent event)
  {
    IMessage message = event.getMessage();
    IChannel messageChannel = message.getChannel();
    String content = message.getContent();

    if (content.charAt(0) == botPrefix)
    {
      for(int i = 0; i < descriptions.length; i++)
      {
        try{
          if(content.substring(1, descriptions[i][0].length() + 1)
            .equals(descriptions[i][0]))
          {
            sendMessage(messageChannel, commands[i].run(content.substring(descriptions[i][0].length() + (commands[i].hasParams() ? 2 : 0))));
            return;
          }
        }catch(StringIndexOutOfBoundsException e)
        {
          // do nothing if it goes out of bounds (there is no error here)
        }
      }
      // error message for entering wrong command
      sendMessage(messageChannel, "The command wasn't recognized, Baka!\nType `" + botPrefix + "help` for a list of possible commands.");
    }
  }

}
