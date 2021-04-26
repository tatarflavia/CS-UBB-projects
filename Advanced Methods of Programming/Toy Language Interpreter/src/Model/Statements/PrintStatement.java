package Model.Statements;

import Errors.MyException;
import Model.Expressions.Exp;
import Model.ProgramState.*;
import Model.Types.Type;
import Model.Values.Value;

public class PrintStatement implements IStatement {
    private Exp exp;

    public PrintStatement(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "print(" +exp.toString()+")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        //expresia evaluata in lista de output
        MyIStack<IStatement> stack=state.getExecutionStack();

        MyIDictionary<String, Value> symTable=state.getSymbolTable();
        MyIList<Value> out=state.getOut();
        IHeapTable<Integer,Value> heapTable=state.getHeapTable();
        Value val=this.exp.evaluate(symTable,heapTable);
        out.add(val);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type=exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public IStatement deepcopy() {
        return new PrintStatement(this.exp.deepcopy());
    }
}
