package me.robsonmafra.twitter;

import me.robsonmafra.solr.SolrUpdate;
import me.robsonmafra.twitter.model.Tweet;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * <p>This is a StatusListener implementation to the Twitter Streaming API.<br>
 * Every new Twitter the method onStatus will be called <br>
 * </p>
 *
 * @author Robson Mafra
 */
public class TwitterListener implements StatusListener {

	private DBCollection twitterCollection;

	public TwitterListener(DB db) {
		this.twitterCollection = db.getCollection("twitters");
	}

	@Override
	public void onException(Exception exception) {
		exception.printStackTrace();
	}
	
	@Override
	public void onStatus(Status status) {
		System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
		Tweet tweet = new Tweet();
		tweet.put("id",status.getId());
		tweet.put("user_id",status.getUser().getId());
		tweet.put("username",status.getUser().getScreenName());
		tweet.put("username_image_url",status.getUser().getProfileImageURL());
		tweet.put("text",status.getText());
		tweet.put("created_at",status.getCreatedAt());
		tweet.put("in_reply_to_status_id",status.getInReplyToStatusId());
		tweet.put("in_reply_to_user_id",status.getInReplyToUserId());
		tweet.put("in_reply_to_screen_name",status.getInReplyToScreenName());
		twitterCollection.insert(tweet);
		SolrUpdate.update();
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		System.out.println("OnDeletionNotice id:" + statusDeletionNotice.getStatusId());
	}

	@Override
	public void onScrubGeo(long userId, long upToStatusId) {
		System.out.println("OnScrubGeo userId:" + userId + " upToStatusId:" + upToStatusId);
	}

	@Override
	public void onStallWarning(StallWarning stallWarning) {
		System.out.println("OnStallWarning:" + stallWarning);
	}

	@Override
	public void onTrackLimitationNotice(int numberOfLimitation) {
		System.out.println("NumberOfLimitation:" + numberOfLimitation);
	}
}
