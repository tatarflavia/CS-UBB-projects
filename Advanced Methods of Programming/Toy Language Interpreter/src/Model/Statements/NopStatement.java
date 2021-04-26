package Model.Statements;

import Errors.MyException;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.MyIStack;
import Model.ProgramState.ProgramState;
import Model.Types.Type;

public class NopStatement implements IStatement {
    public NopStatement() {
    }

    @Override
    public String toString() {
        return "NopStatement{}";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> stack=state.getExecutionStack();
//        if(stack.isEmpty())
//            throw new MyException("trying to execute when the execution stack is empty");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public IStatement deepcopy() {
        return new NopStatement();
    }
}
