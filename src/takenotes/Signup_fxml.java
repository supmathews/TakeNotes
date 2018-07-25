/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takenotes;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author TURBO
 */
public class Signup_fxml implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private String user, pass;
    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/takenotes?zeroDateTimeBehavior=convertToNull";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = null;
    private PreparedStatement preparedStatement;
    
    @FXML
    private TextField userField;

    @FXML
    private TextField passField;

    @FXML
    private Button signupButton;

    
    /*public Signup_fxml()
    {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);      
        } catch (SQLException ex) {
            Logger.getLogger(Signup_fxml.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }*/
    
    public boolean addUsers()
    {
        try {
            user = userField.getText().toString();
            pass = passField.getText().toString();
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("insert into usercredentials " + "(userName, password)" + "values(?, ?)");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Signup_fxml.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
            return false;
        }
    }
    public void intent(ActionEvent event) throws IOException
    {
        if(addUsers())
        {
            Parent root = FXMLLoader.load(getClass().getResource("login_fxml.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else
            System.exit(0);
    }
    public void handleClose(MouseEvent event)
    {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
