package be.civaform.demorabbit.producer;

import be.civaform.demorabbit.config.AmqpDemo2MessageConfiguration;
import be.civaform.demorabbit.config.AmqpDemo3MessageConfiguration;
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

    public void envoyerMessageDemo2(DemoDTO demoDTO) {

        this.convertAndSend(AmqpDemo2MessageConfiguration.DEMO2_EXCHANGE_NAME, "", demoDTO);
    }

    public void envoyerMessageDemo3Key1(DemoDTO demoDTO) {
        this.convertAndSend(AmqpDemo3MessageConfiguration.DEMO3_EXCHANGE_NAME, AmqpDemo3MessageConfiguration.DEMO3_KEY1, demoDTO);
    }

    public void envoyerMessageDemo3Key2(DemoDTO demoDTO) {
        this.convertAndSend(AmqpDemo3MessageConfiguration.DEMO3_EXCHANGE_NAME, AmqpDemo3MessageConfiguration.DEMO3_KEY2, demoDTO);
    }

    public void envoyerMessageDemo3BadKey(DemoDTO demoDTO) {
        this.convertAndSend(AmqpDemo3MessageConfiguration.DEMO3_EXCHANGE_NAME, "BadKey", demoDTO);
    }

}
