package Model.Map;

public interface Iter<T> {

    public T currentItem();

    public boolean isValid();

    public void next();

    public void reset();

}
