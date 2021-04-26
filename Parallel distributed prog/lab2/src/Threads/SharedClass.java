package Threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedClass {
    private ArrayList<Thread> threadList;
    private Consumer consumer;
    private Producer producer;
    private ArrayList<Integer> vector1;
    private ArrayList<Integer> vector2;
    int vectorSize;
    private ArrayList<Integer> sharedBuffer;
    private ReentrantLock sharedLockMechanism;
    private Condition conditionalVariable;
    int sizeOfBuffer=3;

    public SharedClass(ArrayList<Integer> vector1, ArrayList<Integer> vector2,int vectorSize) {
        this.vector1 = vector1;
        this.vector2 = vector2;
        this.vectorSize=vectorSize;
    }

    //making a list of them
    private void initialiseThreads(){
        threadList.add(new Thread(producer));
        threadList.add(new Thread(consumer));
    }
    private  void startThreads(){
        System.out.println("Starting "+threadList.size()+" threads...\n");
        for(Thread thread:threadList){
            thread.start();
        }
        System.out.println("Running threads...\n");
        for (Thread thread : threadList){
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public void start() {
        sharedBuffer=new ArrayList<>();
        sharedLockMechanism=new ReentrantLock();
        conditionalVariable=sharedLockMechanism.newCondition();
        sizeOfBuffer=3;
        threadList=new ArrayList<>();
        //init for consumer and producer
        producer=new Producer(sharedLockMechanism,conditionalVariable,sharedBuffer,sizeOfBuffer,vector1,vector2);
        consumer=new Consumer(sharedLockMechanism,conditionalVariable,sharedBuffer,vectorSize);
        initialiseThreads();
        //starting the threads
        startThreads();
        //print the buffer at the end plus the result
        System.out.println("Buffer at the end(should be empty): "+sharedBuffer);
        System.out.println("Final result is(dot product of the 2 vectors):"+consumer.getFinalResult());


    }
}
