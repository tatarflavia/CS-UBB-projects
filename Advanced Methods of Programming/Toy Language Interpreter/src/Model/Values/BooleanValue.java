package Model.Values;

import Model.Types.BooleanType;
import Model.Types.Type;

public class BooleanValue implements Value{
    private boolean val=false;

    public BooleanValue(boolean val) {
        this.val = val;
    }

    public boolean getValue() {
        return val;
    }

    @Override
    public String toString() {
        if(!val)
            return "false";
        else return "true";
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BooleanValue && ((BooleanValue) obj).getValue()==val)
            return true;
        else return  false;
    }

    @Override
    public Value deepcopy() {
        return new BooleanValue(this.val);
    }
}
