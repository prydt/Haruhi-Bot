package net.pranoydll.bot.utils;

//
// the class that all commands extend
//
public abstract class Command {

  // returns whether it has parameters or not
  public abstract boolean hasParams();

  // run the command and return output
  public abstract String run(String params);
}
