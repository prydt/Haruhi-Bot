package net.pranoydll.bot;

import net.pranoydll.bot.utils.HaruhiBot;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RateLimitException;

/*
 * (C) Copyright Pranoy Dutta (pranoydll) 2016
 * 
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
    		throw new IllegalArgumentException("Didn't pass a Token in!!");
    	
		final HaruhiBot haruhi = new HaruhiBot(args[0]);
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
			public void run()
			{
				try {
					haruhi.getClient().logout();
				} catch (RateLimitException e) {
					System.err.println("Error during logout");
					e.printStackTrace();
				} catch (DiscordException e) {
					System.err.println("Error during logout");
					e.printStackTrace();
				}
				
			}
		}, "Shutdown thread"));
    }
}
