package ru.grape.ws.service.impl;

import org.springframework.stereotype.Service;
import ru.grape.ws.service.MessageCounter;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class AtomicLongMessageCounter implements MessageCounter {

    private AtomicLong atomicLong;

    public AtomicLongMessageCounter() {
        this.atomicLong = new AtomicLong(0L);
    }

    @Override
    public long getMessageCount() {
        return atomicLong.get();
    }

    @Override
    public Long incrementAndGet() {
        return atomicLong.incrementAndGet();
    }

    @Override
    public Long decrementAndGet() {
        return atomicLong.decrementAndGet();
    }

    @Override
    public void nullify() {
        atomicLong.set(0L);
    }
}
