package Model.Expressions;

import Errors.MyException;
import Model.ProgramState.IHeapTable;
import Model.ProgramState.MyIDictionary;
import Model.Types.BooleanType;
import Model.Types.Type;
import Model.Values.BooleanValue;
import Model.Values.Value;
import javafx.beans.property.ReadOnlyBooleanWrapper;

public class LogicExp implements Exp {
    private Exp exp1;
    private Exp exp2;
    private int operand; //1-and 2-or

    public LogicExp(int operand,Exp exp1, Exp exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operand = operand;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> SymTable, IHeapTable<Integer,Value> heapTable) throws MyException {
        Value val1,val2;
        val1=this.exp1.evaluate(SymTable,heapTable);
        if(val1.getType().equals(new BooleanType()))
        {
            val2=this.exp2.evaluate(SymTable,heapTable);
            if(val2.getType().equals(new BooleanType()))
            {
                BooleanValue b1=(BooleanValue)val1;
                BooleanValue b2=(BooleanValue) val2;
                boolean bol1,bol2;
                bol1=b1.getValue();
                bol2=b2.getValue();
                if(this.operand==1) return new BooleanValue(bol1&&bol2);
                if(this.operand==2) return new BooleanValue(bol1||bol2);
            }
            else throw  new MyException("second operand is not a boolean");
        }
        else throw new MyException("first operand is not a boolean");
        return  new BooleanValue(false);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1,type2;
        type1=exp1.typecheck(typeEnv);
        type2=exp2.typecheck(typeEnv);
        if(type1.equals(new BooleanType()))
        {
            if(type2.equals(new BooleanType()))
                return new BooleanType();
            else throw new MyException("second operand is not a boolean!");
        }
        else throw new MyException("first operand is not a boolean!");
    }

    @Override
    public String toString() {
        return "LogicExp(" +
                exp1 +" "+operand+" "+
                exp2 +
                ')';
    }

    @Override
    public Exp deepcopy() {
        return new LogicExp(this.operand,this.exp1.deepcopy(),this.exp2.deepcopy());
    }
}
