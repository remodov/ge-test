package ru.general.electrics.test;

import ru.general.electrics.test.dto.Message;

import java.util.function.Consumer;

/**
 * Receive message class
 */
public class SystemBConsumer implements Consumer<Message> {
    @Override
    public void accept(Message message) {
        sleepOneSecond();

        System.out.println("SystemBConsumer: " + message);
    }

    private void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}