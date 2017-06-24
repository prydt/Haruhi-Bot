package net.pranoydll.bot.utils;

//
// the class for the `help` command
//
public class Helper extends Command {

  private static char botPrefix = '-';

  private static String[][] descriptions = new String[][] {
    {"sp"     ,  "returns winner of a the strawpoll with the id given"        },
    {"help"   ,  "prints out list of all commands"                            },
    {"short"  ,  "returns a shorten bit.ly URL of the url given"              },
    {"google" ,  "a command that returns the top 3 google links to the query" }
  };

  // get the botPrefix
  public static char getBotPrefix() { return botPrefix; }

  // get all the descriptions
  public static String[][] getDescriptions() { return descriptions; }

  // command DOES NOT need params
  public boolean hasParams() { return false; }

  // run the help command
  public String run(String params)
  {
    String out = "```List of All Commands:\n";
    for(int i = 0; i < descriptions.length; i++)
    {
      out += descriptions[i][0] + "\t\t-\t\t" + descriptions[i][1] + '\n';
    }
    return out + "```";
  }
}
