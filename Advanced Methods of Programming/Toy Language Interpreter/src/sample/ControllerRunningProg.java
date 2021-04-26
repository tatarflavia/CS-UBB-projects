package sample;

import Controlller.Service;
import Errors.MyException;
import Model.ProgramState.HeapTable;
import Model.ProgramState.MyDictionary;
import Model.ProgramState.ProgramState;
import Model.Statements.IStatement;
import Model.Values.Value;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

import java.net.Inet4Address;
import java.util.List;
import java.util.Map;

public class ControllerRunningProg {
    private Service service;
    @FXML
    private ListView<String> threadView;
    @FXML
    private TableView<Pair<String,Value>> symbolTableView;
    @FXML
    private TableView<Pair<Integer,Integer>> latchTableView;
    @FXML
    private TableView<Pair<Integer,Value>> heapTableView;
    @FXML
    private ListView<String> outView;
    @FXML
    private ListView<String> stackView;
    @FXML
    private ListView<String> fileTableView;
    @FXML
    private TextField textFileldForNbOfPrgStates;
    @FXML
    private TableColumn<Pair<Integer,Value>,Integer> adressHeapView;
    @FXML
    private TableColumn<Pair<Integer,Value>, Value> valueHeapView;
    @FXML
    private TableColumn<Pair<String,Value>, String> VarNameSymView;
    @FXML
    private TableColumn<Pair<String,Value>, Value> ValueSymView;
    @FXML
    private TableColumn<Pair<Integer,Integer>,Integer> locLatchView;
    @FXML
    private TableColumn<Pair<Integer,Integer>,Integer> valueLatchView;
    private Integer indexCurrentProgram;
    //private IStatement currentProgram;

    public void setService(Service service) {
        this.service = service;
        populateThreads();
        textFileldForNbOfPrgStates.setText(String.valueOf(service.getProgList().size()));
    }
    @FXML
    public void initialize()
    {
        textFileldForNbOfPrgStates.setEditable(false);
        adressHeapView.setCellValueFactory(p->new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        valueHeapView.setCellValueFactory(p -> new SimpleObjectProperty<Value>(p.getValue().getValue()));

        VarNameSymView.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getKey()));
        ValueSymView.setCellValueFactory(p -> new SimpleObjectProperty<Value>(p.getValue().getValue()));

        locLatchView.setCellValueFactory(p->new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        valueLatchView.setCellValueFactory(p->new SimpleIntegerProperty(p.getValue().getValue()).asObject());


    }
    @FXML
    private void populateThreads(){
        List<ProgramState> programList=service.getProgList();
        //System.out.println(programList.size());
        ObservableList<String> listInView= FXCollections.observableArrayList();
        for(int i=0;i<programList.size();i++)
            if(programList.get(i).getExecutionStack().isEmpty()==false)
                listInView.add(programList.get(i).getOriginalProgram().toString());
        textFileldForNbOfPrgStates.setText(String.valueOf(listInView.size()));
        threadView.setItems(listInView);
    }


    @FXML
    public void oneStepButtonClicked(MouseEvent mouseEvent) throws MyException {
        if(textFileldForNbOfPrgStates.getText().equals("0"))
        {
            Alert eroare=new Alert(Alert.AlertType.ERROR);
            eroare.setHeaderText("Error!!!");
            eroare.setContentText("Empty stack for the chosen programm!");
            eroare.showAndWait();
        }
        else{
            this.service.allStep();
            populateAllLists();
        }

    }

    @FXML
    public void threadListClicked(MouseEvent mouseEvent) {
        indexCurrentProgram=threadView.getSelectionModel().getSelectedIndex();
    }
    private void populateOut(){
        ProgramState prg=service.getProgList().get(indexCurrentProgram);
        ObservableList<String> out=FXCollections.observableArrayList();
        out.add(prg.getOut().toString());
        outView.setItems(out);
    }
    private void populateStack(){
        ProgramState prg=service.getProgList().get(indexCurrentProgram);
        ObservableList<String> stack=FXCollections.observableArrayList();
        stack.add(prg.getExecutionStack().toString());
        stackView.setItems(stack);
    }
    private void populateFileTable(){
        ProgramState prg=service.getProgList().get(indexCurrentProgram);
        ObservableList<String> tabl=FXCollections.observableArrayList();
        tabl.add(prg.getFileTable().toString());
        fileTableView.setItems(tabl);
    }
    //asta +sym
    private void populateHeapTable()
    {
        ObservableList<Pair<Integer,Value>> progs=FXCollections.observableArrayList();
        ProgramState prg=service.getProgList().get(indexCurrentProgram);

        for(Map.Entry<Integer,Value> entry:prg.getHeapTable().getContent().entrySet())
        {
            //System.out.println("key"+entry.getKey());
            //System.out.println("val"+entry.getValue());
            progs.add(new Pair<Integer,Value>(entry.getKey(),entry.getValue()));
        }
        //System.out.println("gata"+progs);
        heapTableView.setItems(progs);
    }
    private void populateSymTable()
    {
        ObservableList<Pair<String,Value>> programs=FXCollections.observableArrayList();
        ProgramState prg=service.getProgList().get(indexCurrentProgram);

        for(Map.Entry<String,Value> entry:prg.getSymbolTable().getContent().entrySet())
        {
            //System.out.println("key"+entry.getKey());
            //System.out.println("val"+entry.getValue());
            programs.add(new Pair<String,Value>(entry.getKey(),entry.getValue()));
        }
        //System.out.println("gata"+progs);
        symbolTableView.setItems(programs);
    }
    private void populateLatchTable(){
        ObservableList<Pair<Integer,Integer>> programs=FXCollections.observableArrayList();
        ProgramState prg=service.getProgList().get(indexCurrentProgram);

        for(Map.Entry<Integer,Integer> entry:prg.getLatchTable().getContent().entrySet())
        {
            //System.out.println("key"+entry.getKey());
            //System.out.println("val"+entry.getValue());
            programs.add(new Pair<Integer, Integer>(entry.getKey(),entry.getValue()));
        }
        //System.out.println("gata"+progs);
        latchTableView.setItems(programs);
    }
    public void populateAllLists()
    {
        populateThreads();
        //textFileldForNbOfPrgStates.setText(String.valueOf(service.getProgList().size()));
        populateOut();
        populateStack();
        populateSymTable();
        populateLatchTable();
        populateFileTable();
        populateHeapTable();


    }
}
