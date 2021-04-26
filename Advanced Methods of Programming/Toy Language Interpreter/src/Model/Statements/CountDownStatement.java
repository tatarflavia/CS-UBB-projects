package Model.Statements;

import Errors.MyException;
import Model.Expressions.Exp;
import Model.Expressions.VariableExp;
import Model.ProgramState.*;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class CountDownStatement implements IStatement {
    private Exp var;

    public CountDownStatement(Exp var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return "countDown(" +
                var +
                ')';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        ILatchTable<Integer,Integer> latchTable=state.getLatchTable();
        MyIStack<IStatement> stack=state.getExecutionStack();
        MyIList<Value> out=state.getOut();
        MyIDictionary<String, Value> symTable=state.getSymbolTable();
        VariableExp expFromVar=(VariableExp)var;
        if(symTable.isDefined(expFromVar.getId()) )
        {
            if(symTable.getValue(expFromVar.getId()).getType().equals(new IntType()))
            {
                synchronized (latchTable)
                {
                Value foundIndex=symTable.lookUp(expFromVar.getId());
                IntValue foundInd=(IntValue)foundIndex;
                Integer foundIndInt=foundInd.getValue();
                if(latchTable.isDefined(foundIndInt))
                {
                    Integer valFromFoundInd=latchTable.getValue(foundIndInt);
                    if(valFromFoundInd>0)
                    {
                        Integer newInd=valFromFoundInd-1;
                        latchTable.update(foundIndInt,newInd);
                        out.add(new IntValue(state.getId()));
                        return null;
                    }
                    else out.add(new IntValue(state.getId()));
                }
                else  throw new MyException("Found index from symTable is not an index in the latchtable!");
                }
            }

            else throw new MyException("var is not mapped to an int or is not mapped at all:new latch stmt");}

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar=var.typecheck(typeEnv);
        if(typeVar.equals(new IntType()))
            return typeEnv;
        else throw new MyException("New Latch stmt:right hand side and left hand side have different types.");
    }

    @Override
    public IStatement deepcopy() {
        return new CountDownStatement(var.deepcopy()) ;}
}
