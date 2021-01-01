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
import javafx.beans.property.StringProperty;

/**
 *
 * @author didik
 */
public class Account {
    private IntegerProperty acc_no;
    private DoubleProperty deposite;

    public Account(int acc_no, Double deposite) {
        this.acc_no = new SimpleIntegerProperty(acc_no);
        this.deposite = new SimpleDoubleProperty(deposite);
    }

    public IntegerProperty getAcc_no() {
        return acc_no;
    }

    public void setAcc_no(int acc_no) {
        this.acc_no = new SimpleIntegerProperty(acc_no);
    }

    public DoubleProperty getDeposite() {
        return deposite;
    }

    public void setDeposite(double deposite) {
        this.deposite = new SimpleDoubleProperty(deposite);
    }
    
}
