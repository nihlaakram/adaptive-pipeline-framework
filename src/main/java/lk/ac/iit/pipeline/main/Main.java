package lk.ac.iit.pipeline.main;

import lk.ac.iit.pipeline.core.Stage;
import lk.ac.iit.pipeline.data.Message;
import lk.ac.iit.pipeline.data.Producer;
import lk.ac.iit.pipeline.data.Terminator;

import org.apache.commons.text.RandomStringGenerator;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        //the message size
        int messageSize = Integer.parseInt(args[0]);

        //the no. of messages to use
        int messageCount = Integer.parseInt(args[1]);

        //no of stages
        int stageCount = Integer.parseInt(args[2]);

        //contribution from each stage to the string
        int charCount = messageSize/stageCount;
        RandomStringGenerator random =
                new RandomStringGenerator.Builder()
                        .withinRange('0', 'z')
                        .build();
        String charList = random.generate(charCount);


        //required queues
        LinkedBlockingQueue<Message> [] queues = new LinkedBlockingQueue[stageCount+1];
        for(int i=0; i<6; i++){
            queues[i] = new LinkedBlockingQueue<Message>();
        }

        //create the stages
        Stage [] stages = new Stage[stageCount];
        for(int i=0; i<5; i++){
            stages[i] = new Stage(queues[i], queues[i+1], charList);
        }

        //start threads
        Thread[] threads = new Thread[stageCount];
        for(int i=0; i<5; i++){
            threads[i] = new Thread(stages[i]);
            threads[i].start();
        }

        //fill data
        Producer prod = new Producer(queues[0], messageCount);
        Thread t2 = new Thread(prod);
        t2.start();

        Terminator term = new Terminator(queues[stageCount], System.currentTimeMillis(), messageCount);
        Thread t1 = new Thread(term);
        t1.start();


    }
}
