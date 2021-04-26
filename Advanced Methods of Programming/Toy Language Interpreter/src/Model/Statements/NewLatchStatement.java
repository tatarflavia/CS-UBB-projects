package Model.Statements;

import Errors.MyException;
import Model.Expressions.Exp;
import Model.Expressions.VariableExp;
import Model.ProgramState.*;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;


public class NewLatchStatement implements IStatement {
    private Exp var;
    private Exp exp;
    private static int newFreeLocation;

    public NewLatchStatement(Exp var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "newLatch(" +
                 var + ',' +
                 exp +
                ')';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IHeapTable<Integer, Value> heapTable=state.getHeapTable();
        ILatchTable<Integer,Integer> latchTable=state.getLatchTable();
        MyIStack<IStatement> stack=state.getExecutionStack();
        MyIDictionary<String,Value> symTable=state.getSymbolTable();

        Value number=this.exp.evaluate(symTable,heapTable);
        if(!number.getType().equals(new IntType()))
        {
            throw new MyException("in new latch stmt:the number evaluated from exp is not an int!");
        }
        synchronized (latchTable)
        {
            newFreeLocation++;
            IntValue nbInt=(IntValue) number;
            latchTable.put(newFreeLocation,nbInt.getValue());
            VariableExp expFromVar=(VariableExp)var;
            if(symTable.isDefined(expFromVar.getId()))
            {
                if(symTable.getValue(expFromVar.getId()).getType().equals(new IntType()))
                {
                    symTable.update(expFromVar.getId(),new IntValue(newFreeLocation));
                    return null;
                }
                else throw new MyException("var is not mapped to an int or is not mapped at all:new latch stmt");
            }
            return null;
        }
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar=var.typecheck(typeEnv);
        Type typeExp=exp.typecheck(typeEnv);
        if(typeVar.equals(typeExp) && typeExp.equals(new IntType()))
            return typeEnv;
        else throw new MyException("New Latch stmt:right hand side and left hand side have different types.");
    }

    @Override
    public IStatement deepcopy() {
        return new NewLatchStatement(var.deepcopy(),exp.deepcopy());
    }
}
