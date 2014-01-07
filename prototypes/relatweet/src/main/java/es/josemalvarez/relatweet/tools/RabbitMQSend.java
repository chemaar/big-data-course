package es.josemalvarez.relatweet.tools;
import java.io.IOException;


import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import es.josemalvarez.relatweet.lambda.speed.LambdaArchitectureConstants;

public class RabbitMQSend {
	public static void main(String []args) throws IOException{
		  ConnectionFactory factory = new ConnectionFactory();
		  factory.setHost("localhost");
		  Connection connection = factory.newConnection();
		  Channel channel = connection.createChannel();
		  channel.queueDeclare(LambdaArchitectureConstants.QUEUE_NAME, false, false, false, null);
		  String message = "Hello World!";
		  channel.basicPublish("", LambdaArchitectureConstants.QUEUE_NAME, null, message.getBytes());
		  System.out.println(" [x] Sent '" + message + "'");
		  channel.close();
		  connection.close();
	}
}
