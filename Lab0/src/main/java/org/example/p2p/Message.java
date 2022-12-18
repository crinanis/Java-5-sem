package org.example.p2p;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private final String message;
    private final Date date;

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", date=" + date +
                '}';
    }

    public Message(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }
}
