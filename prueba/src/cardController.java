
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanm
 */
public class cardController implements Initializable {

    @FXML
    private AnchorPane card_form;

    @FXML
    private Label nombre_producto;

    @FXML
    private Label precio_producto;

    @FXML
    private Button producto_addBtn;

    @FXML
    private ImageView producto_imagen;

    @FXML
    private Spinner<Integer> producto_spinner;

    private prodData productoData;
    private Image image;

    private SpinnerValueFactory<Integer> spin;

    public void setData(prodData productoData) {
        this.productoData = productoData;

        nombre_producto.setText(productoData.getDescripcionProducto());
        precio_producto.setText("$" + String.valueOf(productoData.getPrecioProducto()));
        String path = "File:" + productoData.getImagen();
        image = new Image(path, 190, 96, false, true);
        producto_imagen.setImage(image);
    }

    private int cant;

    public void setCantidad() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        producto_spinner.setValueFactory(spin);

    }

    public void addBtn() {
        cant = producto_spinner.getValue();
        if (cant == 0) {

        } else {
            try {
                String sql = "SELECT P.Descripcion, I.Nombre, PI.CantidadNecesaria FROM productos P INNER JOIN producto_ingrediente PI ON (P.CodigoProducto = PI.CodProd) INNER JOIN ingredientes_inventario I ON (PI.IdIngre = I.IdIngrediente)";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
