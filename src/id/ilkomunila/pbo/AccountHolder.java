/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ilkomunila.pbo;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author didik
 */
public abstract class AccountHolder {

    protected IntegerProperty holderID;
    protected StringProperty name;
    protected StringProperty address;
    protected IntegerProperty numAccounts;
    protected ArrayList<Account> accounts;

    public AccountHolder(int holderID, String name, String address) {
        this.holderID = new SimpleIntegerProperty(holderID);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.accounts = new ArrayList<>();
    }

    public AccountHolder(int holderID, String name, String address, Account account) {
        this.holderID = new SimpleIntegerProperty(holderID);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.accounts = new ArrayList<>();
        this.accounts.add(account);
        this.numAccounts=new SimpleIntegerProperty(this.accounts.size());
    }

    public AccountHolder(int holderID, String name, String address, ArrayList<Account> accounts) {
        this.holderID = new SimpleIntegerProperty(holderID);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.accounts = accounts;
        this.numAccounts=new SimpleIntegerProperty(this.accounts.size());
    }

    public Integer getHolderID() {
        return holderID.get();
    }

    public void setHolderID(int holderID) {
        this.holderID = new SimpleIntegerProperty(holderID);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public IntegerProperty holderIDProperty() {
        return holderID;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty addressProperty() {
        return address;
    }
    public IntegerProperty numAccountsProperty() {
        return numAccounts;
    }
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public IntegerProperty getNumAccounts() {
        return new SimpleIntegerProperty(this.accounts.size());
    }
    
}
