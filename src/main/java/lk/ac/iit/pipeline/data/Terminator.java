package lk.ac.iit.pipeline.data;

import java.util.concurrent.LinkedBlockingQueue;

public class Terminator implements Runnable{

    private LinkedBlockingQueue<Message> inQueue;
    private int messageCount;
    private long startTime;



    public void run() {

        long latencyTotal = 0;
        while (true) {


            //check queue size
            if (this.inQueue.size() > 0) {

                //take item from inQueue
                Message msg = this.inQueue.poll();

                    //check if last element
                    if (msg.getTimestamp() == -1) {
                        long endTime = System.currentTimeMillis();

                        double latency = latencyTotal/messageCount;
                        long runTime = (endTime-startTime)/1000;

                        double throughput = messageCount/runTime;
                        System.out.println(" Latency : "+latency+" milli sec " +
                                "\n TPS :"+throughput+" req per sec" +
                                "\n Runtime :"+runTime+" s" +
                                "\n Count : "+messageCount);
                        break;
                    } else {
                        latencyTotal += (System.currentTimeMillis() - msg.getTimestamp());

                    }


            }


        }


    }


    public Terminator(LinkedBlockingQueue<Message> inQueue, long startTime, int messageCount) {
        this.inQueue = inQueue;
        this.startTime = startTime;
        this.messageCount = messageCount;

    }
}
