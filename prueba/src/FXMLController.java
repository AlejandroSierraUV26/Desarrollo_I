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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author juanm
 */
public class FXMLController implements Initializable {

    @FXML
    private Button crear_boton;

    @FXML
    private Button crear_cuentaExistente;

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
    private Button nc_cambiarBtn;

    @FXML
    private TextField nc_confirmarContra;

    @FXML
    private TextField nc_nuevaContra;

    @FXML
    private Button nc_regresar;

    @FXML
    private AnchorPane nuevaContraseña_formulario;

    @FXML
    private Button olvidar_continuarBtn;

    @FXML
    private AnchorPane olvidar_formulario;

    @FXML
    private Button olvidar_regresarBtn;

    @FXML
    private TextField olvidar_telefono;

    @FXML
    private TextField olvidar_usuario;

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
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;

    public void loginBtn() {
        if (iniciar_usuario.getText().isEmpty() || iniciar_contraseña.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Rellene los espacios indicados");
            alert.showAndWait();
        } else {
            if ("AdminSupremo".equals(iniciar_usuario.getText()) && "adminsupremo123".equals(iniciar_contraseña.getText())) {
                String selctData = "SELECT usuario, contraseña FROM administrador WHERE usuario = ? and contraseña = ?";

                connect = baseDatos.connectDB();

                try {

                    prepare = connect.prepareStatement(selctData);
                    prepare.setString(1, iniciar_usuario.getText());
                    prepare.setString(2, iniciar_contraseña.getText());

                    result = prepare.executeQuery();

                    if (result.next()) {
                        
                        data.usuario = iniciar_usuario.getText();
                        
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Información");
                        alert.setHeaderText(null);
                        alert.setContentText("Inicio exitoso!");
                        alert.showAndWait();

                        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                        
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);

                        stage.setTitle("La Sazón de mi Mami");
                        stage.setMinWidth(1100);
                        stage.setMinHeight(600);

                        stage.setScene(scene);
                        stage.show();

                        iniciar_boton.getScene().getWindow().hide();

                    } else {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText(null);
                        alert.setContentText("Usuario o Contraseña incorrectos");
                        alert.showAndWait();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String selctData = "SELECT usuario, contraseña FROM cliente WHERE usuario = ? and contraseña = ?";

                connect = baseDatos.connectDB();

                try {

                    prepare = connect.prepareStatement(selctData);
                    prepare.setString(1, iniciar_usuario.getText());
                    prepare.setString(2, iniciar_contraseña.getText());

                    result = prepare.executeQuery();

                    if (result.next()) {
                        
                        data.usuario = iniciar_usuario.getText();
                        
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Información");
                        alert.setHeaderText(null);
                        alert.setContentText("Inicio exitoso!");
                        alert.showAndWait();
                                               
                        Parent root = FXMLLoader.load(getClass().getResource("mainCliente.fxml"));
                        
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);

                        stage.setTitle("La Sazón de mi Mami");
                        stage.setMinWidth(1100);
                        stage.setMinHeight(600);

                        stage.setScene(scene);
                        stage.show();

                        iniciar_boton.getScene().getWindow().hide();

                    } else {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText(null);
                        alert.setContentText("Usuario o Contraseña incorrectos");
                        alert.showAndWait();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

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

                String revisarUsuario = "SELECT usuario FROM cliente WHERE usuario = '" + registrarse_usuario.getText() + "'";

                prepare = connect.prepareStatement(revisarUsuario);
                result = prepare.executeQuery();

                if (result.next() || "AdminSupremo".equals(registrarse_usuario.getText())) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText(registrarse_usuario.getText() + " ya existe");
                    alert.showAndWait();
                } else if (registrarse_contraseña.getText().length() < 8) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("La contraseña debe ser mayor a 8 caracteres");
                    alert.showAndWait();
                } else if (registrarse_telefono.getText().length() != 10) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("El telefono debe ser de 10 caracteres");
                    alert.showAndWait();
                } else {
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
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void cambiarOlvidoContraseña() {
        olvidar_formulario.setVisible(true);
        iniciar_formulario.setVisible(false);
    }

    public void continuarBtn() {
        if (olvidar_usuario.getText().isEmpty() || olvidar_telefono.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Rellene los especios indicados");
            alert.showAndWait();
        } else if (olvidar_telefono.getText().length() != 10) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("El telefono debe ser de 10 caracteres");
            alert.showAndWait();
        } else {

            String selectData = "SELECT usuario, telefono FROM cliente WHERE usuario = ? AND telefono = ?";
            connect = baseDatos.connectDB();

            try {
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, olvidar_usuario.getText());
                prepare.setString(2, olvidar_telefono.getText());

                result = prepare.executeQuery();

                if (result.next()) {
                    nuevaContraseña_formulario.setVisible(true);
                    olvidar_formulario.setVisible(false);
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("Informacion Incorrecta");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void cambiarContraseñaBtn() {

        if (nc_nuevaContra.getText().isEmpty() || nc_confirmarContra.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Rellene los especios indicados");
            alert.showAndWait();
        } else {

            if (nc_nuevaContra.getText().equals(nc_confirmarContra.getText())) {
                connect = baseDatos.connectDB();

                try {
                    String actualizarContra = "UPDATE cliente SET contraseña = '" + nc_nuevaContra.getText() + "' WHERE usuario = '" + olvidar_usuario.getText() + "'";

                    prepare = connect.prepareStatement(actualizarContra);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText(null);
                    alert.setContentText("Contraseña Actualizada");
                    alert.showAndWait();

                    iniciar_formulario.setVisible(true);
                    nuevaContraseña_formulario.setVisible(false);

                    nc_nuevaContra.setText("");
                    nc_confirmarContra.setText("");
                    olvidar_usuario.setText("");
                    olvidar_telefono.setText("");
                    iniciar_usuario.setText("");
                    iniciar_contraseña.setText("");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("No coinciden las contraseñas");
                alert.showAndWait();
            }
        }

    }

    public void regresarInicio() {
        iniciar_usuario.setText("");
        iniciar_contraseña.setText("");
        olvidar_usuario.setText("");
        olvidar_telefono.setText("");
        olvidar_formulario.setVisible(false);
        iniciar_formulario.setVisible(true);
    }

    public void regresarOlvido() {
        nc_nuevaContra.setText("");
        nc_confirmarContra.setText("");
        olvidar_usuario.setText("");
        olvidar_telefono.setText("");
        nuevaContraseña_formulario.setVisible(false);
        olvidar_formulario.setVisible(true);
    }

    public void cambiarFormulario(ActionEvent event) {

        iniciar_usuario.setText("");
        iniciar_contraseña.setText("");
        registrarse_usuario.setText("");
        registrarse_contraseña.setText("");
        registrarse_telefono.setText("");
        olvidar_usuario.setText("");
        olvidar_telefono.setText("");
        nc_nuevaContra.setText("");
        nc_confirmarContra.setText("");

        TranslateTransition slider = new TranslateTransition();

        if (event.getSource() == crear_boton) {
            slider.setNode(crear_formulario);
            slider.setToX(300);
            slider.setDuration(javafx.util.Duration.seconds(.375));

            slider.setOnFinished((ActionEvent e) -> {
                crear_cuentaExistente.setVisible(true);
                crear_boton.setVisible(false);
                olvidar_formulario.setVisible(false);
                nuevaContraseña_formulario.setVisible(false);
                iniciar_formulario.setVisible(true);
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