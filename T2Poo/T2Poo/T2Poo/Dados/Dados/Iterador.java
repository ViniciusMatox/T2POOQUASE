package Dados;

public interface Iterador<T> {
    void reset();
    boolean hasNext();
    T next();
}
