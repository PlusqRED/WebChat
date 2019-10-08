package ru.grape.ws.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessagePageController {

    @Value("${welcome.message}")
    private String message;

    @GetMapping("/")
    public String welcome(Model model) {
        model.addAttribute("helloMessage", this.message);
        return "chat";
    }
}
