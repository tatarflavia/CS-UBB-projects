package Model.Values;

import Model.Types.IntType;
import Model.Types.Type;

public class IntValue implements Value {
    private int val=0;

    public IntValue(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Int(" +
                 + val +
                ')';
    }

    public int getValue() {
        return val;
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof IntValue && ((IntValue) obj).getValue()==val)
            return true;
        else return  false;
    }

    @Override
    public Value deepcopy() {
        return new IntValue(val);
    }
}
