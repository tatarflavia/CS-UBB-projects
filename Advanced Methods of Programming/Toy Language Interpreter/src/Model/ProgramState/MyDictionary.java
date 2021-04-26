package Model.ProgramState;


import Errors.MyException;
import Model.Values.Value;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyDictionary<Key,T> implements MyIDictionary<Key,T> {
    private HashMap<Key,T> dictionary;

    public MyDictionary() {
        this.dictionary = new HashMap<Key,T>();
    }

    @Override
    public boolean isEmpty() {
        return this.dictionary.isEmpty();
    }

    @Override
    public void put(Key key, T value) {
        this.dictionary.put(key,value);
    }


    @Override
    public int size() {
        return this.dictionary.size();
    }

    @Override
    public boolean isDefined(Key key) throws MyException {
//        if(this.dictionary.isEmpty())
//            throw new MyException("reading from an empty dict isDef");
        return this.dictionary.containsKey(key);
    }

    @Override
    public T getValue(Key key) throws MyException {
        if(!(this.dictionary.containsKey(key)))
            throw new MyException("reading from an empty dict getVAl");
        return this.dictionary.get(key);
    }

    @Override
    public void update(Key key, T value) throws MyException {
        if(!(this.dictionary.containsKey(key)))
            throw new MyException("no such elem in collection");
        this.dictionary.replace(key,value);
    }

    @Override
    public T lookUp(Key key) throws MyException {
        if(this.dictionary.containsKey(key))
            return this.dictionary.get(key);
        else throw new MyException("Variable is not defined");
    }

    @Override
    public HashMap<Key, T> getContent() {
        HashMap<Key,T> map=new HashMap<>();
        map=this.dictionary;
        return map;
    }

    @Override
    public void setContent(Map<Key, T> map) {
        this.dictionary=(HashMap<Key, T>) map;
    }

    @Override
    public Collection<T> values() {
        return this.dictionary.values();
    }




    @Override
    public String toString() {
        return dictionary.toString() ;
    }


}
