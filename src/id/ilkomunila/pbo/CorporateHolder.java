/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ilkomunila.pbo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author didik
 */
public class CorporateHolder extends AccountHolder{
    private StringProperty contact;
    public CorporateHolder(int holderID, String name, String address,String contact,Account account) {
        super(holderID, name, address,account);
        this.contact = new SimpleStringProperty(contact);
    }
    public StringProperty getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = new SimpleStringProperty(contact);
    }
    
}
