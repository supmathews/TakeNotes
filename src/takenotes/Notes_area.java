/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takenotes;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author TURBO
 */
public class Notes_area implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    
    public void handleClose(MouseEvent event)
    {
        System.exit(0);
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
                    VBox box = FXMLLoader.load(getClass().getResource("drawer_box.fxml"));
                    
                    drawer.setSidePane(box);
                    HamburgerBasicCloseTransition transition = new HamburgerBasicCloseTransition(hamburger);
                    
                    transition.setRate(-1);
                    
                    hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{
                        transition.setRate(transition.getRate()*-1);
                        transition.play();
                        
                        if(drawer.isShown())
                            drawer.close();
                        else
                            drawer.open();
                    });
        } catch (IOException ex) {
            Logger.getLogger(Notes_area.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
