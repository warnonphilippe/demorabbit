package be.civaform.demorabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AmqpDemoMessageConfiguration {

    public static final String DEMO_QUEUE_NAME = "demo_queue";
    public static final String DEMO_EXCHANGE_NAME = "demo_exchange";

    public static final String ERREUR_QUEUE_NAME = "erreur_queue";
    public static final String ERREUR_EXCHANGE_NAME = "erreur_exchange";

    public static final String RETRY_QUEUE_NAME = "retry_queue_5s";
    public static final String RETRY_EXCHANGE_NAME = "retry5_exchange_5s";


    @Bean
    public Exchange demoExchange() {
        return ExchangeBuilder.fanoutExchange(DEMO_EXCHANGE_NAME).build();
    }

    @Bean
    public Queue demoQueue() {
        return QueueBuilder.durable(DEMO_QUEUE_NAME).build();
    }

    @Bean
    public Binding webhookMailBinding() {
        return BindingBuilder.bind(demoQueue()).to(demoExchange()).with("*").noargs();
    }

    @Bean
    public Exchange erreurExchange() {
        return ExchangeBuilder.fanoutExchange(ERREUR_EXCHANGE_NAME).build();
    }

    @Bean
    public Queue erreurQueue() {
        return QueueBuilder.durable(ERREUR_QUEUE_NAME).build();
    }

    @Bean
    public Binding erreurBinding() {
        return BindingBuilder.bind(erreurQueue()).to(erreurExchange()).with("*").noargs();
    }

    @Bean
    public Exchange retryExchange() {
        return ExchangeBuilder.fanoutExchange(RETRY_EXCHANGE_NAME).build();
    }

    @Bean
    public Queue retryQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DEMO_EXCHANGE_NAME);
        args.put("x-message-ttl", 5000);
        return new Queue(RETRY_QUEUE_NAME,true,false,false, args);
    }

    @Bean
    public Binding retryBinding() {
        return BindingBuilder.bind(retryQueue()).to(retryExchange()).with("*").noargs();
    }
}
