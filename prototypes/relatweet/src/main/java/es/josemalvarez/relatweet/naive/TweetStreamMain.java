package es.josemalvarez.relatweet.naive;

import java.io.IOException;
import java.text.SimpleDateFormat;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class TweetStreamMain {

	/**
	 * @param args
	 */
	
		 public static void main(String[] args) throws TwitterException {
			 //Remember to configure twitter4j.properties
			 	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
		        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		        StatusListener listener = new StatusListener() {
		 
					public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
						//System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
						
					}

					public void onStallWarning(StallWarning warning) {
						//System.out.println("Got stall warning:" + warning);
						
					}

					public void onStatus(Status status) {
					//	 System.out.println("@" + status.getUser().getScreenName()+","+sdf.format(status.getCreatedAt()) + "," + status.getText());
						 System.out.println(status.getUser().getScreenName()+","+sdf.format(status.getCreatedAt()) + "," + status.getText());
						
					}

					public void onException(Exception e) {
						e.printStackTrace();
						
					}

					public void onScrubGeo(long userId, long upToStatusId) {
						  //System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
						
					}

					public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
						//System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
						
					}
		        };
		        twitterStream.addListener(listener);
		        twitterStream.sample();
		    }
	}

