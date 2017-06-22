package name.lizhe.tool;

import org.apache.camel.CamelContext;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelSample {
	
    private final static String routingKey = "test";
    private final static String exchangeName = "myexchange";
    private final static String queueName = "myqueuename";

	public static void main(String args[]) throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {
			public void configure() {
				Processor p = (exchange)->System.out.println(exchange);
				from("rabbitmq://172.28.128.4:5672/"+exchangeName+"?username=admin&password=admin&threadPoolSize=1&autoAck=false&routingKey="+routingKey+"&queue="+queueName).process(p);
			}
		});
		context.start();
	}
}
