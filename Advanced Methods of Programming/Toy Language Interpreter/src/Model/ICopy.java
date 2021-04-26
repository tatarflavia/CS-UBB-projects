package Model;

import Model.ProgramState.MyIDictionary;
import Model.ProgramState.MyIStack;
import Model.ProgramState.ProgramState;
import Model.Statements.IStatement;
import Model.Values.Value;

public interface ICopy<T> {
    public T deepcopy();
}
