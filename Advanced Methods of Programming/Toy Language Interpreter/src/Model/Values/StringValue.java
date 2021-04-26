package Model.Values;

import Model.Types.StringType;
import Model.Types.Type;

import java.util.Objects;

public class StringValue implements Value {
    private String val="";

    public String getValue() {
        return val;
    }

    public StringValue(String val) {
        this.val = val;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof StringValue && ((StringValue) obj).getValue().equals(val))
            return true;
        else return  false;
    }

    @Override
    public String toString() {
        return "StringVal(" +
                 val +
                ')';
    }

    @Override
    public Value deepcopy() {
        return new StringValue(val);
    }
}
