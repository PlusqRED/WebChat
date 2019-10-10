package ru.grape.ws.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.grape.ws.model.UrlForm;
import ru.grape.ws.service.impl.WebDataLoader;

@Controller
public class DataLoaderWebSocketController {
    private final WebDataLoader webDataLoader;

    public DataLoaderWebSocketController(WebDataLoader webDataLoader) {
        this.webDataLoader = webDataLoader;
    }

    @MessageMapping("/url")
    @SendTo("/topic/url")
    @ResponseBody
    private UrlForm sendDataToUsers(@RequestBody UrlForm form) throws InterruptedException {
        webDataLoader.load(form.getUrl(), form::setData);
        return form;
    }

}
