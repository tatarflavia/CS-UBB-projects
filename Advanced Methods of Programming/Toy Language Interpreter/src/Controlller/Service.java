package Controlller;

import Errors.MyException;
import Model.ProgramState.MyIStack;
import Model.ProgramState.ProgramState;
import Model.Statements.IStatement;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.IRepository;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Service {
    private IRepository repo;
    private ExecutorService executor;
    private List<ProgramState> completedProgramList;

    public Service(IRepository repo) {
        this.repo = repo;
    }

    public List<ProgramState> getProgList()
    {
        return repo.getPrgList();
    }

   private void conservativeGarbageCollector(List<ProgramState> completedProgramList)
   {
       List<Integer> symtable=completedProgramList.stream()
               .map(e->e.getSymbolTable().getContent().values())
               .map(e->getAddrFromSymTable(e))
               .reduce(Stream.of(0).collect(Collectors.toList()), (s1,s2)->Stream.concat(s1.stream(),s2.stream()).collect(Collectors.toList()));
       Collection<Value> heapTableValues=completedProgramList.get(0).getHeapTable().getContent().values();
       List<Integer> heap=heapTableValues.stream()
               .filter(v->v instanceof RefValue)
               .map(v->{RefValue val=(RefValue)v;return val.getAddress();})
               .collect(Collectors.toList());
       symtable.addAll(heap);
       completedProgramList.get(0).getHeapTable().setContent(unsafeGarbageCollector(symtable,completedProgramList.get(0).getHeapTable().getContent()));
   }

    private Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer,Value> heap)
    {
        //is called with the list of adresses from the symbol table and the content of the heap tabl
        //which is a map
        //returns a map(to be set to be heap) that contains only the adresses that have an id in
        //symtable

        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }
    private List<Integer> getAddrFromSymTable(Collection<Value> symTableValues)
    {
        //returns a list made of the addresses from the symbol table(only from the ref values)
        List<Integer> symtable= symTableValues.stream()
                .filter(v->v instanceof RefValue)
                .map(v->{RefValue val=(RefValue)v;return val.getAddress();})
                .collect(Collectors.toList());
        //for the case when an address from heap will reference to another reference
        // ex:1->(4,bool) right, but not for 2->6
        //gets a list made of addresses from the heaptable that have reference to another ref
        return symtable;


    }




    private List<ProgramState> removeCompletedPrg(List<ProgramState> inputProgramList)
    {
        return inputProgramList.stream()
                .filter(p-> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<ProgramState> programStateList)
    {
            //print the prgstate list into file
            programStateList.forEach(prg-> {
                try {
                    repo.logPrgStateExec(prg);
                } catch (MyException e) {
                    e.printStackTrace();
                }
            });
            //run concurrently one step for each thread(program state)
            List<Callable<ProgramState>> callableList=programStateList.stream()
                    .map((ProgramState p)->(Callable<ProgramState>)(()-> {return p.oneStep();}))
                    .collect(Collectors.toList());
            //start the execution of the callables
        try{
            List<ProgramState> newProgList=executor.invokeAll(callableList).stream()
                .map(future->{
                    try{
                        return future.get();
                    }
                    catch (InterruptedException | ExecutionException e) {System.out.println(e.getMessage());}
                return null;})
                .filter(p->p!=null)
                .collect(Collectors.toList());
            //add the new created threads to the list of existing threads
            programStateList.addAll(newProgList);
            programStateList.forEach(prg-> {
                try {
                    repo.logPrgStateExec(prg);
                } catch (MyException e) {
                    e.printStackTrace();
                }
            });
            //save the current programs in the repo
            repo.setPrgList(programStateList);}
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void allStep() throws MyException{
        executor= Executors.newFixedThreadPool(2);
        //this.repo.checkComplete();
        removeCompletedPrg(repo.getPrgList());
        List<ProgramState> programs=repo.getPrgList();
        if(programs.size()>0)
        {
            conservativeGarbageCollector(programs);
            oneStepForAllPrg(programs);
            removeCompletedPrg(this.repo.getPrgList());
            executor.shutdownNow();
        }

    }

}
