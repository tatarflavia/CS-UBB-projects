package Threads;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Consumer implements Runnable {
    private Lock lockMechanism;
    private Condition conditionalVariable;
    private ArrayList<Integer> sharedBuffer;
    private int vectorSize;
    private int finalResult=0;

    public Consumer(Lock lockMechanism, Condition conditionalVariable, ArrayList<Integer> sharedBuffer,int vectorSize) {
        this.lockMechanism = lockMechanism;
        this.conditionalVariable = conditionalVariable;
        this.sharedBuffer = sharedBuffer;
        this.vectorSize=vectorSize;
    }

    @Override
    public void run() {
        for(int i=0;i<vectorSize;i++){
            lockMechanism.lock();
            while(sharedBuffer.size()==0){
                try{
                    conditionalVariable.await();
                }
                catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
            int removedElement=sharedBuffer.remove(sharedBuffer.size()-1);
            finalResult+=removedElement;
            System.out.println("Consumed: "+removedElement);
            conditionalVariable.signalAll();
            lockMechanism.unlock();
        }
    }

    public int getFinalResult() {
        return finalResult;
    }
}
