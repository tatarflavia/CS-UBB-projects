package Model.Statements;

import Errors.MyException;
import Model.ProgramState.MyDictionary;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.MyIStack;
import Model.ProgramState.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BooleanValue;
import Model.Values.IntValue;
import Model.Values.Value;

import java.util.Map;

public class VariableDeclarationStatement implements  IStatement{
    private String name;
    private Type type;

    public VariableDeclarationStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "VarDeclStmt(" +
                 type.toString() +" "+name+
                ')';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> stack=state.getExecutionStack();
//        if(stack.isEmpty())
//            throw new MyException("trying to execute when the execution stack is empty");
        MyIDictionary<String, Value> symTable=state.getSymbolTable();
        if(symTable.isDefined(name)){
            throw new MyException("variable is already declared");
        }
        else
        {
            if(type.equals(new IntType()))
                symTable.put(name,type.defaultValue());
            else symTable.put(name,type.defaultValue());
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        MyIDictionary<String,Type> newEnv=new MyDictionary<>();
        //making a deepcopy for typeEnv
        for(Map.Entry<String,Type> entry:typeEnv.getContent().entrySet())
        {
            newEnv.put(entry.getKey(),entry.getValue().deepcopy());
        }
        newEnv.put(name,type);
        return newEnv;

    }

    @Override
    public IStatement deepcopy() {
        return new VariableDeclarationStatement(this.name,this.type.deepcopy());
    }
}
