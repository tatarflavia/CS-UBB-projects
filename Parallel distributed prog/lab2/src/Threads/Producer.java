package Threads;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Producer implements Runnable {
    private Lock lockMechanism;
    private Condition conditionalVariable;
    private ArrayList<Integer> sharedBuffer;
    private int sharedBufferSize;
    private ArrayList<Integer> vector1;
    private ArrayList<Integer> vector2;

    public Producer(Lock lockMechanism, Condition conditionalVariable, ArrayList<Integer> sharedBuffer, int sharedBufferSize,ArrayList<Integer> vector1,ArrayList<Integer> vector2) {
        this.lockMechanism = lockMechanism;
        this.conditionalVariable = conditionalVariable;
        this.sharedBuffer = sharedBuffer;
        this.sharedBufferSize = sharedBufferSize;
        this.vector1=vector1;
        this.vector2=vector2;
    }

    @Override
    public void run() {
        for(int i=0;i<vector1.size();i++){
            lockMechanism.lock();
            while(sharedBuffer.size()==sharedBufferSize){
                try{
                    conditionalVariable.await();
                }
                catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
            int result=vector1.get(i)*vector2.get(i);
            sharedBuffer.add(result);
            System.out.println("Produced: " + vector1.get(i) +" * "+vector2.get(i)+" ="+result);
            conditionalVariable.signalAll();
            lockMechanism.unlock();
        }
    }
}
