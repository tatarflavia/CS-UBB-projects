package Model.Expressions;

import Errors.MyException;
import Model.ProgramState.IHeapTable;
import Model.ProgramState.MyIDictionary;
import Model.Types.Type;
import Model.Values.Value;

public class VariableExp implements Exp {
    private String id;

    public String getId() {
        return id;
    }

    public VariableExp(String id) {
        this.id = id;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> SymTable, IHeapTable<Integer,Value> heapTable) throws MyException {
        return SymTable.lookUp(this.id);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookUp(id);
    }

    @Override
    public String toString() {
        return "VarExp(" +
                "StrId='" + id  +
                ')';
    }

    @Override
    public Exp deepcopy() {
        return new VariableExp(this.id);
    }
}
