package be.civaform.demorabbit.listener;


import be.civaform.demorabbit.TenantContext;
import be.civaform.demorabbit.config.AmqpDemo2MessageConfiguration;
import be.civaform.demorabbit.config.AmqpDemo3MessageConfiguration;
import be.civaform.demorabbit.dto.DemoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Demo3MessageListener1 extends AbstractMessageListener{

    private static final Logger logger = LoggerFactory.getLogger(Demo3MessageListener1.class);

    public Demo3MessageListener1(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    @RabbitListener(
        queues = AmqpDemo3MessageConfiguration.DEMO3_QUEUE_NAME1,
        containerFactory = "rabbitListenerBizContainerFactory",
        concurrency = "3-10")
    public void onMessage(@Payload DemoDTO dto)  {

        try {
            // TODO traitement du message...
            logger.info("KEY {} - TENANT {} : {}", "UN", TenantContext.getCurrentTenant(), dto.toString());


        } catch (Exception ex) {
            // pour éviter requeing auto en cas d'erreur
            throw new AmqpRejectAndDontRequeueException("Erreur lors du traitement", ex);
        }

    }


}
