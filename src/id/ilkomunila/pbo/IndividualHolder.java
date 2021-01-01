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
public class IndividualHolder extends AccountHolder {

    private StringProperty SSN;
    private StringProperty birthdate;

    public IndividualHolder(int holderID, String name, String address, String SSN, String birthday, Account account) {
        super(holderID, name, address, account);
        this.SSN = new SimpleStringProperty(SSN);
        this.birthdate = new SimpleStringProperty(birthday);
    }

    public IndividualHolder(int holderID, String name, String address, String SSN, String birthday, ArrayList<Account> accounts) {
        super(holderID, name, address, accounts);
        this.SSN = new SimpleStringProperty(SSN);
        this.birthdate = new SimpleStringProperty(birthday);
    }

    public String getSSN() {
        return SSN.get();
    }

    public void setSSN(String SSN) {
        this.SSN = new SimpleStringProperty(SSN);
    }

    public String getBirthdate() {
        return birthdate.get();
    }

    public void setBirthdate(String birthday) {
        this.birthdate = new SimpleStringProperty(birthday);
    }

    public StringProperty SSNProperty() {
        return SSN;
    }

    public StringProperty birthdateProperty() {
        return birthdate;
    }

}
