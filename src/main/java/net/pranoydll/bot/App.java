package net.pranoydll.bot;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

public class App 
{
	/*
	 * Returns a IDiscordClient, logged in with a token 
	 */
	public static IDiscordClient getClient(String token, boolean login) throws DiscordException {
	    ClientBuilder clientBuilder = new ClientBuilder();
	    clientBuilder.withToken(token);
	    if (login) 
	    {
	    	return clientBuilder.login();
		}else {
			return clientBuilder.build();
	    }
	}
	
    public static void main( String[] args )
    {
    	
    }
}
