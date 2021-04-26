import Threads.SharedClass;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int getRandomInt(int min, int max) {
        return (int)(((Math.random() * (max - min)) + min));
    }

    private static ArrayList<Integer> getRandomVector(){
        //make a random vector of size 10
        ArrayList<Integer> vector=new ArrayList<>();
        for(int i=0;i<10;i++){
            vector.add(getRandomInt(0,10));
        }
        return vector;
    }

    public static void main(String[] args) {
        ArrayList<Integer> vector1=getRandomVector();
        ArrayList<Integer> vector2=getRandomVector();
        System.out.println("Vector 1 is: "+vector1);
        System.out.println("Vector 2 is: "+vector2);
        int result=0;
        for(int i=0;i<10;i++){
            result+=vector1.get(i)*vector2.get(i);
        }
        System.out.println("Result should be: "+ result);
        System.out.println("Problem starts now:");
        SharedClass sharedClass=new SharedClass(vector1,vector2,10);
        sharedClass.start();

    }
}
