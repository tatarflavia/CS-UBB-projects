package Model.Statements;

import Errors.MyException;
import Model.Expressions.Exp;
import Model.Expressions.VariableExp;
import Model.ProgramState.*;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class AwaitStatement implements IStatement {
    private Exp var;

    public AwaitStatement(Exp var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return "await(" +
                var +
                ')';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {

        ILatchTable<Integer,Integer> latchTable=state.getLatchTable();
        MyIStack<IStatement> stack=state.getExecutionStack();
        MyIDictionary<String,Value> symTable=state.getSymbolTable();
        VariableExp expFromVar=(VariableExp)var;
        if(symTable.isDefined(expFromVar.getId()) )
        {
            if(symTable.getValue(expFromVar.getId()).getType().equals(new IntType()))
            {
                Value foundIndex=symTable.lookUp(expFromVar.getId());
                IntValue foundInd=(IntValue)foundIndex;
                Integer foundIndInt=foundInd.getValue();
                if(latchTable.isDefined(foundIndInt))
                {
                    Integer valFromFoundInd=latchTable.getValue(foundIndInt);
                    if(valFromFoundInd>0)
                    {
                        stack.push(new AwaitStatement(var));
                        return null;
                    }
                }
                else  throw new MyException("Found index from symTable is not an index in the latchtable!");
            }
            else throw new MyException("var is not mapped to an int or is not mapped at all:new latch stmt");
        }
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
        return new AwaitStatement(var.deepcopy());
    }
}
