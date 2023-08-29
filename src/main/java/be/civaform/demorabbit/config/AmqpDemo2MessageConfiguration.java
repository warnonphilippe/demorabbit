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
public class AmqpDemo2MessageConfiguration {

    // Exchange et queue pour le traitement des demandes de webhook (on dispose déjà des infos RingRing)
    public static final String DEMO2_QUEUE_NAME1 = "demo2_queue1";
    public static final String DEMO2_QUEUE_NAME2 = "demo2_queue2";
    public static final String DEMO2_EXCHANGE_NAME = "demo2_exchange";

    @Bean
    public Exchange demo2Exchange() {
        return ExchangeBuilder.fanoutExchange(DEMO2_EXCHANGE_NAME).build();
    }

    @Bean
    public Queue demo2Queue1() {
        return QueueBuilder.durable(DEMO2_QUEUE_NAME1).build();
    }

    @Bean
    public Binding demo2Binding1() {
        return BindingBuilder.bind(demo2Queue1()).to(demo2Exchange()).with("*").noargs();
    }

    @Bean
    public Queue demo2Queue2() {
        return QueueBuilder.durable(DEMO2_QUEUE_NAME2).build();
    }

    @Bean
    public Binding demo2Binding2() {
        return BindingBuilder.bind(demo2Queue2()).to(demo2Exchange()).with("*").noargs();
    }
}
