package edu.rpi.cs.csci4960.s21.javatan;

/**
* A simple class to store a tuple
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