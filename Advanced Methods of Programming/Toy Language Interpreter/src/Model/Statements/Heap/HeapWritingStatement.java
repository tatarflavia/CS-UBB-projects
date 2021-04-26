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

public class HeapWritingStatement implements IStatement {
    private String var_name;
    private Exp exp;

    public HeapWritingStatement(String var_name, Exp exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "writeHeap(" +
                "var_name='" + var_name + '\'' +
                ", exp=" + exp +
                ')';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, Value> symbolTable=state.getSymbolTable();
        IHeapTable<Integer,Value> heapTable=state.getHeapTable();
        if(!(symbolTable.isDefined(var_name) && symbolTable.getValue(var_name).getType() instanceof RefType))
            throw new MyException("The variable name is not defined in the symbol table or it's type is not a reftype.");
        Type type_from_var_name=symbolTable.getValue(var_name).getType();
        Value value_from_var_name=symbolTable.getValue(var_name);
        RefValue ref_val_from_var=(RefValue) value_from_var_name;
        int addr_from_var_name=ref_val_from_var.getAddress();
        if(!(heapTable.isDefined(addr_from_var_name)))
            throw new MyException("The address of the RefValue associated to var_name in symTable is not a key in the heapTable.");
        Value val_eval=this.exp.evaluate(symbolTable,heapTable);
        if(!(val_eval.getType().equals(ref_val_from_var.getLocationType())))
            throw new MyException("The evaluation must have the same type as the locType of the var_name type.");
        heapTable.update(addr_from_var_name,val_eval);
        return  null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar=typeEnv.lookUp(var_name);
        Type typeExp=exp.typecheck(typeEnv);
        if(typeVar.equals(new RefType(typeExp)))
            return typeEnv;
        else throw new MyException("Heap Writing:the type of variable name and type are different!");

    }

    @Override
    public IStatement deepcopy() {
        return new HeapWritingStatement(var_name,exp.deepcopy());
    }
}
