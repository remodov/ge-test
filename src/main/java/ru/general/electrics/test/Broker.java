package ru.general.electrics.test;

import ru.general.electrics.test.dto.Message;

import java.util.function.Consumer;

/**
 * Store que and consumers.
 */
public interface Broker {
    /**
     * Start broker for send messages by prior for subscribers
     */
    void start();


    /**
     * Stop broker
     */
    void stop();

    /**
     * Add new subscriber for que
     *
     * @param consumer - subscriber for messages
     * @return - broker this link
     */
    DefaultBroker registerConsumer(Consumer<Message> consumer);

    /**
     * Add message by prior
     *
     * @param message - message for add
     */
    void sendMessage(Message message);
}
