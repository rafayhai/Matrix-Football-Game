import java.util.Iterator;

public interface FlexibleIterable<T> extends Iterable<T> {

    public Iterator<T> iterator(String iterableObjectName);


}
