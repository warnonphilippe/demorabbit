package be.civaform.demorabbit.listener;

import be.civaform.demorabbit.producer.AbstractMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


public abstract class AbstractMessageListener extends AbstractMessageSender {

    private static final Logger log = LoggerFactory.getLogger(AbstractMessageListener.class);

    protected AbstractMessageListener(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

}
