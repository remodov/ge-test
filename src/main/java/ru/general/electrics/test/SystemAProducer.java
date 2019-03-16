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
                    while (true){
                        sendMessage();
                    }
                }).start();
    }

    protected void sendMessage() {
        RandomUtil.randomSleepCurrentThread();

        Message message = RandomUtil.generateMessage();

        defaultBroker.sendMessage(message);

        System.out.println("SystemAProducer: " + message);
    }
}