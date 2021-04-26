package Model.Statements;

import Errors.MyException;
import Model.Expressions.Exp;
import Model.ProgramState.IHeapTable;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.MyIStack;
import Model.ProgramState.ProgramState;
import Model.Types.Type;
import Model.Values.Value;

public class AssignStatement implements IStatement {
    private String id;
    private Exp exp;

    public AssignStatement(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return id+"="+ exp.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> stack=state.getExecutionStack();
//        if(stack.isEmpty())
//            throw new MyException("trying to execute when the execution stack is empty");
        MyIDictionary<String, Value> symTable=state.getSymbolTable();
        IHeapTable<Integer,Value> heapTable=state.getHeapTable();
        Value val=exp.evaluate(symTable,heapTable);
        if(symTable.isDefined(id))
        {
            Type typeId=(symTable.getValue(id).getType());
            if(val.getType().equals(typeId))
                symTable.update(id,val);
            else throw new MyException("declared type of variable "+id+" and type of the assigned expression don't match");
        }
        else throw new MyException("the used variable "+ id+" was not declared before");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar=typeEnv.lookUp(id);
        Type typeExp=exp.typecheck(typeEnv);
        if(typeVar.equals(typeExp))
            return typeEnv;
        else throw new MyException("Assignment:right hand side and left hand side have different types.");
    }

    @Override
    public IStatement deepcopy() {
        return new AssignStatement(this.id,this.exp.deepcopy());
    }
}
