package ru.general.electrics.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ApplicationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testSendOneMessageFromOneProducerToOneConsumerSuccess() {
        DefaultBroker defaultBroker = new DefaultBroker();
        defaultBroker.registerConsumer(new SystemBConsumer());

        new SystemAProducer(defaultBroker).sendMessage();

        defaultBroker.sendMessagesToConsumers();

        String[] split = outContent.toString().split("\n");
        String producerSendMessage = split[0].replace("SystemAProducer: ","");
        String consumerReceiveMessage = split[1].replace("SystemBConsumer: ","");

        assertEquals(producerSendMessage , consumerReceiveMessage);
    }

    @Test
    public void testSendOneMessageFromOneProducerToTwoConsumersSuccess() {
        Broker defaultBroker = new DefaultBroker();
        defaultBroker.registerConsumer(new SystemBConsumer());
        defaultBroker.registerConsumer(new SystemBConsumer());

        new SystemAProducer(defaultBroker).sendMessage();

        ((DefaultBroker) defaultBroker).sendMessagesToConsumers();

        String[] split = outContent.toString().split("\n");
        String producerSendMessage = split[0].replace("SystemAProducer: ","");
        String consumerOneReceiveMessage = split[1].replace("SystemBConsumer: ","");
        String consumerTwoReceiveMessage = split[2].replace("SystemBConsumer: ","");

        assertEquals(consumerOneReceiveMessage , consumerTwoReceiveMessage);
        assertEquals(consumerOneReceiveMessage , producerSendMessage);
    }

    @Test
    public void testSuccessBrokerStop() {
        Broker defaultBroker = new DefaultBroker();

        defaultBroker.start();

        defaultBroker.stop();
    }

    @Test
    public void testSuccessProducerStop() {
        SystemAProducer systemAProducer = new SystemAProducer(new DefaultBroker());

        systemAProducer.start();

        systemAProducer.stop();
    }

}