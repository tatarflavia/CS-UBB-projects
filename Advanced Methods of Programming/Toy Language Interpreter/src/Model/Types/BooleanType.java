package Model.Types;

import Model.Values.BooleanValue;
import Model.Values.Value;

public class BooleanType implements Type {
    @Override
    public String toString() {
        return "BoolType";
    }
    public boolean equals(Object another)
    {
        if(another instanceof BooleanType)
            return true;
        else return false;
    }

    @Override
    public Type deepcopy() {
        return new BooleanType();
    }

    @Override
    public Value defaultValue() {
        return new BooleanValue(false);
    }
}
