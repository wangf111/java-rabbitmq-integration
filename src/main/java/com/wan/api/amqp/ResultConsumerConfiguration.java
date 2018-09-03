package com.wan.api.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResultConsumerConfiguration extends RabbitMqConfiguration
{
	protected final String scrapingResultQueue = "scrapingResult.queue";

    @Autowired
    private ResultHandler scrapingResultHandler;

	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		template.setRoutingKey(this.scrapingResultQueue);
		template.setQueue(this.scrapingResultQueue);
        template.setMessageConverter(jsonMessageConverter());
		return template;
	}

    @Bean
	public Queue scrapingResultQueue() {
		return new Queue(this.scrapingResultQueue);
	}

	@Bean
	public SimpleMessageListenerContainer listenerContainer() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setQueueNames(this.scrapingResultQueue);
		container.setMessageListener(messageListenerAdapter());

		return container;
	}

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(scrapingResultHandler, jsonMessageConverter());
    }
}
