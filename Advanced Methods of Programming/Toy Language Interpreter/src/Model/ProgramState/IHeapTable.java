package Model.ProgramState;

import Errors.MyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface IHeapTable<Key,T> {
    public boolean isEmpty();
    public void put(Key key, T value);
    public int size();
    public boolean isDefined(Key key) throws MyException;
    public T getValue(Key key) throws MyException;
    public void update(Key key, T value) throws MyException;
    //public T lookUp(Key key) throws MyException;
    public void setContent(Map<Key,T> map);
    HashMap<Key,T> getContent();
    public int getFreeAddress();
    public Collection<T> values();
}
