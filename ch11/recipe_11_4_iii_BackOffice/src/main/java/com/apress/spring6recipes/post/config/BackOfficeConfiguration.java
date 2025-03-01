package com.apress.spring6recipes.post.config;

import com.apress.spring6recipes.post.MailListener;
import com.apress.spring6recipes.post.MailMessageConverter;
import jakarta.jms.ConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

@Configuration
@EnableJms
public class BackOfficeConfiguration {

  @Bean
  public ConnectionFactory connectionFactory() {
    return new ActiveMQConnectionFactory("tcp://localhost:61616");
  }

  @Bean
  public MailListener mailListener() {
    return new MailListener();
  }

  @Bean
  public SimpleJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory cf) {
    var listenerContainerFactory = new SimpleJmsListenerContainerFactory();
    listenerContainerFactory.setConnectionFactory(cf);
    listenerContainerFactory.setMessageConverter(new MailMessageConverter());
    return listenerContainerFactory;
  }
}
