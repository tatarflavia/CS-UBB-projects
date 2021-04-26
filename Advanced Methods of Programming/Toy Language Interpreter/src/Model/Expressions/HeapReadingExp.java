package Model.Expressions;

import Errors.MyException;
import Model.ProgramState.IHeapTable;
import Model.ProgramState.MyIDictionary;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

import java.sql.Ref;

public class HeapReadingExp implements Exp {
    private Exp exp;

    public HeapReadingExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "readHeap(" +"exp="+
                 exp +
                ')';
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> SymTable, IHeapTable<Integer,Value> heapTable) throws MyException {
        Value val=this.exp.evaluate(SymTable,heapTable);
        if(!(val instanceof RefValue))
            throw new MyException("Expression can't be evaluated to refvalue!");
        RefValue refValue=(RefValue) val;
        int address=refValue.getAddress();
        if(!(heapTable.isDefined(address)))
            throw new MyException("No such address in the heapTable.");
        Value val_from_addr=heapTable.getValue(address);
        return val_from_addr;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type=this.exp.typecheck(typeEnv);
        if(type instanceof RefType)
        {
            RefType refType=(RefType) type;
            return refType.getInner();
        }
        else throw new MyException("the heapReading exp argument is not a reftype!");
    }

    @Override
    public Exp deepcopy() {
        return new HeapReadingExp(this.exp.deepcopy());
    }
}
