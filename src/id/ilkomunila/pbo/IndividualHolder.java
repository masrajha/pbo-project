/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ilkomunila.pbo;

import java.util.ArrayList;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author didik
 */
public class IndividualHolder extends AccountHolder{
    private StringProperty SSN;
    private StringProperty birthday;

    public IndividualHolder(int holderID, String name, String address, String SSN, String birthday, Account account) {
        super(holderID, name, address, account);
        this.SSN = new SimpleStringProperty(SSN);
        this.birthday = new SimpleStringProperty(birthday);
    }
    public IndividualHolder(int holderID, String name, String address, String SSN, String birthday, ArrayList<Account> accounts) {
        super(holderID, name, address, accounts);
        this.SSN = new SimpleStringProperty(SSN);
        this.birthday = new SimpleStringProperty(birthday);
    }
    public StringProperty getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = new SimpleStringProperty(SSN);
    }

    public StringProperty getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = new SimpleStringProperty(birthday);
    }
    
    
    
}
