package name.lizhe.tool;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class InitQueue {
    private final static String routingKey = "test.*.name";
    private final static String exchangeName = "myexchange";
    private final static String queueName = "myqueuename";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ地址
        factory.setHost("172.28.128.4");
        factory.setUsername("admin");
		factory.setPassword("admin");
//		factory.setVirtualHost("/newhost");
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("myexchange", "topic");
        //声明要关注的队列
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);
        System.out.println("Customer Waiting Received messages");
      
    }
}