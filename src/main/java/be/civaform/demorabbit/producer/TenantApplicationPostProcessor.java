package be.civaform.demorabbit.producer;


import be.civaform.demorabbit.TenantContext;
import be.civaform.demorabbit.config.AmqpBizConfiguration;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;

public class TenantApplicationPostProcessor implements MessagePostProcessor {

    private final String type;

    public TenantApplicationPostProcessor(String type) {
        this.type = type;
    }

    @Override
    public Message postProcessMessage(Message message) {
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        message.getMessageProperties().setHeader(AmqpBizConfiguration.TENANT_PROPERTY, getCurrentTenant());
        message.getMessageProperties().setHeader(AmqpBizConfiguration.APPLICATION_PROPERTY, getCurrentApp());
        message.getMessageProperties().setType(type);
        return message;
    }

    protected String getCurrentApp() {
        return TenantContext.getCurrentApplication();
    }

    protected String getCurrentTenant() {
        return TenantContext.getCurrentTenant();
    }


}
