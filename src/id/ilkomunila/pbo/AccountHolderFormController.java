/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ilkomunila.pbo;

import id.ilkomunila.pbo.db.DBHelper;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author didik
 */
public class AccountHolderFormController implements Initializable {
    
   @FXML
    private TableView<IndividualHolder> tblAccountHolder;
    
    @FXML
    private TableColumn<IndividualHolder, IntegerProperty> idColumn;

    @FXML
    private TableColumn<IndividualHolder, String> nameColumn;

    @FXML
    private TableColumn<IndividualHolder, String> addressColumn;

    @FXML
    private TableColumn<IndividualHolder, String> ssnColumn;

    @FXML
    private TableColumn<IndividualHolder, String> bdColumn;

    @FXML
    private TableColumn<IndividualHolder, IntegerProperty> numAccColumn;

    @FXML
    private TableView<Account> tblAccount;

    @FXML
    private TableColumn<Account, IntegerProperty> noColumn;

    @FXML
    private TableColumn<Account, IntegerProperty> accNumColumn;

    @FXML
    private TableColumn<Account, DoubleProperty> balanceColumn;

    @FXML
    private TextField tfHolderID;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfSSN;
    
    @FXML
    private DatePicker dpBirthDate;
    
    @FXML
    private TextField tfAccNumber;

    @FXML
    private TextField tfBalance;

    @FXML
    private Label lblDBStatus;
    
    @FXML
    private Label lbActionStatus;
      
    AccountHolderDataModel accHolder;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Sukses");
        try {
              accHolder =new AccountHolderDataModel();
              lblDBStatus.setText(accHolder.conn!=null?"Connected":"Not Connected");
         
        } catch (SQLException ex){
            System.out.println("Gagal");
        }
    }    
    public void handleButtonAddAccount(ActionEvent event){
        LocalDate ld = dpBirthDate.getValue();
        String birthdate=String.format("%d-%02d-%02d", ld.getYear(),ld.getMonthValue(),ld.getDayOfMonth());
    //            System.out.println(birthdate);
        IndividualHolder ih = new IndividualHolder(Integer.parseInt(tfHolderID.getText()),tfName.getText(),tfAddress.getText(),
                tfSSN.getText(),birthdate,new Account(Integer.parseInt(tfAccNumber.getText()),Double.parseDouble(tfBalance.getText())));

       try {
           accHolder.addAccountHolder(ih);
           lbActionStatus.setText("Account data added successfuly");
           lbActionStatus.setTextFill(Color.web("#0d39ba"));
           
       } catch (SQLException ex) {
           Logger.getLogger(AccountHolderFormController.class.getName()).log(Level.SEVERE, null, ex);
           lbActionStatus.setText("Failed saving data account");
           lbActionStatus.setTextFill(Color.web("#0d39ba"));
       }
    }
    
    public void loadDataIndividualHolder(){
        
    }
    
}
