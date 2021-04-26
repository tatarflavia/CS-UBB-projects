package Model.Statements.File;

import Errors.MyException;
import Model.Expressions.Exp;
import Model.ProgramState.*;
import Model.Statements.IStatement;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile implements IStatement {
    private Exp exp;
    private String var_name;

    public ReadFile(Exp exp, String var_name) {
        this.exp = exp;
        this.var_name = var_name;
    }

    @Override
    public String toString() {
        return "ReadFile(" +
                "exp=" + exp +
                ", var_name=" + var_name +
                ')';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        //MyIStack<IStatement> stack=state.getExecutionStack();
        //IFileTable<StringValue, BufferedReader> fileTable=state.getFileTable();
        MyIDictionary<String,Value> symbolTable=state.getSymbolTable();
        IHeapTable<Integer,Value> heapTable=state.getHeapTable();
        if(!symbolTable.isDefined(var_name))
        {
            throw new MyException("The variable doesn't exist!");
        }
        Value value=this.exp.evaluate(symbolTable,heapTable);
        if(!value.getType().equals(new StringType()))
        {
            throw new MyException("The expression can't be evaluated to string!");
        }
        StringValue strVal=(StringValue) value;
        try{
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(strVal.getValue()));
            String strCurrentLine = bufferedReader.readLine();
            if(strCurrentLine==null)
            {
                IntValue value1=new IntValue(0);
                symbolTable.update(this.var_name,value1);
            }
            else {IntValue value1=new IntValue(Integer.parseInt(strCurrentLine));
                symbolTable.update(this.var_name,value1);  }

            }
        catch (NumberFormatException e){throw new MyException("Can't convert to integer!");}
        catch (FileNotFoundException e){throw new MyException("File not found!");}
        catch (IOException e){throw new MyException("Error in reading (IO exception)");}
        return null;

    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar=typeEnv.lookUp(var_name);
        Type typeExp=exp.typecheck(typeEnv);
        if(typeVar.equals(new IntType()) && typeExp.equals(new StringType()))
            return typeEnv;
        else throw new MyException("Reading File:the type of variable name or exp are wrong!");

    }

    @Override
    public IStatement deepcopy() {
        return new ReadFile(this.exp.deepcopy(),this.var_name);
    }
}
