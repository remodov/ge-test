package ru.general.electrics.test;

public class Application {

    /**
     * Start three SystemB for read que and one SystemA for write
     */
    public static void main(String[] args) {
        Broker defaultBroker = new DefaultBroker();
        defaultBroker.registerConsumer(new SystemBConsumer())
              .registerConsumer(new SystemBConsumer())
              .registerConsumer(new SystemBConsumer())
              .start();

        new SystemAProducer(defaultBroker).start();
    }
}
