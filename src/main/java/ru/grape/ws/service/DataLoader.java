package ru.grape.ws.service;

import java.util.function.Consumer;

public interface DataLoader {
    void load(String url, Consumer<String> processor);
}
