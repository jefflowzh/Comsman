/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author zeplh
 */
public class IncompatiblePartException extends Exception {

    /**
     * Creates a new instance of <code>IncompatiblePartException</code> without
     * detail message.
     */
    public IncompatiblePartException() {
    }

    /**
     * Constructs an instance of <code>IncompatiblePartException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IncompatiblePartException(String msg) {
        super(msg);
    }
}
