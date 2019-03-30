package co.com.devco.booking.booking.infraestructura.rabbit;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConf {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");

        connectionFactory.setPort(5672);
        connectionFactory.setUsername("viaje");
        connectionFactory.setPassword("viaje");
        connectionFactory.setChannelCheckoutTimeout(10000);
        connectionFactory.setRequestedHeartBeat(3_000);

        return connectionFactory;

    }

    @Bean
    public SimpleMessageListenerContainer container(CachingConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames("devco.booking.viajecomprado");
        container.setMessageListener(new Consumidor());
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return container;
    }
}
