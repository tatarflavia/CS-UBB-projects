package Model.Types;

import Model.Values.IntValue;
import Model.Values.Value;

public class IntType implements Type{

    @Override
    public String toString() {
        return "IntType";
    }

    public boolean equals(Object another)
    {
        if(another instanceof IntType)
            return true;
        else return  false;
    }

    @Override
    public Type deepcopy() {
        return new IntType();
    }

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }
}

