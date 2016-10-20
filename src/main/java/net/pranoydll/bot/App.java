package net.pranoydll.bot;

import net.pranoydll.bot.utils.HaruhiBot;
import sx.blah.discord.util.DiscordException;

/*
 * A bot that kinda just does what I want it too... :p
 * 
 * @author Pranoydll
 * @version 1.0-SNAPSHOT
 */

public class App 
{
    public static void main(String[] args)
    {
    	if(args.length < 1)
    		throw new IllegalArgumentException("You didn't give a token!!");
    	try
    	{
		HaruhiBot haruhi = new HaruhiBot(args[0]);
	}
    	catch (DiscordException e)
    	{
    		System.err.println("Error while logging in...\n");
    		e.printStackTrace();
    	}   	
    }
}
