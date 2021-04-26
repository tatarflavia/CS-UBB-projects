package Model.Statements.Heap;

import Errors.MyException;
import Model.Expressions.Exp;
import Model.ProgramState.IHeapTable;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.ProgramState;
import Model.Statements.IStatement;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

public class HeapAllocationStatement implements IStatement {
    private String var_name;
    private Exp exp;

    public HeapAllocationStatement(String var_name, Exp exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "new(" +
                "var_name='" + var_name + '\'' +
                ", exp=" + exp +
                ')';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, Value> symbolTable=state.getSymbolTable();
        IHeapTable<Integer,Value> heapTable=state.getHeapTable();
        if(!(symbolTable.isDefined(var_name) && symbolTable.getValue(var_name).getType() instanceof RefType))
        {
            throw new MyException("Either var_name is not defined or it's type is not a reftype.");
        }
        Value val=this.exp.evaluate(symbolTable,heapTable);
        Value val_from_var=symbolTable.getValue(var_name);
        RefValue ref_val_from_var=(RefValue) val_from_var;
        Type loc_type=ref_val_from_var.getLocationType();
        if(!(val.getType().equals(loc_type)))
            throw new MyException("the expression and the location type of var_name don't have the same type!");
        int newAddress= heapTable.getFreeAddress();
        heapTable.put(newAddress,val);
        //ref_val_from_var.setAddress(newAddress);
        //symbolTable.update(var_name,ref_val_from_var);
        symbolTable.update(var_name,new RefValue(newAddress,val.getType()));

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar=typeEnv.lookUp(var_name);
        Type typeExp=exp.typecheck(typeEnv);
        if(typeVar.equals(new RefType(typeExp)))
            return typeEnv;
        else throw new MyException("Heap Allocation:the type of variable name and type are different!");
    }

    @Override
    public IStatement deepcopy() {
        return new HeapAllocationStatement(var_name,exp.deepcopy());
    }
}
