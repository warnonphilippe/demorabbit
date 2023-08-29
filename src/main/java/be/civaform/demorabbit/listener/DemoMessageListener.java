package be.civaform.demorabbit.listener;


import be.civaform.demorabbit.TenantContext;
import be.civaform.demorabbit.config.AmqpDemoMessageConfiguration;
import be.civaform.demorabbit.dto.DemoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class DemoMessageListener extends AbstractMessageListener{

    private static final Logger logger = LoggerFactory.getLogger(DemoMessageListener.class);

    public DemoMessageListener(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    @RabbitListener(
        queues = AmqpDemoMessageConfiguration.DEMO_QUEUE_NAME,
        containerFactory = "rabbitListenerBizContainerFactory",
        concurrency = "3-10")
    public void onMessage(@Payload DemoDTO dto)  {

        try {
            // TODO traitement du message...
            logger.info("TENANT {} : {}", TenantContext.getCurrentTenant(), dto.toString());
            dto.setEssais(dto.getEssais() + 1);
            if (dto.isErreur()){
                if (dto.getEssais() < 3){
                    this.convertAndSend(AmqpDemoMessageConfiguration.RETRY_EXCHANGE_NAME, "", dto);
                } else {
                    this.convertAndSend(AmqpDemoMessageConfiguration.ERREUR_EXCHANGE_NAME, "", dto);
                }
            }

        } catch (Exception ex) {
            // pour éviter requeing auto en cas d'erreur
            throw new AmqpRejectAndDontRequeueException("Erreur lors du traitement", ex);
        }

    }


}
