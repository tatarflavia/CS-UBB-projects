package Model.Statements;

import Errors.MyException;
import Model.Expressions.Exp;
import Model.ProgramState.IHeapTable;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.MyIStack;
import Model.ProgramState.ProgramState;
import Model.Types.BooleanType;
import Model.Types.Type;
import Model.Values.BooleanValue;
import Model.Values.Value;

import javax.swing.text.StyledEditorKit;

public class IfStatement implements IStatement{
    private Exp exp;
    private IStatement thenS;
    private IStatement elseS;

    public IfStatement(Exp exp, IStatement thenS, IStatement elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public String toString() {
        return "IF("+ exp.toString()+") THEN(" +thenS.toString()
                +")ELSE("+elseS.toString()+")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> stack=state.getExecutionStack();
//        if(stack.isEmpty())
//            throw new MyException("trying to execute when the execution stack is empty");
        MyIDictionary<String, Value> symTable=state.getSymbolTable();
        IHeapTable<Integer,Value> heapTable=state.getHeapTable();
        Value cond=this.exp.evaluate(symTable,heapTable);
        if(!(cond.getType().equals(new BooleanType())))
            throw new MyException("conditional expr is not a boolean");
        else
        {
            if(((BooleanValue) cond).getValue())
                stack.push(this.thenS);
            else stack.push(this.elseS);
        }
    return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp=exp.typecheck(typeEnv);
        if(typeExp.equals(new BooleanType()))
        {
            MyIDictionary<String,Type> thenEnv,elseEnv;
            thenEnv=thenS.typecheck(typeEnv);
            elseEnv=elseS.typecheck(typeEnv);
            return typeEnv;
        }
        else throw new MyException("The condition of IF has not the type boolean as an exp!");
    }

    @Override
    public IStatement deepcopy() {
        return new IfStatement(this.exp.deepcopy(),this.thenS.deepcopy(),this.elseS.deepcopy());
    }
}
