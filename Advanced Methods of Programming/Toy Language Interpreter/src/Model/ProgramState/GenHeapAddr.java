package Model.ProgramState;

public class GenHeapAddr {
    private static int current=1;
    public static int getFreeAddress(){return current++;}
}
