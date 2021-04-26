package Model.Statements;

import Errors.MyException;
import Model.ICopy;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.ProgramState;
import Model.Types.Type;


public interface IStatement extends ICopy<IStatement> {
    public ProgramState execute(ProgramState state) throws MyException;
    MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}
