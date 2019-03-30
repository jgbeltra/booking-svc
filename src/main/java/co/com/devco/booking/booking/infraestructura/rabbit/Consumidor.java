package co.com.devco.booking.booking.infraestructura.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class Consumidor implements MessageListener{
    @Override
    public void onMessage(Message message) {
        System.out.println(new String(message.getBody()));
    }
}
