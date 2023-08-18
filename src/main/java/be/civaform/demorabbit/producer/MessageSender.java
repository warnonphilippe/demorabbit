package be.civaform.demorabbit.producer;

import be.civaform.demorabbit.config.AmqpDemoMessageConfiguration;
import be.civaform.demorabbit.dto.DemoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSender extends AbstractMessageSender{

    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    public MessageSender(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    public void envoyerMessageDemo(DemoDTO demoDTO) {

        this.convertAndSend(AmqpDemoMessageConfiguration.DEMO_EXCHANGE_NAME, "", demoDTO);
    }

}
