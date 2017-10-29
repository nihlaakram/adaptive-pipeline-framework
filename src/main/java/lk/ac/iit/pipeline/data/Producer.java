package lk.ac.iit.pipeline.data;

import java.util.concurrent.LinkedBlockingQueue;

public class Producer implements Runnable {
    private LinkedBlockingQueue<Message> inQueue;
    private int messageCount;


    public void run() {
        try {
            for (int i = 0; i < this.messageCount; i++) {
                this.inQueue.put(new Message(System.currentTimeMillis(), ""));
            }
            //terminating message
            this.inQueue.put(new Message(-1, null));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public Producer(LinkedBlockingQueue<Message> inQueue, int messageCount) {
        this.inQueue = inQueue;
        this.messageCount = messageCount;
    }
}
