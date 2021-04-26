package Model.Statements.File;

import Errors.MyException;
import Model.Expressions.Exp;
import Model.ProgramState.*;
import Model.Statements.IStatement;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStatement {
    private Exp exp;

    public CloseRFile(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "CloseRFile(" +
                "exp=" + exp +
                ')';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> stack=state.getExecutionStack();
        MyIDictionary<String, Value> symbolTable=state.getSymbolTable();
        IFileTable<StringValue, BufferedReader> fileTable=state.getFileTable();
        IHeapTable<Integer,Value> heapTable=state.getHeapTable();
        Value val=this.exp.evaluate(symbolTable,heapTable);
        if(!(val.getType().equals(new StringType())))
        {
            throw new MyException("Expression can't be evaluated to string!");
        }
        StringValue strVal=(StringValue) val;
        if(!(fileTable.isDefined(strVal)))
        {
            throw new MyException("String value not found in keys of the fileTable");
        }
        try{BufferedReader reader=fileTable.getValue(strVal);
            reader.close();
            fileTable.remove(strVal);
        }
        catch (IOException e){throw new MyException("Can't close the file");}
        return null;

    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp=exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public IStatement deepcopy() {
        return new CloseRFile(this.exp.deepcopy());
    }
}
