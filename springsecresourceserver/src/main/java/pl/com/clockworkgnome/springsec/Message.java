package pl.com.clockworkgnome.springsec;

import java.util.UUID;

public class Message {

    private UUID id;
    private String content;

    public Message(String message) {
        this.id = UUID.randomUUID();
        this.content = message;
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
