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
public class AmqpDemo3MessageConfiguration {

    public static final String DEMO3_QUEUE_NAME1 = "demo3_queue1";
    public static final String DEMO3_QUEUE_NAME2 = "demo3_queue2";
    public static final String DEMO3_EXCHANGE_NAME = "demo3_exchange-topic";

    public static final String DEMO3_KEY1 = "demo3_key1";
    public static final String DEMO3_KEY2 = "demo3_key2";

    @Bean
    public Exchange demo3Exchange() {
        return ExchangeBuilder.topicExchange(DEMO3_EXCHANGE_NAME).build();
    }

    @Bean
    public Queue demo3Queue1() {
        return QueueBuilder.durable(DEMO3_QUEUE_NAME1).build();
    }

    @Bean
    public Binding demo3Binding1() {
        return BindingBuilder.bind(demo3Queue1()).to(demo3Exchange()).with(DEMO3_KEY1).noargs();
    }

    @Bean
    public Queue demo3Queue2() {
        return QueueBuilder.durable(DEMO3_QUEUE_NAME2).build();
    }

    @Bean
    public Binding demo3Binding2() {
        return BindingBuilder.bind(demo3Queue2()).to(demo3Exchange()).with(DEMO3_KEY2).noargs();
    }
}
