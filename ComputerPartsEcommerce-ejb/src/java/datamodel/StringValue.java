/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import java.io.Serializable;

/**
 *
 * @author weidonglim
 */
public class StringValue implements Serializable {

    private String value;

    public StringValue() {
        value = "";
    }

    public StringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
