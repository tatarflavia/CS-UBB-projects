package Model.Types;

import Model.Values.StringValue;
import Model.Values.Value;

public class StringType implements Type {
    @Override
    public Value defaultValue() {
        return new StringValue("");
    }

    @Override
    public String toString() {
        return "StringType";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof StringType)
            return true;
        else return  false;
    }

    @Override
    public Type deepcopy() {
        return new StringType();
    }
}
