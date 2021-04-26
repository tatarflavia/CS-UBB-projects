package Model.ProgramState;

import java.util.ArrayList;

public class MyList<T> implements MyIList<T> {
    private ArrayList<T> list;

    public MyList() {
        this.list = new ArrayList<T>();
    }

    @Override
    public boolean isEmpty() {
        if(this.list.isEmpty())
            return true;
        else return false;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public void add(T val) {
        this.list.add(val);
    }

    @Override
    public String toString() {
        return list.toString() ;
    }

}
