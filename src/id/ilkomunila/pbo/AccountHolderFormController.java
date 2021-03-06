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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<IndividualHolder, Integer> idColumn;

    @FXML
    private TableColumn<IndividualHolder, String> nameColumn;

    @FXML
    private TableColumn<IndividualHolder, String> addressColumn;

    @FXML
    private TableColumn<IndividualHolder, String> genderColumn;

    @FXML
    private TableColumn<IndividualHolder, String> bdColumn;

    @FXML
    private TableColumn<IndividualHolder, Integer> numAccColumn;

    @FXML
    private TableView<Account> tblAccount;

    @FXML
    private TableColumn<Account, Integer> noColumn;

    @FXML
    private TableColumn<Account, Integer> accNumColumn;

    @FXML
    private TableColumn<Account, Double> balanceColumn;

    @FXML
    private TextField tfHolderID;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfAddress;

    @FXML
    private ComboBox cbGender;

    @FXML
    private DatePicker dpBirthDate;

    @FXML
    private TextField tfAccNumber;

    @FXML
    private TextField tfBalance;
    
    @FXML
    private TextField tfNewHolderID;
    @FXML
    private TextField tfNewAccNumber;

    @FXML
    private TextField tfNewAccBalance;
    
    @FXML
    private Button btnAddAccount;
    @FXML
    private Label lblDBStatus;

    @FXML
    private Label lbActionStatus;

    @FXML
    private Button btnReload;

    AccountHolderDataModel accHolder;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female");
        cbGender.setItems(gender);

        try {
            accHolder = new AccountHolderDataModel();
            lblDBStatus.setText(accHolder.conn != null ? "Connected" : "Not Connected");
            tfHolderID.setText("" + accHolder.nextAccountHolderID());
            tfHolderID.setDisable(true);
            tfAccNumber.setText(tfHolderID.getText() + "01");
            tfAccNumber.setDisable(true);
            dpBirthDate.setValue(LocalDate.of(LocalDate.now().getYear() - 17, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()));
            btnReload.fire();
//            System.out.println("Sukses");
        } catch (SQLException ex) {
            System.out.println("Gagal");
            ex.printStackTrace();
        }
        tblAccountHolder.getSelectionModel().selectedIndexProperty().addListener(listener -> {
            if (tblAccountHolder.getSelectionModel().getSelectedItem() != null) {
                IndividualHolder ih = tblAccountHolder.getSelectionModel().getSelectedItem();
                System.out.println(ih.getAccounts().size());
                loadDataAccount(ih.getHolderID());        //Polymorphisme
            }
        });
    }

    public void handleButtonAddAccountHolder(ActionEvent event) {
        LocalDate ld = dpBirthDate.getValue();
        String birthdate = String.format("%d-%02d-%02d", ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
        //            System.out.println(birthdate);
        IndividualHolder ih = new IndividualHolder(Integer.parseInt(tfHolderID.getText()), tfName.getText(), tfAddress.getText(),
                cbGender.getSelectionModel().getSelectedItem().toString(), birthdate, new Account(Integer.parseInt(tfAccNumber.getText()), Double.parseDouble(tfBalance.getText())));

        try {
            accHolder.addAccountHolder(ih);
            lbActionStatus.setText("Account data added successfuly");
            lbActionStatus.setTextFill(Color.web("#0d39ba"));
            btnReload.fire();

        } catch (SQLException ex) {
            Logger.getLogger(AccountHolderFormController.class.getName()).log(Level.SEVERE, null, ex);
            lbActionStatus.setText("Failed saving data account");
            lbActionStatus.setTextFill(Color.web("#0d39ba"));
        }
    }

    public void loadDataIndividualHolder(ActionEvent event) {
        ObservableList<IndividualHolder> data = accHolder.getIndividualHolders();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("holderID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        bdColumn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        numAccColumn.setCellValueFactory(new PropertyValueFactory<>("numAccounts"));
        tblAccountHolder.setItems(null);
        tblAccountHolder.setItems(data);
        btnAddAccount.setDisable(true);
    }

    public void loadDataAccount(int holderID) {
        ObservableList<Account> data = accHolder.getAccounts(holderID);
        
        accNumColumn.setCellValueFactory(new PropertyValueFactory<>("accNumber"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        tblAccount.setItems(null);
        tblAccount.setItems(data);
        btnAddAccount.setDisable(false);
        tfNewAccNumber.setText(""+accHolder.nextAccountNumber(holderID));
        tfNewHolderID.setText(""+holderID);
    }

    public void handleClearForm(ActionEvent event) {
        try {
            tfHolderID.setText("" + accHolder.nextAccountHolderID());
        } catch (SQLException ex) {
            Logger.getLogger(AccountHolderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfHolderID.setDisable(true);
        tfName.setText("");
        tfAddress.setText("");
        cbGender.getSelectionModel().clearSelection();
        dpBirthDate.setValue(LocalDate.of(LocalDate.now().getYear() - 17, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()));
        tfAccNumber.setText(tfHolderID.getText() + "01");
        tfAccNumber.setDisable(true);
        tfBalance.setText("");
    }
    @FXML
    void handleAddAccount(ActionEvent event) {
        try {
            accHolder.addAccount(Integer.parseInt(tfNewHolderID.getText()),
                    new Account(Integer.parseInt(tfNewAccNumber.getText()),Double.parseDouble(tfNewAccBalance.getText())));
            
            loadDataAccount(tblAccountHolder.getSelectionModel().getSelectedItem().getHolderID());
            tfNewAccBalance.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(AccountHolderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
