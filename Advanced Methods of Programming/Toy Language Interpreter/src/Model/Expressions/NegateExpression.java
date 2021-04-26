package Model.Expressions;

import Errors.MyException;
import Model.ProgramState.IHeapTable;
import Model.ProgramState.MyIDictionary;
import Model.Types.BooleanType;
import Model.Types.Type;
import Model.Values.BooleanValue;
import Model.Values.Value;

public class NegateExpression implements Exp {
    private Exp exp;

    public NegateExpression(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "not(" +
                exp +
                ')';
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> SymTable, IHeapTable<Integer, Value> heapTable) throws MyException {
        Value val1;
        val1=this.exp.evaluate(SymTable,heapTable);
        BooleanValue b1=(BooleanValue)val1;
        boolean bol1;
        bol1=b1.getValue();
        if(bol1) return new BooleanValue(false);
        else return new BooleanValue(true);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1;
        type1=exp.typecheck(typeEnv);
        if(type1.equals(new BooleanType())) {
            return new BooleanType();
        }
        else throw new MyException("not:exp not bool!");
    }

    @Override
    public Exp deepcopy() {
        return new NegateExpression(exp.deepcopy());
    }
}
