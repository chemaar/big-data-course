package es.josemalvarez.relatweet.lambda.speed.workers;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import es.josemalvarez.relatweet.lambda.speed.LambdaArchitectureConstants;

public class TweetTimelineObserver implements Observer {

	private Channel channel;
	private Connection connection;
	public TweetTimelineObserver() throws IOException{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();
		channel.queueDeclare(LambdaArchitectureConstants.QUEUE_NAME, false, false, false, null);

	}
	public void update(Observable from, Object value) {
		try {
			channel.basicPublish("", LambdaArchitectureConstants.QUEUE_NAME, null, value.toString().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}  

	}
	protected void finalize() throws Throwable {
		super.finalize();
		channel.close();
		connection.close();
	}

}

