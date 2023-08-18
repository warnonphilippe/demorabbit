package be.civaform.demorabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpDemoMessageConfiguration {

    // Exchange et queue pour le traitement des demandes de webhook (on dispose déjà des infos RingRing)
    public static final String DEMO_QUEUE_NAME = "demo_queue";
    public static final String DEMO_EXCHANGE_NAME = "demo_exchange";

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
}
