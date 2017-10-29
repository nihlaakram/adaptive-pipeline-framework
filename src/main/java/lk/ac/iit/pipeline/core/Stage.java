package lk.ac.iit.pipeline.core;

import lk.ac.iit.pipeline.data.Message;

import java.util.concurrent.LinkedBlockingQueue;

public class Stage implements Runnable {


    private LinkedBlockingQueue<Message> inQueue;
    private LinkedBlockingQueue<Message> outQueue;
    private String charList;



    public void run() {
        while (true) {


            //check queue size
            if (this.inQueue.size() > 0) {

                //take item from inQueue
                Message msg = this.inQueue.poll();

                try {
                    //check if last element
                    if (msg.getTimestamp() == -1) {
                        this.outQueue.put(msg);
                        break;
                    } else {
                        //add content
                        msg.addToMessage(this.charList);

                        //push it to outQueue
                        this.outQueue.put(msg);


                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        }


    }


    public Stage(LinkedBlockingQueue<Message> inQueue, LinkedBlockingQueue<Message> outQueue, String charList) {
        this.inQueue = inQueue;
        this.outQueue = outQueue;
        this.charList = charList;
    }

}
