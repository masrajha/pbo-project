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

    private StringProperty gender;
    private StringProperty birthdate;

    public IndividualHolder(int holderID, String name, String address, String gender, String birthday, Account account) {
        super(holderID, name, address, account);
        this.gender = new SimpleStringProperty(gender);
        this.birthdate = new SimpleStringProperty(birthday);
    }

    public IndividualHolder(int holderID, String name, String address, String gender, String birthday, ArrayList<Account> accounts) {
        super(holderID, name, address, accounts);
        this.gender = new SimpleStringProperty(gender);
        this.birthdate = new SimpleStringProperty(birthday);
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender = new SimpleStringProperty(gender);
    }

    public String getBirthdate() {
        return birthdate.get();
    }

    public void setBirthdate(String birthday) {
        this.birthdate = new SimpleStringProperty(birthday);
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public StringProperty birthdateProperty() {
        return birthdate;
    }

}
