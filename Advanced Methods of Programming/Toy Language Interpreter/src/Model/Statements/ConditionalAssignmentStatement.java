package Model.Statements;

import Errors.MyException;
import Model.Expressions.Exp;
import Model.Expressions.RelationalExpression;
import Model.Expressions.ValueExp;
import Model.Expressions.VariableExp;
import Model.ProgramState.MyIDictionary;
import Model.ProgramState.MyIStack;
import Model.ProgramState.ProgramState;
import Model.Types.Type;
import Model.Values.IntValue;

public class ConditionalAssignmentStatement implements IStatement{
    private Exp exp1,exp2,exp3;
    private Exp v;


    public ConditionalAssignmentStatement(Exp v,Exp exp1, Exp exp2, Exp exp3) {
        this.v=v;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3=exp3;
    }

    @Override
    public String toString() {
        return "SwitchStatements{" +
                "v="+v+
                "exp=" + exp3 +
                ", exp1=" + exp1 +
                ", exp2=" + exp2 +
                '}';
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> stack=state.getExecutionStack();
        //if (exp1) then v=exp2 else v=exp3
        VariableExp v2=(VariableExp)v;
        String id=v2.getId();
        IStatement newif=new IfStatement(new RelationalExpression(exp1,new ValueExp(new IntValue(0)),4),new AssignStatement(id,exp2),new AssignStatement(id,exp3));
        stack.push(newif);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public IStatement deepcopy() {
        return new ConditionalAssignmentStatement(v.deepcopy(),exp1.deepcopy(),exp2.deepcopy(),exp3.deepcopy());
    }
}
