/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takenotes;

import com.mysql.jdbc.MysqlIO;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
 *
 * @author TURBO
 */
public class login_fxml implements Initializable {

    private String user;
    private String pass;
    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/takenotes?zeroDateTimeBehavior=convertToNull";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = null;
    private DatabaseConnection dbcon;
    public login_fxml()
    {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    @FXML
    private TextField userNameField;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passFIeld;
    
    @FXML
    private Label wrongCreds;
    
    @FXML
    public void handleClose(MouseEvent event)
    {
        System.exit(0);
    }
    
    
    public boolean isLogin(String user_name, String pass_word) throws SQLException
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        String query = "select *from usercredentials where userName = ? and password = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally{
            preparedStatement.close();
            resultSet.close();
        }
        
    }
    
    public void loginAuth(ActionEvent event) throws IOException
    {
        user = userNameField.getText().toString();
        pass = passFIeld.getText().toString();
        
        try {
            if(isLogin(user, pass))
            {
                
                Parent parent = FXMLLoader.load(getClass().getResource("notes_area.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
            else
                wrongCreds.setText("Invalid Username/Password");
                
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    

    
    public void intent(MouseEvent e) throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("signup_fxml.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
