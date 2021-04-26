package Model.ProgramState;

import Errors.MyException;
import Model.Values.StringValue;

public interface IFileTable<filename,filedesc> {
    public boolean isEmpty();
    public filedesc getValue(filename key);
    public void put(filename key, filedesc value);
    boolean isDefined(filename key);
    public void remove(filename key);
}
