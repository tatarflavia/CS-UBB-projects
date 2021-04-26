package Model.ProgramState;

import Errors.MyException;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<T>();
    }



    @Override
    public boolean isEmpty() {
        if(this.stack.isEmpty())
            return true;
        else return false;
    }

    @Override
    public T pop() throws MyException {
        if(this.stack.isEmpty())
            throw new MyException("reading from an empty stack");
        return this.stack.pop();
    }


    @Override
    public void push(T v) {
        this.stack.push(v);
    }



    @Override
    public String toString() {
        return stack.toString() ;
    }
}
