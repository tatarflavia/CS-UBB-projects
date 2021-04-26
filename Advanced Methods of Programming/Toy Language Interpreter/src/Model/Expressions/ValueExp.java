package Model.Expressions;

import Errors.MyException;
import Model.ProgramState.IHeapTable;
import Model.ProgramState.MyIDictionary;
import Model.Types.BooleanType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BooleanValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class ValueExp implements Exp {
    private Value e;

    public ValueExp(Value e) {
        this.e = e;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> SymTable, IHeapTable<Integer,Value> heapTable) throws MyException {
        return this.e;

    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return e.getType();
    }

    @Override
    public String toString() {
        return "ValueExp(" +
                e +
                ')';
    }

    @Override
    public Exp deepcopy() {
        return new ValueExp(this.e.deepcopy());
    }
}
