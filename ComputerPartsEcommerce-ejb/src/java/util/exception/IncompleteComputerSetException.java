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
public class IncompleteComputerSetException extends Exception {

    /**
     * Creates a new instance of <code>IncompleteComputerSetException</code>
     * without detail message.
     */
    public IncompleteComputerSetException() {
    }

    /**
     * Constructs an instance of <code>IncompleteComputerSetException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public IncompleteComputerSetException(String msg) {
        super(msg);
    }
}
