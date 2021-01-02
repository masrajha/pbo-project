/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ilkomunila.pbo;

import id.ilkomunila.pbo.db.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author didik
 */
public class AccountHolderDataModel {

    Connection conn = null;

    public AccountHolderDataModel() throws SQLException {
        conn = DBHelper.getConnection();
    }

    public AccountHolderDataModel(String driver) throws SQLException {
        conn = DBHelper.getConnection(driver);
    }

    public void addAccountHolder(IndividualHolder holder) throws SQLException {
        String insertHolder = "INSERT INTO account_holder (holder_id, name, address) VALUES (?,?,?)";
        String insertIndivual = "INSERT INTO individual_holder (holder_id, SSN, birthdate) VALUES (?,?,?)";
        String insertAccount = "INSERT INTO account (holder_id, acc_number, balance) VALUES (?,?,?)";

        PreparedStatement stmtHolder = conn.prepareStatement(insertHolder);
        stmtHolder.setInt(1, holder.getHolderID());
        stmtHolder.setString(2, holder.getName());
        stmtHolder.setString(3, holder.getAddress());
        stmtHolder.execute();

        PreparedStatement stmtIndividual = conn.prepareStatement(insertIndivual);
        stmtIndividual.setInt(1, holder.getHolderID());
        stmtIndividual.setString(2, holder.getSSN());
        stmtIndividual.setString(3, holder.getBirthdate());
        stmtIndividual.execute();

        PreparedStatement stmtAccount = conn.prepareStatement(insertAccount);
        stmtAccount.setInt(1, holder.getHolderID());
        stmtAccount.setInt(2, holder.getAccounts().get(0).getAccNumber());
        stmtAccount.setDouble(3, holder.getAccounts().get(0).getBalance());
        stmtAccount.execute();

    }

    public void addAccountHolder(CorporateHolder holder) throws SQLException {
        String insertHolder = "INSERT INTO account_holder (holder_id, name, address) VALUES (?,?,?)";
        String insertCorporate = "INSERT INTO corporate_holder (holder_id, contact) VALUES (?,?)";
        String insertAccount = "INSERT INTO account (holder_id, acc_number, balance) VALUES (?,?,?)";

        PreparedStatement stmtHolder = conn.prepareStatement(insertHolder);
        stmtHolder.setInt(1, holder.getHolderID());
        stmtHolder.setString(2, holder.getName());
        stmtHolder.setString(3, holder.getAddress());
        stmtHolder.execute();

        PreparedStatement stmtCorporate = conn.prepareStatement(insertCorporate);
        stmtCorporate.setInt(1, holder.getHolderID());
        stmtCorporate.setString(2, holder.getContact());
        stmtCorporate.execute();

        PreparedStatement stmtAccount = conn.prepareStatement(insertAccount);
        stmtAccount.setInt(1, holder.getHolderID());
        stmtAccount.setInt(2, holder.getAccounts().get(0).getAccNumber());
        stmtAccount.setDouble(3, holder.getAccounts().get(0).getBalance());
        stmtAccount.execute();

    }

    public ObservableList<IndividualHolder> getIndividualHolders() {
        ObservableList<IndividualHolder> data = FXCollections.observableArrayList();
        String sql = "SELECT holder_id, name, address, SSN, birthdate "
                + "FROM account_holder NATURAL JOIN individual_holder "
                + "ORDER BY name";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                ArrayList<Account> accounts = new ArrayList<>();
                String sqlAccount = "SELECT acc_number, balance "
                        + "FROM account "
                        + "WHERE holder_id=" + rs.getInt(1);

                ResultSet rsAccount = conn.createStatement().executeQuery(sqlAccount);
                while (rsAccount.next()) {
                    accounts.add(new Account(rsAccount.getInt(1), rsAccount.getDouble(2)));
                }

                data.add(new IndividualHolder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), accounts));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountHolderDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

    public ObservableList<CorporateHolder> getCorporateHolders() {
        ObservableList<CorporateHolder> data = FXCollections.observableArrayList();
        String sql = "SELECT holder_id, name, address, contact "
                + "FROM account_holder NATURAL JOIN corporate_holder "
                + "ORDER BY name";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                ArrayList<Account> accounts = new ArrayList<>();
                String sqlAccount = "SELECT acc_number, balance "
                        + "FROM account "
                        + "WHERE holder_id=" + rs.getInt(1);

                ResultSet rsAccount = conn.createStatement().executeQuery(sqlAccount);
                while (rsAccount.next()) {
                    accounts.add(new Account(rsAccount.getInt(1), rsAccount.getDouble(2)));
                }
                data.add(new CorporateHolder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), accounts));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return data;
    }

    public int nextAccountHolderID() throws SQLException {
        String sql = "SELECT MAX(holder_id) FROM account_holder";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while (rs.next()) {
//            System.out.println("Haloooo "+rs.getInt(1));
            return (rs.getInt(1)==0?1000001:rs.getInt(1)+1);
        }
        return 1000001;
    }
}
