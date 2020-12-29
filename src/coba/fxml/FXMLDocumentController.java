/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coba.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author didik
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button btn;

    @FXML
    private Label label;

    @FXML
    private TextField tfNama;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Name "+tfNama.getText()+" saved");
    }
    
    @FXML
    private void mouseEnteredAction(MouseEvent event){
        label.setText("Mouse di atas button");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn.setOnMouseExited(event -> {
            label.setText("");
        });
    }    
    
}
