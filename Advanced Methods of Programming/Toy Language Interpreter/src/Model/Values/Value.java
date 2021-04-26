package Model.Values;

import Model.ICopy;
import Model.Types.Type;

public interface Value extends ICopy<Value> {
    public Type getType();
}
