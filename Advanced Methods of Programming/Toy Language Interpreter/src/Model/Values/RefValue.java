package Model.Values;

import Model.Types.RefType;
import Model.Types.Type;

import java.util.Objects;

public class RefValue implements Value {
    private int address;
    private Type locationType;

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    @Override
    public String toString() {
        return "RefVal(" +
                address +
                ", " + locationType +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof RefValue)
            return true;
        else return  false;
        }

    public void setAddress(int address) {
        this.address = address;
    }


    public int getAddress() {
        return address;
    }

    public Type getLocationType() {
        return locationType;
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public Value deepcopy() {
        return new RefValue(address,locationType.deepcopy());
    }
}
