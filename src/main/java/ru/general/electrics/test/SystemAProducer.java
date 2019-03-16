package ru.general.electrics.test;

import ru.general.electrics.test.dto.Message;
import ru.general.electrics.test.utils.RandomUtil;

/**
 * Write data
 */
public class SystemAProducer {
    private final Broker defaultBroker;

    public SystemAProducer(Broker defaultBroker) {
        this.defaultBroker = defaultBroker;
    }

    /**
     * Write message with random time in que
     */
    public void start() {
        new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        sendMessage();
                    }
                }).start();
    }

    public void stop() {
        Thread.currentThread().interrupt();
    }

    protected void sendMessage() {
        RandomUtil.randomSleepCurrentThread();

        Message message = RandomUtil.generateMessage();

        defaultBroker.sendMessage(message);

        System.out.println("SystemAProducer: " + message);
    }
}