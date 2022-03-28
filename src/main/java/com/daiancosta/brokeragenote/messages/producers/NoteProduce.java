package com.daiancosta.brokeragenote.messages.producers;

import com.daiancosta.brokeragenote.domain.entities.FileInfo;
import com.daiancosta.brokeragenote.domain.entities.messages.producers.NoteProducerMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NoteProduce {

    @Value("${brokerage.rabbitmq.exchange}")
    String exchange;

    @Value("${brokerage.rabbitmq.routingkey}")
    String routingKey;

    public final RabbitTemplate rabbitTemplate;

    @Autowired
    public NoteProduce(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(final NoteProducerMessage payload) {
        rabbitTemplate.convertAndSend(exchange, routingKey, payload);
    }
}