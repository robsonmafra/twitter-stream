package me.robsonmafra.twitter;

import java.util.Arrays;

import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * <p>Responsible for start the streaming and create the filter query. <br>
 * The words will be used to filter and just the Twitters with this words will be collected.
 * </p>
 *
 * @author Robson Mafra
 */
public class TwitterFilterStream {

	private static final String CONSUMER_KEY = "BkHvc11pS9UJPF3OohXZw";
	private static final String CONSUMER_SECRET = "BWNHEZtdyCJEQfL3BMurDZZ5gZXn36jdEdlPpvQQ4ZA";
	
	private static final String ACCESS_TOKEN = "217244020-0veDVb7eZ6DqaSIC5gNEHgnJJ8t3tzxsmB5D463v";
	private static final String ACCESS_TOKEN_SECRET = "2JA9TKwC2K2qMUOPIRgvG9xVJ39KNowJIEctWint1Xd4D";

	private String[] words;
	private TwitterStream twitterStream = null;
	
	public TwitterFilterStream(String [] words) {
		this.words = Arrays.copyOf(words, words.length);
	}
	
	public void start() {
		System.out.println("Start twitter streaming...");
		try {
			this.stop();
			this.startStreaming();
		} catch (TwitterException e) {
			System.out.println("Fail to shutdown the stream "+ e);
			this.stop();
		}
	}
	
	public void stop() {
		if (twitterStream != null) {
			System.out.println("Stoping Twitter Stream...");
			try{
				twitterStream.cleanUp(); // shutdown internal stream consuming thread
				twitterStream.shutdown(); // Shuts down internal dispatcher thread shared by all TwitterStream instances.
				twitterStream = null;
			} catch(Exception e) {
				System.out.println("Fail to shutdown the stream "+ e);
			}			
		}
	}

	private void startStreaming() throws TwitterException {
		System.out.println("Start twitter streaming...");

		twitterStream = new TwitterStreamFactory(buildConfiguration()).getInstance();
		TwitterListener twitterListener = new TwitterListener();
		twitterStream.addListener(twitterListener);

		FilterQuery query = new FilterQuery();
		query.track(words);

		twitterStream.filter(query);
	}

	private Configuration buildConfiguration() {
		ConfigurationBuilder config = new ConfigurationBuilder();
		config.setDebugEnabled(true)
			.setOAuthConsumerKey(CONSUMER_KEY)
			.setOAuthConsumerSecret(CONSUMER_SECRET)
			.setOAuthAccessToken(ACCESS_TOKEN)
			.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		return config.build();
	}
}
