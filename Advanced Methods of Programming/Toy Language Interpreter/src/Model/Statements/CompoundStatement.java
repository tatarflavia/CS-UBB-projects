package Model.Statements;

import Errors.MyException;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.MyIStack;
import Model.ProgramState.ProgramState;
import Model.Types.Type;

public class CompoundStatement implements IStatement {
    private IStatement first;
    private IStatement second;

    public CompoundStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> stack=state.getExecutionStack();
//        if(stack.isEmpty())
//            throw new MyException("trying to execute when the execution stack is empty");
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return second.typecheck(first.typecheck(typeEnv));
    }

    @Override
    public String toString() {
        return  "("+first.toString() + ";" + second.toString()+")";
    }

    @Override
    public IStatement deepcopy() {
        return new CompoundStatement(this.first.deepcopy(),this.second.deepcopy());
    }
}
