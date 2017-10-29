package lk.ac.iit.pipeline.data;

public class Message {


    private long timestamp;
    private String message;

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void addToMessage(String message) {
        this.message = this.message + message;
    }

    public Message(long timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }


}
