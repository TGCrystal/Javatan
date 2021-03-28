package src.edu.rpi.cs.csci4960.s21.javatan;

public class Tuple<T, K> {
    public final T t;
    public final K k;
    public Tuple(T t, K k) {
        this.t = t;
        this.k = k;
    }
}