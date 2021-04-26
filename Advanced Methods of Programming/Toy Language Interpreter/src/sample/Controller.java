package sample;

import Controlller.Service;
import Errors.MyException;
import Model.Expressions.*;
import Model.ProgramState.*;
import Model.Statements.*;
import Model.Statements.File.*;
import Model.Statements.Heap.*;
import Model.Types.*;
import Model.Values.*;
import Repository.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Controller {
    private Integer index=null;
    private ArrayList<IStatement> listOfProgs=null;
    @FXML
    private ListView<String> listOfProgsView;
    @FXML
    public void initialize(){
        createListOfProgs();
        populateListOfProgsInView();
    }

    public void listOfProgsClicked(MouseEvent mouseEvent) {
        index=listOfProgsView.getSelectionModel().getSelectedIndex();
    }

    private void createListOfProgs(){
        listOfProgs=new ArrayList<IStatement>();
        IStatement ex1=new CompoundStatement(
                new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(
                        new AssignStatement("v",new ValueExp(new IntValue(2))),
                        new PrintStatement(new VariableExp("v"))));
        IStatement ex2=new CompoundStatement(
                new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(
                                new AssignStatement("a", new ArithmeticExp(1, new ValueExp(new IntValue(2)),
                                        new ArithmeticExp(3,new ValueExp(new IntValue(3)),new ValueExp(new IntValue(5))))),
                                new CompoundStatement(
                                        new AssignStatement("b",new ArithmeticExp(1,new VariableExp("a"),new ValueExp(new IntValue(1)))),
                                        new PrintStatement(new VariableExp("b"))))));
        IStatement ex3=new CompoundStatement(
                new VariableDeclarationStatement("a",new BooleanType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("v",new IntType()),new CompoundStatement(
                        new AssignStatement("a",new ValueExp(new BooleanValue(true))),
                        new CompoundStatement(new IfStatement(new VariableExp("a"),
                                new AssignStatement("v",new ValueExp(new IntValue(2))),
                                new AssignStatement("v",new ValueExp(new IntValue(3)))),
                                new PrintStatement(new VariableExp("v"))))));
        //ex in plus cu deschis fisiere ex prof
        IStatement ex4=new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(new AssignStatement("varf",new ValueExp(new StringValue("test.txt"))),
                        new CompoundStatement(new OpenRFile(new VariableExp("varf")),
                                new CompoundStatement(new VariableDeclarationStatement("varc",new IntType()),
                                        new CompoundStatement(new ReadFile(new VariableExp("varf"),"varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExp("varc")),new CompoundStatement(new ReadFile(new VariableExp("varf"),"varc"),
                                                        new CompoundStatement(new PrintStatement(new VariableExp("varc")),new CloseRFile(new VariableExp("varf"))))))))));

        //ex de la garbage collector
        IStatement ex5=new CompoundStatement(new VariableDeclarationStatement("v",new RefType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v",new ValueExp(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a",new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a",new VariableExp("v")),
                                        new CompoundStatement(new HeapAllocationStatement("v",new ValueExp(new IntValue(30))),
                                                new PrintStatement(new HeapReadingExp(new HeapReadingExp(new VariableExp("a")))))))));
        //ex de la while statement
        IStatement decl71 = new VariableDeclarationStatement("v", new IntType());
        IStatement as71 = new AssignStatement("v", new ValueExp(new IntValue(4)));
        IStatement while71 = new WhileStatement(new RelationalExpression(new VariableExp("v"), new ValueExp(new IntValue(0)),5),
                new CompoundStatement(new PrintStatement(new VariableExp("v")), new AssignStatement("v", new ArithmeticExp(2, new VariableExp("v"), new ValueExp(new IntValue(1))))));
        IStatement pr71 = new PrintStatement(new VariableExp("v"));

        IStatement ex6 =  new CompoundStatement(decl71,
                new CompoundStatement(as71,
                        new CompoundStatement(while71, pr71)));

        //ex de la heap allocation
        IStatement ex7 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExp(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExp("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExp("v")),
                                                new PrintStatement(new VariableExp("a")))))));
        //ex de la heap reading
        IStatement ex8 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExp(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExp("v")),
                                        new CompoundStatement(new PrintStatement(new HeapReadingExp(new VariableExp("v"))),
                                                new PrintStatement(new ArithmeticExp(1,new HeapReadingExp(new HeapReadingExp(new VariableExp("a"))),
                                                        new ValueExp(new IntValue(5)))))))));
        //ex de la heap writing
        IStatement ex9 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExp(new IntValue(20))),
                        new CompoundStatement(new PrintStatement(new HeapReadingExp(new VariableExp("v"))),
                                new CompoundStatement(new HeapWritingStatement("v", new ValueExp(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExp(1,new HeapReadingExp(new VariableExp("v")), new ValueExp(new IntValue(5))))))));
        //ex de la thread-uri
        IStatement ex10=new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("a",new RefType(new IntType())),
                        new CompoundStatement(new AssignStatement("v",new ValueExp(new IntValue(10))),
                                new CompoundStatement(new HeapAllocationStatement("a",new ValueExp(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(
                                                new CompoundStatement(new HeapWritingStatement("a",new ValueExp(new IntValue(30))),
                                                        new CompoundStatement(new AssignStatement("v",new ValueExp(new IntValue(32))),
                                                                new CompoundStatement(new PrintStatement(new VariableExp("v")),
                                                                        new PrintStatement(new HeapReadingExp(new VariableExp("a"))))
                                                        )
                                                )
                                        ),
                                                new CompoundStatement(new PrintStatement(new VariableExp("v")),
                                                        new PrintStatement(new HeapReadingExp(new VariableExp("a")))))))));

        //add in list:
        listOfProgs.add(ex1);
        listOfProgs.add(ex2);
        listOfProgs.add(ex3);
        listOfProgs.add(ex4);
        listOfProgs.add(ex5);
        listOfProgs.add(ex6);
        listOfProgs.add(ex7);
        listOfProgs.add(ex8);
        listOfProgs.add(ex9);
        listOfProgs.add(ex10);

    }

    private void populateListOfProgsInView(){
        ObservableList<String> listInView= FXCollections.observableArrayList();
        listInView.addAll(listOfProgs.stream().map(Object::toString).collect(Collectors.toList()));
        listOfProgsView.setItems(listInView);
    }

    private void createNewService(Integer indx) throws MyException, IOException {
        IStatement ex10=listOfProgs.get(indx);
        MyIDictionary<String, Type> envDict10=new MyDictionary<>();
        envDict10=ex10.typecheck(envDict10);
        MyStack<IStatement> stack10 = new MyStack<>();
        MyList<Value> list10=new MyList<>();
        MyDictionary<String,Value> dict10=new MyDictionary<>();
        FileTable<StringValue, BufferedReader> fileTable10=new FileTable<>();
        HeapTable<Integer,Value> heapTable10=new HeapTable<>();
        LatchTable<Integer,Integer> latchTable10=new LatchTable<>();
        ProgramState prg10=new ProgramState(stack10,dict10,list10,fileTable10,heapTable10,latchTable10,ex10);
        IRepository repo10=new Repository("log"+ indx + ".txt");
        repo10.add(prg10);
        Service serv10=new Service(repo10);

        Stage stage=new Stage();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("programStates.fxml"));
        Parent root = loader.load();
        ControllerRunningProg controller2=loader.getController();
        controller2.setService(serv10);
        stage.setTitle("Running Program");
        stage.setScene(new Scene(root, 916, 674));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        //stage.show();
    }

    @FXML
    public void runProgButtonClicked(MouseEvent mouseEvent) throws MyException,IOException {
        if(index==null)
        {
            Alert eroare=new Alert(Alert.AlertType.ERROR);
            eroare.setHeaderText("Error!!!");
            eroare.setContentText("Please choose a program!");
            eroare.showAndWait();
            return;
        }
        createNewService(index);
        index=null;
    }
}
