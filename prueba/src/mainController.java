
import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanm
 */
public class mainController implements Initializable {

    @FXML
    private TextField cantidadCodProd_texto;

    @FXML
    private TextField cantidadIdIngre_texto;

    @FXML
    private TextField cantidadNecesaria_texto;

    @FXML
    private Button cantidad_addBtn;

    @FXML
    private TableColumn<prodIngreData, String> cantidad_cod;

    @FXML
    private Button cantidad_deleteBtn;

    @FXML
    private TableColumn<prodIngreData, String> cantidad_id;

    @FXML
    private TableColumn<prodIngreData, String> cantidad_necesaria;

    @FXML
    private Button cantidad_updateBtn;

    @FXML
    private Button clientes_btn;

    @FXML
    private Button dashboard_btn;

    @FXML
    private TextField ingredienteID_texto;

    @FXML
    private TextField ingredienteNombre_texto;

    @FXML
    private TextField ingredienteStock_texto;

    @FXML
    private Button ingrediente_addBtn;

    @FXML
    private Button ingrediente_deleteBtn;

    @FXML
    private TableColumn<ingreData, String> ingrediente_id;

    @FXML
    private TableColumn<ingreData, String> ingrediente_nombre;

    @FXML
    private TableColumn<ingreData, String> ingrediente_stock;

    @FXML
    private Button ingrediente_updateBtn;

    @FXML
    private Button inventario_btn;

    @FXML
    private AnchorPane inventario_form;

    @FXML
    private TableView<prodIngreData> inventario_tabla_cantidad;

    @FXML
    private TableView<ingreData> inventario_tabla_ingrediente;

    @FXML
    private TableView<prodData> inventario_tabla_producto;

    @FXML
    private Button limpiar_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button menu_btn;

    @FXML
    private TextField productoCodigo_texto;

    @FXML
    private TextField productoDesc_texto;

    @FXML
    private TextField productoPrecio_texto;

    @FXML
    private Button producto_addBtn;

    @FXML
    private TableColumn<prodData, String> producto_cod;

    @FXML
    private Button producto_deleteBtn;

    @FXML
    private TableColumn<prodData, String> producto_desc;

    @FXML
    private ImageView producto_imagen;

    @FXML
    private Button producto_importarBtn;

    @FXML
    private TableColumn<prodData, String> producto_precio;

    @FXML
    private Button producto_updateBtn;

    @FXML
    private Button salir_btn;

    @FXML
    private Label usuario;

    private Alert alert;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private Statement statement2;
    private ResultSet result;
    private ResultSet result2;

    private Image image;

    public void inventarioProdAddBtn() {
        if (productoCodigo_texto.getText().isEmpty()
                || productoDesc_texto.getText().isEmpty()
                || productoPrecio_texto.getText().isEmpty()
                || data.path == null) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Rellene los espacios indicados");
            alert.showAndWait();

        } else {
            String checkProdID = "SELECT CodigoProducto FROM productos WHERE CodigoProducto = '" + productoCodigo_texto.getText() + "'";

            connect = baseDatos.connectDB();

            try {

                statement = connect.createStatement();
                result = statement.executeQuery(checkProdID);

                if (result.next()) {

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("El codigo de producto " + productoCodigo_texto.getText() + " ya está tomado");
                    alert.showAndWait();

                } else {

                    String insertData = "INSERT INTO productos " + "(CodigoProducto, Descripcion, Precio) " + "VALUES(?,?,?)";

                    prepare = connect.prepareCall(insertData);
                    prepare.setString(1, productoCodigo_texto.getText());
                    prepare.setString(2, productoDesc_texto.getText());
                    prepare.setString(3, productoPrecio_texto.getText());

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("INFORMACION");
                    alert.setHeaderText(null);
                    alert.setContentText("Producto Añadido");
                    alert.showAndWait();

                    inventoryShowProdData();
                    inventarioLimpiarBtn();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inventarioIngreAddBtn() {
        if (ingredienteID_texto.getText().isEmpty()
                || ingredienteNombre_texto.getText().isEmpty()
                || ingredienteStock_texto.getText().isEmpty()) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Rellene los espacios indicados");
            alert.showAndWait();

        } else {
            String checkIngreID = "SELECT IdIngrediente FROM ingredientes_inventario WHERE IdIngrediente = '" + ingredienteID_texto.getText() + "'";

            connect = baseDatos.connectDB();

            try {

                statement = connect.createStatement();
                result = statement.executeQuery(checkIngreID);

                if (result.next()) {

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("El codigo del ingrediente " + ingredienteNombre_texto.getText() + " ya está tomado");
                    alert.showAndWait();

                } else {

                    String insertData = "INSERT INTO ingredientes_inventario " + "(IdIngrediente, Nombre, Stock) " + "VALUES(?,?,?)";

                    prepare = connect.prepareCall(insertData);
                    prepare.setString(1, ingredienteID_texto.getText());
                    prepare.setString(2, ingredienteNombre_texto.getText());
                    prepare.setString(3, ingredienteStock_texto.getText());

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("INFORMACION");
                    alert.setHeaderText(null);
                    alert.setContentText("Ingrediente Añadido");
                    alert.showAndWait();

                    inventoryShowIngreData();
                    inventarioLimpiarBtn();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inventarioCantidadAddBtn() {
        if (cantidadCodProd_texto.getText().isEmpty()
                || cantidadIdIngre_texto.getText().isEmpty()
                || cantidadNecesaria_texto.getText().isEmpty()) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Rellene los espacios indicados");
            alert.showAndWait();

        } else {

            String checkCodProd = "SELECT CodigoProducto FROM productos WHERE CodigoProducto = '" + cantidadCodProd_texto.getText() + "'";
            String checkIdIngre = "SELECT IdIngrediente FROM ingredientes_inventario WHERE IdIngrediente = '" + cantidadIdIngre_texto.getText() + "'";

            connect = baseDatos.connectDB();

            try {

                statement = connect.createStatement();
                result = statement.executeQuery(checkCodProd);
                statement2 = connect.createStatement();
                result2 = statement2.executeQuery(checkIdIngre);

                if (result.next() && result2.next()) {

                    String checkCodIDProdIngre = "SELECT CodProd, IdIngre FROM producto_ingrediente WHERE CodProd = '" + cantidadCodProd_texto.getText() + "' and IdIngre = '" + cantidadIdIngre_texto.getText() + "'";
                    connect = baseDatos.connectDB();

                    try {

                        statement = connect.createStatement();
                        result = statement.executeQuery(checkCodIDProdIngre);

                        if (result.next()) {
                            alert = new Alert(AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setHeaderText(null);
                            alert.setContentText("El codigo de producto " + cantidadCodProd_texto.getText() + " con Id de ingrediente " + cantidadIdIngre_texto.getText() + " ya existen");
                            alert.showAndWait();
                        } else {
                            String insertData = "INSERT INTO producto_ingrediente " + "(CodProd, IdIngre, CantidadNecesaria) " + "VALUES(?,?,?)";

                            prepare = connect.prepareCall(insertData);
                            prepare.setString(1, cantidadCodProd_texto.getText());
                            prepare.setString(2, cantidadIdIngre_texto.getText());
                            prepare.setString(3, cantidadNecesaria_texto.getText());

                            prepare.executeUpdate();

                            alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("INFORMACION");
                            alert.setHeaderText(null);
                            alert.setContentText("Datos Añadidos");
                            alert.showAndWait();

                            inventoryShowProdIngreData();
                            inventarioLimpiarBtn();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("El Codigo o ID no existen");
                    alert.showAndWait();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inventarioLimpiarBtn() {

        productoCodigo_texto.setText("");
        productoDesc_texto.setText("");
        productoPrecio_texto.setText("");
        ingredienteID_texto.setText("");
        ingredienteNombre_texto.setText("");
        ingredienteStock_texto.setText("");
        cantidadCodProd_texto.setText("");
        cantidadIdIngre_texto.setText("");
        cantidadNecesaria_texto.setText("");
        data.path = "";

        producto_imagen.setImage(null);

    }

    public void InventarioProdEliminarBtn() {
        if (productoCodigo_texto.getText().isEmpty()
                || productoDesc_texto.getText().isEmpty()
                || productoPrecio_texto.getText().isEmpty()
                || data.path == null) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Rellene los espacios indicados");
            alert.showAndWait();

        } else {

            String checkCodigoProd = "SELECT CodigoProducto FROM productos WHERE CodigoProducto = '" + productoCodigo_texto.getText() + "'";

            connect = baseDatos.connectDB();

            try {

                statement = connect.createStatement();
                result = statement.executeQuery(checkCodigoProd);

                if (result.next()) {

                    String checkCodProd = "SELECT CodProd FROM producto_ingrediente WHERE CodProd = '" + productoCodigo_texto.getText() + "'";
                    connect = baseDatos.connectDB();

                    try {

                        statement = connect.createStatement();
                        result = statement.executeQuery(checkCodProd);

                        if (result.next()) {
                            alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Debes eliminar las relaciones de: " + productoCodigo_texto.getText() + " de la tercera tabla para poder eliminar el producto");
                            alert.showAndWait();
                        } else {

                            alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("");
                            alert.setHeaderText(null);
                            alert.setContentText("Estás seguro que quieres ELIMINAR el producto: " + productoCodigo_texto.getText() + "?");
                            Optional<ButtonType> option = alert.showAndWait();

                            if (option.get().equals(ButtonType.OK)) {
                                String deleteData = "DELETE FROM productos WHERE CodigoProducto = " + productoCodigo_texto.getText();
                                try {
                                    prepare = connect.prepareStatement(deleteData);
                                    prepare.executeUpdate();

                                    alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Eliminado Correctamente");
                                    alert.showAndWait();

                                    inventoryShowProdData();

                                    inventarioLimpiarBtn();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                alert = new Alert(AlertType.ERROR);
                                alert.setTitle("Error Message");
                                alert.setHeaderText(null);
                                alert.setContentText("Cancelado");
                                alert.showAndWait();
                            }

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("El codigo del prodcuto " + productoCodigo_texto.getText() + " no existe");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void InventarioIngreEliminarBtn() {
        if (ingredienteID_texto.getText().isEmpty()
                || ingredienteNombre_texto.getText().isEmpty()
                || ingredienteStock_texto.getText().isEmpty()) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Rellene los espacios indicados");
            alert.showAndWait();

        } else {
            String checkCodigoProd = "SELECT IdIngrediente FROM ingredientes_inventario WHERE IdIngrediente = '" + ingredienteID_texto.getText() + "'";

            connect = baseDatos.connectDB();

            try {

                statement = connect.createStatement();
                result = statement.executeQuery(checkCodigoProd);

                if (result.next()) {

                    String checkIngreID = "SELECT IdIngre FROM producto_ingrediente WHERE IdIngre = '" + ingredienteID_texto.getText() + "'";
                    connect = baseDatos.connectDB();

                    try {

                        statement = connect.createStatement();
                        result = statement.executeQuery(checkIngreID);

                        if (result.next()) {
                            alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Debes eliminar las relaciones de: " + ingredienteID_texto.getText() + " de la tercera tabla para poder eliminar el ingrediente");
                            alert.showAndWait();
                        } else {

                            alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("");
                            alert.setHeaderText(null);
                            alert.setContentText("Estás seguro que quieres ELIMINAR el ingrediente: " + ingredienteID_texto.getText() + "?");
                            Optional<ButtonType> option = alert.showAndWait();

                            if (option.get().equals(ButtonType.OK)) {
                                String deleteData = "DELETE FROM ingredientes_inventario WHERE IdIngrediente = " + ingredienteID_texto.getText();
                                try {
                                    prepare = connect.prepareStatement(deleteData);
                                    prepare.executeUpdate();

                                    alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Eliminado Correctamente");
                                    alert.showAndWait();

                                    inventoryShowIngreData();

                                    inventarioLimpiarBtn();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                alert = new Alert(AlertType.ERROR);
                                alert.setTitle("Error Message");
                                alert.setHeaderText(null);
                                alert.setContentText("Cancelado");
                                alert.showAndWait();
                            }

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("El codigo del ingrediente " + ingredienteID_texto.getText() + " no existe");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void InventarioProdIngreEliminarBtn() {
        if (cantidadCodProd_texto.getText().isEmpty()
                || cantidadIdIngre_texto.getText().isEmpty()
                || cantidadNecesaria_texto.getText().isEmpty()) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Rellene los espacios indicados");
            alert.showAndWait();

        } else {
            String checkProdIngre = "SELECT CodProd, IdIngre FROM producto_ingrediente WHERE CodProd = '" + cantidadCodProd_texto.getText() + "' and IdIngre = '" + cantidadIdIngre_texto.getText() + "'";

            connect = baseDatos.connectDB();

            try {

                statement = connect.createStatement();
                result = statement.executeQuery(checkProdIngre);

                if (result.next()) {

                    alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("Estás seguro que quieres ELIMINAR el ingrediente: " + ingredienteID_texto.getText() + "?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {
                        String deleteData = "DELETE FROM producto_ingrediente WHERE CodProd = '" + cantidadCodProd_texto.getText() + "' and IdIngre = '" + cantidadIdIngre_texto.getText() + "'";
                        try {
                            prepare = connect.prepareStatement(deleteData);
                            prepare.executeUpdate();

                            alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("");
                            alert.setHeaderText(null);
                            alert.setContentText("Eliminado Correctamente");
                            alert.showAndWait();

                            inventoryShowProdIngreData();

                            inventarioLimpiarBtn();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Cancelado");
                        alert.showAndWait();
                    }

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("El codigo del producto " + cantidadCodProd_texto.getText() + " con el ingrediente" + cantidadIdIngre_texto.getText() + " no existe");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void inventarioProdActualizarBtn() {

        if (productoCodigo_texto.getText().isEmpty()
                || productoDesc_texto.getText().isEmpty()
                || productoPrecio_texto.getText().isEmpty()
                || data.path == null) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Rellene los espacios indicados");
            alert.showAndWait();

        } else {

            String checkCodProd = "SELECT CodigoProducto FROM productos WHERE CodigoProducto = '" + productoCodigo_texto.getText() + "'";

            connect = baseDatos.connectDB();

            try {

                statement = connect.createStatement();
                result = statement.executeQuery(checkCodProd);

                if (result.next()) {

                    String updateData = "UPDATE productos SET "
                            + "Descripcion = ?, Precio = ? "
                            + "WHERE CodigoProducto = ?";

                    connect = baseDatos.connectDB();

                    try {

                        alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Estás seguro que quieres ACTUALIZAR el producto: " + productoCodigo_texto.getText() + "?");
                        Optional<ButtonType> option = alert.showAndWait();

                        if (option.get().equals(ButtonType.OK)) {
                            prepare = connect.prepareStatement(updateData);
                            prepare = connect.prepareStatement(updateData);
                            prepare.setString(1, productoDesc_texto.getText());
                            prepare.setString(2, productoPrecio_texto.getText());
                            prepare.setString(3, productoCodigo_texto.getText());
                            prepare.executeUpdate();

                            alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("");
                            alert.setHeaderText(null);
                            alert.setContentText("Actualizado Correctamente");
                            alert.showAndWait();

                            inventoryShowProdData();

                            inventarioLimpiarBtn();
                        } else {
                            alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Cancelado");
                            alert.showAndWait();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("El codigo del prodcuto " + productoCodigo_texto.getText() + " no existe");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void inventarioIngreActualizarBtn() {

        if (ingredienteID_texto.getText().isEmpty()
                || ingredienteNombre_texto.getText().isEmpty()
                || ingredienteStock_texto.getText().isEmpty()) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Rellene los espacios indicados");
            alert.showAndWait();

        } else {

            String checkIngreID = "SELECT IdIngrediente FROM ingredientes_inventario WHERE IdIngrediente = '" + ingredienteID_texto.getText() + "'";

            connect = baseDatos.connectDB();

            try {

                statement = connect.createStatement();
                result = statement.executeQuery(checkIngreID);

                if (result.next()) {

                    String updateData = "UPDATE ingredientes_inventario SET "
                            + "Nombre = ?, Stock = ? "
                            + "WHERE IdIngrediente = ?";

                    connect = baseDatos.connectDB();

                    try {

                        alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Estás seguro que quieres ACTUALIZAR el ingrediente: " + ingredienteID_texto.getText() + "?");
                        Optional<ButtonType> option = alert.showAndWait();

                        if (option.get().equals(ButtonType.OK)) {
                            prepare = connect.prepareStatement(updateData);
                            prepare = connect.prepareStatement(updateData);
                            prepare.setString(1, ingredienteNombre_texto.getText());
                            prepare.setString(2, ingredienteStock_texto.getText());
                            prepare.setString(3, ingredienteID_texto.getText());
                            prepare.executeUpdate();

                            alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("");
                            alert.setHeaderText(null);
                            alert.setContentText("Actualizado Correctamente");
                            alert.showAndWait();

                            inventoryShowIngreData();

                            inventarioLimpiarBtn();
                        } else {
                            alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Cancelado");
                            alert.showAndWait();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("El codigo del ingrediente " + ingredienteID_texto.getText() + " no existe");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inventarioProdIngreActualizarBtn() {

        if (cantidadCodProd_texto.getText().isEmpty()
                || cantidadIdIngre_texto.getText().isEmpty()
                || cantidadNecesaria_texto.getText().isEmpty()) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Rellene los espacios indicados");
            alert.showAndWait();

        } else {

            String checkProdIngre = "SELECT CodProd, IdIngre FROM producto_ingrediente WHERE CodProd = '" + cantidadCodProd_texto.getText() + "' and IdIngre = '" + cantidadIdIngre_texto.getText() + "'";

            connect = baseDatos.connectDB();

            try {

                statement = connect.createStatement();
                result = statement.executeQuery(checkProdIngre);

                if (result.next()) {

                    String updateData = "UPDATE producto_ingrediente SET "
                            + "CantidadNecesaria = ? "
                            + "WHERE CodProd = ? and IdIngre = ?";

                    connect = baseDatos.connectDB();

                    try {

                        alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Estás seguro que quieres ACTUALIZAR el producto: " + cantidadCodProd_texto.getText() + " con el ingrediente " + cantidadIdIngre_texto.getText() + "?");
                        Optional<ButtonType> option = alert.showAndWait();

                        if (option.get().equals(ButtonType.OK)) {
                            prepare = connect.prepareStatement(updateData);
                            prepare = connect.prepareStatement(updateData);
                            prepare.setString(1, cantidadNecesaria_texto.getText());
                            prepare.setString(2, cantidadCodProd_texto.getText());
                            prepare.setString(3, cantidadIdIngre_texto.getText());
                            prepare.executeUpdate();

                            alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("");
                            alert.setHeaderText(null);
                            alert.setContentText("Actualizado Correctamente");
                            alert.showAndWait();

                            inventoryShowProdIngreData();

                            inventarioLimpiarBtn();
                        } else {
                            alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Cancelado");
                            alert.showAndWait();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("El codigo del producto " + cantidadCodProd_texto.getText() + " no existe con el ingrediente " + cantidadIdIngre_texto.getText());
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inventarioProdSelectData() {

        prodData productoData = inventario_tabla_producto.getSelectionModel().getSelectedItem();
        int num = inventario_tabla_producto.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        productoCodigo_texto.setText(String.valueOf(productoData.getCodigoProducto()));
        productoDesc_texto.setText(productoData.getDescripcionProducto());
        productoPrecio_texto.setText(String.valueOf(productoData.getPrecioProducto()));

    }

    public void inventarioIngreSelectData() {

        ingreData ingredienteData = inventario_tabla_ingrediente.getSelectionModel().getSelectedItem();
        int num = inventario_tabla_ingrediente.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        ingredienteID_texto.setText(String.valueOf(ingredienteData.getIngredienteId()));
        ingredienteNombre_texto.setText(ingredienteData.getNombreIngrediente());
        ingredienteStock_texto.setText(String.valueOf(ingredienteData.getStockIngredinte()));

    }

    public void inventarioProdIngreSelectData() {

        prodIngreData productoIngredienteData = inventario_tabla_cantidad.getSelectionModel().getSelectedItem();
        int num = inventario_tabla_cantidad.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        cantidadCodProd_texto.setText(String.valueOf(productoIngredienteData.getCodigoProducto()));
        cantidadIdIngre_texto.setText(String.valueOf(productoIngredienteData.getIngredienteId()));
        cantidadNecesaria_texto.setText(String.valueOf(productoIngredienteData.getCantidad()));

    }

    public void inventarioImportBtn() {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new ExtensionFilter("Abrir Imagen", "*png", "*jpg"));

        File file = openFile.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 100, 100, false, true);

            producto_imagen.setImage(image);
        }
    }

    public ObservableList<prodData> inventoryProdDataList() {

        ObservableList<prodData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM productos";

        connect = baseDatos.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            prodData datosProd;

            while (result.next()) {

                datosProd = new prodData(result.getInt("CodigoProducto"),
                        result.getString("Descripcion"),
                        result.getInt("Precio")
                );

                listData.add(datosProd);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<prodData> inventoryProdListData;

    public void inventoryShowProdData() {
        inventoryProdListData = inventoryProdDataList();

        producto_cod.setCellValueFactory(new PropertyValueFactory<>("codigoProducto"));
        producto_desc.setCellValueFactory(new PropertyValueFactory<>("descripcionProducto"));
        producto_precio.setCellValueFactory(new PropertyValueFactory<>("precioProducto"));

        inventario_tabla_producto.setItems(inventoryProdListData);

    }

    public ObservableList<ingreData> inventoryIngreDataList() {

        ObservableList<ingreData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM ingredientes_inventario";

        connect = baseDatos.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ingreData datosIngre;

            while (result.next()) {

                datosIngre = new ingreData(result.getInt("IdIngrediente"),
                        result.getString("Nombre"),
                        result.getInt("Stock")
                );

                listData.add(datosIngre);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<ingreData> inventoryIngreListData;

    public void inventoryShowIngreData() {
        inventoryIngreListData = inventoryIngreDataList();

        ingrediente_id.setCellValueFactory(new PropertyValueFactory<>("ingredienteId"));
        ingrediente_nombre.setCellValueFactory(new PropertyValueFactory<>("nombreIngrediente"));
        ingrediente_stock.setCellValueFactory(new PropertyValueFactory<>("stockIngredinte"));

        inventario_tabla_ingrediente.setItems(inventoryIngreListData);

    }

    public ObservableList<prodIngreData> inventoryProdIngreDataList() {

        ObservableList<prodIngreData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM producto_ingrediente";

        connect = baseDatos.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            prodIngreData datosprodIngre;

            while (result.next()) {

                datosprodIngre = new prodIngreData(result.getInt("CodProd"),
                        result.getInt("IdIngre"),
                        result.getInt("CantidadNecesaria")
                );

                listData.add(datosprodIngre);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<prodIngreData> inventoryProdIngreListData;

    public void inventoryShowProdIngreData() {
        inventoryProdIngreListData = inventoryProdIngreDataList();

        cantidad_cod.setCellValueFactory(new PropertyValueFactory<>("codigoProducto"));
        cantidad_id.setCellValueFactory(new PropertyValueFactory<>("ingredienteId"));
        cantidad_necesaria.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        inventario_tabla_cantidad.setItems(inventoryProdIngreListData);

    }

    public void logout() {

        try {

            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Seguro que desea salir?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                salir_btn.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setTitle("La Sazón de mi Mami");

                stage.setScene(scene);
                stage.show();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void displayUsername() {

        String user = data.usuario;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);

        usuario.setText(user);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        displayUsername();
        inventoryShowProdData();
        inventoryShowIngreData();
        inventoryShowProdIngreData();
    }

}
