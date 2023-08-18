package be.civaform.demorabbit.config;

import be.civaform.demorabbit.TenantContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

public class RabbitListenerBizAroundAdvice implements MethodInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RabbitListenerBizAroundAdvice.class);

    /**
     * place the "AroundAdvice" around each new message being processed.
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Message message = (Message) invocation.getArguments()[1];

        String tenant = (String) message.getMessageProperties().getHeaders().get(AmqpBizConfiguration.TENANT_PROPERTY);
        log.debug("Tenant in Message header is {}", tenant);
        TenantContext.setCurrentTenant(tenant);

        String application = (String) message.getMessageProperties().getHeaders().get(AmqpBizConfiguration.APPLICATION_PROPERTY);
        log.debug("Application in Message header is {}", application);
        TenantContext.setCurrentApplication(application);

        return invocation.proceed();
    }

}
