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

public class WhileStatement implements IStatement {
    private Exp exp;
    private IStatement statement;

    public WhileStatement(Exp exp, IStatement statement) {
        this.exp = exp;
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "while(" +
                  exp +
                ")" + statement ;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, Value> symbolTable=state.getSymbolTable();
        IHeapTable<Integer,Value> heapTable=state.getHeapTable();
        MyIStack<IStatement> stack=state.getExecutionStack();
        Value val_eval=this.exp.evaluate(symbolTable,heapTable);
        if(val_eval instanceof BooleanValue)
        {
            BooleanValue booleanValue_from_eval=(BooleanValue) val_eval;
            if(booleanValue_from_eval.getValue())
            {
                stack.push(new WhileStatement(exp,statement));
                stack.push(this.statement);
                //state.setExecutionStack(stack);

            }

        }
        else throw new MyException("Expression from while is not a boolean.");
        return null;

    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp=exp.typecheck(typeEnv);
        if(typeExp.equals(new BooleanType()))
        {
            MyIDictionary<String,Type> typEnv1 = statement.typecheck(typeEnv);
            return typeEnv;
        }
        else throw new MyException("While Statement:the expression can't be evaluated to boolean!");


    }

    @Override
    public IStatement deepcopy() {
        return new WhileStatement(this.exp.deepcopy(),this.statement.deepcopy());
    }
}
