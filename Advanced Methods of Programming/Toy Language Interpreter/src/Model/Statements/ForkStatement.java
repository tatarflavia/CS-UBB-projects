package Model.Statements;

import Errors.MyException;
import Model.ProgramState.*;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.util.Map;

public class ForkStatement implements IStatement {
    private IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    public IStatement getStatement() {
        return statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> parentStack=state.getExecutionStack();
        MyIDictionary<String, Value> parentSymTable=state.getSymbolTable();
        IHeapTable<Integer,Value> parentHeapTable=state.getHeapTable();
        IFileTable<StringValue, BufferedReader> parentFileTable=state.getFileTable();
        MyIList<Value> parentOut=state.getOut();
        ILatchTable<Integer,Integer> latchTable=state.getLatchTable();
        //creating the parts for the child prg state
        MyIStack<IStatement> childStack=new MyStack<IStatement>();
        //childStack.push(this.statement);
        MyIDictionary<String, Value> childSymTable=new MyDictionary<String,Value>();
        //create deepcopy for parentSymtable
        for(Map.Entry<String,Value> entry:parentSymTable.getContent().entrySet())
        {
            childSymTable.put(entry.getKey(),entry.getValue().deepcopy());
        }
        ProgramState childPrg=new ProgramState(childStack,childSymTable,parentOut,parentFileTable,parentHeapTable,latchTable,this.statement);
        return childPrg;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        MyIDictionary<String,Type> typEnv1 = statement.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "ForkStatement(" +
                 statement +
                ')';
    }

    @Override
    public IStatement deepcopy() {
        return new ForkStatement(this.statement.deepcopy()) ;
    }
}
