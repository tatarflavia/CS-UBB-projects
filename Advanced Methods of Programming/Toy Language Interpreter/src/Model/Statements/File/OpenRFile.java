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
import java.io.FileReader;
import java.io.IOException;

public class OpenRFile implements IStatement {
    private Exp exp;

    public OpenRFile(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "OpenRFile(" +
                "exp=" + exp +
                ')';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> stack=state.getExecutionStack();
        MyIDictionary<String, Value> symbolTable=state.getSymbolTable();
        IFileTable<StringValue, BufferedReader> fileTable=state.getFileTable();
        IHeapTable<Integer,Value> heapTable=state.getHeapTable();
        Value value=this.exp.evaluate(symbolTable,heapTable);
        if(!(value.getType().equals(new StringType())))
        {
            throw new MyException("The expression can't be evaluated to a string!");
        }
        StringValue strVal=(StringValue) value;
        if(fileTable.isDefined(strVal))
            throw new MyException("The expression is already in the table!");
        try{
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(strVal.getValue()));
            fileTable.put(strVal,bufferedReader);
        }
        catch (IOException i){throw new MyException("Error is IO operation!");}
        //catch (FileNotFoundException e){throw new MyException("The file doesn't exist!");}

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp=exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public IStatement deepcopy() {
        return new OpenRFile(this.exp.deepcopy());
    }
}
