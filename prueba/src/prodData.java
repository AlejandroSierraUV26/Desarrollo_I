/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author juanm
 */
public class prodData {

    private Integer codigoProducto;
    private String descripcionProducto;
    private Integer precioProducto;
    private String imagen;

    public prodData(Integer codigoProducto,
            String descripcionProducto,
            Integer precioProducto, String imagen) {

        this.codigoProducto = codigoProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
        this.imagen = imagen;
    }
    
    public Integer getCodigoProducto() {
        return codigoProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public Integer getPrecioProducto() {
        return precioProducto;
    }
    
    public String getImagen(){
        return imagen;
    }
    
    

}
