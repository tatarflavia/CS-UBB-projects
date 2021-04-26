package Model.Types;

import Model.ICopy;
import Model.Values.Value;

public interface Type extends ICopy<Type> {
    Value defaultValue();
}
