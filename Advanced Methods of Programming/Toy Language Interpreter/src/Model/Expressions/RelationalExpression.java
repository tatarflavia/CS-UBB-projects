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

public class RelationalExpression implements Exp {
    private Exp exp1;
    private Exp exp2;
    private int operand; //1:<    2:<=    3:==    4:!=    5:>    6:>=

    public RelationalExpression(Exp exp1, Exp exp2, int operand) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operand = operand;
    }

    @Override
    public String toString() {
        return "RelExp(" +
                exp1 +" "+operand+" "+
                exp2 +
                ')';
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> SymTable, IHeapTable<Integer,Value> heapTable) throws MyException {
        Value val1,val2;
        val1=this.exp1.evaluate(SymTable,heapTable);
        if(val1.getType().equals(new IntType()))
        {
            val2=this.exp2.evaluate(SymTable,heapTable);
            if(val2.getType().equals(new IntType()))
            {
                IntValue i1,i2;
                i1=(IntValue) val1;
                i2=(IntValue) val2;
                int n1,n2;
                n1=i1.getValue();
                n2=i2.getValue();
                if(this.operand==1) return new BooleanValue(n1<n2);
                if(this.operand==2) return new BooleanValue(n1<=n2);
                if(this.operand==3) return new BooleanValue(n1==n2);
                if(this.operand==4) return new BooleanValue(n1!=n2);
                if(this.operand==5) return new BooleanValue(n1>n2);
                if(this.operand==6) return new BooleanValue(n1>=n2);
            }
            else throw new MyException("second operand is not an integer.");
        }
        else throw new MyException("first operand is not an integer.");
        return new BooleanValue(false);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1,type2;
        type1=exp1.typecheck(typeEnv);
        type2=exp2.typecheck(typeEnv);
        if(type1.equals(new IntType()))
        {
            if(type2.equals(new IntType()))
                return new BooleanType();
            else throw new MyException("second operand is not an int!");
        }
        else throw new MyException("RE:first operand is not a int!");
    }

    @Override
    public Exp deepcopy() {
        return new RelationalExpression(this.exp1.deepcopy(),this.exp2.deepcopy(),this.operand);
    }
}
