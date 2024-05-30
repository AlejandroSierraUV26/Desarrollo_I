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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;

    public void regBtn() {
        if (registrarse_usuario.getText().isEmpty() || registrarse_contraseña.getText().isEmpty() || registrarse_telefono.getText().isEmpty()) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Rellene los especios indicados");
            alert.showAndWait();

        } else {

            String regData = "INSERT INTO cliente (Usuario, Contraseña, telefono)" + "VALUES(?,?,?)";
            connect = baseDatos.connectDB();

            try {
                prepare = connect.prepareStatement(regData);
                prepare.setString(1, registrarse_usuario.getText());
                prepare.setString(2, registrarse_contraseña.getText());
                prepare.setString(3, registrarse_telefono.getText());

                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Registro Exitoso");
                alert.showAndWait();

                registrarse_usuario.setText("");
                registrarse_contraseña.setText("");
                registrarse_telefono.setText("");
                
                TranslateTransition slider = new TranslateTransition();

                slider.setNode(crear_formulario);
                slider.setToX(0);
                slider.setDuration(javafx.util.Duration.seconds(.375));

                slider.setOnFinished((ActionEvent e) -> {
                    crear_cuentaExistente.setVisible(false);
                    crear_boton.setVisible(true);
                });

                slider.play();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void cambiarFormulario(ActionEvent event) {

        TranslateTransition slider = new TranslateTransition();

        if (event.getSource() == crear_boton) {
            slider.setNode(crear_formulario);
            slider.setToX(300);
            slider.setDuration(javafx.util.Duration.seconds(.375));

            slider.setOnFinished((ActionEvent e) -> {
                crear_cuentaExistente.setVisible(true);
                crear_boton.setVisible(false);
            });

            slider.play();
        } else if (event.getSource() == crear_cuentaExistente) {
            slider.setNode(crear_formulario);
            slider.setToX(0);
            slider.setDuration(javafx.util.Duration.seconds(.375));

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
