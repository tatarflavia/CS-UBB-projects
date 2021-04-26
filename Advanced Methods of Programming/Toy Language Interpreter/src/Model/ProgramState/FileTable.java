package Model.ProgramState;

import Model.Values.StringValue;

import java.util.HashMap;

public class FileTable<filename,filedesc> implements IFileTable<filename,filedesc> {
    private HashMap<filename,filedesc> filetable;

    public FileTable() {
        filetable = new HashMap<filename,filedesc>();
    }

    @Override
    public boolean isEmpty() {
        return this.filetable.isEmpty();
    }

    @Override
    public filedesc getValue(filename key) {
        return this.filetable.get(key);
    }

    @Override
    public void put(filename key, filedesc value) {
        this.filetable.put(key,value);
    }

    @Override
    public boolean isDefined(filename key)  {
//        if(this.dictionary.isEmpty())
//            throw new MyException("reading from an empty dict isDef");
        return this.filetable.containsKey(key);
    }

    @Override
    public void remove(filename key) {
        this.filetable.remove(key);
    }

    @Override
    public String toString() {
        return filetable.toString();
    }
}
