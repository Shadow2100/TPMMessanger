/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import java.io.Serializable;

/**
 *
 * @author Vova
 */
public class Message implements Serializable {

    private String cryptType;
    private String text = null;

    public String getCryptType() {
        return cryptType;
    }

    public Message(String cryptType, String text) {
        this.text = text;
        this.cryptType = cryptType;
    }

    public Message() {
    }

    public String getText() {
        return text;
    }

}
