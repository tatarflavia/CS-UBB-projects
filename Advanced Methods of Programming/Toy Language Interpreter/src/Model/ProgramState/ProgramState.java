package Model.ProgramState;

import Errors.MyException;
import Model.Statements.IStatement;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;

public class ProgramState {
    private MyIStack<IStatement> executionStack;
    private MyIDictionary<String, Value> symbolTable;
    private MyIList<Value> out;
    private IFileTable<StringValue, BufferedReader> fileTable;
    private IHeapTable<Integer,Value> heapTable;
    private ILatchTable<Integer,Integer> latchTable;
    IStatement originalProgram;
    private int id;
    private static int static_id;


    public ProgramState(MyIStack<IStatement> stack,MyIDictionary<String,Value> symTable, MyIList<Value> output,IFileTable<StringValue, BufferedReader> fileTab,IHeapTable<Integer,Value> heapTabl,ILatchTable<Integer,Integer> latchTabl,IStatement program)
    {
        executionStack=stack;
        symbolTable=symTable;
        out=output;
        fileTable=fileTab;
        heapTable=heapTabl;
        latchTable=latchTabl;
        this.originalProgram=program.deepcopy();
        stack.push(program);
        id = getNextId();
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public int getId() {
        return id;
    }

    public IHeapTable<Integer, Value> getHeapTable() {
        return heapTable;
    }

    public void setHeapTable(IHeapTable<Integer, Value> heapTable) {
        this.heapTable = heapTable;
    }

    public void setExecutionStack(MyIStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public void setSymbolTable(MyIDictionary<String, Value> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public ILatchTable<Integer, Integer> getLatchTable() {
        return latchTable;
    }

    public void setLatchTable(ILatchTable<Integer, Integer> latchTable) {
        this.latchTable = latchTable;
    }

    @Override
    public String toString() {
        return "ProgramState{" +
                "\nID="+id+
                "\nExecutionStack=" + executionStack +
                ", \nSymbolTable=" + symbolTable +
                ", \nOut=" + out +
                ", \nFileTable=" + fileTable +
                ", \nHeapTable=" + heapTable +
                ", \nOriginalProgram=" + originalProgram +
                ", \nLatchTable="+latchTable+
                '}';
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public MyIStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public MyIDictionary<String, Value> getSymbolTable() {
        return symbolTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public IFileTable<StringValue,BufferedReader> getFileTable(){return this.fileTable;}

    public void setFileTable(IFileTable<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public Boolean isNotCompleted(){
        return !(this.executionStack.isEmpty());
    }

    private synchronized static int getNextId()
    {
        ++static_id;
        return static_id;
    }
    public ProgramState oneStep() throws MyException {
        if(executionStack.isEmpty())
            throw new MyException("Stack is empty...");
        IStatement curentStatement=executionStack.pop();
        return curentStatement.execute(this);
    }
}
