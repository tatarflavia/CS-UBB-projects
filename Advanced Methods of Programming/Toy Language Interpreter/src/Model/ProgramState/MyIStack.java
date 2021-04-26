package Model.ProgramState;

import Errors.MyException;

public interface MyIStack <T>{
    public boolean isEmpty();
    public T pop() throws MyException;
    public void push(T v);
}
