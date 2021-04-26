package Model.ProgramState;

import Errors.MyException;

import java.util.Collection;
import java.util.HashMap;

public class LatchTable<Key,T> implements ILatchTable<Key,T> {
    private HashMap<Key,T> latchTable;

    public LatchTable() {
        this.latchTable = new HashMap<>();
    }

    @Override
    public String toString() {
        return "LatchTable(" +
                 latchTable +
                ')';
    }

    @Override
    public boolean isEmpty() {
        return latchTable.isEmpty();
    }

    @Override
    public void put(Key key, T value) {
        this.latchTable.put(key,value);
    }

    @Override
    public int size() {
        return this.latchTable.size();
    }

    @Override
    public boolean isDefined(Key key) throws MyException {
        return this.latchTable.containsKey(key);
    }

    @Override
    public T getValue(Key key) throws MyException {
        return this.latchTable.get(key);
    }

    @Override
    public void update(Key key, T value) throws MyException {
        this.latchTable.replace(key,value);
    }

    @Override
    public HashMap<Key, T> getContent() {
        HashMap<Key,T> map=new HashMap<>();
        map=this.latchTable;
        return map;
    }

    @Override
    public Collection<T> values() {
        return this.latchTable.values();
    }
}
