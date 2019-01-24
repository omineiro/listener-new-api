package org.camunda.pocesferaFinal;


import org.camunda.pocesferaFinal.domain.ReceiveMessage;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class Configurations {

    private static final String QUEUE_NAME  = "queue-pubsub-test";

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListener messageListener) {
        SimpleMessageListenerContainer simpleListener = new SimpleMessageListenerContainer();
        simpleListener.setConnectionFactory(connectionFactory);
        simpleListener.setMessageListener(messageListener);
        simpleListener.setQueueNames(QUEUE_NAME);

        return simpleListener;
    }

    @Bean
    MessageListener messageListener() {
        return new ReceiveMessage();
    }

    @Bean
    public FilterRegistrationBean processCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

}
