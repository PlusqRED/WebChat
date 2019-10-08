package ru.grape.ws.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.grape.ws.model.Message;
import ru.grape.ws.service.impl.AtomicLongMessageCounter;

@Controller
public class MessageWebSocketController {

    private final AtomicLongMessageCounter atomicLongMessageCounter;

    public MessageWebSocketController(AtomicLongMessageCounter atomicLongMessageCounter) {
        this.atomicLongMessageCounter = atomicLongMessageCounter;
    }

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public Message greeting(Message message) {
        message.setCountNumber(atomicLongMessageCounter.incrementAndGet());
        return message;
    }
}
