package ru.general.electrics.test;

import ru.general.electrics.test.dto.Message;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.function.Consumer;

/**
 * Store que and consumers.
 */
public class DefaultBroker implements Broker {
    private final ConcurrentLinkedQueue<Consumer<Message>> consumers = new ConcurrentLinkedQueue<>();

    private final PriorityBlockingQueue<Message> priorityQueue = new PriorityBlockingQueue<>();

    @Override
    public void start() {
        new Thread(
                () -> {
                    while (true) {
                        sendMessagesToConsumers();
                    }
                }).start();
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

    public DefaultBroker registerConsumer(Consumer<Message> consumer) {
        if (consumer == null) {
            throw new IllegalArgumentException("Consumer can not be null");
        }

        consumers.add(consumer);

        return this;
    }

    public void sendMessage(Message message) {
        priorityQueue.add(message);
    }
}