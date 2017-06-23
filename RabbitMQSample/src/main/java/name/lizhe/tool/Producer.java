package name.lizhe.tool;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	public final static String routingKey = "test.123.name";

	public static void main(String[] args) throws IOException, TimeoutException {
		// 创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		// 设置RabbitMQ相关信息
		factory.setHost("172.28.128.4");
		factory.setUsername("admin");
		factory.setPassword("admin");
		// factory.setPort(2088);
		// 创建一个新的连接
		Connection connection = factory.newConnection();
		// 创建一个通道
		Channel channel = connection.createChannel();
		// 声明一个队列 channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "Hello RabbitMQ";
		// 发送消息到队列中
		channel.basicPublish("myexchange", routingKey, null, message.getBytes("UTF-8"));
		System.out.println("Producer Send +'" + message + "'");
		channel.close();
		connection.close();
	}
}