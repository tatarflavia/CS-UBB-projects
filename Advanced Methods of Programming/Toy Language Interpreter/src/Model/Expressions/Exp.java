package Model.Expressions;

import Errors.MyException;
import Model.ICopy;
import Model.ProgramState.IHeapTable;
import Model.ProgramState.MyIDictionary;
import Model.Types.Type;
import Model.Values.Value;

public interface Exp extends ICopy<Exp> {
    public Value evaluate(MyIDictionary<String,Value> SymTable, IHeapTable<Integer,Value> heapTable) throws MyException;
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}
