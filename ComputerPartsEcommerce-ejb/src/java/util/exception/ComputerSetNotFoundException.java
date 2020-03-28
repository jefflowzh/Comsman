/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author weidonglim
 */
public class ComputerSetNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>ComputerSetNotFoundException</code>
     * without detail message.
     */
    public ComputerSetNotFoundException() {
    }

    /**
     * Constructs an instance of <code>ComputerSetNotFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ComputerSetNotFoundException(String msg) {
        super(msg);
    }
}
