/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ilkomunila.pbo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author didik
 */
public class Account {

    private IntegerProperty accNumber;
    private DoubleProperty balance;

    public Account(int acc_no, Double deposite) {
        this.accNumber = new SimpleIntegerProperty(acc_no);
        this.balance = new SimpleDoubleProperty(deposite);
    }

    public Integer getAccNumber() {
        return accNumber.get();
    }

    public void setAccNumber(int acc_no) {
        this.accNumber = new SimpleIntegerProperty(acc_no);
    }

    public Double getBalance() {
        return balance.get();
    }

    public void setBalance(double amt) {
        this.balance = new SimpleDoubleProperty(amt);
    }

    public IntegerProperty accNumberProperty() {
        return accNumber;
    }

    public DoubleProperty balanceProperty() {
        return balance;
    }
    
    public void deposite(Double amt){
        this.balance.set(this.getBalance()+amt);
    }
    
    public void withdraw(Double amt){
        this.balance.set(this.getBalance()- amt);
    }
}
