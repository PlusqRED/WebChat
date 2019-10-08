package ru.grape.ws.service;

public interface MessageCounter {
    long getMessageCount();

    Long incrementAndGet();

    Long decrementAndGet();

    void nullify();
}
