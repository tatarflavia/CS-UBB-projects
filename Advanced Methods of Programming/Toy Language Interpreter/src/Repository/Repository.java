package Repository;

import Errors.MyException;
import Model.ProgramState.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<ProgramState> states;
    private String logFilePath;

    public Repository(String logFilePath) {
        this.logFilePath = logFilePath;
        this.states = new ArrayList<>();
    }


    @Override
    public void add(ProgramState prgState) {
        this.states.add(prgState);
    }

    @Override
    public void logPrgStateExec(ProgramState prg) throws MyException {
        try{int ok=0;
            PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            if(ok==0){
                logFile.println("_______________________________________________________________________");
                ok=1;
            }
                 logFile.println(prg.toString());
            logFile.println("\n");
            logFile.close();
        }
        catch(IOException e){throw  new MyException("an error occured!");}

    }

    @Override
    public List<ProgramState> getPrgList() {
        return this.states;
    }

    @Override
    public void setPrgList(List<ProgramState> list) {
        this.states=list;
    }

    @Override
    public void checkComplete() {
        for(int i=0;i<this.states.size();i++)
        {
            if(this.states.get(i).getExecutionStack().isEmpty())
                this.states.remove(i);
        }
    }
}
