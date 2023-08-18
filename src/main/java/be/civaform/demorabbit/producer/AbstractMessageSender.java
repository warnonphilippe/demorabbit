package be.civaform.demorabbit.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public abstract class AbstractMessageSender {

    protected RabbitTemplate rabbitTemplate;

    protected AbstractMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    protected <T> void convertAndSend(String exchangeName, String routingKey, T data) {
        this.rabbitTemplate.convertAndSend(exchangeName, routingKey, data,
            new TenantApplicationPostProcessor(data.getClass().getName()));
    }
}
