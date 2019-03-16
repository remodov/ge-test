package ru.general.electrics.test;

import ru.general.electrics.test.dto.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.function.Consumer;

/**
 * Store que and consumers.
 */
public class DefaultBroker implements Broker {
    private final List<Consumer<Message>> consumers = new ArrayList<>();

    private final PriorityBlockingQueue<Message> priorityQueue = new PriorityBlockingQueue<>();

    @Override
    public void start() {
        new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        sendMessagesToConsumers();
                    }
                }).start();
    }

    @Override
    public void stop() {
        Thread.currentThread().interrupt();
    }

    /**
     * Invoke all registered consumers for first message by prior
     */
    protected void sendMessagesToConsumers() {
        if (priorityQueue.size() > 0) {
            Message messageForSend = priorityQueue.peek();

            consumers.forEach(consumer -> consumer.accept(messageForSend));

            priorityQueue.remove(messageForSend);
        }

        System.out.println("priorityQueue size: " + priorityQueue.size());
    }

    public synchronized DefaultBroker registerConsumer(Consumer<Message> consumer) {
        consumers.add(consumer);
        return this;
    }

    public void sendMessage(Message message) {
        priorityQueue.add(message);
    }
}