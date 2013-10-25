package me.robsonmafra.twitter;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

/**
 * <p>This is a StatusListener implementation to the Twitter Streaming API.<br>
 * Every new Twitter the method onStatus will be called <br>
 * </p>
 *
 * @author Robson Mafra
 */
public class TwitterListener implements StatusListener {

	@Override
	public void onException(Exception exception) {
		exception.printStackTrace();
	}
	
	@Override
	public void onStatus(Status status) {
		System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
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
