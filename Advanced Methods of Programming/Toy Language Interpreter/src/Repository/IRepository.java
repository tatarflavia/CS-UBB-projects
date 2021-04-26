package Repository;

import Errors.MyException;
import Model.ProgramState.ProgramState;

import java.util.List;

public interface IRepository {
   public void add(ProgramState prgState);
   void logPrgStateExec(ProgramState prg) throws MyException;
   List<ProgramState> getPrgList();
   void setPrgList(List<ProgramState> list);
   public void checkComplete();
}
