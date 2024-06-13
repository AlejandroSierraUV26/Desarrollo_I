/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author juanm
 */
public class prodIngreData {
    
    private Integer codigoProducto;
    private Integer ingredienteId;
    private Integer cantidad;
    

    public prodIngreData(Integer codigoProducto,
            Integer ingredienteId,
            Integer cantidad) {

        this.codigoProducto = codigoProducto;
        this.ingredienteId = ingredienteId;
        this.cantidad = cantidad;
        

    }

    public Integer getCodigoProducto() {
        return codigoProducto;
    }

    public Integer getIngredienteId() {
        return ingredienteId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    
}
