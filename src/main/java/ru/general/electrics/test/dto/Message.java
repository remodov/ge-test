package ru.general.electrics.test.dto;

import java.util.Objects;

/**
 * Message class for transfer data and prior between System A and System B
 */
public class Message implements Comparable<Message> {

    private final String message;
    private final Integer priority;

    public Message(String message, Integer priority) {
        this.message = message;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", priority=" + priority +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(message, message1.message) &&
               Objects.equals(priority, message1.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, priority);
    }

    @Override
    public int compareTo(Message o) {
        return o.priority.compareTo(this.priority);
    }
}
