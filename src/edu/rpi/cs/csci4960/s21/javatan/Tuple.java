package edu.rpi.cs.csci4960.s21.javatan;

/**
* A simple class to store a tuple of two of any type of objects. The first type corresponds
* to the first variable t, and the second type corresponds to the second variable k.
* The tuple is immutable, the references can't be changed after creation.
*
* @author Chuanfeng Xiong
* @author Chris Lamberston
* @author Ruben McWilliams
* @author Trevor Crystal
*/
public class Tuple<T, K> {
    public final T t;
    public final K k;

    /**
    * Sole constructor for the tuple class
    *
    * @param t the first value to store
    * @param k the second value to store
    */
    public Tuple(T t, K k) {
        this.t = t;
        this.k = k;
    }
}