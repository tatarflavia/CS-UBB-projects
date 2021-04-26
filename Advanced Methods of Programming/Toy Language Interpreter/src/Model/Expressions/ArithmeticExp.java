package Model.Expressions;

import Errors.MyException;
import Model.ProgramState.IHeapTable;
import Model.ProgramState.MyIDictionary;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class ArithmeticExp implements Exp {
    private Exp exp1;
    private Exp exp2;
    private int operand; //1-plus, 2-minus, 3-star, 4-divide

    public ArithmeticExp(int operand,Exp exp1, Exp exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operand = operand;
    }


    @Override
    public Value evaluate(MyIDictionary<String, Value> SymTable, IHeapTable<Integer,Value> heapTable) throws MyException {
        Value val1,val2;
        val1=exp1.evaluate(SymTable,heapTable);
        if(val1.getType().equals(new IntType()))
        {
            val2=exp2.evaluate(SymTable,heapTable);
            if(val2.getType().equals(new IntType()))
            {
                IntValue i1=(IntValue)val1;
                IntValue i2=(IntValue)val2;
                int n1,n2;
                n1=((IntValue) val1).getValue();
                n2=((IntValue) val2).getValue();
                if(operand==1) return new IntValue(n1+n2);
                if(operand==2) return new IntValue(n1-n2);
                if(operand==3) return new IntValue(n1*n2);
                if(operand==4)
                {
                    if(n2==0) throw new MyException("division by zero");
                    else return new IntValue(n1/n2);
                }
            }
            else throw new MyException("second operand is not an integer");
        }
        else throw new MyException("first operand is not an integer");
        return new IntValue(0);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1,type2;
        type1=exp1.typecheck(typeEnv);
        type2=exp2.typecheck(typeEnv);
        if (type1.equals(new IntType()))
        {
            if (type2.equals(new IntType()))
                return new IntType();
            else throw new MyException("second operand is not an integer!");
        }
        else throw new MyException("AE:first operand is not an integer!");
    }

    @Override
    public String toString() {
        return "ArithExp(" +
                 exp1 +" "+operand+" "+
                 exp2 +
                ')';
    }

    @Override
    public Exp deepcopy() {
        return new ArithmeticExp(this.operand,this.exp1.deepcopy(),this.exp2.deepcopy());
    }
}
