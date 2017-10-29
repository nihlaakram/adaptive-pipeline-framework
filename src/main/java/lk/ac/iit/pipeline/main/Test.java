package lk.ac.iit.pipeline.main;

public class Test implements Runnable {


    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }


    public static void main(String[] args) {
        Test test = new Test();
        Thread t = new Thread(test);

        Test test1 = new Test();
        Thread t1 = new Thread(test1);
        t.start();
        t1.start();
    }
}
