package ru.grape.ws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String name;
    private String content;
    private Long countNumber;
    private Short hours;
    private Short minutes;
    private Short seconds;
}
