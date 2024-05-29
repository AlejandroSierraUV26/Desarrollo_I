/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author juanm
 */
public class FXMLController implements Initializable {

    @FXML
    private Button crear_boton;

    @FXML
    private AnchorPane crear_formulario;

    @FXML
    private Button iniciar_boton;

    @FXML
    private PasswordField iniciar_contraseña;

    @FXML
    private AnchorPane iniciar_formulario;

    @FXML
    private Hyperlink iniciar_hipervinculo;

    @FXML
    private TextField iniciar_usuario;

    @FXML
    private PasswordField registrarse_contraseña;

    @FXML
    private AnchorPane registrarse_formulario;

    @FXML
    private TextField registrarse_telefono;

    @FXML
    private TextField registrarse_usuario;

    @FXML
    private Button registrase_boton;

    @FXML
    private Button crear_cuentaExistente;

    public void cambiarFormulario(ActionEvent event) {

        TranslateTransition slider = new TranslateTransition();

        if (event.getSource() == crear_boton) {
            slider.setNode(crear_formulario);
            slider.setToX(300);
            slider.setDuration(javafx.util.Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                crear_cuentaExistente.setVisible(true);
                crear_boton.setVisible(false);
            });

            slider.play();
        } else if (event.getSource() == crear_cuentaExistente) {
            slider.setNode(crear_formulario);
            slider.setToX(0);
            slider.setDuration(javafx.util.Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                crear_cuentaExistente.setVisible(false);
                crear_boton.setVisible(true);
            });

            slider.play();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
