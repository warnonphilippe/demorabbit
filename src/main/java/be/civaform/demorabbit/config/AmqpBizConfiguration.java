package be.civaform.demorabbit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AmqpBizConfiguration {

    public static final String TENANT_PROPERTY = "tenant";
    public static final String APPLICATION_PROPERTY = "application";

    @Primary //fonctionne encore dans le nouveau blueprint, mais a condition de définir un nouveau nom
    @Bean("bizRabbitTemplate")
    public RabbitTemplate customRabbitTemplate(final ConnectionFactory connectionFactory, ObjectMapper mapper) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter(mapper));
        return rabbitTemplate;
    }

    @Bean("bizMessageConverter")
    public Jackson2JsonMessageConverter messageConverter(ObjectMapper mapper) {
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean("rabbitListenerBizContainerFactory")
    public SimpleRabbitListenerContainerFactory rabbitListenerBizContainerFactory(ConnectionFactory connectionFactory, ObjectMapper mapper) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter(mapper));
        factory.setAdviceChain(rabbitListenerBizAroundAdvice());
        return factory;
    }

    @Bean
    public RabbitListenerBizAroundAdvice rabbitListenerBizAroundAdvice() {
        return new RabbitListenerBizAroundAdvice();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
