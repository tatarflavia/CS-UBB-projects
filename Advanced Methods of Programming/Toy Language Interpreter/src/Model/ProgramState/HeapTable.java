package Model.ProgramState;

import Errors.MyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HeapTable<Adress,content> implements IHeapTable<Adress,content>{
    private HashMap<Adress,content> heap;
    private int current=1;

    public HeapTable() {
        this.heap = new HashMap<>();

    }

    @Override
    public String toString() {
        return heap.toString() ;
    }

    @Override
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    @Override
    public void put(Adress adress, content value) {
        this.heap.put(adress,value);

    }

    @Override
    public int size() {
        return this.heap.size();
    }

    @Override
    public boolean isDefined(Adress adress) throws MyException {
        return this.heap.containsKey(adress);
    }

    @Override
    public content getValue(Adress adress) throws MyException {
        return this.heap.get(adress);
    }

    @Override
    public void update(Adress adress, content value) throws MyException {
        this.heap.replace(adress,value);
    }

    @Override
    public void setContent(Map<Adress, content> map) {
        this.heap=(HashMap<Adress, content>) map;
    }

    @Override
    public HashMap<Adress, content> getContent() {
        HashMap<Adress,content> map=new HashMap<>();
        map=this.heap;
        return map;
    }

    @Override
    public int getFreeAddress() {
        return current++;
    }

    @Override
    public Collection<content> values() {
        return this.heap.values();
    }
}
