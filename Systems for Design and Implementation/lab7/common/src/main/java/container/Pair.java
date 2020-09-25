package container;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a pair collection
 * @param <U> is the first element in the pair
 * @param <V> is the second element in the pair
 */
public class Pair<U, V> implements Serializable {
    public final U first;
    public final V second;

    public Pair(U first, V second) {
        this.first=first;
        this.second=second;
    }

    @Override
    public boolean equals(Object otherPair) {
        Pair pair=(Pair) otherPair;
        return pair.first.equals(first) && pair.second.equals(second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first,second);
    }
}
