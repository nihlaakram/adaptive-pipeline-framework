package lk.ac.iit.pipeline.data;

import java.util.concurrent.LinkedBlockingQueue;

public class Terminator implements Runnable{

    private LinkedBlockingQueue<Message> inQueue;


    public void run() {

        while (true) {


            //check queue size
            if (this.inQueue.size() > 0) {

                //take item from inQueue
                Message msg = this.inQueue.poll();

                    //check if last element
                    if (msg.getTimestamp() == -1) {
                        System.out.println(System.currentTimeMillis() - msg.getTimestamp());
                        break;
                    } else {
                        System.out.println(System.currentTimeMillis() - msg.getTimestamp());

                    }


            }


        }


    }


    public Terminator(LinkedBlockingQueue<Message> inQueue) {
        this.inQueue = inQueue;
    }
}
